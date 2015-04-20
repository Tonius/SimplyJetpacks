package tonius.simplyjetpacks.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class TDItems {
    
    public static ItemStack ductFluxLeadstone = null;
    public static ItemStack ductFluxHardened = null;
    public static ItemStack ductFluxRedstoneEnergy = null;
    public static ItemStack ductFluxResonant = null;
    
    public static void init() {
        SimplyJetpacks.logger.info("Stealing Thermal Dynamics's items");
        
        Item ductFlux = GameRegistry.findItem("ThermalDynamics", "ThermalDynamics_0");
        
        ductFluxLeadstone = new ItemStack(ductFlux, 1, 0);
        ductFluxHardened = new ItemStack(ductFlux, 1, 1);
        ductFluxRedstoneEnergy = new ItemStack(ductFlux, 1, 2);
        ductFluxResonant = new ItemStack(ductFlux, 1, 4);
    }
    
}
