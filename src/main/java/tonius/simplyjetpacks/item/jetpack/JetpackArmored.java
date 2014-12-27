package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.item.ItemJetpack;

public class JetpackArmored extends Jetpack {
    
    public final int armorReduction;
    public final int armorEnergyPerHit;
    
    public JetpackArmored(int tier, EnumRarity rarity, boolean hasModel, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, double speedVerticalHover, double speedVerticalHoverSlow, float speedSideways, float sprintSpeedModifier, float sprintEnergyModifier, boolean enchantable, int enchantability, boolean emergencyHoverMode, int armorReduction, int armorEnergyPerHit) {
        super(tier, rarity, hasModel, energyCapacity, energyPerTick, speedVertical, accelVertical, speedVerticalHover, speedVerticalHoverSlow, speedSideways, sprintSpeedModifier, sprintEnergyModifier, enchantable, enchantability, emergencyHoverMode);
        this.armorReduction = armorReduction;
        this.armorEnergyPerHit = armorEnergyPerHit;
    }
    
    @Override
    public String getBaseName() {
        return "jetpack." + this.tier + ".armored";
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
    public ArmorProperties getProperties(EntityLivingBase player, ItemJetpack item, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.isUnblockable()) {
            return super.getProperties(player, item, armor, source, damage, slot);
        }
        int maxAbsorbed = this.getEnergyPerDamage(armor) > 0 ? 25 * (item.getEnergyStored(armor) / this.getEnergyPerDamage(armor)) : 0;
        return new ArmorProperties(0, 0.75D * (this.armorReduction / 20.0D), maxAbsorbed);
    }
    
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemJetpack item, ItemStack armor, int slot) {
        if (item.getEnergyStored(armor) >= this.armorEnergyPerHit) {
            return this.armorReduction;
        }
        return 0;
    }
    
    @Override
    public void damageArmor(EntityLivingBase entity, ItemJetpack item, ItemStack armor, DamageSource source, int damage, int slot) {
        item.extractEnergy(armor, damage * this.getEnergyPerDamage(armor), false);
    }
    
}
