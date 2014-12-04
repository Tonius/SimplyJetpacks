package tonius.simplyjetpacks.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Config {
    
    public static Configuration config;
    public static Configuration configClient;
    public static List<Section> configSections = new ArrayList<Section>();
    
    public static Map<Integer, JetpackDefaults> jetpackDefaults = new HashMap<Integer, JetpackDefaults>();
    public static Map<Integer, JetpackConfig> jetpackConfigs = new HashMap<Integer, JetpackConfig>();
    
    public static Map<Integer, FluxPackDefaults> fluxPackDefaults = new HashMap<Integer, FluxPackDefaults>();
    public static Map<Integer, FluxPackConfig> fluxPackConfigs = new HashMap<Integer, FluxPackConfig>();
    
    public static final Section sectionIntegration = new Section(false, "Integration Settings", "integration");
    public static final Section sectionControls = new Section(true, "Controls Settings", "controls");
    public static final Section sectionAesthetics = new Section(true, "Aesthetics Settings", "aesthetics");
    public static final Section sectionGui = new Section(true, "GUI Settings", "gui");
    public static final Section sectionCrafting = new Section(false, "Crafting Settings", "crafting");
    
    // integration
    public static boolean enableIntegrationTE = Defaults.enableIntegrationTE;
    public static boolean enableIntegrationEIO = Defaults.enableIntegrationEIO;
    
    // controls
    public static boolean customControls = Defaults.customControls;
    public static String flyKey = Defaults.flyKey;
    public static String descendKey = Defaults.descendKey;
    public static boolean invertHoverSneakingBehavior = Defaults.invertHoverSneakingBehavior;
    public static boolean sneakChangesToggleBehavior = Defaults.sneakChangesToggleBehavior;
    
    // aesthetics
    public static boolean enableJetpackModel = Defaults.enableJetpackModel;
    
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
    
    // crafting
    public static boolean enableCraftingArmorPlating = Defaults.enableCraftingArmorPlating;
    public static boolean enableCraftingPotatoJetpack = Defaults.enableCraftingPotatoJetpack;
    public static boolean enableCraftingJetPlate = Defaults.enableCraftingJetPlate;
    
    private static void initJetpackConfigs() {
        // energyCapacity, energyPerTick, speedVertical,
        // accelVertical, speedVerticalHover, speedVerticalHoverSlow,
        // speedSideways, sprintSpeedModifier, sprintEnergyModifier,
        // armorDisplay, armorAbsorption, armorEnergyPerHit,
        // enchantable, enchantability, emergencyHoverMode, chargerRate
        jetpackDefaults.put(0, new JetpackDefaults(1200, 45, 0.9D, 0.5D, null, null, null, null, null, null, null, null, null, null, null, null));
        jetpackDefaults.put(1, new JetpackDefaults(25000, 10, 0.22D, 0.1D, 0.18D, 0.14D, 0.0D, null, null, 4, 0.3D, 80, false, 8, false, null));
        jetpackDefaults.put(2, new JetpackDefaults(400000, 50, 0.3D, 0.12D, 0.18D, 0.1D, 0.08D, null, null, 5, 0.4D, 80, false, 11, false, null));
        jetpackDefaults.put(3, new JetpackDefaults(2000000, 100, 0.48D, 0.13D, 0.34D, 0.03D, 0.14D, 1.3D, 3.0D, 6, 0.5D, 120, true, 14, true, null));
        jetpackDefaults.put(4, new JetpackDefaults(10000000, 200, 0.8D, 0.14D, 0.4D, 0.005D, 0.19D, 1.8D, 5.0D, 7, 0.6D, 160, true, 17, true, null));
        jetpackDefaults.put(5, new JetpackDefaults(50000000, 400, 0.9D, 0.15D, 0.45D, 0.0D, 0.21D, 2.4D, 8.0D, 8, 0.8D, 240, true, 20, true, 20000));
        
        for (int i : jetpackDefaults.keySet()) {
            jetpackConfigs.put(i, new JetpackConfig(new Section(false, "Tuning - Jetpack tier " + i, "tuningJetpack" + i), jetpackDefaults.get(i)));
        }
        
        jetpackDefaults.put(9001, new JetpackDefaults(null, null, 0.9D, 0.15D, 0.45D, 0.0D, 0.21D, 2.5D, null, 8, 0.8D, null, true, 20, true, 20000));
        jetpackConfigs.put(9001, new JetpackConfig(new Section(false, "Tuning - Creative Jetpack", "tuningJetpackCreative"), jetpackDefaults.get(9001)));
    }
    
    private static void initFluxPackConfigs() {
        // energyCapacity, energyInRate, energyOutRate, armorDisplay,
        // armorAbsorption, armorEnergyPerHit, enchantable, enchantability
        fluxPackDefaults.put(1, new FluxPackDefaults(400000, 80, 80, null, null, null, false, 4));
        fluxPackDefaults.put(2, new FluxPackDefaults(2000000, 400, 400, 3, 0.2D, 80, false, 6));
        fluxPackDefaults.put(3, new FluxPackDefaults(10000000, 2000, 2000, 4, 0.3D, 120, true, 8));
        fluxPackDefaults.put(4, new FluxPackDefaults(50000000, 10000, 10000, 5, 0.4D, 160, true, 10));
        
        for (int i : fluxPackDefaults.keySet()) {
            fluxPackConfigs.put(i, new FluxPackConfig(new Section(false, "Tuning - Flux Pack tier " + i, "tuningFluxPack" + i), fluxPackDefaults.get(i)));
        }
        
        fluxPackDefaults.put(9001, new FluxPackDefaults(null, null, 20000, 6, 0.5D, null, true, 10));
        fluxPackConfigs.put(9001, new FluxPackConfig(new Section(false, "Tuning - Creative Flux Pack", "tuningFluxPackCreative"), fluxPackDefaults.get(9001)));
    }
    
    public static void preInit(FMLPreInitializationEvent evt) {
        FMLCommonHandler.instance().bus().register(new Config());
        
        config = new Configuration(new File(evt.getModConfigurationDirectory(), SimplyJetpacks.MODID + ".cfg"));
        configClient = new Configuration(new File(evt.getModConfigurationDirectory(), SimplyJetpacks.MODID + "-client.cfg"));
        
        initJetpackConfigs();
        initFluxPackConfigs();
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
            
            Jetpack.reconstructJetpacks();
            FluxPack.reconstructFluxPacks();
            SimplyJetpacks.proxy.updateCustomKeybinds();
        }
    }
    
    public static void processConfig() {
        enableIntegrationTE = config.get(sectionIntegration.name, "Thermal Expansion integration", Defaults.enableIntegrationTE, "When enabled, Simply Jetpacks will register its Thermal Expansion-based jetpacks and flux packs.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationTE);
        enableIntegrationEIO = config.get(sectionIntegration.name, "Ender IO integration", Defaults.enableIntegrationEIO, "When enabled, Simply Jetpacks will register its Ender IO-based jetpacks and flux packs.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationEIO);
        
        customControls = configClient.get(sectionControls.name, "Custom controls", Defaults.customControls, "When enabled, the key codes specified here will be used for the fly and descend keys. Otherwise, the vanilla jump and sneak keys will be used.").getBoolean(Defaults.customControls);
        flyKey = configClient.get(sectionControls.name, "Custom Fly key", Defaults.flyKey, "The name of the Fly key when custom controls are enabled.").getString();
        descendKey = configClient.get(sectionControls.name, "Custom Descend key", Defaults.descendKey, "The name of the Descend key when custom controls are enabled.").getString();
        invertHoverSneakingBehavior = configClient.get(sectionControls.name, "Invert Hover Mode sneaking behavior", Defaults.invertHoverSneakingBehavior, "Invert Hover Mode sneaking behavior").getBoolean(Defaults.invertHoverSneakingBehavior);
        sneakChangesToggleBehavior = configClient.get(sectionControls.name, "Sneak Changes Toggle Behavior", Defaults.sneakChangesToggleBehavior, "If enabled, when sneaking, the Turn on/off and Switch mode keys will respectively toggle JetPlate chargers and emergency hover mode. If not, use /simplyjetpacks_switch or /sjs to toggle these features.").getBoolean(Defaults.sneakChangesToggleBehavior);
        
        enableJetpackModel = configClient.get(sectionAesthetics.name, "Enable Jetpack 3D Model", Defaults.enableJetpackModel, "When enabled, worn jetpacks will have a 3D armor model. Otherwise, flat textures will be used.").getBoolean(Defaults.enableJetpackModel);
        
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
        
        enableCraftingArmorPlating = config.get(sectionCrafting.name, "Armor Plating craftable", Defaults.enableCraftingArmorPlating, "When enabled, Armor Plating items will be craftable, and thus armored jetpacks are available.").setRequiresMcRestart(true).getBoolean(Defaults.enableCraftingArmorPlating);
        enableCraftingPotatoJetpack = config.get(sectionCrafting.name, "Potato Jetpack craftable", Defaults.enableCraftingPotatoJetpack, "When enabled, the Potato Jetpack will be craftable.").setRequiresMcRestart(true).getBoolean(Defaults.enableCraftingPotatoJetpack);
        enableCraftingJetPlate = config.get(sectionCrafting.name, "JetPlates craftable", Defaults.enableCraftingJetPlate, "When enabled, JetPlates will be craftable.").setRequiresMcRestart(true).getBoolean(Defaults.enableCraftingJetPlate);
        
        for (JetpackConfig jc : jetpackConfigs.values()) {
            jc.processConfig(config);
        }
        
        for (FluxPackConfig fc : fluxPackConfigs.values()) {
            fc.processConfig(config);
        }
    }
    
}
