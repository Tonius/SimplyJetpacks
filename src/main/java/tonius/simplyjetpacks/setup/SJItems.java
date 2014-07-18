package tonius.simplyjetpacks.setup;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.item.MetaItem;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.recipes.JetpackUpgradingRecipe;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJItems {

    public static Item jetpacks = null;
    public static Item components = null;
    public static Item particleCustomizers = null;

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

        Jetpack.reconstructJetpacks();
        jetpacks = new ItemJetpack();

        Map<Integer, MetaItem> componentsMap = new HashMap<Integer, MetaItem>();
        String[] leatherStrapTooltips = new String[2];
        leatherStrapTooltips[0] = "tooltip.leatherStrap.description.1";
        leatherStrapTooltips[1] = "tooltip.leatherStrap.description.2";
        componentsMap.put(0, new MetaItem("leatherStrap", null, leatherStrapTooltips));
        String[] thrusterTooltips = new String[2];
        thrusterTooltips[0] = "tooltip.thruster.description.1";
        thrusterTooltips[1] = "tooltip.thruster.description.2";
        componentsMap.put(11, new MetaItem("thruster.te.1", null, thrusterTooltips));
        componentsMap.put(12, new MetaItem("thruster.te.2", null, thrusterTooltips));
        componentsMap.put(13, new MetaItem("thruster.te.3", StringUtils.YELLOW, thrusterTooltips));
        componentsMap.put(14, new MetaItem("thruster.te.4", StringUtils.BRIGHT_BLUE, thrusterTooltips));
        String[] armorPlatingTooltips = new String[2];
        armorPlatingTooltips[0] = "tooltip.armorPlating.description.1";
        armorPlatingTooltips[1] = "tooltip.armorPlating.description.2";
        componentsMap.put(21, new MetaItem("armorPlating.1", null, armorPlatingTooltips));
        componentsMap.put(22, new MetaItem("armorPlating.2", null, armorPlatingTooltips));
        componentsMap.put(23, new MetaItem("armorPlating.3", null, armorPlatingTooltips));
        componentsMap.put(24, new MetaItem("armorPlating.4", StringUtils.BRIGHT_BLUE, armorPlatingTooltips));
        components = new ItemMeta(componentsMap, "components");

        Map<Integer, MetaItem> particlesMap = new HashMap<Integer, MetaItem>();
        String[] particlesTooltips = new String[2];
        particlesTooltips[0] = "tooltip.particleCustomizers.description.1";
        particlesTooltips[1] = "tooltip.particleCustomizers.description.2";
        particlesMap.put(0, new MetaItem("particle.0", null, particlesTooltips));
        particlesMap.put(1, new MetaItem("particle.1", null, particlesTooltips));
        particlesMap.put(2, new MetaItem("particle.2", null, particlesTooltips));
        particlesMap.put(3, new MetaItem("particle.3", null, particlesTooltips));
        particleCustomizers = new ItemMeta(particlesMap, "particleCustomizers");
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

        // wait for TE
        // if (MainConfig.enableCraftingArmorPlating) {
        // ItemStack ingotBronze;
        // for (int i = 0; i < OreDictionary.getOres("ingotBronze").size(); i++)
        // {
        // ingotBronze = OreDictionary.getOres("ingotBronze").get(i).copy();
        // ingotBronze.stackSize = 10;
        // TERecipes.addSmelterRecipe(3200, new ItemStack(components, 1, 5),
        // ingotBronze, new ItemStack(components, 1, 6), null, 0);
        // }
        //
        // ItemStack ingotInvar;
        // for (int i = 0; i < OreDictionary.getOres("ingotInvar").size(); i++)
        // {
        // ingotInvar = OreDictionary.getOres("ingotInvar").get(i).copy();
        // ingotInvar.stackSize = 10;
        // TERecipes.addSmelterRecipe(4800, new ItemStack(components, 1, 6),
        // ingotInvar, new ItemStack(components, 1, 7), null, 0);
        // }
        //
        // ItemStack ingotEnderium;
        // for (int i = 0; i < OreDictionary.getOres("ingotEnderium").size();
        // i++) {
        // ingotEnderium = OreDictionary.getOres("ingotEnderium").get(i).copy();
        // ingotEnderium.stackSize = 10;
        // TERecipes.addSmelterRecipe(6400, new ItemStack(components, 1, 7),
        // ingotEnderium, new ItemStack(components, 1, 8), null, 0);
        // }
        // }
    }

}
