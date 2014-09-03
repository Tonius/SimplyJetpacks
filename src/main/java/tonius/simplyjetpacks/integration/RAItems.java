package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class RAItems {
    
    public static ItemStack plateFlux = null;
    public static ItemStack armorFluxPlate = null;
    
    public static boolean init() {
        if (Loader.isModLoaded("RedstoneArsenal")) {
            SimplyJetpacks.logger.info("Stealing Redstone Arsenal's items");
            
            plateFlux = GameRegistry.findItemStack("RedstoneArsenal", "plateFlux", 1);
            armorFluxPlate = GameRegistry.findItemStack("RedstoneArsenal", "armorFluxPlate", 1);
            
            return true;
        }
        return false;
    }
    
}
