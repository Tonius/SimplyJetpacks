package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class JetpackArmored extends Jetpack {

    public int armorDisplay;
    public double armorAbsorption;
    public int energyPerHit;

    public JetpackArmored(int tier, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, float speedSideways, double speedVerticalHover, double speedVerticalHoverSlow, int armorDisplay, double armorAbsorption, int energyPerHit) {
        super(tier, energyCapacity, energyPerTick, speedVertical, accelVertical, speedSideways, speedVerticalHover, speedVerticalHoverSlow);
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
        int maxAbsorbed = 100;
        if (this.tier != 9001) {
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
