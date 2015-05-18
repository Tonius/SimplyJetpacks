package tonius.simplyjetpacks.integration;

import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.Loader;

public enum ModType {
    
    SIMPLY_JETPACKS("", SimplyJetpacks.MODID),
    THERMAL_EXPANSION(".te", "ThermalExpansion"),
    THERMAL_DYNAMICS(null, "ThermalDynamics"),
    REDSTONE_ARSENAL(null, "RedstoneArsenal"),
    REDSTONE_ARMORY(null, "RArm"),
    ENDER_IO(".eio", "EnderIO"),
    BUILDCRAFT(".bc", "BuildCraft|Core");
    
    public final String suffix;
    public final String[] modids;
    public final boolean loaded;
    
    private ModType(String suffix, String... modids) {
        this.suffix = suffix;
        this.modids = modids;
        
        for (String s : this.modids) {
            if (!Loader.isModLoaded(s)) {
                this.loaded = false;
                return;
            }
        }
        this.loaded = true;
    }
    
}
