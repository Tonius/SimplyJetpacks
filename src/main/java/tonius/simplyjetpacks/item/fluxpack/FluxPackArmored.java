package tonius.simplyjetpacks.item.fluxpack;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.item.ItemFluxPack;

public class FluxPackArmored extends FluxPack {
    
    public int armorReduction;
    public int armorEnergyPerHit;
    
    public FluxPackArmored(int tier, EnumRarity rarity, int energyCapacity, int energyPerTickIn, int energyPerTickOut, boolean enchantable, int enchantability, int armorReduction, int armorEnergyPerHit) {
        super(tier, rarity, energyCapacity, energyPerTickIn, energyPerTickOut, enchantable, enchantability);
        this.armorReduction = armorReduction;
        this.armorEnergyPerHit = armorEnergyPerHit;
    }
    
    @Override
    public String getBaseName() {
        return "fluxpack." + this.tier + ".armored";
    }
    
    @Override
    public boolean isArmored() {
        return true;
    }
    
    protected int getEnergyPerDamage(ItemStack stack) {
        int unbreakingLevel = MathHelper.clamp_int(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
        return (int) Math.round(this.armorEnergyPerHit * (5 - unbreakingLevel) / 5.0D);
    }
    
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemFluxPack item, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.isUnblockable()) {
            return super.getProperties(player, item, armor, source, damage, slot);
        }
        int maxAbsorbed = this.getEnergyPerDamage(armor) > 0 ? 25 * (item.getEnergyStored(armor) / this.getEnergyPerDamage(armor)) : 0;
        return new ArmorProperties(0, 0.75D * (this.armorReduction / 20.0D), maxAbsorbed);
    }
    
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemFluxPack item, ItemStack armor, int slot) {
        if (item.getEnergyStored(armor) >= this.armorEnergyPerHit) {
            return this.armorReduction;
        }
        return 0;
    }
    
    @Override
    public void damageArmor(EntityLivingBase entity, ItemFluxPack item, ItemStack armor, DamageSource source, int damage, int slot) {
        item.extractEnergy(armor, damage * this.getEnergyPerDamage(armor), false);
    }
    
}
