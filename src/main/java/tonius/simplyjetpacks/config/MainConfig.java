package tonius.simplyjetpacks.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MainConfig {

    // item config
    public static boolean enableJetpackParticles;
    public static boolean invertHoverSneakingBehavior;

    // crafting
    public static boolean enableCraftingArmorPlating;
    public static boolean enableCraftingJetpackTier0;
    public static boolean upgradingRecipesOnly;

    // gui
    public static boolean enableEnergyHUD;
    public static boolean hideJetpackTier0Warning;
    public static boolean holdShiftForDetails;
    public static boolean showExactEnergyInHUD;

    public static void loadConfig(Configuration cfg) {
        try {
            cfg.load();

            // item config
            enableJetpackParticles = cfg.get("itemconfig", "Enable jetpack particles", true).getBoolean(true);
            invertHoverSneakingBehavior = cfg.get("itemconfig", "Invert Hover Mode sneaking behavior", false).getBoolean(false);

            // crafting
            enableCraftingArmorPlating = cfg.get("crafting", "Armor Plating", true).getBoolean(true);
            enableCraftingJetpackTier0 = cfg.get("crafting", "Potato Jetpack", true).getBoolean(true);

            Property upgradingRecipesOnlyProp = cfg.get("crafting", "Upgrading Recipes Only", false);
            upgradingRecipesOnlyProp.comment = "When enabled, most jetpacks are not directly craftable, but only by upgrading previous tiers.";
            upgradingRecipesOnly = upgradingRecipesOnlyProp.getBoolean(false);

            // gui
            enableEnergyHUD = cfg.get("gui", "Enable energy HUD", true).getBoolean(true);
            hideJetpackTier0Warning = cfg.get("gui", "Hide Tuberous Jetpack warning", false).getBoolean(false);
            holdShiftForDetails = cfg.get("gui", "Hold Shift for details", true).getBoolean(true);
            showExactEnergyInHUD = cfg.get("gui", "Show exact amount of RF in energy HUD", false).getBoolean(false);
        } finally {
            if (cfg.hasChanged())
                cfg.save();
        }
    }
}
