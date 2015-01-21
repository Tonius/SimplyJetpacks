package tonius.simplyjetpacks.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.item.meta.PackBase;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Config {
    
    public static Configuration config;
    public static Configuration configClient;
    public static List<Section> configSections = new ArrayList<Section>();
    
    public static final Section sectionIntegration = new Section(false, "Integration Settings", "integration");
    public static final Section sectionControls = new Section(true, "Controls Settings", "controls");
    public static final Section sectionSounds = new Section(true, "Sound Settings", "sounds");
    public static final Section sectionGui = new Section(true, "GUI Settings", "gui");
    public static final Section sectionCrafting = new Section(false, "Crafting Settings", "crafting");
    
    // integration
    public static boolean enableIntegrationTE = Defaults.enableIntegrationTE;
    public static boolean enableIntegrationEIO = Defaults.enableIntegrationEIO;
    public static boolean enableIntegrationBC = Defaults.enableIntegrationBC;
    
    // controls
    public static boolean customControls = Defaults.customControls;
    public static String flyKey = Defaults.flyKey;
    public static String descendKey = Defaults.descendKey;
    public static boolean invertHoverSneakingBehavior = Defaults.invertHoverSneakingBehavior;
    public static boolean sneakChangesToggleBehavior = Defaults.sneakChangesToggleBehavior;
    
    // sounds
    public static boolean jetpackSounds = Defaults.jetpackSounds;
    
    // gui
    public static boolean enableStateChatMessages = Defaults.enableStateChatMessages;
    public static boolean enableEnergyHUD = Defaults.enableEnergyHUD;
    public static boolean enableStateHUD = Defaults.enableStateHUD;
    public static int energyHUDOffsetX = Defaults.energyHUDOffsetX;
    public static int energyHUDOffsetY = Defaults.energyHUDOffsetY;
    public static int energyHUDPosition = Defaults.energyHUDPosition;
    public static double energyHUDScale = Defaults.energyHUDScale;
    public static boolean holdShiftForDetails = Defaults.holdShiftForDetails;
    public static boolean minimalEnergyHUD = Defaults.minimalEnergyHUD;
    public static boolean showEnergyHUDWhileChatting = Defaults.showEnergyHUDWhileChatting;
    public static boolean showExactEnergyInHUD = Defaults.showExactEnergyInHUD;
    
    public static void preInit(FMLPreInitializationEvent evt) {
        FMLCommonHandler.instance().bus().register(new Config());
        
        config = new Configuration(new File(evt.getModConfigurationDirectory(), SimplyJetpacks.MODID + ".cfg"));
        configClient = new Configuration(new File(evt.getModConfigurationDirectory(), SimplyJetpacks.MODID + "-client.cfg"));
        
        syncConfig();
        SimplyJetpacks.proxy.updateCustomKeybinds();
    }
    
    public static void syncConfig() {
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
    
    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent evt) {
        onConfigChanged(evt.modID);
    }
    
    public static void onConfigChanged(String modid) {
        if (modid.equals(SimplyJetpacks.MODID)) {
            syncConfig();
            SimplyJetpacks.proxy.updateCustomKeybinds();
        }
    }
    
    public static void processConfig() {
        enableIntegrationTE = config.get(sectionIntegration.name, "Thermal Expansion integration", Defaults.enableIntegrationTE, "When enabled, Simply Jetpacks will register its Thermal Expansion-based jetpacks and flux packs.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationTE);
        enableIntegrationEIO = config.get(sectionIntegration.name, "Ender IO integration", Defaults.enableIntegrationEIO, "When enabled, Simply Jetpacks will register its Ender IO-based jetpacks and flux packs.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationEIO);
        enableIntegrationBC = config.get(sectionIntegration.name, "BuildCraft integration", Defaults.enableIntegrationBC, "When enabled, Simply Jetpacks will register its BuildCraft-based jetpacks.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationBC);
        
        customControls = configClient.get(sectionControls.name, "Custom controls", Defaults.customControls, "When enabled, the key codes specified here will be used for the fly and descend keys. Otherwise, the vanilla jump and sneak keys will be used.").getBoolean(Defaults.customControls);
        flyKey = configClient.get(sectionControls.name, "Custom Fly key", Defaults.flyKey, "The name of the Fly key when custom controls are enabled.").getString();
        descendKey = configClient.get(sectionControls.name, "Custom Descend key", Defaults.descendKey, "The name of the Descend key when custom controls are enabled.").getString();
        invertHoverSneakingBehavior = configClient.get(sectionControls.name, "Invert Hover Mode sneaking behavior", Defaults.invertHoverSneakingBehavior, "Invert Hover Mode sneaking behavior").getBoolean(Defaults.invertHoverSneakingBehavior);
        sneakChangesToggleBehavior = configClient.get(sectionControls.name, "Sneak Changes Toggle Behavior", Defaults.sneakChangesToggleBehavior, "If enabled, when sneaking, the Turn on/off and Switch mode keys will respectively toggle JetPlate chargers and emergency hover mode. If not, use /simplyjetpacks_switch or /sjs to toggle these features.").getBoolean(Defaults.sneakChangesToggleBehavior);
        
        jetpackSounds = configClient.get(sectionSounds.name, "Jetpack Sounds", Defaults.jetpackSounds, "When enabled, jetpacks will make sounds when used.").getBoolean(Defaults.jetpackSounds);
        
        enableStateChatMessages = configClient.get(sectionGui.name, "Enable State Chat Messages", Defaults.enableStateChatMessages, "When enabled, switching jetpacks on or off will display chat messages.").getBoolean(Defaults.enableStateChatMessages);
        enableEnergyHUD = configClient.get(sectionGui.name, "Enable Energy HUD", Defaults.enableEnergyHUD, "When enabled, a HUD that displays your current jetpack's energy level will show.").getBoolean(Defaults.enableEnergyHUD);
        enableStateHUD = configClient.get(sectionGui.name, "Enable State HUD", Defaults.enableStateHUD, "When enabled, a HUD that displays your current jetpack's engine and hover mode state will show.").getBoolean(Defaults.enableStateHUD);
        energyHUDOffsetX = configClient.get(sectionGui.name, "Energy HUD Offset - X", Defaults.energyHUDOffsetX, "The energy HUD display will be shifted horizontally by this value. This value may be negative.").getInt(Defaults.energyHUDOffsetX);
        energyHUDOffsetY = configClient.get(sectionGui.name, "Energy HUD Offset - Y", Defaults.energyHUDOffsetY, "The energy HUD display will be shifted vertically by this value. This value may be negative.").getInt(Defaults.energyHUDOffsetY);
        energyHUDPosition = configClient.get(sectionGui.name, "Energy HUD Base Position", Defaults.energyHUDPosition, "The base position of the energy HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").setMinValue(0).setMaxValue(HUDPosition.values().length - 1).getInt(Defaults.energyHUDPosition);
        energyHUDScale = Math.abs(configClient.get(sectionGui.name, "Energy HUD Scale", Defaults.energyHUDScale, "How large the energy HUD will be rendered. Default is 1.0, can be bigger or smaller").setMinValue(0.001D).getDouble(Defaults.energyHUDScale));
        holdShiftForDetails = configClient.get(sectionGui.name, "Hold Shift for Details", Defaults.holdShiftForDetails, "When enabled, item details are only shown in the tooltip when holding Shift.").getBoolean(Defaults.holdShiftForDetails);
        minimalEnergyHUD = configClient.get(sectionGui.name, "Minimal Energy HUD", Defaults.minimalEnergyHUD, "When enabled, only the actual power amounts will be rendered on the energy HUD.").getBoolean(Defaults.minimalEnergyHUD);
        showEnergyHUDWhileChatting = configClient.get(sectionGui.name, "Show Energy HUD while chatting", Defaults.showEnergyHUDWhileChatting, "When enabled, the energy HUD will display even when the chat window is opened.").getBoolean(Defaults.showEnergyHUDWhileChatting);
        showExactEnergyInHUD = configClient.get(sectionGui.name, "Exact energy amounts in Energy HUD", Defaults.showExactEnergyInHUD, "When enabled, the energy HUD will display the exact amount of RF other than just a percentage.").getBoolean(Defaults.showExactEnergyInHUD);
        
        PackBase.loadAllConfigs(config);
    }
    
}
