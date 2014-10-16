package tonius.simplyjetpacks.item.jetpack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.SyncTracker;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemIndex;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.setup.JetpackIcon;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;

public class Jetpack {
    
    public static final int ARMORED_META_OFFSET = 100;
    
    private static Map<Integer, Jetpack> jetpacksCommon = new HashMap<Integer, Jetpack>();
    private static int highestMetaCommon;
    private static Map<Integer, Jetpack> jetpacksPerMod = new HashMap<Integer, Jetpack>();
    private static int highestMetaPerMod;
    
    public final int tier;
    public final EnumRarity rarity;
    
    public final int energyCapacity;
    public final int energyPerTick;
    
    public final double speedVertical;
    public final double accelVertical;
    public final double speedVerticalHover;
    public final double speedVerticalHoverSlow;
    public final float speedSideways;
    
    public final boolean enchantable;
    public final int enchantability;
    public final boolean emergencyHoverMode;
    
    public Jetpack(int tier, EnumRarity rarity, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, double speedVerticalHover, double speedVerticalHoverSlow, float speedSideways, boolean enchantable, int enchantability, boolean emergencyHoverMode) {
        this.tier = tier;
        this.rarity = rarity;
        
        this.energyCapacity = energyCapacity;
        this.energyPerTick = energyPerTick;
        
        this.speedVertical = speedVertical;
        this.accelVertical = accelVertical;
        this.speedVerticalHover = speedVerticalHover;
        this.speedVerticalHoverSlow = speedVerticalHoverSlow;
        this.speedSideways = speedSideways;
        
        this.enchantable = enchantable;
        this.enchantability = enchantability;
        this.emergencyHoverMode = emergencyHoverMode;
    }
    
    public static Jetpack getJetpack(ItemIndex index, int meta) {
        switch (index) {
        case COMMON:
            return jetpacksCommon.get(meta);
        case PER_MOD:
            return jetpacksPerMod.get(meta);
        }
        return null;
    }
    
    public static int getHighestMeta(ItemIndex index) {
        switch (index) {
        case COMMON:
            return highestMetaCommon;
        case PER_MOD:
            return highestMetaPerMod;
        }
        return 0;
    }
    
    public static void addJetpack(ItemIndex index, int meta, Jetpack jetpack) {
        switch (index) {
        case COMMON:
            jetpacksCommon.put(meta, jetpack);
            if (highestMetaCommon < meta) {
                highestMetaCommon = meta;
            }
            break;
        case PER_MOD:
            jetpacksPerMod.put(meta, jetpack);
            if (highestMetaPerMod < meta) {
                highestMetaPerMod = meta;
            }
        }
    }
    
