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

public class SJConfig {
    
    public static Configuration config;
    public static Configuration configClient;
    public static List<ConfigSection> configSections = new ArrayList<ConfigSection>();
    
    public static Map<Integer, JetpackDefaults> jetpackDefaults = new HashMap<Integer, JetpackDefaults>();
    public static Map<Integer, JetpackConfig> jetpackConfigs = new HashMap<Integer, JetpackConfig>();
    
    public static Map<Integer, FluxPackDefaults> fluxPackDefaults = new HashMap<Integer, FluxPackDefaults>();
    public static Map<Integer, FluxPackConfig> fluxPackConfigs = new HashMap<Integer, FluxPackConfig>();
    
    public static final ConfigSection sectionControls = new ConfigSection(true, "Controls Settings", "controls");
    public static final ConfigSection sectionGui = new ConfigSection(true, "GUI Settings", "gui");
    public static final ConfigSection sectionCrafting = new ConfigSection(false, "Crafting Settings", "crafting");
    
    // controls
    public static boolean customControls = SJDefaults.customControls;
    public static String flyKey = SJDefaults.flyKey;
    public static String descendKey = SJDefaults.descendKey;
    public static boolean invertHoverSneakingBehavior = SJDefaults.invertHoverSneakingBehavior;
    
    // gui
    public static boolean enableStateChatMessages = SJDefaults.enableStateChatMessages;
    public static boolean enableEnergyHUD = SJDefaults.enableEnergyHUD;
    public static boolean enableStateHUD = SJDefaults.enableStateHUD;
    public static int energyHUDOffsetX = SJDefaults.energyHUDOffsetX;
    public static int energyHUDOffsetY = SJDefaults.energyHUDOffsetY;
    public static int energyHUDPosition = SJDefaults.energyHUDPosition;
    public static double energyHUDScale = SJDefaults.energyHUDScale;
    public static boolean holdShiftForDetails = SJDefaults.holdShiftForDetails;
    public static boolean minimalEnergyHUD = SJDefaults.minimalEnergyHUD;
    public static boolean showEnergyHUDWhileChatting = SJDefaults.showEnergyHUDWhileChatting;
    public static boolean showExactEnergyInHUD = SJDefaults.showExactEnergyInHUD;
    
    // crafting
    public static boolean enableCraftingArmorPlating = SJDefaults.enableCraftingArmorPlating;
    public static boolean enableCraftingPotatoJetpack = SJDefaults.enableCraftingPotatoJetpack;
    public static boolean enableCraftingFluxJetPlate = SJDefaults.enableCraftingFluxJetPlate;
    
    private static void initJetpackConfigs() {
        jetpackDefaults.put(0, new JetpackDefaults(1200, 45, 0.9D, 0.5D, null, null, null, null, null, null, null, null, null, null));
        jetpackDefaults.put(1, new JetpackDefaults(25000, 10, 0.22D, 0.1D, 0.18D, 0.14D, 0.0D, 4, 0.3D, 80, false, 8, false, null));
        jetpackDefaults.put(2, new JetpackDefaults(400000, 50, 0.3D, 0.12D, 0.18D, 0.1D, 0.08D, 5, 0.4D, 80, false, 11, false, null));
        jetpackDefaults.put(3, new JetpackDefaults(2000000, 100, 0.48D, 0.13D, 0.34D, 0.03D, 0.14D, 6, 0.5D, 120, true, 14, true, null));
        jetpackDefaults.put(4, new JetpackDefaults(10000000, 200, 0.8D, 0.14D, 0.4D, 0.005D, 0.19D, 7, 0.6D, 160, true, 17, true, null));
        jetpackDefaults.put(5, new JetpackDefaults(50000000, 400, 0.9D, 0.15D, 0.45D, 0.0D, 0.21D, 8, 0.8D, 240, true, 20, true, 20000));
        
        for (int i : jetpackDefaults.keySet()) {
            jetpackConfigs.put(i, new JetpackConfig(new ConfigSection(false, "Tuning - Jetpack tier " + i, "tuningJetpack" + i), jetpackDefaults.get(i)));
        }
        
        jetpackDefaults.put(9001, new JetpackDefaults(null, null, 0.9D, 0.15D, 0.45D, 0.0D, 0.21D, 8, 0.8D, null, true, 20, true, 20000));
        jetpackConfigs.put(9001, new JetpackConfig(new ConfigSection(false, "Tuning - Creative Jetpack", "tuningJetpackCreative"), jetpackDefaults.get(9001)));
    }
    
    private static void initFluxPackConfigs() {
        fluxPackDefaults.put(1, new FluxPackDefaults(400000, 80, 80, null, null, null, false, 4));
        fluxPackDefaults.put(2, new FluxPackDefaults(2000000, 400, 400, 3, 0.2D, 80, false, 6));
        fluxPackDefaults.put(3, new FluxPackDefaults(10000000, 2000, 2000, 4, 0.3D, 120, true, 8));
        fluxPackDefaults.put(4, new FluxPackDefaults(50000000, 10000, 10000, 5, 0.4D, 160, true, 10));
        
        for (int i : fluxPackDefaults.keySet()) {
            fluxPackConfigs.put(i, new FluxPackConfig(new ConfigSection(false, "Tuning - Flux Pack tier " + i, "tuningFluxPack" + i), fluxPackDefaults.get(i)));
        }
        
        fluxPackDefaults.put(9001, new FluxPackDefaults(null, null, 20000, 6, 0.5D, null, true, 10));
        fluxPackConfigs.put(9001, new FluxPackConfig(new ConfigSection(false, "Tuning - Creative Flux Pack", "tuningFluxPackCreative"), fluxPackDefaults.get(9001)));
    }
    
