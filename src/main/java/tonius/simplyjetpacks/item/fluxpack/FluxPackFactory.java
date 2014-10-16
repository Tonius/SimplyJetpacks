package tonius.simplyjetpacks.item.fluxpack;

import net.minecraft.item.EnumRarity;
import tonius.simplyjetpacks.config.FluxPackConfig;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemIndex;
import tonius.simplyjetpacks.item.jetpack.Jetpack;

public class FluxPackFactory {
    
    public static void newFluxPack(ItemIndex index, int tier, EnumRarity rarity, boolean canBeArmored) {
        FluxPackConfig config = SJConfig.fluxPackConfigs.get(tier);
        if (config != null) {
            FluxPack fluxPack;
            switch (tier) {
            case 9001:
                fluxPack = new FluxPackCreative(config.energyOutRate, config.enchantable, config.enchantability, config.armorDisplay, config.armorAbsorption);
                break;
            default:
                fluxPack = new FluxPack(tier, rarity, config.energyCapacity, config.energyInRate, config.energyOutRate, config.enchantable, config.enchantability);
                if (canBeArmored) {
                    FluxPack fluxPackArmored = new FluxPackArmored(tier, rarity, config.energyCapacity, config.energyInRate, config.energyOutRate, config.enchantable, config.enchantability, config.armorDisplay, config.armorAbsorption, config.armorEnergyPerHit);
                    FluxPack.addFluxPack(index, tier + Jetpack.ARMORED_META_OFFSET, fluxPackArmored);
                }
            }
            FluxPack.addFluxPack(index, tier, fluxPack);
        }
    }
    
}
