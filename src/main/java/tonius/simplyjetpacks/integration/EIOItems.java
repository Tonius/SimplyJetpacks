package tonius.simplyjetpacks.integration;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class EIOItems {
    
    public static ItemStack capacitorBankOld;
    public static ItemStack capacitorBank;
    public static ItemStack capacitorBankVibrant;
    public static ItemStack redstoneConduit;
    public static ItemStack energyConduit1;
    public static ItemStack energyConduit2;
    public static ItemStack energyConduit3;
    public static ItemStack basicCapacitor;
    public static ItemStack doubleCapacitor;
    public static ItemStack octadicCapacitor;
    public static ItemStack machineChassis;
    public static ItemStack basicGear;
    public static ItemStack pulsatingCrystal;
    public static ItemStack vibrantCrystal;
    public static ItemStack enderCrystal;
    
    public static void init() {
        SimplyJetpacks.logger.info("Stealing Ender IO's items");
        
        capacitorBankOld = new ItemStack(GameRegistry.findBlock("EnderIO", "blockCapacitorBank"));
        
        Block capBankBlock = GameRegistry.findBlock("EnderIO", "blockCapBank");
        capacitorBank = new ItemStack(capBankBlock, 1, 2);
        capacitorBankVibrant = new ItemStack(capBankBlock, 1, 3);
        
        redstoneConduit = new ItemStack(GameRegistry.findItem("EnderIO", "itemRedstoneConduit"), 1, 2);
        
        Item energyConduitItem = GameRegistry.findItem("EnderIO", "itemPowerConduit");
        energyConduit1 = new ItemStack(energyConduitItem, 1, 0);
        energyConduit2 = new ItemStack(energyConduitItem, 1, 1);
        energyConduit3 = new ItemStack(energyConduitItem, 1, 2);
        
        Item capacitorItem = GameRegistry.findItem("EnderIO", "itemBasicCapacitor");
        basicCapacitor = new ItemStack(capacitorItem, 1, 0);
        doubleCapacitor = new ItemStack(capacitorItem, 1, 1);
        octadicCapacitor = new ItemStack(capacitorItem, 1, 2);
        
        Item machinePartItem = GameRegistry.findItem("EnderIO", "itemMachinePart");
        machineChassis = new ItemStack(machinePartItem, 1, 0);
        basicGear = new ItemStack(machinePartItem, 1, 1);
        
        Item materialsItem = GameRegistry.findItem("EnderIO", "itemMaterial");
        pulsatingCrystal = new ItemStack(materialsItem, 1, 5);
        vibrantCrystal = new ItemStack(materialsItem, 1, 6);
        enderCrystal = new ItemStack(materialsItem, 1, 8);
    }
    
}
