package tonius.simplyjetpacks.integration;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class BCItems {
    
    // Transport
    public static Object pipeFluidStone = null;
    public static Object pipeEnergyGold = null;
    
    // Energy
    public static Object engineCombustion = null;
    
    // Factory
    public static Object tank = null;
    
    // Silicon
    public static Object chipsetGold = null;
    
    public static void init() {
        SimplyJetpacks.logger.info("Stealing BuildCraft's items");
        
        if (Loader.isModLoaded("BuildCraft|Transport")) {
            pipeFluidStone = new ItemStack(GameRegistry.findItem("BuildCraft|Transport", "item.buildcraftPipe.pipefluidsstone"));
            pipeEnergyGold = new ItemStack(GameRegistry.findItem("BuildCraft|Transport", "item.buildcraftPipe.pipepowergold"));
        } else {
            pipeFluidStone = "blockGlass";
            pipeEnergyGold = "dustRedstone";
        }
        
        if (Loader.isModLoaded("BuildCraft|Energy")) {
            Block engine = GameRegistry.findBlock("BuildCraft|Energy", "engineBlock");
            if (engine == null) {
                engine = GameRegistry.findBlock("BuildCraft|Core", "engineBlock");
            }
            engineCombustion = new ItemStack(engine, 1, 2);
        } else {
            engineCombustion = "gearIron";
        }
        
        if (Loader.isModLoaded("BuildCraft|Factory")) {
            tank = new ItemStack(GameRegistry.findBlock("BuildCraft|Factory", "tankBlock"));
        } else {
            tank = "blockGlass";
        }
        
        if (Loader.isModLoaded("BuildCraft|Silicon")) {
            chipsetGold = new ItemStack(GameRegistry.findItem("BuildCraft|Silicon", "redstoneChipset"), 1, 2);
        } else {
            chipsetGold = "gearGold";
        }
    }
    
    public static ItemStack getStack(Object o) {
        if (o instanceof ItemStack) {
            return ((ItemStack) o).copy();
        }
        
        if (o instanceof String) {
            return OreDictionary.getOres((String) o).get(0);
        }
        
        return null;
    }
    
}