    public static void preInit(FMLPreInitializationEvent evt) {
        FMLCommonHandler.instance().bus().register(new SJConfig());
        
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
        customControls = configClient.get(sectionControls.name, "Custom controls", SJDefaults.customControls, "When enabled, the key codes specified here will be used for the fly and descend keys. Otherwise, the vanilla jump and sneak keys will be used.").getBoolean(SJDefaults.customControls);
        flyKey = configClient.get(sectionControls.name, "Custom Fly key", SJDefaults.flyKey, "The name of the Fly key when custom controls are enabled.").getString();
        descendKey = configClient.get(sectionControls.name, "Custom Descend key", SJDefaults.descendKey, "The name of the Descend key when custom controls are enabled.").getString();
        invertHoverSneakingBehavior = configClient.get(sectionControls.name, "Invert Hover Mode sneaking behavior", SJDefaults.invertHoverSneakingBehavior, "Invert Hover Mode sneaking behavior").getBoolean(SJDefaults.invertHoverSneakingBehavior);
        
        enableStateChatMessages = configClient.get(sectionGui.name, "Enable State Chat Messages", SJDefaults.enableStateChatMessages, "When enabled, switching jetpacks on or off will display chat messages.").getBoolean(SJDefaults.enableStateChatMessages);
        enableEnergyHUD = configClient.get(sectionGui.name, "Enable Energy HUD", SJDefaults.enableEnergyHUD, "When enabled, a HUD that displays your current jetpack's energy level will show.").getBoolean(SJDefaults.enableEnergyHUD);
        enableStateHUD = configClient.get(sectionGui.name, "Enable State HUD", SJDefaults.enableStateHUD, "When enabled, a HUD that displays your current jetpack's engine and hover mode state will show.").getBoolean(SJDefaults.enableStateHUD);
        energyHUDOffsetX = configClient.get(sectionGui.name, "Energy HUD Offset - X", SJDefaults.energyHUDOffsetX, "The energy HUD display will be shifted horizontally by this value. This value may be negative.").getInt(SJDefaults.energyHUDOffsetX);
        energyHUDOffsetY = configClient.get(sectionGui.name, "Energy HUD Offset - Y", SJDefaults.energyHUDOffsetY, "The energy HUD display will be shifted vertically by this value. This value may be negative.").getInt(SJDefaults.energyHUDOffsetY);
        energyHUDPosition = configClient.get(sectionGui.name, "Energy HUD Base Position", SJDefaults.energyHUDPosition, "The base position of the energy HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").setMinValue(0).setMaxValue(HUDPosition.values().length - 1).getInt(SJDefaults.energyHUDPosition);
        energyHUDScale = Math.abs(configClient.get(sectionGui.name, "Energy HUD Scale", SJDefaults.energyHUDScale, "How large the energy HUD will be rendered. Default is 1.0, can be bigger or smaller").setMinValue(0).getDouble(SJDefaults.energyHUDScale));
        holdShiftForDetails = configClient.get(sectionGui.name, "Hold Shift for Details", SJDefaults.holdShiftForDetails, "When enabled, item details are only shown in the tooltip when holding Shift.").getBoolean(SJDefaults.holdShiftForDetails);
        minimalEnergyHUD = configClient.get(sectionGui.name, "Minimal Energy HUD", SJDefaults.minimalEnergyHUD, "When enabled, only the actual power amounts will be rendered on the energy HUD.").getBoolean(SJDefaults.minimalEnergyHUD);
        showEnergyHUDWhileChatting = configClient.get(sectionGui.name, "Show Energy HUD while chatting", SJDefaults.showEnergyHUDWhileChatting, "When enabled, the energy HUD will display even when the chat window is opened.").getBoolean(SJDefaults.showEnergyHUDWhileChatting);
        showExactEnergyInHUD = configClient.get(sectionGui.name, "Exact energy amounts in Energy HUD", SJDefaults.showExactEnergyInHUD, "When enabled, the energy HUD will display the exact amount of RF other than just a percentage.").getBoolean(SJDefaults.showExactEnergyInHUD);
        
        enableCraftingArmorPlating = config.get(sectionCrafting.name, "Armor Plating craftable", SJDefaults.enableCraftingArmorPlating, "When enabled, Armor Plating items will be craftable, and thus armored jetpacks are available.").setRequiresMcRestart(true).getBoolean(SJDefaults.enableCraftingArmorPlating);
        enableCraftingPotatoJetpack = config.get(sectionCrafting.name, "Potato Jetpack craftable", SJDefaults.enableCraftingPotatoJetpack, "When enabled, the Potato Jetpack will be craftable.").setRequiresMcRestart(true).getBoolean(SJDefaults.enableCraftingPotatoJetpack);
        enableCraftingFluxJetPlate = config.get(sectionCrafting.name, "Flux-Infused JetPlate craftable", SJDefaults.enableCraftingFluxJetPlate, "When enabled, the Flux-Infused JetPlate will be craftable.").setRequiresMcRestart(true).getBoolean(SJDefaults.enableCraftingFluxJetPlate);
        
        for (JetpackConfig jc : jetpackConfigs.values()) {
            jc.processConfig(config);
        }
        
        for (FluxPackConfig fc : fluxPackConfigs.values()) {
            fc.processConfig(config);
        }
    }
    
}
