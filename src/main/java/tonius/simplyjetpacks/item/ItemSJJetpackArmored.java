package tonius.simplyjetpacks.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemSJJetpackArmored extends ItemSJJetpack {

    protected int armorDisplay;
    protected double armorAbsorption;
    protected int damageEnergy;

    public ItemSJJetpackArmored(int id, EnumArmorMaterial material, String name, int maxEnergy, int maxInput, int tickEnergy, double maxSpeed, double acceleration, double forwardThrust, double hoverModeIdleSpeed, double hoverModeActiveSpeed, int armorDisplay, double armorAbsorption, int damageEnergy) {
        super(id, material, name, maxEnergy, maxInput, tickEnergy, maxSpeed, acceleration, forwardThrust, hoverModeIdleSpeed, hoverModeActiveSpeed);
        this.armorDisplay = armorDisplay;
        this.armorAbsorption = armorAbsorption;
        this.damageEnergy = damageEnergy;
    }

    public double getAbsorptionRatio(ItemStack armor) {
        return this.getEnergyStored(armor) < this.damageEnergy ? 0.01D : this.armorAbsorption;
    }

    public int getMaxAbsorption(ItemStack armor) {
        int maxAbsorption = (int) Math.floor(29 * this.getEnergyStored(armor) / this.damageEnergy);
        return maxAbsorption;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, this.getAbsorptionRatio(armor), this.getMaxAbsorption(armor));
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return this.armorDisplay;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        this.subtractEnergy(stack, damage * this.damageEnergy, false);
    }

}
