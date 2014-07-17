package tonius.simplyjetpacks.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SJConfig {

    public static Configuration config;
    public static List<ConfigSection> configSections = new ArrayList<ConfigSection>();

    public static final ConfigSection sectionItem = new ConfigSection("Item Settings", "item");
    public static final ConfigSection sectionCrafting = new ConfigSection("Crafting Settings", "crafting");
    public static final ConfigSection sectionGui = new ConfigSection("GUI Settings", "gui");

    // item default
    public static final boolean enableJetpackParticles_default = true;
    public static final boolean invertHoverSneakingBehavior_default = false;

    // crafting default
    public static final boolean enableCraftingArmorPlating_default = true;
    public static final boolean enableCraftingPotatoJetpack_default = true;

    // gui default
    public static final boolean enableEnergyHUD_default = true;
    public static final int energyHUDOffsetX_default = 0;
    public static final int energyHUDOffsetY_default = 0;
    public static final int energyHUDPosition_default = HUDPosition.TOP_LEFT.ordinal();
    public static final double energyHUDScale_default = 1.0F;
    public static final boolean holdShiftForDetails_default = true;
    public static final boolean minimalEnergyHUD_default = false;
    public static final boolean showEnergyHUDWhileChatting_default = true;
    public static final boolean showExactEnergyInHUD_default = false;

    // item
    public static boolean enableJetpackParticles = enableJetpackParticles_default;
    public static boolean invertHoverSneakingBehavior = invertHoverSneakingBehavior_default;

    // crafting
    public static boolean enableCraftingArmorPlating = enableCraftingArmorPlating_default;
    public static boolean enableCraftingPotatoJetpack = enableCraftingPotatoJetpack_default;

    // gui
    public static boolean enableEnergyHUD = enableEnergyHUD_default;
    public static int energyHUDOffsetX = energyHUDOffsetX_default;
    public static int energyHUDOffsetY = energyHUDOffsetY_default;
    public static int energyHUDPosition = energyHUDPosition_default;
    public static double energyHUDScale = energyHUDScale_default;
    public static boolean holdShiftForDetails = holdShiftForDetails_default;
    public static boolean minimalEnergyHUD = minimalEnergyHUD_default;
    public static boolean showEnergyHUDWhileChatting = showEnergyHUDWhileChatting_default;
    public static boolean showExactEnergyInHUD = showExactEnergyInHUD_default;

    public static void preInit(FMLPreInitializationEvent evt) {
        FMLCommonHandler.instance().bus().register(new SJConfig());
        config = new Configuration(evt.getSuggestedConfigurationFile());
        SimplyJetpacks.logger.info("Loading configuration file");
        syncConfig();
    }

    public static void syncConfig() {
        try {
            processConfig();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent evt) {
        if (evt.modID.equals("simplyjetpacks")) {
            SimplyJetpacks.logger.info("Updating configuration file");
            syncConfig();
        }
    }

    public static void processConfig() {
        enableJetpackParticles = config.get(sectionItem.name, "Enable jetpack particles", enableJetpackParticles_default, "When enabled, jetpacks will emit particles when active.").getBoolean(enableJetpackParticles_default);
        invertHoverSneakingBehavior = config.get(sectionItem.name, "Invert Hover Mode sneaking behavior", invertHoverSneakingBehavior_default, "Invert Hover Mode sneaking behavior").getBoolean(invertHoverSneakingBehavior_default);

        enableCraftingArmorPlating = config.get(sectionCrafting.name, "Armor Plating craftable", enableCraftingArmorPlating_default, "When enabled, Armor Plating items will be craftable, and thus armored jetpacks are available. (Requires restart)").getBoolean(enableCraftingArmorPlating_default);
        enableCraftingPotatoJetpack = config.get(sectionCrafting.name, "Potato Jetpack craftable", enableCraftingPotatoJetpack_default, "When enabled, the Potato Jetpack will be craftable. (Requires restart)").getBoolean(enableCraftingPotatoJetpack_default);

        enableEnergyHUD = config.get(sectionGui.name, "Enable Energy HUD", enableEnergyHUD_default, "When enabled, a HUD that displays your current jetpack's energy level will show.").getBoolean(enableEnergyHUD_default);
        energyHUDOffsetX = config.get(sectionGui.name, "Energy HUD Offset - X", energyHUDOffsetX_default, "The energy HUD display will be shifted horizontally by this value. This value may be negative.").getInt(energyHUDOffsetX_default);
        energyHUDOffsetY = config.get(sectionGui.name, "Energy HUD Offset - Y", energyHUDOffsetY_default, "The energy HUD display will be shifted vertically by this value. This value may be negative.").getInt(energyHUDOffsetY_default);
        int energyHUDPosition_temp = config.get(sectionGui.name, "Energy HUD Base Position", energyHUDPosition_default, "The base position of the energy HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").getInt(energyHUDPosition_default);
        energyHUDPosition = energyHUDPosition_temp < HUDPosition.values().length ? energyHUDPosition_temp : 0;
        energyHUDScale = Math.abs(config.get(sectionGui.name, "Energy HUD Scale", energyHUDScale_default, "How large the energy HUD will be rendered. Default is 1.0, can be bigger or smaller").getDouble(energyHUDScale_default));
        holdShiftForDetails = config.get(sectionGui.name, "Hold Shift for Details", holdShiftForDetails_default, "When enabled, item details are only shown in the tooltip when holding Shift.").getBoolean(holdShiftForDetails_default);
        minimalEnergyHUD = config.get(sectionGui.name, "Minimal Energy HUD", minimalEnergyHUD_default, "When enabled, only the actual power amounts will be rendered on the energy HUD.").getBoolean(minimalEnergyHUD_default);
        showEnergyHUDWhileChatting = config.get(sectionGui.name, "Show Energy HUD while chatting", showEnergyHUDWhileChatting_default, "When enabled, the energy HUD will display even when the chat window is opened.").getBoolean(showEnergyHUDWhileChatting_default);
        showExactEnergyInHUD = config.get(sectionGui.name, "Exact energy amounts in Energy HUD", showExactEnergyInHUD_default, "When enabled, the energy HUD will display the exact amount of RF other than just a percentage.").getBoolean(showExactEnergyInHUD_default);
    }

}
