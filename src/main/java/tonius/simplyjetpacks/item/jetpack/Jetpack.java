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
import tonius.simplyjetpacks.SyncTracker;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.setup.JetpackIcon;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;

public class Jetpack {

    private static Map<Integer, Jetpack> jetpacks = new HashMap<Integer, Jetpack>();
    private static int highestMeta;

    public int tier;
    public boolean enchantable;
    public int energyCapacity;
    public int energyPerTick;
    public double speedVertical;
    public double accelVertical;
    public float speedSideways;
    public int energyPerTickHover;
    public double speedVerticalHover;
    public double speedVerticalHoverSlow;
    public boolean emergencyHoverMode;

    public Jetpack(int meta, int tier, boolean enchantable, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, float speedSideways, double speedVerticalHover, double speedVerticalHoverSlow, boolean emergencyHoverMode) {
        this.tier = tier;
        this.enchantable = enchantable;
        this.energyCapacity = energyCapacity;
        this.energyPerTick = energyPerTick;
        this.speedVertical = speedVertical;
        this.accelVertical = accelVertical;
        this.speedSideways = speedSideways;
        this.energyPerTickHover = (int) (energyPerTick / 1.5);
        this.speedVerticalHover = speedVerticalHover;
        this.speedVerticalHoverSlow = speedVerticalHoverSlow;
        this.emergencyHoverMode = emergencyHoverMode;

        addJetpack(meta, this);
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

    public static void reconstructJetpacks() {
        new JetpackPotato(0, 0, SJConfig.tuberousEnergyCapacity, SJConfig.tuberousEnergyPerTick, SJConfig.tuberousSpeedVertical, SJConfig.tuberousAccelVertical);
        new Jetpack(1, 1, SJConfig.leadstoneEnchantable, SJConfig.leadstoneEnergyCapacity, SJConfig.leadstoneEnergyPerTick, SJConfig.leadstoneSpeedVertical, SJConfig.leadstoneAccelVertical, (float) SJConfig.leadstoneSpeedSideways, SJConfig.leadstoneSpeedVerticalHover, SJConfig.leadstoneSpeedVerticalHoverSlow, SJConfig.leadstoneEmergencyHoverMode);
        new Jetpack(2, 2, SJConfig.hardenedEnchantable, SJConfig.hardenedEnergyCapacity, SJConfig.hardenedEnergyPerTick, SJConfig.hardenedSpeedVertical, SJConfig.hardenedAccelVertical, (float) SJConfig.hardenedSpeedSideways, SJConfig.hardenedSpeedVerticalHover, SJConfig.hardenedSpeedVerticalHoverSlow, SJConfig.hardenedEmergencyHoverMode);
        new Jetpack(3, 3, SJConfig.reinforcedEnchantable, SJConfig.reinforcedEnergyCapacity, SJConfig.reinforcedEnergyPerTick, SJConfig.reinforcedSpeedVertical, SJConfig.reinforcedAccelVertical, (float) SJConfig.reinforcedSpeedSideways, SJConfig.reinforcedSpeedVerticalHover, SJConfig.reinforcedSpeedVerticalHoverSlow, SJConfig.reinforcedEmergencyHoverMode);
        new Jetpack(4, 4, SJConfig.resonantEnchantable, SJConfig.resonantEnergyCapacity, SJConfig.resonantEnergyPerTick, SJConfig.resonantSpeedVertical, SJConfig.resonantAccelVertical, (float) SJConfig.resonantSpeedSideways, SJConfig.resonantSpeedVerticalHover, SJConfig.resonantSpeedVerticalHoverSlow, SJConfig.resonantEmergencyHoverMode);
        new JetpackArmored(101, 1, SJConfig.leadstoneEnchantable, SJConfig.leadstoneEnergyCapacity, SJConfig.leadstoneEnergyPerTick, SJConfig.leadstoneSpeedVertical, SJConfig.leadstoneAccelVertical, (float) SJConfig.leadstoneSpeedSideways, SJConfig.leadstoneSpeedVerticalHover, SJConfig.leadstoneSpeedVerticalHoverSlow, SJConfig.leadstoneEmergencyHoverMode, SJConfig.leadstoneArmorDisplay, SJConfig.leadstoneArmorAbsorption, SJConfig.leadstoneArmorEnergyPerHit);
        new JetpackArmored(102, 2, SJConfig.hardenedEnchantable, SJConfig.hardenedEnergyCapacity, SJConfig.hardenedEnergyPerTick, SJConfig.hardenedSpeedVertical, SJConfig.hardenedAccelVertical, (float) SJConfig.hardenedSpeedSideways, SJConfig.hardenedSpeedVerticalHover, SJConfig.hardenedSpeedVerticalHoverSlow, SJConfig.hardenedEmergencyHoverMode, SJConfig.hardenedArmorDisplay, SJConfig.hardenedArmorAbsorption, SJConfig.hardenedArmorEnergyPerHit);
        new JetpackArmored(103, 3, SJConfig.reinforcedEnchantable, SJConfig.reinforcedEnergyCapacity, SJConfig.reinforcedEnergyPerTick, SJConfig.reinforcedSpeedVertical, SJConfig.reinforcedAccelVertical, (float) SJConfig.reinforcedSpeedSideways, SJConfig.reinforcedSpeedVerticalHover, SJConfig.reinforcedSpeedVerticalHoverSlow, SJConfig.reinforcedEmergencyHoverMode, SJConfig.reinforcedArmorDisplay, SJConfig.reinforcedArmorAbsorption, SJConfig.reinforcedArmorEnergyPerHit);
        new JetpackArmored(104, 4, SJConfig.resonantEnchantable, SJConfig.resonantEnergyCapacity, SJConfig.resonantEnergyPerTick, SJConfig.resonantSpeedVertical, SJConfig.resonantAccelVertical, (float) SJConfig.resonantSpeedSideways, SJConfig.resonantSpeedVerticalHover, SJConfig.resonantSpeedVerticalHoverSlow, SJConfig.resonantEmergencyHoverMode, SJConfig.resonantArmorDisplay, SJConfig.resonantArmorAbsorption, SJConfig.resonantArmorEnergyPerHit);
        new JetpackCreative(9001, SJConfig.creativeEnchantable, SJConfig.creativeSpeedVertical, SJConfig.creativeAccelVertical, (float) SJConfig.creativeSpeedSideways, SJConfig.creativeSpeedVerticalHover, SJConfig.creativeSpeedVerticalHoverSlow, SJConfig.creativeEmergencyHoverMode, SJConfig.creativeArmorDisplay, SJConfig.creativeArmorAbsorption, 0);
        new JetpackIcon(9002);
    }

    public String getBaseName() {
        return "jetpack." + this.tier;
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

    public int getPlatingMeta() {
        return this.tier + 120;
    }

    public void useJetpack(EntityLivingBase user, ItemStack armor, ItemJetpack item, boolean force) {
        if (this.isOn(armor)) {
            boolean hoverMode = this.isHoverModeOn(armor);
            double hoverSpeed = this.getHoverSpeed(armor, user);
            boolean jumpKeyDown = true;
            double currentAccel = user.motionY < 0.3D ? this.accelVertical * 2.5 : this.accelVertical;
            if (!force && user instanceof EntityPlayer && !SyncTracker.isJumpKeyDown((EntityPlayer) user)) {
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
                            user.motionY = Math.min(user.motionY + currentAccel, this.speedVertical);
                        } else {
                            user.motionY = Math.min(user.motionY + currentAccel, this.speedVerticalHover);
                        }
                    } else {
                        user.motionY = Math.min(user.motionY + currentAccel, -hoverSpeed);
                    }

                    if (user instanceof EntityPlayer) {
                        if (SyncTracker.isForwardKeyDown((EntityPlayer) user)) {
                            user.moveFlying(0, this.speedSideways, this.speedSideways);
                        }
                        if (SyncTracker.isBackwardKeyDown((EntityPlayer) user)) {
                            user.moveFlying(0, -this.speedSideways, this.speedSideways * 0.8F);
                        }
                        if (SyncTracker.isLeftKeyDown((EntityPlayer) user)) {
                            user.moveFlying(this.speedSideways, 0, this.speedSideways);
                        }
                        if (SyncTracker.isRightKeyDown((EntityPlayer) user)) {
                            user.moveFlying(-this.speedSideways, 0, this.speedSideways);
                        }
                    }

                    if (!user.worldObj.isRemote) {
                        user.fallDistance = 0.0F;
                        if (user instanceof EntityPlayerMP) {
                            ((EntityPlayerMP) user).playerNetServerHandler.floatingTickCount = 0;
                        }
                    }
                }
            }
        }

