package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemArmoredJetpack extends ItemJetpack {

    protected int armorDisplay;
    protected double armorAbsorption;
    protected int energyPerDamage;

    public ItemArmoredJetpack(int id, EnumArmorMaterial material, String name, int maxEnergy, int maxInput, int jetpackTier, int tickEnergy, double maxSpeed, double acceleration, double forwardThrust, double hoverModeIdleSpeed, double hoverModeActiveSpeed, int armorDisplay, double armorAbsorption, int energyPerDamage) {
        super(id, material, name, maxEnergy, maxInput, jetpackTier, tickEnergy, maxSpeed, acceleration, forwardThrust, hoverModeIdleSpeed, hoverModeActiveSpeed);
        this.armorDisplay = armorDisplay;
        this.armorAbsorption = armorAbsorption;
        this.energyPerDamage = energyPerDamage;
    }

    @Override
    public boolean isArmored() {
        return true;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (this.energyPerDamage != 0) {
            int maxAbsorbed = this.getEnergyStored(armor) / this.energyPerDamage * 100;
            return new ArmorProperties(0, this.armorAbsorption, maxAbsorbed);
        } else {
            int maxAbsorbed = 12500000;
            return new ArmorProperties(0, this.armorAbsorption, maxAbsorbed);
        }
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        if (this.getEnergyStored(armor) >= this.energyPerDamage) {
            return this.armorDisplay;
        }
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        this.subtractEnergy(stack, damage * this.energyPerDamage, false);
    }

}