    public static void reconstructJetpacks() {
        JetpackFactory.newJetpack(ItemIndex.COMMON, 0, null, false);
        addJetpack(ItemIndex.COMMON, 1, new JetpackIcon());
        JetpackFactory.newJetpack(ItemIndex.COMMON, 9001, null, false);
        
        JetpackFactory.newJetpack(ItemIndex.PER_MOD, 1, EnumRarity.common, true);
        JetpackFactory.newJetpack(ItemIndex.PER_MOD, 2, EnumRarity.common, true);
        JetpackFactory.newJetpack(ItemIndex.PER_MOD, 3, EnumRarity.uncommon, true);
        JetpackFactory.newJetpack(ItemIndex.PER_MOD, 4, EnumRarity.rare, true);
        JetpackFactory.newJetpack(ItemIndex.PER_MOD, 5, EnumRarity.epic, false);
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
    
    public boolean consumesEnergy() {
        return true;
    }
    
    public void useJetpack(EntityLivingBase user, ItemStack armor, ItemJetpack item, boolean force) {
        if (this.isOn(armor)) {
            boolean hoverMode = this.isHoverModeOn(armor);
            double hoverSpeed = this.getHoverSpeed(armor, user);
            boolean flyKeyDown = force || SyncTracker.isFlyKeyDown(user);
            boolean descendKeyDown = SyncTracker.isDescendKeyDown(user);
            double currentAccel = user.motionY < 0.3D ? this.accelVertical * 2.5 : this.accelVertical;
            
            if (flyKeyDown || hoverMode && !user.onGround) {
                item.extractEnergy(armor, this.energyPerTick, false);
                
                if (item.getEnergyStored(armor) > 0) {
                    if (flyKeyDown) {
                        if (!hoverMode) {
                            user.motionY = Math.min(user.motionY + currentAccel, this.speedVertical);
                        } else {
                            if (descendKeyDown) {
                                user.motionY = Math.min(user.motionY + currentAccel, -this.speedVerticalHoverSlow);
                            } else {
                                user.motionY = Math.min(user.motionY + currentAccel, this.speedVerticalHover);
                            }
                        }
                    } else {
                        user.motionY = Math.min(user.motionY + currentAccel, -hoverSpeed);
                    }
                    
                    if (SyncTracker.isForwardKeyDown(user)) {
                        user.moveFlying(0, this.speedSideways, this.speedSideways);
                    }
                    if (SyncTracker.isBackwardKeyDown(user)) {
                        user.moveFlying(0, -this.speedSideways, this.speedSideways * 0.8F);
                    }
                    if (SyncTracker.isLeftKeyDown(user)) {
                        user.moveFlying(this.speedSideways, 0, this.speedSideways);
                    }
                    if (SyncTracker.isRightKeyDown(user)) {
                        user.moveFlying(-this.speedSideways, 0, this.speedSideways);
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
                    for (int i = 0; i <= 16; i++) {
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
        boolean flyKeyDown = SyncTracker.isFlyKeyDown(user);
        if (this.isOn(itemStack) && item.getEnergyStored(itemStack) > 0 && (flyKeyDown || this.isHoverModeOn(itemStack) && !user.onGround && user.motionY < 0)) {
            return this.getParticleType(itemStack);
        }
        return null;
    }
    
    public void toggle(ItemStack itemStack, EntityPlayer player, boolean showInChat) {
        String msg = "";
        if (this.isOn(itemStack)) {
            msg = StringUtils.translate("chat.jetpack.engine") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled");
            itemStack.stackTagCompound.setBoolean("JetpackOn", false);
        } else {
            msg = StringUtils.translate("chat.jetpack.engine") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled");
            itemStack.stackTagCompound.setBoolean("JetpackOn", true);
        }
        if (player != null && player.worldObj.isRemote && showInChat) {
            player.addChatMessage(new ChatComponentText(msg));
        }
    }
    
    public boolean isHoverModeOn(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("JetpackHoverModeOn");
    }
    
    public void switchMode(ItemStack itemStack, EntityPlayer player, boolean showInChat) {
        String msg = "";
        if (this.isHoverModeOn(itemStack)) {
            msg = StringUtils.translate("chat.jetpack.hoverMode") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled");
            itemStack.stackTagCompound.setBoolean("JetpackHoverModeOn", false);
        } else {
            msg = StringUtils.translate("chat.jetpack.hoverMode") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled");
            itemStack.stackTagCompound.setBoolean("JetpackHoverModeOn", true);
        }
        if (player != null && player.worldObj.isRemote && showInChat) {
            player.addChatMessage(new ChatComponentText(msg));
        }
    }
    
    public void setMobMode(ItemStack itemStack) {
        if (!this.isOn(itemStack)) {
            itemStack.stackTagCompound.setBoolean("JetpackOn", true);
        }
        if (this.isHoverModeOn(itemStack)) {
            itemStack.stackTagCompound.setBoolean("JetpackHoverModeOn", false);
        }
    }
    
    public double getHoverSpeed(ItemStack jetpack, EntityLivingBase user) {
        if (SJConfig.invertHoverSneakingBehavior) {
            return SyncTracker.isDescendKeyDown(user) ? this.speedVerticalHoverSlow : this.speedVerticalHover;
        } else {
            return SyncTracker.isDescendKeyDown(user) ? this.speedVerticalHover : this.speedVerticalHoverSlow;
        }
    }
    
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, int energyStored) {
        list.add(StringUtils.getChargeText(this.tier == 9001, energyStored, this.energyCapacity));
    }
    
    public void addShiftInformation(ItemStack itemStack, EntityPlayer player, List list) {
        list.add(StringUtils.getStateText(this.isOn(itemStack)));
        list.add(StringUtils.getHoverModeText(this.isHoverModeOn(itemStack)));
        list.add(StringUtils.getEnergyUsageText(this.energyPerTick));
        list.add(StringUtils.getArmoredText(this.isArmored()));
        list.add(StringUtils.getParticlesText(this.getParticleType(itemStack)));
        list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.1"));
        list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.2"));
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
        itemStack.setItemDamage(itemStack.getItemDamage() + ARMORED_META_OFFSET);
        player.worldObj.playSoundAtEntity(player, "random.anvil_use", 0.8F, 0.9F + player.getRNG().nextFloat() * 0.2F);
    }
    
    public void removeArmor(ItemStack itemStack, EntityPlayer player) {
        itemStack.setItemDamage(itemStack.getItemDamage() - ARMORED_META_OFFSET);
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
