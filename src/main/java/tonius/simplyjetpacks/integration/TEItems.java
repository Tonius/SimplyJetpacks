package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class TEItems {

    public static ItemStack capacitorBasic = null;
    public static ItemStack capacitorHardened = null;
    public static ItemStack capacitorPotato = null;
    public static ItemStack capacitorReinforced = null;
    public static ItemStack capacitorResonant = null;
    public static ItemStack dynamoReactant = null;
    public static ItemStack dynamoMagmatic = null;
    public static ItemStack dynamoEnervation = null;
    public static ItemStack dynamoSteam = null;
    public static ItemStack pneumaticServo = null;
    public static ItemStack powerCoilGold = null;

    public static boolean init() {
        if (Loader.isModLoaded("ThermalExpansion")) {
            SimplyJetpacks.logger.info("Stealing Thermal Expansion's items");

            capacitorBasic = GameRegistry.findItemStack("ThermalExpansion", "capacitorBasic", 1);
            capacitorHardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
            capacitorPotato = GameRegistry.findItemStack("ThermalExpansion", "capacitorPotato", 1);
            capacitorReinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
            capacitorResonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
            dynamoReactant = GameRegistry.findItemStack("ThermalExpansion", "dynamoReactant", 1);
            dynamoMagmatic = GameRegistry.findItemStack("ThermalExpansion", "dynamoMagmatic", 1);
            dynamoEnervation = GameRegistry.findItemStack("ThermalExpansion", "dynamoEnervation", 1);
            dynamoSteam = GameRegistry.findItemStack("ThermalExpansion", "dynamoSteam", 1);
            pneumaticServo = GameRegistry.findItemStack("ThermalExpansion", "pneumaticServo", 1);
            powerCoilGold = GameRegistry.findItemStack("ThermalExpansion", "powerCoilGold", 1);

            return true;
        }
        return false;
    }

}
