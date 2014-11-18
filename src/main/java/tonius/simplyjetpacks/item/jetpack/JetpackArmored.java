package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.item.ItemJetpack;

public class JetpackArmored extends Jetpack {
    
    public final int armorDisplay;
    public final double armorAbsorption;
    public final int armorEnergyPerHit;
    
    public JetpackArmored(int tier, EnumRarity rarity, boolean hasModel, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, double speedVerticalHover, double speedVerticalHoverSlow, float speedSideways, boolean enchantable, int enchantability, boolean emergencyHoverMode, int armorDisplay, double armorAbsorption, int energyPerHit) {
        super(tier, rarity, hasModel, energyCapacity, energyPerTick, speedVertical, accelVertical, speedVerticalHover, speedVerticalHoverSlow, speedSideways, enchantable, enchantability, emergencyHoverMode);
        this.armorDisplay = armorDisplay;
        this.armorAbsorption = armorAbsorption;
        this.armorEnergyPerHit = energyPerHit;
    }
    
    @Override
    public String getBaseName() {
        return "jetpack." + this.tier + ".armored";
    }
    
    @Override
    public boolean isArmored() {
        return true;
    }
    
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemJetpack item, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.isUnblockable()) {
            return super.getProperties(player, item, armor, source, damage, slot);
        }
        int maxAbsorbed = Integer.MAX_VALUE;
        if (this.consumesEnergy()) {
            maxAbsorbed = this.armorEnergyPerHit > 0 ? item.getEnergyStored(armor) / this.armorEnergyPerHit * 100 : 0;
        }
        return new ArmorProperties(0, this.armorAbsorption, maxAbsorbed);
    }
    
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemJetpack item, ItemStack armor, int slot) {
        if (item.getEnergyStored(armor) >= this.armorEnergyPerHit) {
            return this.armorDisplay;
        }
        return 0;
    }
    
    @Override
    public void damageArmor(EntityLivingBase entity, ItemJetpack item, ItemStack armor, DamageSource source, int damage, int slot) {
        item.extractEnergy(armor, damage * this.armorEnergyPerHit, false);
    }
    
}
