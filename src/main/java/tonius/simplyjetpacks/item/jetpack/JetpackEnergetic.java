package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.item.EnumRarity;

public class JetpackEnergetic extends JetpackArmored {

    public JetpackEnergetic(int meta, int tier, boolean enchantable, EnumRarity rarity, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, float speedSideways, double speedVerticalHover, double speedVerticalHoverSlow, boolean emergencyHoverMode, int armorDisplay, double armorAbsorption, int energyPerHit) {
        super(meta, tier, enchantable, rarity, energyCapacity, energyPerTick, speedVertical, accelVertical, speedSideways, speedVerticalHover, speedVerticalHoverSlow, emergencyHoverMode, armorDisplay, armorAbsorption, energyPerHit);
    }

    @Override
    public String getBaseName() {
        return "jetpack." + this.tier;
    }

    @Override
    public boolean hasArmoredVersion() {
        return false;
    }

}
