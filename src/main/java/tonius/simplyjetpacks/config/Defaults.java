package tonius.simplyjetpacks.config;

import tonius.simplyjetpacks.client.util.RenderUtils.HUDPositions;
import tonius.simplyjetpacks.integration.ModType;

public abstract class Defaults {
    
    // item
    public static final boolean flammableFluidsExplode = false;
    
    // integration
    public static final boolean enableIntegrationTE = ModType.THERMAL_EXPANSION.loaded;
    public static final boolean enableIntegrationEIO = ModType.ENDER_IO.loaded && !enableIntegrationTE;
    public static final boolean enableIntegrationBC = ModType.BUILDCRAFT.loaded && !enableIntegrationTE && !enableIntegrationEIO;
    
    // controls
    public static final boolean customControls = false;
    public static final String flyKey = "SPACE";
    public static final String descendKey = "LSHIFT";
    public static final boolean invertHoverSneakingBehavior = false;
    
    // aesthetics
    public static final boolean enableArmor3DModels = true;
    
    // sounds
    public static final boolean jetpackSounds = true;
    
    // gui
    public static final boolean holdShiftForDetails = true;
    public static final int HUDPosition = HUDPositions.TOP_LEFT.ordinal();
    public static final int HUDOffsetX = 0;
    public static final int HUDOffsetY = 0;
    public static final double HUDScale = 1.0D;
    public static final boolean showHUDWhileChatting = true;
    public static final boolean enableFuelHUD = true;
    public static final boolean minimalFuelHUD = false;
    public static final boolean showExactFuelInHUD = false;
    public static final boolean enableStateHUD = true;
    public static final boolean enableStateChatMessages = false;
    
}
