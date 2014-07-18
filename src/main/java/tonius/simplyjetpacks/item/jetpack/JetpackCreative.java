package tonius.simplyjetpacks.item.jetpack;

public class JetpackCreative extends JetpackArmored {

    public JetpackCreative(int meta, double speedVertical, double accelVertical, float speedSideways, double speedVerticalHover, double speedVerticalHoverSlow, boolean emergencyHoverMode, int armorDisplay, double armorAbsorption, int energyPerHit) {
        super(meta, 9001, 9001, 0, speedVertical, accelVertical, speedSideways, speedVerticalHover, speedVerticalHoverSlow, emergencyHoverMode, armorDisplay, armorAbsorption, energyPerHit);
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

}
