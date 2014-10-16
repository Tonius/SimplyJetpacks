package tonius.simplyjetpacks.item.fluxpack;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.item.ItemFluxPack;

public class FluxPackArmored extends FluxPack {
    
    public int armorDisplay;
    public double armorAbsorption;
    public int armorEnergyPerHit;
    
    public FluxPackArmored(int tier, EnumRarity rarity, int energyCapacity, int energyPerTickIn, int energyPerTickOut, boolean enchantable, int enchantability, int armorDisplay, double armorAbsorption, int energyPerHit) {
        super(tier, rarity, energyCapacity, energyPerTickIn, energyPerTickOut, enchantable, enchantability);
        this.armorDisplay = armorDisplay;
        this.armorAbsorption = armorAbsorption;
        this.armorEnergyPerHit = energyPerHit;
    }
    
    @Override
    public String getBaseName() {
        return "fluxpack." + this.tier + ".armored";
    }
    
    @Override
    public boolean isArmored() {
        return true;
    }
    
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemFluxPack item, ItemStack armor, DamageSource source, double damage, int slot) {
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
    public int getArmorDisplay(EntityPlayer player, ItemFluxPack item, ItemStack armor, int slot) {
        if (item.getEnergyStored(armor) >= this.armorEnergyPerHit) {
            return this.armorDisplay;
        }
        return 0;
    }
    
    @Override
    public void damageArmor(EntityLivingBase entity, ItemFluxPack item, ItemStack armor, DamageSource source, int damage, int slot) {
        item.extractEnergy(armor, damage * this.armorEnergyPerHit, false);
    }
    
}
