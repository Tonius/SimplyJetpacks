package tonius.simplyjetpacks.item.jetpack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageJetpackParticles;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class Jetpack {

    private static Map<Integer, Jetpack> jetpacks = new HashMap<Integer, Jetpack>();
    private static int highestMeta;

    public String mod;
    public int tier;
    public int energyCapacity;
    public int energyPerTick;
    public double speedVertical;
    public double accelVertical;
    public float speedSideways;
    public int energyPerTickHover;
    public double speedVerticalHover;
    public double speedVerticalHoverSlow;

    public Jetpack(String mod, int tier, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, float speedSideways, double speedVerticalHover, double speedVerticalHoverSlow) {
        this.mod = mod;
        this.tier = tier;
        this.energyCapacity = energyCapacity;
        this.energyPerTick = energyPerTick;
        this.speedVertical = speedVertical;
        this.accelVertical = accelVertical;
        this.speedSideways = speedSideways;
        this.energyPerTickHover = (int) (energyPerTick / 1.5);
        this.speedVerticalHover = speedVerticalHover;
        this.speedVerticalHoverSlow = speedVerticalHoverSlow;
    }

    public static Jetpack getJetpack(int meta) {
        return jetpacks.get(meta);
    }

    public static int getHighestMeta() {
        return highestMeta;
    }

    public static void addJetpack(int meta, Jetpack jetpack) {
        jetpacks.put(meta, jetpack);
        if (highestMeta < meta) {
            highestMeta = meta;
        }
    }

    public String getBaseName() {
        return "jetpack." + this.mod + "." + this.tier;
    }

    public boolean isArmored() {
        return false;
    }

    public boolean hasArmoredVersion() {
        return true;
    }

    public boolean isVisible() {
        return true;
    }

    public boolean hasEmptyItem() {
        return true;
    }

    public boolean hasDamageBar() {
        return true;
    }

    public void useJetpack(EntityLivingBase user, ItemStack armor, ItemJetpack item, boolean force) {
        if (this.isOn(armor)) {
            boolean hoverMode = this.isHoverModeOn(armor);
            double hoverSpeed = this.getHoverSpeed(armor, user);
            boolean jumpKeyDown = true;
            if (!force && user instanceof EntityPlayer && !KeyboardTracker.isJumpKeyDown((EntityPlayer) user)) {
                jumpKeyDown = false;
            }

            if (jumpKeyDown || (hoverMode && !user.onGround && user.motionY < 0)) {
                int usedPower = hoverMode ? this.energyPerTickHover : this.energyPerTick;
                if (!user.worldObj.isRemote) {
                    item.extractEnergy(armor, usedPower, false);
                }

                if (item.getEnergyStored(armor) > 0) {
                    if (jumpKeyDown) {
                        if (!hoverMode) {
                            user.motionY = Math.min(user.motionY + this.accelVertical, this.speedVertical);
                        } else {
                            user.motionY = Math.min(user.motionY + this.accelVertical, this.speedVerticalHover);
                        }
                    } else {
                        user.motionY = Math.max(user.motionY, -hoverSpeed);
                    }

                    if (user instanceof EntityPlayer) {
                        if (KeyboardTracker.isForwardKeyDown((EntityPlayer) user)) {
                            user.moveFlying(0, this.speedSideways, this.speedSideways);
                        }
                        if (KeyboardTracker.isBackwardKeyDown((EntityPlayer) user)) {
                            user.moveFlying(0, -this.speedSideways, this.speedSideways * 0.8F);
                        }
                        if (KeyboardTracker.isLeftKeyDown((EntityPlayer) user)) {
                            user.moveFlying(this.speedSideways, 0, this.speedSideways);
                        }
                        if (KeyboardTracker.isRightKeyDown((EntityPlayer) user)) {
                            user.moveFlying(-this.speedSideways, 0, this.speedSideways);
                        }
                    }

                    if (!user.worldObj.isRemote) {
                        user.fallDistance = 0.0F;
                        if (user instanceof EntityPlayerMP) {
                            ((EntityPlayerMP) user).playerNetServerHandler.floatingTickCount = 0;
                        }
                        this.sendParticlePacket(user, this.getParticleType(armor));
                    }
                }
            }
        }
    }

    public void sendParticlePacket(EntityLivingBase user, JetpackParticleType particle) {
        PacketHandler.instance.sendToAllAround(new MessageJetpackParticles(user.getEntityId(), particle), new TargetPoint(user.worldObj.provider.dimensionId, user.posX, user.posY, user.posZ, 96));
    }

    public boolean isOn(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("JetpackOn");
    }

    public void toggle(ItemStack itemStack, EntityPlayer player) {
        String msg = "";
        if (this.isOn(itemStack)) {
            msg = StringUtils.translate("chat.jetpack.engine") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled");
            itemStack.stackTagCompound.setBoolean("JetpackOn", false);
        } else {
            msg = StringUtils.translate("chat.jetpack.engine") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled");
            itemStack.stackTagCompound.setBoolean("JetpackOn", true);
        }
        player.addChatMessage(new ChatComponentText(msg));
    }

    public boolean isHoverModeOn(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("JetpackHoverModeOn");
    }

    public void toggleHoverMode(ItemStack itemStack, EntityPlayer player) {
        String msg = "";
        if (this.isHoverModeOn(itemStack)) {
            msg = StringUtils.translate("chat.jetpack.hoverMode") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled");
            itemStack.stackTagCompound.setBoolean("JetpackHoverModeOn", false);
        } else {
            msg = StringUtils.translate("chat.jetpack.hoverMode") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled");
            itemStack.stackTagCompound.setBoolean("JetpackHoverModeOn", true);
        }
        player.addChatMessage(new ChatComponentText(msg));
    }

    public double getHoverSpeed(ItemStack jetpack, EntityLivingBase user) {
        if (SJConfig.invertHoverSneakingBehavior) {
            return user.isSneaking() ? this.speedVerticalHoverSlow : this.speedVerticalHover;
        } else {
            return user.isSneaking() ? this.speedVerticalHover : this.speedVerticalHoverSlow;
        }
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, int energyStored) {
        list.add(StringUtils.getChargeText(this.tier == 9001, energyStored, this.energyCapacity));
        if (StringUtils.canShowDetails()) {
            list.add(StringUtils.getStateText(this.isOn(itemStack)));
            list.add(StringUtils.getHoverModeText(this.isHoverModeOn(itemStack)));
            int currentTickEnergy = this.isHoverModeOn(itemStack) ? this.energyPerTickHover : this.energyPerTick;
            list.add(StringUtils.getEnergyUsageText(currentTickEnergy));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.1"));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.2"));
            if (this.hasArmoredVersion() && SJConfig.enableCraftingArmorPlating) {
                list.add(StringUtils.getArmorText(this.isArmored()));
                if (!this.isArmored()) {
                    list.add(StringUtils.getRequiredArmorText(this.tier));
                }
            }
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

    public JetpackParticleType getParticleType(ItemStack jetpack) {
        int particle = StackUtils.getNBT(jetpack).getInteger("JetpackParticleType");
        JetpackParticleType particleType = JetpackParticleType.values()[particle];
        if (particleType != null) {
            return particleType;
        }
        return JetpackParticleType.DEFAULT;
    }

    public void setParticleType(ItemStack jetpack, JetpackParticleType particle) {
        StackUtils.getNBT(jetpack).setInteger("JetpackParticleType", particle.ordinal());
    }

    public ArmorProperties getProperties(EntityLivingBase player, ItemJetpack item, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, 1, 0);
    }

    public int getArmorDisplay(EntityPlayer player, ItemJetpack item, ItemStack armor, int slot) {
        return 0;
    }

    public void damageArmor(EntityLivingBase entity, ItemJetpack item, ItemStack armor, DamageSource source, int damage, int slot) {
    }

}
