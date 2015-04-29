package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class BCItems {
    
    // Transport
    public static ItemStack pipeFluidStone = null;
    public static ItemStack pipeEnergyGold = null;
    
    // Energy
    public static ItemStack engineCombustion = null;
    
    // Factory
    public static ItemStack tank = null;
    
    // Silicon
    public static ItemStack chipsetGold = null;
    
    public static void init() {
        SimplyJetpacks.logger.info("Stealing BuildCraft's items");
        
        pipeFluidStone = new ItemStack(GameRegistry.findItem("BuildCraft|Transport", "item.buildcraftPipe.pipefluidsstone"));
        pipeEnergyGold = new ItemStack(GameRegistry.findItem("BuildCraft|Transport", "item.buildcraftPipe.pipepowergold"));
        
        engineCombustion = new ItemStack(GameRegistry.findBlock("BuildCraft|Energy", "engineBlock"), 1, 2);
        
        tank = new ItemStack(GameRegistry.findBlock("BuildCraft|Factory", "tankBlock"));
        
        chipsetGold = new ItemStack(GameRegistry.findItem("BuildCraft|Silicon", "redstoneChipset"), 1, 2);
    }
    
}
