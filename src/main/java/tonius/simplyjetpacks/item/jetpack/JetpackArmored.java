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
    
    public final int armorDisplay;
    public final double armorAbsorption;
    public final int armorEnergyPerHit;
    
    public JetpackArmored(int tier, EnumRarity rarity, boolean hasModel, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, double speedVerticalHover, double speedVerticalHoverSlow, float speedSideways, float sprintSpeedModifier, float sprintEnergyModifier, boolean enchantable, int enchantability, boolean emergencyHoverMode, int armorDisplay, double armorAbsorption, int energyPerHit) {
        super(tier, rarity, hasModel, energyCapacity, energyPerTick, speedVertical, accelVertical, speedVerticalHover, speedVerticalHoverSlow, speedSideways, sprintSpeedModifier, sprintEnergyModifier, enchantable, enchantability, emergencyHoverMode);
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
    
    protected int getEnergyPerDamage(ItemStack stack) {
        int unbreakingLevel = MathHelper.clamp_int(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
        return this.armorEnergyPerHit * (5 - unbreakingLevel) / 5;
    }
    
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemJetpack item, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.isUnblockable()) {
            return super.getProperties(player, item, armor, source, damage, slot);
        }
        int maxAbsorbed = this.getEnergyPerDamage(armor) > 0 ? 25 * item.getEnergyStored(armor) / this.getEnergyPerDamage(armor) : 0;
        // diamond reduction amount = 8
        // 1 / 20 (max armor) = 0.05
        // 8 * 0.05 = 0.4
        return new ArmorProperties(0, this.armorAbsorption * 8 * 0.05, maxAbsorbed);
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
        item.extractEnergy(armor, damage * this.getEnergyPerDamage(armor), false);
    }
    
}
