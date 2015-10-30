package tonius.simplyjetpacks.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPositions;
import tonius.simplyjetpacks.item.meta.PackBase;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Config {
    
    public static final List<Section> configSections = new ArrayList<Section>();
    private static final Section sectionItem = new Section(false, "Item Settings", "item");
    private static final Section sectionIntegration = new Section(false, "Integration Settings", "integration");
    private static final Section sectionControls = new Section(true, "Controls Settings", "controls");
    private static final Section sectionAesthetics = new Section(true, "Aesthetics Settings", "aesthetics");
    private static final Section sectionSounds = new Section(true, "Sound Settings", "sounds");
    private static final Section sectionGui = new Section(true, "GUI Settings", "gui");
    public static Configuration config;
    public static Configuration configClient;
    
    // item
    public static int enchantFuelEfficiencyID = Defaults.enchantFuelEfficiencyID;
    public static boolean flammableFluidsExplode = Defaults.flammableFluidsExplode;
    public static boolean addRAItemsIfNotInstalled = Defaults.addRAItemsIfNotInstalled;
    
    // integration
    public static boolean enableIntegrationTE = Defaults.enableIntegrationTE;
    public static boolean enableIntegrationEIO = Defaults.enableIntegrationEIO;
    public static boolean enableIntegrationBC = Defaults.enableIntegrationBC;
    
    // controls
    public static boolean customControls = Defaults.customControls;
    public static String flyKey = Defaults.flyKey;
    public static String descendKey = Defaults.descendKey;
    public static boolean invertHoverSneakingBehavior = Defaults.invertHoverSneakingBehavior;
    public static boolean doubleTapSprintInAir = Defaults.doubleTapSprintInAir;
    
    // aesthetics
    public static boolean enableArmor3DModels = Defaults.enableArmor3DModels;
    
    // sounds
    public static boolean jetpackSounds = Defaults.jetpackSounds;
    
    // gui
    public static boolean holdShiftForDetails = Defaults.holdShiftForDetails;
    public static int HUDPosition = Defaults.HUDPosition;
    public static int HUDOffsetX = Defaults.HUDOffsetX;
    public static int HUDOffsetY = Defaults.HUDOffsetY;
    public static double HUDScale = Defaults.HUDScale;
    public static boolean showHUDWhileChatting = Defaults.showHUDWhileChatting;
    public static boolean enableFuelHUD = Defaults.enableFuelHUD;
    public static boolean minimalFuelHUD = Defaults.minimalFuelHUD;
    public static boolean showExactFuelInHUD = Defaults.showExactFuelInHUD;
    public static boolean enableStateHUD = Defaults.enableStateHUD;
    public static boolean enableStateChatMessages = Defaults.enableStateChatMessages;
    
    public static void preInit(FMLPreInitializationEvent evt) {
        FMLCommonHandler.instance().bus().register(new Config());
        
        config = new Configuration(new File(evt.getModConfigurationDirectory(), SimplyJetpacks.MODID + ".cfg"));
        configClient = new Configuration(new File(evt.getModConfigurationDirectory(), SimplyJetpacks.MODID + "-client.cfg"));
        
        syncConfig();
        SimplyJetpacks.proxy.updateCustomKeybinds(flyKey, descendKey);
    }
    
    private static void syncConfig() {
        SimplyJetpacks.logger.info("Loading configuration files");
        try {
            processConfig();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            config.save();
            configClient.save();
        }
    }
    
    public static void onConfigChanged(String modid) {
        if (modid.equals(SimplyJetpacks.MODID)) {
            syncConfig();
            SimplyJetpacks.proxy.updateCustomKeybinds(flyKey, descendKey);
        }
    }
    
    private static void processConfig() {
        enchantFuelEfficiencyID = config.get(sectionItem.name, "Fuel Efficiency enchant ID", Defaults.enchantFuelEfficiencyID, "The ID of the Fuel Efficiency enchantment. Set to 0 to disable.").setMinValue(0).setMaxValue(255).setRequiresMcRestart(true).getInt();
        flammableFluidsExplode = config.get(sectionItem.name, "Jetpacks explode in flammable fluid blocks", Defaults.flammableFluidsExplode, "When enabled, jetpacks will explode and kill their users when they are being used to fly through flammable fluid blocks.").getBoolean(Defaults.flammableFluidsExplode);
        addRAItemsIfNotInstalled = config.get(sectionItem.name, "Add Redstone Arsenal items if not installed", Defaults.addRAItemsIfNotInstalled, "When enabled, Simply Jetpacks will register some crafting components from Redstone Arsenal to make the Flux-Infused JetPlate craftable if Redstone Arsenal is not installed.").getBoolean(Defaults.addRAItemsIfNotInstalled);
        
        enableIntegrationTE = config.get(sectionIntegration.name, "Thermal Expansion integration", Defaults.enableIntegrationTE, "When enabled, Simply Jetpacks will register its Thermal Expansion-based jetpacks and flux packs.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationTE);
        enableIntegrationEIO = config.get(sectionIntegration.name, "Ender IO integration", Defaults.enableIntegrationEIO, "When enabled, Simply Jetpacks will register its Ender IO-based jetpacks and flux packs.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationEIO);
        enableIntegrationBC = config.get(sectionIntegration.name, "BuildCraft integration", Defaults.enableIntegrationBC, "When enabled, Simply Jetpacks will register its BuildCraft-based jetpacks.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationBC);
        
        customControls = configClient.get(sectionControls.name, "Custom controls", Defaults.customControls, "When enabled, the key codes specified here will be used for the fly and descend keys. Otherwise, the vanilla jump and sneak keys will be used.").getBoolean(Defaults.customControls);
        flyKey = configClient.get(sectionControls.name, "Custom Fly key", Defaults.flyKey, "The name of the Fly key when custom controls are enabled.").getString();
        descendKey = configClient.get(sectionControls.name, "Custom Descend key", Defaults.descendKey, "The name of the Descend key when custom controls are enabled.").getString();
        invertHoverSneakingBehavior = configClient.get(sectionControls.name, "Invert Hover Mode sneaking behavior", Defaults.invertHoverSneakingBehavior, "Invert Hover Mode sneaking behavior").getBoolean(Defaults.invertHoverSneakingBehavior);
        doubleTapSprintInAir = configClient.get(sectionControls.name, "Allow double-tap sprinting while flying", Defaults.doubleTapSprintInAir, "When enabled, sprinting by double-tapping the forward key will work while flying with a jetpack. Can be used as an easier way to activate a jetpack's boost while airborne (the sprint key also works).").getBoolean(Defaults.doubleTapSprintInAir);
        
        enableArmor3DModels = configClient.get(sectionAesthetics.name, "Enable Armor 3D Models", Defaults.enableArmor3DModels, "When enabled, worn jetpacks and flux packs will have a 3D armor model. Otherwise, flat textures will be used.").getBoolean(Defaults.enableArmor3DModels);
        
        jetpackSounds = configClient.get(sectionSounds.name, "Jetpack Sounds", Defaults.jetpackSounds, "When enabled, jetpacks will make sounds when used.").getBoolean(Defaults.jetpackSounds);
        
        holdShiftForDetails = configClient.get(sectionGui.name, "Hold Shift for Details", Defaults.holdShiftForDetails, "When enabled, item details are only shown in the tooltip when holding Shift.").getBoolean(Defaults.holdShiftForDetails);
        HUDPosition = configClient.get(sectionGui.name, "HUD Base Position", Defaults.HUDPosition, "The base position of the HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").setMinValue(0).setMaxValue(HUDPositions.values().length - 1).getInt(Defaults.HUDPosition);
        HUDOffsetX = configClient.get(sectionGui.name, "HUD Offset - X", Defaults.HUDOffsetX, "The HUD display will be shifted horizontally by this value. This value may be negative.").getInt(Defaults.HUDOffsetX);
        HUDOffsetY = configClient.get(sectionGui.name, "HUD Offset - Y", Defaults.HUDOffsetY, "The HUD display will be shifted vertically by this value. This value may be negative.").getInt(Defaults.HUDOffsetY);
        HUDScale = Math.abs(configClient.get(sectionGui.name, "HUD Scale", Defaults.HUDScale, "How large the HUD will be rendered. Default is 1.0, can be bigger or smaller").setMinValue(0.001D).getDouble(Defaults.HUDScale));
        showHUDWhileChatting = configClient.get(sectionGui.name, "Show HUD while chatting", Defaults.showHUDWhileChatting, "When enabled, the HUD will display even when the chat window is opened.").getBoolean(Defaults.showHUDWhileChatting);
        enableFuelHUD = configClient.get(sectionGui.name, "Enable Fuel HUD", Defaults.enableFuelHUD, "When enabled, a HUD that displays the fuel level of the currently worn jetpack or flux pack will show.").getBoolean(Defaults.enableFuelHUD);
        minimalFuelHUD = configClient.get(sectionGui.name, "Minimal Fuel HUD", Defaults.minimalFuelHUD, "When enabled, only the fuel amounts themselves will be rendered on the fuel HUD.").getBoolean(Defaults.minimalFuelHUD);
        showExactFuelInHUD = configClient.get(sectionGui.name, "Exact fuel amounts in HUD", Defaults.showExactFuelInHUD, "When enabled, the fuel HUD will display the exact amount of RF or mB other than just a percentage.").getBoolean(Defaults.showExactFuelInHUD);
        enableStateHUD = configClient.get(sectionGui.name, "Enable State HUD", Defaults.enableStateHUD, "When enabled, a HUD that displays the states (engine/mode/etc.) of the currently worn jetpack or flux pack will show.").getBoolean(Defaults.enableStateHUD);
        enableStateChatMessages = configClient.get(sectionGui.name, "Enable State Chat Messages", Defaults.enableStateChatMessages, "When enabled, switching jetpacks or flux packs on or off will display chat messages.").getBoolean(Defaults.enableStateChatMessages);
        
        PackBase.loadAllConfigs(config);
    }
    
    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent evt) {
        onConfigChanged(evt.modID);
    }
    
}
