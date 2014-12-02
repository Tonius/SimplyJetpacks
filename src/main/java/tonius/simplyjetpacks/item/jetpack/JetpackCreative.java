package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.item.EnumRarity;

public class JetpackCreative extends JetpackJetPlate {
    
    public JetpackCreative(double speedVertical, double accelVertical, double speedVerticalHover, double speedVerticalHoverSlow, float speedSideways, float sprintSpeedModifier, float sprintEnergyModifier, boolean enchantable, int enchantability, boolean emergencyHoverMode, int armorDisplay, double armorAbsorption, int energyPerTickOut) {
        super(9001, EnumRarity.epic, true, 9001, 0, speedVertical, accelVertical, speedVerticalHover, speedVerticalHoverSlow, speedSideways, sprintSpeedModifier, sprintEnergyModifier, enchantable, enchantability, emergencyHoverMode, armorDisplay, armorAbsorption, 0, energyPerTickOut);
    }
    
    @Override
    public String getBaseName() {
        return "jetpack.creative";
    }
    
    @Override
    public boolean hasEmptyItem() {
        return false;
    }
    
    @Override
    public boolean hasDamageBar() {
        return false;
    }
    
    @Override
    public boolean consumesEnergy() {
        return false;
    }
    
}
