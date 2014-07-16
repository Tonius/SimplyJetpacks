package tonius.simplyjetpacks.item.jetpack;

public class JetpackCreative extends JetpackArmored {

    public JetpackCreative(double speedVertical, double accelVertical, float speedSideways, double speedVerticalHover, double speedVerticalHoverSlow, int armorDisplay, double armorAbsorption, int energyPerHit) {
        super(9001, 9001, 0, speedVertical, accelVertical, speedSideways, speedVerticalHover, speedVerticalHoverSlow, armorDisplay, armorAbsorption, energyPerHit);
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
