package tonius.simplyjetpacks.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.MainConfig;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.integration.TERecipes;
import tonius.simplyjetpacks.item.ItemComponents;
import tonius.simplyjetpacks.item.ItemParticleCustomizers;
import tonius.simplyjetpacks.item.ItemSJ;
import tonius.simplyjetpacks.item.jetpack.ItemJetpack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.item.jetpack.JetpackArmored;
import tonius.simplyjetpacks.item.jetpack.JetpackCreative;
import tonius.simplyjetpacks.item.jetpack.JetpackPotato;
import tonius.simplyjetpacks.recipes.JetpackUpgradingRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJItems {

    public static Item jetpacks = null;
    public static ItemSJ components = null;
    public static ItemSJ particleCustomizers = null;

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

        Jetpack.addJetpack(0, new JetpackPotato(0, 1200, 45, 0.9D, 0.5D));
        Jetpack.addJetpack(1, new Jetpack(1, 25000, 20, 0.22D, 0.16D, 0, 0.2D, 0.18D));
        Jetpack.addJetpack(2, new Jetpack(2, 400000, 75, 0.3D, 0.18D, 0.04F, 0.2D, 0.1D));
        Jetpack.addJetpack(3, new Jetpack(3, 2000000, 150, 0.5D, 0.2D, 0.14F, 0.22D, 0.03D));
        Jetpack.addJetpack(4, new Jetpack(4, 10000000, 400, 0.75D, 0.32D, 0.19F, 0.24D, 0.005D));
        Jetpack.addJetpack(101, new JetpackArmored(1, 25000, 20, 0.22D, 0.16D, 0, 0.18D, 0.2D, 5, 0.2D, 80));
        Jetpack.addJetpack(102, new JetpackArmored(2, 400000, 75, 0.3D, 0.18D, 0.04F, 0.2D, 0.1D, 6, 0.3D, 80));
        Jetpack.addJetpack(103, new JetpackArmored(3, 2000000, 150, 0.5D, 0.2D, 0.14F, 0.22D, 0.03D, 7, 0.4D, 80));
        Jetpack.addJetpack(104, new JetpackArmored(4, 10000000, 400, 0.75D, 0.32D, 0.19F, 0.24D, 0.005D, 8, 0.6D, 80));
        Jetpack.addJetpack(9001, new JetpackCreative(0.75D, 0.32D, 0.19F, 0.25D, 0.0D, 8, 0.6D, 0));
        Jetpack.addJetpack(9002, new JetpackIcon());

        jetpacks = new ItemJetpack();
        components = new ItemComponents();
        particleCustomizers = new ItemParticleCustomizers();
    }

    private static void registerItems() {
        SimplyJetpacks.logger.info("Registering items");

        GameRegistry.registerItem(jetpacks, "jetpacks");
        GameRegistry.registerItem(components, "components");
        GameRegistry.registerItem(particleCustomizers, "particleCustomizers");
    }

    private static void registerRecipes() {
        SimplyJetpacks.logger.info("Registering recipes");

        // wait for TE
        // if (teAvailable) {
        // if (MainConfig.enableCraftingJetpackTier0) {
        // GameRegistry.addRecipe(new
        // ShapedOreRecipe(jetpackTier0.getChargedItem(jetpackTier0).copy(), new
        // Object[] { "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetTin",
        // 'P', TEItems.capacitorPotato, 'R', "dustRedstone" }));
        // }
        //
        // GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components,
        // 1, 0), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotLead", 'P',
        // TEItems.conduitItemOpaque, 'C', TEItems.powerCoilGold, 'G',
        // "gearCopper", 'D', TEItems.dynamoSteam, 'S', TEItems.pneumaticServo
        // }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components,
        // 1, 1), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotInvar", 'P',
        // TEItems.conduitItemFastOpaque, 'C', TEItems.powerCoilGold, 'G',
        // "gearBronze", 'D', TEItems.dynamoCompression, 'S',
        // TEItems.pneumaticServo }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components,
        // 1, 2), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotElectrum", 'P',
        // TEItems.conduitEnergyReinforcedEmpty, 'C', TEItems.powerCoilGold,
        // 'G', "gearInvar", 'D', TEItems.dynamoReactant, 'S',
        // TEItems.pneumaticServo }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components,
        // 1, 3), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotEnderium", 'P',
        // TEItems.conduitEnergyReinforced, 'C', TEItems.powerCoilGold, 'G',
        // "gearElectrum", 'D', TEItems.dynamoMagmatic, 'S',
        // TEItems.pneumaticServo }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components,
        // 1, 4), new Object[] { "LIL", "LIL", 'L', Items.leather, 'I',
        // "ingotIron" }));
        //
        // if (MainConfig.enableCraftingArmorPlating) {
        // GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components,
        // 1, 5), new Object[] { "TIT", "III", "TIT", 'I', "ingotIron", 'T',
        // "ingotTin" }));
        // }
        //
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(jetpackTier1), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotLead", 'B', TEItems.capacitorBasic, 'T', new
        // ItemStack(components, 1, 0), 'J', new ItemStack(components, 1, 4)
        // }));
        // GameRegistry.addRecipe(new JetpackUpgradingRecipe(new
        // ItemStack(jetpackTier2), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotInvar", 'B', TEItems.capacitorHardened, 'T', new
        // ItemStack(components, 1, 1), 'J', new ItemStack(jetpackTier1, 1,
        // OreDictionary.WILDCARD_VALUE) }));
        // GameRegistry.addRecipe(new JetpackUpgradingRecipe(new
        // ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', new
        // ItemStack(components, 1, 2), 'J', new ItemStack(jetpackTier2, 1,
        // OreDictionary.WILDCARD_VALUE) }));
        // GameRegistry.addRecipe(new JetpackUpgradingRecipe(new
        // ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', new
        // ItemStack(components, 1, 3), 'J', new ItemStack(jetpackTier3, 1,
        // OreDictionary.WILDCARD_VALUE) }));
        //
        // if (!MainConfig.upgradingRecipesOnly) {
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(jetpackTier2), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotInvar", 'B', TEItems.capacitorHardened, 'T', new
        // ItemStack(components, 1, 1), 'J', new ItemStack(components, 1, 4)
        // }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', new
        // ItemStack(components, 1, 2), 'J', new ItemStack(components, 1, 4)
        // }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', new
        // ItemStack(components, 1, 3), 'J', new ItemStack(components, 1, 4)
        // }));
        //
        // GameRegistry.addRecipe(new JetpackUpgradingRecipe(new
        // ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', new
        // ItemStack(components, 1, 2), 'J', new ItemStack(jetpackTier1, 1,
        // OreDictionary.WILDCARD_VALUE) }));
        // GameRegistry.addRecipe(new JetpackUpgradingRecipe(new
        // ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', new
        // ItemStack(components, 1, 3), 'J', new ItemStack(jetpackTier1, 1,
        // OreDictionary.WILDCARD_VALUE) }));
        // GameRegistry.addRecipe(new JetpackUpgradingRecipe(new
        // ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I',
        // "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', new
        // ItemStack(components, 1, 3), 'J', new ItemStack(jetpackTier2, 1,
        // OreDictionary.WILDCARD_VALUE) }));
        // }
        //
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(particleCustomizers, 1, 0), new Object[] { " D ", "DCD",
        // " D ", 'C', "dustCoal", 'D', Blocks.torch }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(particleCustomizers, 1, 1), new Object[] { " D ", "DCD",
        // " D ", 'C', "dustCoal", 'D', Blocks.glass }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(particleCustomizers, 1, 2), new Object[] { " C ", "CCC",
        // " C ", 'C', "dustCoal" }));
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(particleCustomizers, 1, 3), new Object[] { " R ", " C ",
        // "G B", 'C', "dustCoal", 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue"
        // }));
        // }
        for (int i = 0; i <= Jetpack.getHighestMeta(); i++) {
            Jetpack jetpack = Jetpack.getJetpack(i);
            if (jetpack != null) {
                GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpacks, 1, i), new Object[] { "J", "P", 'J', new ItemStack(jetpacks, 1, i), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
            }
        }
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
