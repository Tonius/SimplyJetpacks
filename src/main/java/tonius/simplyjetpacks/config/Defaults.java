package tonius.simplyjetpacks.config;

import tonius.simplyjetpacks.client.util.RenderUtils.HUDPositions;
import tonius.simplyjetpacks.setup.ModType;

public abstract class Defaults {
    
    // integration
    public static final boolean enableIntegrationTE = ModType.THERMAL_EXPANSION.isLoaded();
    public static final boolean enableIntegrationEIO = ModType.ENDER_IO.isLoaded() && !enableIntegrationTE;
    public static final boolean enableIntegrationBC = ModType.BUILDCRAFT.isLoaded() && !enableIntegrationTE && !enableIntegrationEIO;
    
    // controls
    public static final boolean customControls = false;
    public static final String flyKey = "SPACE";
    public static final String descendKey = "LSHIFT";
    public static final boolean invertHoverSneakingBehavior = false;
    public static final boolean sneakChangesToggleBehavior = true;
    
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
