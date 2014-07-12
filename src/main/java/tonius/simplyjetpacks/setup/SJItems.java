package tonius.simplyjetpacks.setup;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.MainConfig;
import tonius.simplyjetpacks.config.TuningConfig;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.integration.TERecipes;
import tonius.simplyjetpacks.item.ItemComponents;
import tonius.simplyjetpacks.item.ItemParticleCustomizers;
import tonius.simplyjetpacks.item.ItemSJ;
import tonius.simplyjetpacks.item.jetpack.ItemArmoredJetpack;
import tonius.simplyjetpacks.item.jetpack.ItemJetpack;
import tonius.simplyjetpacks.item.jetpack.ItemJetpackNew;
import tonius.simplyjetpacks.item.jetpack.ItemPotatoJetpack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.recipes.JetpackUpgradingRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJItems {

    public static ArmorMaterial enumArmorJetpack = ArmorMaterial.IRON;

    public static ItemJetpack[] jetpacks = null;
    public static ItemJetpack[] armoredJetpacks = null;

    public static ItemJetpack jetpackTier0 = null;
    public static ItemJetpack jetpackTier1 = null;
    public static ItemJetpack jetpackArmoredTier1 = null;
    public static ItemJetpack jetpackTier2 = null;
    public static ItemJetpack jetpackArmoredTier2 = null;
    public static ItemJetpack jetpackTier3 = null;
    public static ItemJetpack jetpackArmoredTier3 = null;
    public static ItemJetpack jetpackTier4 = null;
    public static ItemJetpack jetpackArmoredTier4 = null;
    public static ItemJetpack jetpackCreative = null;
    public static ItemSJ components = null;
    public static ItemSJ particleCustomizers = null;

    public static Item newJetpacks = null;

    private static boolean teAvailable = false;

    public static void preInit() {
        constructItems();
        registerItems();
    }

    public static void init() {
        teAvailable = TEItems.init();
        registerRecipes();
        doIMC();
    }

    private static void constructItems() {
        SimplyJetpacks.logger.info("Constructing items");

        jetpackTier0 = new ItemPotatoJetpack(enumArmorJetpack, "jetpackTier0", TuningConfig.jetpackTier0_maxEnergy, 0, 0, TuningConfig.jetpackTier0_energyUsage, TuningConfig.jetpackTier0_maxSpeed, TuningConfig.jetpackTier0_acceleration, 0, 0.25, 0.25);
        jetpackTier1 = new ItemJetpack(enumArmorJetpack, "jetpackTier1", TuningConfig.jetpackTier1_maxEnergy, TuningConfig.jetpackTier1_maxChargingRate, 1, TuningConfig.jetpackTier1_energyUsage, TuningConfig.jetpackTier1_maxSpeed, TuningConfig.jetpackTier1_acceleration, TuningConfig.jetpackTier1_forwardThrust, TuningConfig.jetpackTier1_hoverModeIdleSpeed, TuningConfig.jetpackTier1_hoverModeSneakingSpeed);
        jetpackArmoredTier1 = new ItemArmoredJetpack(enumArmorJetpack, "jetpackArmoredTier1", TuningConfig.jetpackTier1_maxEnergy, TuningConfig.jetpackTier1_maxChargingRate, 1, TuningConfig.jetpackTier1_energyUsage, TuningConfig.jetpackTier1_maxSpeed, TuningConfig.jetpackTier1_acceleration, TuningConfig.jetpackTier1_forwardThrust, TuningConfig.jetpackTier1_hoverModeIdleSpeed, TuningConfig.jetpackTier1_hoverModeSneakingSpeed, TuningConfig.jetpackArmoredTier1_armorDisplay, TuningConfig.jetpackArmoredTier1_armorAbsorption, TuningConfig.jetpackArmoredTier1_energyPerDamage);
        jetpackTier2 = new ItemJetpack(enumArmorJetpack, "jetpackTier2", TuningConfig.jetpackTier2_maxEnergy, TuningConfig.jetpackTier2_maxChargingRate, 2, TuningConfig.jetpackTier2_energyUsage, TuningConfig.jetpackTier2_maxSpeed, TuningConfig.jetpackTier2_acceleration, TuningConfig.jetpackTier2_forwardThrust, TuningConfig.jetpackTier2_hoverModeIdleSpeed, TuningConfig.jetpackTier2_hoverModeSneakingSpeed);
        jetpackArmoredTier2 = new ItemArmoredJetpack(enumArmorJetpack, "jetpackArmoredTier2", TuningConfig.jetpackTier2_maxEnergy, TuningConfig.jetpackTier2_maxChargingRate, 2, TuningConfig.jetpackTier2_energyUsage, TuningConfig.jetpackTier2_maxSpeed, TuningConfig.jetpackTier2_acceleration, TuningConfig.jetpackTier2_forwardThrust, TuningConfig.jetpackTier2_hoverModeIdleSpeed, TuningConfig.jetpackTier2_hoverModeSneakingSpeed, TuningConfig.jetpackArmoredTier2_armorDisplay, TuningConfig.jetpackArmoredTier2_armorAbsorption, TuningConfig.jetpackArmoredTier2_energyPerDamage);
        jetpackTier3 = new ItemJetpack(enumArmorJetpack, "jetpackTier3", TuningConfig.jetpackTier3_maxEnergy, TuningConfig.jetpackTier3_maxChargingRate, 3, TuningConfig.jetpackTier3_energyUsage, TuningConfig.jetpackTier3_maxSpeed, TuningConfig.jetpackTier3_acceleration, TuningConfig.jetpackTier3_forwardThrust, TuningConfig.jetpackTier3_hoverModeIdleSpeed, TuningConfig.jetpackTier3_hoverModeSneakingSpeed);
        jetpackArmoredTier3 = new ItemArmoredJetpack(enumArmorJetpack, "jetpackArmoredTier3", TuningConfig.jetpackTier3_maxEnergy, TuningConfig.jetpackTier3_maxChargingRate, 3, TuningConfig.jetpackTier3_energyUsage, TuningConfig.jetpackTier3_maxSpeed, TuningConfig.jetpackTier3_acceleration, TuningConfig.jetpackTier3_forwardThrust, TuningConfig.jetpackTier3_hoverModeIdleSpeed, TuningConfig.jetpackTier3_hoverModeSneakingSpeed, TuningConfig.jetpackArmoredTier3_armorDisplay, TuningConfig.jetpackArmoredTier3_armorAbsorption, TuningConfig.jetpackArmoredTier3_energyPerDamage);
        jetpackTier4 = new ItemJetpack(enumArmorJetpack, "jetpackTier4", TuningConfig.jetpackTier4_maxEnergy, TuningConfig.jetpackTier4_maxChargingRate, 4, TuningConfig.jetpackTier4_energyUsage, TuningConfig.jetpackTier4_maxSpeed, TuningConfig.jetpackTier4_acceleration, TuningConfig.jetpackTier4_forwardThrust, TuningConfig.jetpackTier4_hoverModeIdleSpeed, TuningConfig.jetpackTier4_hoverModeSneakingSpeed);
        jetpackArmoredTier4 = new ItemArmoredJetpack(enumArmorJetpack, "jetpackArmoredTier4", TuningConfig.jetpackTier4_maxEnergy, TuningConfig.jetpackTier4_maxChargingRate, 4, TuningConfig.jetpackTier4_energyUsage, TuningConfig.jetpackTier4_maxSpeed, TuningConfig.jetpackTier4_acceleration, TuningConfig.jetpackTier4_forwardThrust, TuningConfig.jetpackTier4_hoverModeIdleSpeed, TuningConfig.jetpackTier4_hoverModeSneakingSpeed, TuningConfig.jetpackArmoredTier4_armorDisplay, TuningConfig.jetpackArmoredTier4_armorAbsorption, TuningConfig.jetpackArmoredTier4_energyPerDamage);
        jetpackCreative = new ItemArmoredJetpack(enumArmorJetpack, "jetpackCreative", 9001, 0, 9001, 0, TuningConfig.jetpackCreative_maxSpeed, TuningConfig.jetpackCreative_acceleration, TuningConfig.jetpackCreative_forwardThrust, TuningConfig.jetpackCreative_hoverModeIdleSpeed, TuningConfig.jetpackCreative_hoverModeSneakingSpeed, TuningConfig.jetpackCreative_armorDisplay, TuningConfig.jetpackCreative_armorAbsorption, TuningConfig.jetpackCreative_energyPerDamage);

        jetpacks = new ItemJetpack[] { jetpackTier0, jetpackTier1, jetpackTier2, jetpackTier3, jetpackTier4 };
        armoredJetpacks = new ItemJetpack[] { null, jetpackArmoredTier1, jetpackArmoredTier2, jetpackArmoredTier3, jetpackArmoredTier4 };

        components = new ItemComponents();
        particleCustomizers = new ItemParticleCustomizers();

        Map<Integer, Jetpack> newJetpacksItems = new HashMap<Integer, Jetpack>();
        newJetpacksItems.put(0, new Jetpack(0, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacksItems.put(1, new Jetpack(1, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacksItems.put(2, new Jetpack(2, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacksItems.put(3, new Jetpack(3, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacksItems.put(4, new Jetpack(4, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacksItems.put(101, new Jetpack(1, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacksItems.put(102, new Jetpack(2, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacksItems.put(103, new Jetpack(3, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacksItems.put(104, new Jetpack(4, 0, 0, 0, 0, 0, 0, 0, 0));
        newJetpacks = new ItemJetpackNew(newJetpacksItems);
    }

    private static void registerItems() {
        SimplyJetpacks.logger.info("Registering items");

        GameRegistry.registerItem(jetpackTier0, "jetpackTier0");
        GameRegistry.registerItem(jetpackTier1, "jetpackTier1");
        GameRegistry.registerItem(jetpackArmoredTier1, "jetpackArmoredTier1");
        GameRegistry.registerItem(jetpackTier2, "jetpackTier2");
        GameRegistry.registerItem(jetpackArmoredTier2, "jetpackArmoredTier2");
        GameRegistry.registerItem(jetpackTier3, "jetpackTier3");
        GameRegistry.registerItem(jetpackArmoredTier3, "jetpackArmoredTier3");
        GameRegistry.registerItem(jetpackTier4, "jetpackTier4");
        GameRegistry.registerItem(jetpackArmoredTier4, "jetpackArmoredTier4");
        GameRegistry.registerItem(jetpackCreative, "jetpackCreative");

        GameRegistry.registerItem(components, "components");
        GameRegistry.registerItem(particleCustomizers, "particleCustomizers");

        GameRegistry.registerItem(newJetpacks, "newJetpacks");
    }

    private static void registerRecipes() {
        SimplyJetpacks.logger.info("Registering recipes");

        if (teAvailable) {
            if (MainConfig.enableCraftingJetpackTier0) {
                GameRegistry.addRecipe(new ShapedOreRecipe(jetpackTier0.getChargedItem(jetpackTier0).copy(), new Object[] { "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetTin", 'P', TEItems.capacitorPotato, 'R', "dustRedstone" }));
            }

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 0), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotLead", 'P', TEItems.conduitItemOpaque, 'C', TEItems.powerCoilGold, 'G', "gearCopper", 'D', TEItems.dynamoSteam, 'S', TEItems.pneumaticServo }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 1), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotInvar", 'P', TEItems.conduitItemFastOpaque, 'C', TEItems.powerCoilGold, 'G', "gearBronze", 'D', TEItems.dynamoCompression, 'S', TEItems.pneumaticServo }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 2), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotElectrum", 'P', TEItems.conduitEnergyReinforcedEmpty, 'C', TEItems.powerCoilGold, 'G', "gearInvar", 'D', TEItems.dynamoReactant, 'S', TEItems.pneumaticServo }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 3), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotEnderium", 'P', TEItems.conduitEnergyReinforced, 'C', TEItems.powerCoilGold, 'G', "gearElectrum", 'D', TEItems.dynamoMagmatic, 'S', TEItems.pneumaticServo }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 4), new Object[] { "LIL", "LIL", 'L', Items.leather, 'I', "ingotIron" }));

            if (MainConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 5), new Object[] { "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin" }));
            }

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier1), new Object[] { "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic, 'T', new ItemStack(components, 1, 0), 'J', new ItemStack(components, 1, 4) }));
            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier2), new Object[] { "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened, 'T', new ItemStack(components, 1, 1), 'J', new ItemStack(jetpackTier1, 1, OreDictionary.WILDCARD_VALUE) }));
            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', new ItemStack(components, 1, 2), 'J', new ItemStack(jetpackTier2, 1, OreDictionary.WILDCARD_VALUE) }));
            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', new ItemStack(components, 1, 3), 'J', new ItemStack(jetpackTier3, 1, OreDictionary.WILDCARD_VALUE) }));

            if (!MainConfig.upgradingRecipesOnly) {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier2), new Object[] { "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened, 'T', new ItemStack(components, 1, 1), 'J', new ItemStack(components, 1, 4) }));
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', new ItemStack(components, 1, 2), 'J', new ItemStack(components, 1, 4) }));
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', new ItemStack(components, 1, 3), 'J', new ItemStack(components, 1, 4) }));

                GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', new ItemStack(components, 1, 2), 'J', new ItemStack(jetpackTier1, 1, OreDictionary.WILDCARD_VALUE) }));
                GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', new ItemStack(components, 1, 3), 'J', new ItemStack(jetpackTier1, 1, OreDictionary.WILDCARD_VALUE) }));
                GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', new ItemStack(components, 1, 3), 'J', new ItemStack(jetpackTier2, 1, OreDictionary.WILDCARD_VALUE) }));
            }

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(particleCustomizers, 1, 0), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', Blocks.torch }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(particleCustomizers, 1, 1), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', Blocks.glass }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(particleCustomizers, 1, 2), new Object[] { " C ", "CCC", " C ", 'C', "dustCoal" }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(particleCustomizers, 1, 3), new Object[] { " R ", " C ", "G B", 'C', "dustCoal", 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue" }));
        }
        for (ItemJetpack jetpack : jetpacks) {
            if (jetpack != null) {
                GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpack), new Object[] { "J", "P", 'J', new ItemStack(jetpack, 1, OreDictionary.WILDCARD_VALUE), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
            }
        }
        for (ItemJetpack jetpack : armoredJetpacks) {
            if (jetpack != null) {
                GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpack), new Object[] { "J", "P", 'J', new ItemStack(jetpack, 1, OreDictionary.WILDCARD_VALUE), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
            }
        }
        GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackCreative), new Object[] { "J", "P", 'J', new ItemStack(jetpackCreative, 1, OreDictionary.WILDCARD_VALUE), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
    }

    private static void doIMC() {
        SimplyJetpacks.logger.info("Doing intermod communication");

        if (MainConfig.enableCraftingArmorPlating) {
            ItemStack ingotBronze;
            for (int i = 0; i < OreDictionary.getOres("ingotBronze").size(); i++) {
                ingotBronze = OreDictionary.getOres("ingotBronze").get(i).copy();
                ingotBronze.stackSize = 10;
                TERecipes.addSmelterRecipe(3200, new ItemStack(components, 1, 5), ingotBronze, new ItemStack(components, 1, 6), null, 0);
            }

            ItemStack ingotInvar;
            for (int i = 0; i < OreDictionary.getOres("ingotInvar").size(); i++) {
                ingotInvar = OreDictionary.getOres("ingotInvar").get(i).copy();
                ingotInvar.stackSize = 10;
                TERecipes.addSmelterRecipe(4800, new ItemStack(components, 1, 6), ingotInvar, new ItemStack(components, 1, 7), null, 0);
            }

            ItemStack ingotEnderium;
            for (int i = 0; i < OreDictionary.getOres("ingotEnderium").size(); i++) {
                ingotEnderium = OreDictionary.getOres("ingotEnderium").get(i).copy();
                ingotEnderium.stackSize = 10;
                TERecipes.addSmelterRecipe(6400, new ItemStack(components, 1, 7), ingotEnderium, new ItemStack(components, 1, 8), null, 0);
            }
        }
    }

}