        if (!user.worldObj.isRemote && this.emergencyHoverMode && item.getEnergyStored(armor) > 0 && (!this.isHoverModeOn(armor) || !this.isOn(armor))) {
            if (user.posY < -5) {
                this.doEmergencyHoverMode(armor, user);
            } else if (user instanceof EntityPlayer) {
                if (!((EntityPlayer) user).capabilities.isCreativeMode && user.fallDistance - 1.2F >= user.getHealth()) {
                    for (int i = 0; i <= 10; i++) {
                        if (!user.worldObj.isAirBlock(Math.round((float) user.posX - 0.5F), Math.round((float) user.posY) - i, Math.round((float) user.posZ - 0.5F))) {
                            this.doEmergencyHoverMode(armor, user);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void doEmergencyHoverMode(ItemStack armor, EntityLivingBase user) {
        StackUtils.getNBT(armor).setBoolean("JetpackOn", true);
        StackUtils.getNBT(armor).setBoolean("JetpackHoverModeOn", true);
        if (user instanceof EntityPlayer) {
            ((EntityPlayer) user).addChatMessage(new ChatComponentText(StringUtils.LIGHT_RED + StringUtils.translate("chat.jetpack.emergencyHoverMode")));
        }
    }

    public boolean isOn(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("JetpackOn");
    }

    public JetpackParticleType particleToShow(ItemStack itemStack, ItemJetpack item, EntityLivingBase user) {
        boolean jumpKeyDown = true;
        if (user instanceof EntityPlayer && !SyncTracker.isJumpKeyDown((EntityPlayer) user)) {
            jumpKeyDown = false;
        }
        if (this.isOn(itemStack) && item.getEnergyStored(itemStack) > 0 && (jumpKeyDown || (this.isHoverModeOn(itemStack) && !user.onGround && user.motionY < 0))) {
            return this.getParticleType(itemStack);
        }
        return null;
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
                    list.add(StringUtils.getRequiredArmorText(this.getPlatingMeta() - 120));
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

    public void applyArmor(ItemStack itemStack, EntityPlayer player) {
        itemStack.setItemDamage(itemStack.getItemDamage() + 100);
        player.worldObj.playSoundAtEntity(player, "random.anvil_use", 0.8F, 0.9F + player.getRNG().nextFloat() * 0.2F);
    }

    public void removeArmor(ItemStack itemStack, EntityPlayer player) {
        itemStack.setItemDamage(itemStack.getItemDamage() - 100);
        player.worldObj.playSoundAtEntity(player, "random.break", 1.0F, 0.9F + player.getRNG().nextFloat() * 0.2F);
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
