package tonius.simplyjetpacks.setup;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.integration.TERecipes;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.item.MetaItem;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.recipes.JetpackUpgradingRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJItems {

    public static ItemJetpack jetpacks = null;
    public static ItemMeta components = null;
    public static ItemMeta particleCustomizers = null;

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
        componentsMap.put(0, new MetaItem("leatherStrap", leatherStrapTooltips, EnumRarity.common));

        String[] thrusterTooltips = new String[2];
        thrusterTooltips[0] = "tooltip.thruster.description.1";
        thrusterTooltips[1] = "tooltip.thruster.description.2";
        componentsMap.put(11, new MetaItem("thruster.1", thrusterTooltips, EnumRarity.common));
        componentsMap.put(12, new MetaItem("thruster.2", thrusterTooltips, EnumRarity.common));
        componentsMap.put(13, new MetaItem("thruster.3", thrusterTooltips, EnumRarity.uncommon));
        componentsMap.put(14, new MetaItem("thruster.4", thrusterTooltips, EnumRarity.rare));

        String[] armorPlatingTooltips = new String[2];
        armorPlatingTooltips[0] = "tooltip.armorPlating.description.1";
        armorPlatingTooltips[1] = "tooltip.armorPlating.description.2";
        componentsMap.put(121, new MetaItem("armorPlating.1", armorPlatingTooltips, EnumRarity.common));
        componentsMap.put(122, new MetaItem("armorPlating.2", armorPlatingTooltips, EnumRarity.common));
        componentsMap.put(123, new MetaItem("armorPlating.3", armorPlatingTooltips, EnumRarity.common));
        componentsMap.put(124, new MetaItem("armorPlating.4", armorPlatingTooltips, EnumRarity.rare));

        components = new ItemMeta(componentsMap, "components");

        Map<Integer, MetaItem> particlesMap = new HashMap<Integer, MetaItem>();
        String[] particlesTooltips = new String[2];
        particlesTooltips[0] = "tooltip.particleCustomizers.description.1";
        particlesTooltips[1] = "tooltip.particleCustomizers.description.2";
        particlesMap.put(0, new MetaItem("particle.0", particlesTooltips, EnumRarity.common));
        particlesMap.put(1, new MetaItem("particle.1", particlesTooltips, EnumRarity.common));
        particlesMap.put(2, new MetaItem("particle.2", particlesTooltips, EnumRarity.common));
        particlesMap.put(3, new MetaItem("particle.3", particlesTooltips, EnumRarity.common));
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

        if (teAvailable) {
            if (SJConfig.enableCraftingPotatoJetpack) {
                GameRegistry.addRecipe(new ShapedOreRecipe(jetpacks.getChargedItem(jetpacks, 0).copy(), new Object[] { "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetTin", 'P', TEItems.capacitorPotato.copy(), 'R', "dustRedstone" }));
            }

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 0), new Object[] { "LIL", "LIL", 'L', Items.leather, 'I', "ingotIron" }));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 11), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotLead", 'P', Blocks.glass, 'C', TEItems.powerCoilGold.copy(), 'G', "gearCopper", 'D', TEItems.dynamoSteam.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 12), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotInvar", 'P', Blocks.redstone_block, 'C', TEItems.powerCoilGold.copy(), 'G', "gearBronze", 'D', TEItems.dynamoReactant.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 13), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotElectrum", 'P', "ingotSignalum", 'C', TEItems.powerCoilGold.copy(), 'G', "gearInvar", 'D', TEItems.dynamoMagmatic.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 14), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotEnderium", 'P', "ingotLumium", 'C', TEItems.powerCoilGold.copy(), 'G', "gearElectrum", 'D', TEItems.dynamoEnervation.copy(), 'S', TEItems.pneumaticServo.copy() }));

            if (SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(components, 1, 121), new Object[] { "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin" }));
            }

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpacks, 1, 1), new Object[] { "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic.copy(), 'T', new ItemStack(components, 1, 11), 'J', new ItemStack(components, 1, 0) }));
            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpacks, 1, 2), new Object[] { "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened.copy(), 'T', new ItemStack(components, 1, 12), 'J', new ItemStack(jetpacks, 1, 1) }));
            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpacks, 1, 3), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced.copy(), 'T', new ItemStack(components, 1, 13), 'J', new ItemStack(jetpacks, 1, 2) }));
            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpacks, 1, 4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant.copy(), 'T', new ItemStack(components, 1, 14), 'J', new ItemStack(jetpacks, 1, 3) }));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(particleCustomizers, 1, 0), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', Blocks.torch }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(particleCustomizers, 1, 1), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', Blocks.glass }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(particleCustomizers, 1, 2), new Object[] { " C ", "CCC", " C ", 'C', "dustCoal" }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(particleCustomizers, 1, 3), new Object[] { " R ", " C ", "G B", 'C', "dustCoal", 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue" }));
        }
        for (int i = 0; i <= Jetpack.getHighestMeta(); i++) {
            Jetpack jetpack = Jetpack.getJetpack(i);
            if (jetpack != null) {
                GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpacks, 1, i), new Object[] { "J", "P", 'J', new ItemStack(jetpacks, 1, i), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
            }
        }
    }

    private static void doIMC() {
        SimplyJetpacks.logger.info("Doing intermod communication");

        if (teAvailable) {
            if (SJConfig.enableCraftingArmorPlating) {
                ItemStack ingotBronze;
                for (int i = 0; i < OreDictionary.getOres("ingotBronze").size(); i++) {
                    ingotBronze = OreDictionary.getOres("ingotBronze").get(i).copy();
                    ingotBronze.stackSize = 10;
                    TERecipes.addSmelterRecipe(3200, new ItemStack(components, 1, 121), ingotBronze, new ItemStack(components, 1, 122), null, 0);
                }

                ItemStack ingotInvar;
                for (int i = 0; i < OreDictionary.getOres("ingotInvar").size(); i++) {
                    ingotInvar = OreDictionary.getOres("ingotInvar").get(i).copy();
                    ingotInvar.stackSize = 10;
                    TERecipes.addSmelterRecipe(4800, new ItemStack(components, 1, 122), ingotInvar, new ItemStack(components, 1, 123), null, 0);
                }

                ItemStack ingotEnderium;
                for (int i = 0; i < OreDictionary.getOres("ingotEnderium").size(); i++) {
                    ingotEnderium = OreDictionary.getOres("ingotEnderium").get(i).copy();
                    ingotEnderium.stackSize = 10;
                    TERecipes.addSmelterRecipe(6400, new ItemStack(components, 1, 123), ingotEnderium, new ItemStack(components, 1, 124), null, 0);
                }
            }
        }
    }

}
