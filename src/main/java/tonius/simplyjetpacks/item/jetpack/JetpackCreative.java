package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.item.EnumRarity;

public class JetpackCreative extends JetpackFluxPlate {
    
    public JetpackCreative(int meta, boolean enchantable, double speedVertical, double accelVertical, float speedSideways, double speedVerticalHover, double speedVerticalHoverSlow, boolean emergencyHoverMode, int armorDisplay, double armorAbsorption, int energyPerTickOut) {
        super(meta, 9001, enchantable, EnumRarity.epic, 9001, 0, speedVertical, accelVertical, speedSideways, speedVerticalHover, speedVerticalHoverSlow, emergencyHoverMode, armorDisplay, armorAbsorption, 0, energyPerTickOut);
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
    public boolean hasArmoredVersion() {
        return false;
    }
    
    @Override
    public boolean allowCharger() {
        return true;
    }
    
}
