package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.item.ItemJetpack;

public class JetpackArmored extends Jetpack {

    public int armorDisplay;
    public double armorAbsorption;
    public int energyPerHit;

    public JetpackArmored(int meta, int tier, boolean enchantable, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, float speedSideways, double speedVerticalHover, double speedVerticalHoverSlow, boolean emergencyHoverMode, int armorDisplay, double armorAbsorption, int energyPerHit) {
        super(meta, tier, enchantable, energyCapacity, energyPerTick, speedVertical, accelVertical, speedSideways, speedVerticalHover, speedVerticalHoverSlow, emergencyHoverMode);
        this.armorDisplay = armorDisplay;
        this.armorAbsorption = armorAbsorption;
        this.energyPerHit = energyPerHit;
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
        if (!(this instanceof JetpackCreative)) {
            maxAbsorbed = this.energyPerHit > 0 ? item.getEnergyStored(armor) / this.energyPerHit * 100 : 0;
        }
        return new ArmorProperties(0, this.armorAbsorption, maxAbsorbed);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemJetpack item, ItemStack armor, int slot) {
        if (item.getEnergyStored(armor) >= this.energyPerHit) {
            return this.armorDisplay;
        }
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemJetpack item, ItemStack armor, DamageSource source, int damage, int slot) {
        item.extractEnergy(armor, damage * this.energyPerHit, false);
    }

}
