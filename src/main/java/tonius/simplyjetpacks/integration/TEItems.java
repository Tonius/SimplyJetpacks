package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class TEItems {
    
    public static ItemStack capacitorBasic = null;
    public static ItemStack capacitorHardened = null;
    public static ItemStack capacitorReinforced = null;
    public static ItemStack capacitorResonant = null;
    public static ItemStack cellBasic = null;
    public static ItemStack dynamoReactant = null;
    public static ItemStack dynamoMagmatic = null;
    public static ItemStack dynamoEnervation = null;
    public static ItemStack dynamoSteam = null;
    public static ItemStack frameCellReinforcedFull = null;
    public static ItemStack frameIlluminator = null;
    public static ItemStack pneumaticServo = null;
    public static ItemStack powerCoilElectrum = null;
    public static ItemStack powerCoilGold = null;
    
    public static void init() {
        SimplyJetpacks.logger.info("Stealing Thermal Expansion's items");
        
        capacitorBasic = GameRegistry.findItemStack("ThermalExpansion", "capacitorBasic", 1);
        capacitorHardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        capacitorReinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        capacitorResonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        cellBasic = GameRegistry.findItemStack("ThermalExpansion", "cellBasic", 1);
        dynamoReactant = GameRegistry.findItemStack("ThermalExpansion", "dynamoReactant", 1);
        dynamoMagmatic = GameRegistry.findItemStack("ThermalExpansion", "dynamoMagmatic", 1);
        dynamoEnervation = GameRegistry.findItemStack("ThermalExpansion", "dynamoEnervation", 1);
        dynamoSteam = GameRegistry.findItemStack("ThermalExpansion", "dynamoSteam", 1);
        frameCellReinforcedFull = GameRegistry.findItemStack("ThermalExpansion", "frameCellReinforcedFull", 1);
        frameIlluminator = GameRegistry.findItemStack("ThermalExpansion", "frameIlluminator", 1);
        pneumaticServo = GameRegistry.findItemStack("ThermalExpansion", "pneumaticServo", 1);
        powerCoilElectrum = GameRegistry.findItemStack("ThermalExpansion", "powerCoilElectrum", 1);
        powerCoilGold = GameRegistry.findItemStack("ThermalExpansion", "powerCoilGold", 1);
    }
    
}
