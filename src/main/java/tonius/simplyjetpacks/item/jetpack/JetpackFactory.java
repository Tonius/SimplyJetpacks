package tonius.simplyjetpacks.item.jetpack;

import net.minecraft.item.EnumRarity;
import tonius.simplyjetpacks.config.JetpackConfig;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemIndex;

public abstract class JetpackFactory {
    
    public static void newJetpack(ItemIndex index, int tier, EnumRarity rarity, boolean canBeArmored) {
        JetpackConfig config = SJConfig.jetpackConfigs.get(tier);
        if (config != null) {
            Jetpack jetpack;
            switch (tier) {
            case 0:
                jetpack = new JetpackPotato(tier, config.energyCapacity, config.energyPerTick, config.speedVertical, config.accelVertical);
                break;
            case 5:
                jetpack = new JetpackJetPlate(tier, rarity, config.energyCapacity, config.energyPerTick, config.speedVertical, config.accelVertical, config.speedVerticalHover, config.speedVerticalHoverSlow, config.speedSideways.floatValue(), config.enchantable, config.enchantability, config.emergencyHoverMode, config.armorDisplay, config.armorAbsorption, config.armorEnergyPerHit, config.chargerRate);
                break;
            case 9001:
                jetpack = new JetpackCreative(config.speedVertical, config.accelVertical, config.speedVerticalHover, config.speedVerticalHoverSlow, config.speedSideways.floatValue(), config.enchantable, config.enchantability, config.emergencyHoverMode, config.armorDisplay, config.armorAbsorption, config.chargerRate);
                break;
            default:
                jetpack = new Jetpack(tier, rarity, config.energyCapacity, config.energyPerTick, config.speedVertical, config.accelVertical, config.speedVerticalHover, config.speedVerticalHoverSlow, config.speedSideways.floatValue(), config.enchantable, config.enchantability, config.emergencyHoverMode);
                if (canBeArmored) {
                    Jetpack jetpackArmored = new JetpackArmored(tier, rarity, config.energyCapacity, config.energyPerTick, config.speedVertical, config.accelVertical, config.speedVerticalHover, config.speedVerticalHoverSlow, config.speedSideways.floatValue(), config.enchantable, config.enchantability, config.emergencyHoverMode, config.armorDisplay, config.armorAbsorption, config.armorEnergyPerHit);
                    Jetpack.addJetpack(index, tier + Jetpack.ARMORED_META_OFFSET, jetpackArmored);
                }
            }
            Jetpack.addJetpack(index, tier, jetpack);
        }
    }
    
}
