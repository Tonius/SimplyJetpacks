package tonius.simplyjetpacks.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
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
    public static final boolean holdShiftForDetails_default = true;
    public static final boolean showExactEnergyInHUD_default = false;

    // item
    public static boolean enableJetpackParticles = enableJetpackParticles_default;
    public static boolean invertHoverSneakingBehavior = invertHoverSneakingBehavior_default;

    // crafting
    public static boolean enableCraftingArmorPlating = enableCraftingArmorPlating_default;
    public static boolean enableCraftingPotatoJetpack = enableCraftingPotatoJetpack_default;

    // gui
    public static boolean enableEnergyHUD = enableEnergyHUD_default;
    public static boolean holdShiftForDetails = holdShiftForDetails_default;
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
        enableJetpackParticles = config.get(sectionItem.name, "enableJetpackParticles", enableJetpackParticles_default, "When enabled, jetpacks will emit particles when active.").getBoolean(enableJetpackParticles_default);
        invertHoverSneakingBehavior = config.get(sectionItem.name, "invertHoverSneakingBehavior", invertHoverSneakingBehavior_default, "Invert Hover Mode sneaking behavior").getBoolean(invertHoverSneakingBehavior_default);

        enableCraftingArmorPlating = config.get(sectionCrafting.name, "enableCraftingArmorPlating", enableCraftingArmorPlating_default, "When enabled, Armor Plating items will be craftable, and thus armored jetpacks are available. (Requires restart)").getBoolean(enableCraftingArmorPlating_default);
        enableCraftingPotatoJetpack = config.get(sectionCrafting.name, "enableCraftingPotatoJetpack", enableCraftingPotatoJetpack_default, "When enabled, the Potato Jetpack will be craftable. (Requires restart)").getBoolean(enableCraftingPotatoJetpack_default);

        enableEnergyHUD = config.get(sectionGui.name, "enableEnergyHUD", enableEnergyHUD_default, "When enabled, a HUD that displays your current jetpack's energy level will show.").getBoolean(enableEnergyHUD_default);
        holdShiftForDetails = config.get(sectionGui.name, "holdShiftForDetails", holdShiftForDetails_default, "When enabled, item details are only shown in the tooltip when holding Shift.").getBoolean(holdShiftForDetails_default);
        showExactEnergyInHUD = config.get(sectionGui.name, "showExactEnergyInHUD", showExactEnergyInHUD_default, "When enabled, the energy HUD will display the exact amount of RF other than just a percentage.").getBoolean(showExactEnergyInHUD_default);
    }

}
