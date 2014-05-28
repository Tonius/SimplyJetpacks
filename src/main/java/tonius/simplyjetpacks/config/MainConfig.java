package tonius.simplyjetpacks.config;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class MainConfig {
    // item ids
    public static int jetpackTier0ID;
    public static int jetpackTier1ID;
    public static int jetpackTier2ID;
    public static int jetpackTier3ID;
    public static int jetpackTier4ID;
    public static int jetpackArmoredTier1ID;
    public static int jetpackArmoredTier2ID;
    public static int jetpackArmoredTier3ID;
    public static int jetpackArmoredTier4ID;
    public static int jetpackCreativeID;
    public static int componentsID;
    public static int particleCustomizersID;

    // item config
    public static boolean enableJetpackParticles;
    public static boolean mobsUseJetpacks;
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

            // item ids
            jetpackTier0ID = cfg.getItem("jetpackTier0", 18000).getInt() - 256;
            jetpackTier1ID = cfg.getItem("jetpackTier1", 18001).getInt() - 256;
            jetpackTier2ID = cfg.getItem("jetpackTier2", 18002).getInt() - 256;
            jetpackTier3ID = cfg.getItem("jetpackTier3", 18003).getInt() - 256;
            jetpackTier4ID = cfg.getItem("jetpackTier4", 18004).getInt() - 256;
            componentsID = cfg.getItem("components", 18005).getInt() - 256;
            jetpackArmoredTier1ID = cfg.getItem("jetpackArmoredTier1", 18007).getInt() - 256;
            jetpackArmoredTier2ID = cfg.getItem("jetpackArmoredTier2", 18008).getInt() - 256;
            jetpackArmoredTier3ID = cfg.getItem("jetpackArmoredTier3", 18009).getInt() - 256;
            jetpackArmoredTier4ID = cfg.getItem("jetpackArmoredTier4", 18010).getInt() - 256;
            jetpackCreativeID = cfg.getItem("jetpackCreative", 18011).getInt() - 256;
            particleCustomizersID = cfg.getItem("particleCustomizers", 18012).getInt() - 256;

            // item config
            enableJetpackParticles = cfg.get("itemconfig", "enableJetpackParticles", true).getBoolean(true);
            Property mobsUseJetpacksProp = cfg.get("itemconfig", "mobsUseJetpacks", false);
            mobsUseJetpacksProp.comment = "Disabled by default because some mods like Morph cause problems with it, only enable if you know what you're doing";
            mobsUseJetpacks = mobsUseJetpacksProp.getBoolean(false);
            invertHoverSneakingBehavior = cfg.get("itemconfig", "invertHoverSneakingBehavior", false).getBoolean(false);

            // crafting
            enableCraftingArmorPlating = cfg.get("crafting", "enableCraftingArmorPlating", true).getBoolean(true);
            enableCraftingJetpackTier0 = cfg.get("crafting", "enableCraftingJetpackTier0", true).getBoolean(true);
            upgradingRecipesOnly = cfg.get("crafting", "upgradingRecipesOnly", false).getBoolean(false);

            // gui
            enableEnergyHUD = cfg.get("gui", "enableEnergyHUD", true).getBoolean(true);
            hideJetpackTier0Warning = cfg.get("gui", "hideJetpackTier0Warning", false).getBoolean(false);
            holdShiftForDetails = cfg.get("gui", "holdShiftForDetails", true).getBoolean(true);
            showExactEnergyInHUD = cfg.get("gui", "showExactEnergyInHUD", false).getBoolean(false);
        } finally {
            if (cfg.hasChanged())
                cfg.save();
        }
    }
}
