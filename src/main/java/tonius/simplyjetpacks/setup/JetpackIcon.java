package tonius.simplyjetpacks.setup;

import tonius.simplyjetpacks.item.jetpack.Jetpack;

public class JetpackIcon extends Jetpack {

    public JetpackIcon() {
        super(0, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public String getBaseName() {
        return "jetpack.icon";
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public boolean hasDamageBar() {
        return false;
    }

}
