package tonius.simplyjetpacks.integration;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class RAItems {
    
    public static ItemStack plateFlux = null;
    public static ItemStack armorFluxPlate = null;
    
    public static void init() {
        SimplyJetpacks.logger.info("Stealing Redstone Arsenal's items");
        
        plateFlux = GameRegistry.findItemStack("RedstoneArsenal", "plateFlux", 1);
        armorFluxPlate = GameRegistry.findItemStack("RedstoneArsenal", "armorFluxPlate", 1);
    }
    
}
