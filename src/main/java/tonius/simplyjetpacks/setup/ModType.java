package tonius.simplyjetpacks.setup;

import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.Loader;

public enum ModType {
    
    SIMPLY_JETPACKS(SimplyJetpacks.MODID, ""),
    THERMAL_EXPANSION("ThermalExpansion", ".te"),
    ENDER_IO("EnderIO", ".eio");
    
    public final String modid;
    public final String suffix;
    
    private ModType(String modid, String suffix) {
        this.modid = modid;
        this.suffix = suffix;
    }
    
    public boolean isLoaded() {
        return Loader.isModLoaded(this.modid);
    }
    
}
