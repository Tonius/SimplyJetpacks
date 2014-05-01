package tonius.simplyjetpacks;

import static net.minecraftforge.oredict.RecipeSorter.Category.SHAPED;

import java.io.File;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemSJJetpack;
import tonius.simplyjetpacks.item.ItemSJJetpackPotato;
import tonius.simplyjetpacks.item.ItemSJSimpleMeta;
import tonius.simplyjetpacks.recipes.RecipeUpgradeHardenedJetpack;
import tonius.simplyjetpacks.recipes.RecipeUpgradeLeadstoneJetpack;
import tonius.simplyjetpacks.recipes.RecipeUpgradeRedstoneJetpack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "simplyjetpacks", name = "SimplyJetpacks", dependencies = "required-after:CoFHCore;required-after:ThermalExpansion")
@NetworkMod(channels = { "SmpJet" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class SimplyJetpacks {

    @Instance("simplyjetpacks")
    public static SimplyJetpacks instance;

    @SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
    public static CommonProxy proxy;

    public static Configuration config;
    public static Logger logger;
    public static KeyboardTracker keyboard;

    public static CreativeTabs creativeTab = new CreativeTabs("tabSimplyJetpacks") {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(SimplyJetpacks.jetpackTier4, 1, 0);
        }
    };

    public static EnumArmorMaterial enumArmorJetpack = EnumHelper.addArmorMaterial("enumArmorJetpack", 15, new int[] { 0, 0, 0, 0 }, 0);

    public static Item jetpackTier0 = null;
    public static Item jetpackTier1 = null;
    public static Item jetpackTier2 = null;
    public static Item jetpackTier3 = null;
    public static Item jetpackTier4 = null;
    public static Item metaItem1 = null;
    public static final String[] METAITEM1_NAMES = new String[] { "Leadstone Thruster", "Hardened Thruster", EnumChatFormatting.YELLOW + "Redstone Thruster", EnumChatFormatting.AQUA + "Resonant Thruster" };

    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        logger.info("Starting Simply Jetpacks");

        logger.info("Loading configuration file");
        config = new Configuration(new File(evt.getModConfigurationDirectory(), "simplyjetpacks.cfg"));
        ConfigReader.loadConfig(config);

        logger.info("Registering items");

        jetpackTier0 = new ItemSJJetpackPotato(ConfigReader.jetpackTier0ID, enumArmorJetpack);
        jetpackTier1 = new ItemSJJetpack(ConfigReader.jetpackTier1ID, enumArmorJetpack, "jetpackTier1", ConfigReader.jetpackTier1_maxEnergy, ConfigReader.jetpackTier1_maxChargingRate, ConfigReader.jetpackTier1_energyUsage, ConfigReader.jetpackTier1_maxSpeed, ConfigReader.jetpackTier1_acceleration, ConfigReader.jetpackTier1_forwardThrust, ConfigReader.jetpackTier1_hoverModeIdleSpeed, ConfigReader.jetpackTier1_hoverModeSneakingSpeed);
        jetpackTier2 = new ItemSJJetpack(ConfigReader.jetpackTier2ID, enumArmorJetpack, "jetpackTier2", ConfigReader.jetpackTier2_maxEnergy, ConfigReader.jetpackTier2_maxChargingRate, ConfigReader.jetpackTier2_energyUsage, ConfigReader.jetpackTier2_maxSpeed, ConfigReader.jetpackTier2_acceleration, ConfigReader.jetpackTier2_forwardThrust, ConfigReader.jetpackTier2_hoverModeIdleSpeed, ConfigReader.jetpackTier2_hoverModeSneakingSpeed);
        jetpackTier3 = new ItemSJJetpack(ConfigReader.jetpackTier3ID, enumArmorJetpack, "jetpackTier3", ConfigReader.jetpackTier3_maxEnergy, ConfigReader.jetpackTier3_maxChargingRate, ConfigReader.jetpackTier3_energyUsage, ConfigReader.jetpackTier3_maxSpeed, ConfigReader.jetpackTier3_acceleration, ConfigReader.jetpackTier3_forwardThrust, ConfigReader.jetpackTier3_hoverModeIdleSpeed, ConfigReader.jetpackTier3_hoverModeSneakingSpeed);
        jetpackTier4 = new ItemSJJetpack(ConfigReader.jetpackTier4ID, enumArmorJetpack, "jetpackTier4", ConfigReader.jetpackTier4_maxEnergy, ConfigReader.jetpackTier4_maxChargingRate, ConfigReader.jetpackTier4_energyUsage, ConfigReader.jetpackTier4_maxSpeed, ConfigReader.jetpackTier4_acceleration, ConfigReader.jetpackTier4_forwardThrust, ConfigReader.jetpackTier4_hoverModeIdleSpeed, ConfigReader.jetpackTier4_hoverModeSneakingSpeed);
        metaItem1 = new ItemSJSimpleMeta(ConfigReader.metaItem1ID, "metaItem1", METAITEM1_NAMES);

        logger.info("Registering localized names");

        LanguageRegistry.addName(jetpackTier0, "Tuberous Jetpack");
        LanguageRegistry.addName(jetpackTier1, "Leadstone Jetpack");
        LanguageRegistry.addName(jetpackTier2, "Hardened Jetpack");
        LanguageRegistry.addName(jetpackTier3, EnumChatFormatting.YELLOW + "Redstone Jetpack");
        LanguageRegistry.addName(jetpackTier4, EnumChatFormatting.AQUA + "Resonant Jetpack");
        LanguageRegistry.addName(metaItem1, "Metaitem 1");
        LanguageRegistry.instance().addStringLocalization("itemGroup.tabSimplyJetpacks", "en_US", "Simply Jetpacks");
        LanguageRegistry.instance().addStringLocalization("death.attack.jetpackpotato", "%1$s attempted to fly a potato");
        LanguageRegistry.instance().addStringLocalization("death.attack.jetpackpotato.player", "%1$s attempted to fly a potato");
        LanguageRegistry.instance().addStringLocalization("death.attack.jetpackpotato.item", "%1$s attempted to fly a potato");

        logger.info("Registering handlers");
        proxy.registerHandlers();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent evt) {
        logger.info("Registering recipes");
        
        // Use our recipes before the ShapedOreRecipes we register for NEI 
        RecipeSorter.register("simplyJetpacks:shaped",     RecipeUpgradeLeadstoneJetpack.class,    SHAPED,    "before:forge:shapedore");
        RecipeSorter.register("simplyJetpacks:shaped",     RecipeUpgradeHardenedJetpack.class,    SHAPED,    "before:forge:shapedore");
        RecipeSorter.register("simplyJetpacks:shaped",     RecipeUpgradeRedstoneJetpack.class,    SHAPED,    "before:forge:shapedore");

        ItemStack armorInvarPlate = GameRegistry.findItemStack("ThermalExpansion", "armorInvarPlate", 1);
        ItemStack capacitorBasic = GameRegistry.findItemStack("ThermalExpansion", "capacitorBasic", 1);
        ItemStack capacitorHardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack capacitorPotato = GameRegistry.findItemStack("ThermalExpansion", "capacitorPotato", 1);
        ItemStack capacitorReinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack capacitorResonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        ItemStack conduitEnergyReinforced = GameRegistry.findItemStack("ThermalExpansion", "conduitEnergyReinforced", 1);
        ItemStack conduitEnergyReinforcedEmpty = GameRegistry.findItemStack("ThermalExpansion", "conduitEnergyReinforcedEmpty", 1);
        ItemStack conduitItemFastOpaque = GameRegistry.findItemStack("ThermalExpansion", "conduitItemFastOpaque", 1);
        ItemStack conduitItemOpaque = GameRegistry.findItemStack("ThermalExpansion", "conduitItemOpaque", 1);
        ItemStack dynamoCompression = GameRegistry.findItemStack("ThermalExpansion", "dynamoCompression", 1);
        ItemStack dynamoMagmatic = GameRegistry.findItemStack("ThermalExpansion", "dynamoMagmatic", 1);
        ItemStack dynamoReactant = GameRegistry.findItemStack("ThermalExpansion", "dynamoReactant", 1);
        ItemStack dynamoSteam = GameRegistry.findItemStack("ThermalExpansion", "dynamoSteam", 1);
        ItemStack pneumaticServo = GameRegistry.findItemStack("ThermalExpansion", "pneumaticServo", 1);
        ItemStack powerCoilGold = GameRegistry.findItemStack("ThermalExpansion", "powerCoilGold", 1);

        if (ConfigReader.enableCraftingJetpackTier0) {
            ItemStack fullJetpackPotato = new ItemStack(jetpackTier0, 1, 31);
            ItemSJJetpackPotato fullJetpackPotatoItem = ((ItemSJJetpackPotato) fullJetpackPotato.getItem());
            fullJetpackPotatoItem.addEnergy(fullJetpackPotato, fullJetpackPotatoItem.getMaxEnergyStored(fullJetpackPotato), false);
            GameRegistry.addRecipe(new ShapedOreRecipe(fullJetpackPotato, new Object[] { "S S", "NPN", "R R", 'S', Item.silk, 'N', "nuggetTin", 'P', capacitorPotato, 'R', "dustRedstone" }));
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 0), new Object[] { "PCP", "GBG", "DSD", 'P', conduitItemOpaque, 'C', powerCoilGold, 'B', capacitorBasic, 'G', "gearCopper", 'D', dynamoSteam, 'S', pneumaticServo }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 1), new Object[] { "PCP", "GBG", "DSD", 'P', conduitItemFastOpaque, 'C', powerCoilGold, 'B', capacitorHardened, 'G', "gearBronze", 'D', dynamoCompression, 'S', pneumaticServo }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 2), new Object[] { "PCP", "GBG", "DSD", 'P', conduitEnergyReinforcedEmpty, 'C', powerCoilGold, 'B', capacitorReinforced, 'G', "gearInvar", 'D', dynamoReactant, 'S', pneumaticServo }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 3), new Object[] { "PCP", "GBG", "DSD", 'P', conduitEnergyReinforced, 'C', powerCoilGold, 'B', capacitorResonant, 'G', "gearElectrum", 'D', dynamoMagmatic, 'S', pneumaticServo }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier1, 1, 31), new Object[] { "IBI", "IJI", " T ", 'I', "ingotLead", 'B', capacitorBasic, 'T', new ItemStack(metaItem1, 1, 0), 'J', Item.plateLeather }));
        GameRegistry.addRecipe(new RecipeUpgradeLeadstoneJetpack());
        GameRegistry.addRecipe(new RecipeUpgradeHardenedJetpack());
        GameRegistry.addRecipe(new RecipeUpgradeRedstoneJetpack());
        // add ShapedOreRecipe for NEI
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier2, 1, 31), new Object[] { "IBI", "IJI", " T ", 'I', "ingotInvar", 'B', capacitorHardened, 'T', new ItemStack(metaItem1, 1, 1), 'J', new ItemStack(jetpackTier1, 1, OreDictionary.WILDCARD_VALUE) }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier3, 1, 31), new Object[] { "IBI", "IJI", " T ", 'I', "ingotElectrum", 'B', capacitorReinforced, 'T', new ItemStack(metaItem1, 1, 2), 'J', new ItemStack(jetpackTier2, 1, OreDictionary.WILDCARD_VALUE) }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier4, 1, 31), new Object[] { "IBI", "IJI", " T ", 'I', "ingotEnderium", 'B', capacitorResonant, 'T', new ItemStack(metaItem1, 1, 3), 'J', new ItemStack(jetpackTier3, 1, OreDictionary.WILDCARD_VALUE) }));

        if (!ConfigReader.upgradingRecipesOnly) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier2, 1, 31), new Object[] { "IBI", "IJI", " T ", 'I', "ingotInvar", 'B', capacitorHardened, 'T', new ItemStack(metaItem1, 1, 1), 'J', Item.plateLeather }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier3, 1, 31), new Object[] { "IBI", "IJI", " T ", 'I', "ingotElectrum", 'B', capacitorReinforced, 'T', new ItemStack(metaItem1, 1, 2), 'J', Item.plateIron }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier4, 1, 31), new Object[] { "IBI", "IJI", " T ", 'I', "ingotEnderium", 'B', capacitorResonant, 'T', new ItemStack(metaItem1, 1, 3), 'J', armorInvarPlate }));
        }
    }

}
