package tonius.simplyjetpacks.setup;

import net.minecraft.item.EnumRarity;
import tonius.simplyjetpacks.item.jetpack.Jetpack;

public class JetpackIcon extends Jetpack {
    
    public JetpackIcon() {
        super(0, EnumRarity.epic, 0, 0, 0, 0, 0, 0, 0, false, 0, false);
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
    
    @Override
    public boolean hasArmoredVersion() {
        return false;
    }
    
}
