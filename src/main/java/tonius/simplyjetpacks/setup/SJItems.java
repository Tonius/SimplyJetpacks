package tonius.simplyjetpacks.setup;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.integration.RAItems;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.integration.TERecipes;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.item.ItemMeta.MetaItem;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.recipes.SJUpgradingRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJItems {

    public static ItemJetpack jetpacks = null;
    public static ItemFluxPack fluxpacks = null;
    public static ItemMeta components = null;
    public static ItemMeta particleCustomizers = null;

    public static ItemStack jetpackTuberous = null;
    public static ItemStack jetpackLeadstone = null;
    public static ItemStack jetpackLeadstoneArmored = null;
    public static ItemStack jetpackHardened = null;
    public static ItemStack jetpackHardenedArmored = null;
    public static ItemStack jetpackReinforced = null;
    public static ItemStack jetpackReinforcedArmored = null;
    public static ItemStack jetpackResonant = null;
    public static ItemStack jetpackResonantArmored = null;
    public static ItemStack jetpackFluxPlate = null;
    public static ItemStack jetpackCreative = null;

    public static ItemStack fluxpackLeadstone = null;
    public static ItemStack fluxpackHardened = null;
    public static ItemStack fluxpackHardenedArmored = null;
    public static ItemStack fluxpackReinforced = null;
    public static ItemStack fluxpackReinforcedArmored = null;
    public static ItemStack fluxpackResonant = null;
    public static ItemStack fluxpackResonantArmored = null;

    public static ItemStack leatherStrap = null;
    public static ItemStack thrusterLeadstone = null;
    public static ItemStack thrusterHardened = null;
    public static ItemStack thrusterReinforced = null;
    public static ItemStack thrusterResonant = null;
    public static ItemStack thrusterEnergetic = null;
    public static ItemStack armorPlatingIron = null;
    public static ItemStack armorPlatingTinkersAlloy = null;
    public static ItemStack armorPlatingInvar = null;
    public static ItemStack armorPlatingEnderium = null;
    public static ItemStack unitGlowstoneEmpty = null;
    public static ItemStack unitGlowstone = null;
    public static ItemStack unitCryotheumEmpty = null;
    public static ItemStack unitCryotheum = null;

    public static ItemStack particleDefault = null;
    public static ItemStack particleNone = null;
    public static ItemStack particleSmoke = null;
    public static ItemStack particleRainbowSmoke = null;

    private static boolean teAvailable = false;
    private static boolean raAvailable = false;

    public static void preInit() {
        constructItems();
        registerItems();
    }

    public static void init() {
        teAvailable = TEItems.init();
        raAvailable = RAItems.init();
        registerRecipes();
        doIMC();
    }

    private static void constructItems() {
        SimplyJetpacks.logger.info("Constructing items");

        Jetpack.reconstructJetpacks();
        jetpacks = new ItemJetpack();
        FluxPack.reconstructFluxPacks();
        fluxpacks = new ItemFluxPack();

        components = new ItemMeta("components");
        particleCustomizers = new ItemMeta("particleCustomizers");

        jetpackTuberous = jetpacks.getChargedItem(jetpacks, 0);
        jetpackLeadstone = new ItemStack(jetpacks, 1, 1);
        jetpackLeadstoneArmored = new ItemStack(jetpacks, 1, 101);
        jetpackHardened = new ItemStack(jetpacks, 1, 2);
        jetpackHardenedArmored = new ItemStack(jetpacks, 1, 102);
        jetpackReinforced = new ItemStack(jetpacks, 1, 3);
        jetpackReinforcedArmored = new ItemStack(jetpacks, 1, 103);
        jetpackResonant = new ItemStack(jetpacks, 1, 4);
        jetpackResonantArmored = new ItemStack(jetpacks, 1, 104);
        jetpackFluxPlate = new ItemStack(jetpacks, 1, 5);
        jetpackCreative = new ItemStack(jetpacks, 1, 9001);

        fluxpackLeadstone = new ItemStack(fluxpacks, 1, 1);
        fluxpackHardened = new ItemStack(fluxpacks, 1, 2);
        fluxpackHardenedArmored = new ItemStack(fluxpacks, 1, 102);
        fluxpackReinforced = new ItemStack(fluxpacks, 1, 3);
        fluxpackReinforcedArmored = new ItemStack(fluxpacks, 1, 103);
        fluxpackResonant = new ItemStack(fluxpacks, 1, 4);
        fluxpackResonantArmored = new ItemStack(fluxpacks, 1, 104);

        leatherStrap = components.addMetaItem(0, new MetaItem("leatherStrap", null, EnumRarity.common), true, false);

        thrusterLeadstone = components.addMetaItem(11, new MetaItem("thruster.1", null, EnumRarity.common), true, false);
        thrusterHardened = components.addMetaItem(12, new MetaItem("thruster.2", null, EnumRarity.common), true, false);
        thrusterReinforced = components.addMetaItem(13, new MetaItem("thruster.3", null, EnumRarity.uncommon), true, false);
        thrusterResonant = components.addMetaItem(14, new MetaItem("thruster.4", null, EnumRarity.rare), true, false);
        thrusterEnergetic = components.addMetaItem(15, new MetaItem("thruster.5", null, EnumRarity.epic), true, false);

        armorPlatingIron = components.addMetaItem(121, new MetaItem("armorPlating.1", null, EnumRarity.common), true, false);
        armorPlatingTinkersAlloy = components.addMetaItem(122, new MetaItem("armorPlating.2", null, EnumRarity.common), true, false);
        armorPlatingInvar = components.addMetaItem(123, new MetaItem("armorPlating.3", null, EnumRarity.common), true, false);
        armorPlatingEnderium = components.addMetaItem(124, new MetaItem("armorPlating.4", null, EnumRarity.rare), true, false);

        unitGlowstoneEmpty = components.addMetaItem(60, new MetaItem("unitGlowstone.empty", null, EnumRarity.common), true, false);
        unitGlowstone = components.addMetaItem(61, new MetaItem("unitGlowstone", null, EnumRarity.uncommon), true, false);
        unitCryotheumEmpty = components.addMetaItem(62, new MetaItem("unitCryotheum.empty", null, EnumRarity.common), true, false);
        unitCryotheum = components.addMetaItem(63, new MetaItem("unitCryotheum", null, EnumRarity.rare), true, false);

        String[] particlesTooltips = new String[2];
        particlesTooltips[0] = "tooltip.particleCustomizers.description.1";
        particlesTooltips[1] = "tooltip.particleCustomizers.description.2";
        particleDefault = particleCustomizers.addMetaItem(0, new MetaItem("particle.0", particlesTooltips, EnumRarity.common), true, false);
        particleNone = particleCustomizers.addMetaItem(1, new MetaItem("particle.1", particlesTooltips, EnumRarity.common), true, false);
        particleSmoke = particleCustomizers.addMetaItem(2, new MetaItem("particle.2", particlesTooltips, EnumRarity.common), true, false);
        particleRainbowSmoke = particleCustomizers.addMetaItem(3, new MetaItem("particle.3", particlesTooltips, EnumRarity.common), true, false);
    }

    private static void registerItems() {
        SimplyJetpacks.logger.info("Registering items");

        GameRegistry.registerItem(jetpacks, "jetpacks");
        GameRegistry.registerItem(fluxpacks, "fluxpacks");
        GameRegistry.registerItem(components, "components");
        GameRegistry.registerItem(particleCustomizers, "particleCustomizers");

        GameRegistry.registerCustomItemStack("jetpack.0", jetpackTuberous);
        GameRegistry.registerCustomItemStack("jetpack.1", jetpackLeadstone);
        GameRegistry.registerCustomItemStack("jetpack.1.armored", jetpackLeadstoneArmored);
        GameRegistry.registerCustomItemStack("jetpack.2", jetpackHardened);
        GameRegistry.registerCustomItemStack("jetpack.2.armored", jetpackHardenedArmored);
        GameRegistry.registerCustomItemStack("jetpack.3", jetpackReinforced);
        GameRegistry.registerCustomItemStack("jetpack.3.armored", jetpackReinforcedArmored);
        GameRegistry.registerCustomItemStack("jetpack.4", jetpackResonant);
        GameRegistry.registerCustomItemStack("jetpack.4.armored", jetpackResonantArmored);
        GameRegistry.registerCustomItemStack("jetpack.5", jetpackFluxPlate);

        GameRegistry.registerCustomItemStack("fluxpack.1", fluxpackLeadstone);
        GameRegistry.registerCustomItemStack("fluxpack.2", fluxpackHardened);
        GameRegistry.registerCustomItemStack("fluxpack.2.armored", fluxpackHardenedArmored);
        GameRegistry.registerCustomItemStack("fluxpack.3", fluxpackReinforced);
        GameRegistry.registerCustomItemStack("fluxpack.3.armored", fluxpackReinforcedArmored);
        GameRegistry.registerCustomItemStack("fluxpack.4", fluxpackResonant);
        GameRegistry.registerCustomItemStack("fluxpack.4.armored", fluxpackResonantArmored);
    }

    private static void registerRecipes() {
        SimplyJetpacks.logger.info("Registering recipes");

        if (teAvailable) {
            if (SJConfig.enableCraftingPotatoJetpack) {
                GameRegistry.addRecipe(new ShapedOreRecipe(jetpacks.getChargedItem(jetpacks, 0), new Object[] { "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetTin", 'P', TEItems.capacitorPotato.copy(), 'R', "dustRedstone" }));
            }

            GameRegistry.addRecipe(new ShapedOreRecipe(leatherStrap.copy(), new Object[] { "LIL", "LIL", 'L', Items.leather, 'I', "ingotIron" }));

            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterLeadstone.copy(), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotLead", 'P', Blocks.glass, 'C', TEItems.powerCoilGold.copy(), 'G', "gearCopper", 'D', TEItems.dynamoSteam.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterHardened.copy(), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotInvar", 'P', Blocks.redstone_block, 'C', TEItems.powerCoilGold.copy(), 'G', "gearBronze", 'D', TEItems.dynamoReactant.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterReinforced.copy(), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotElectrum", 'P', "ingotSignalum", 'C', TEItems.powerCoilGold.copy(), 'G', "gearInvar", 'D', TEItems.dynamoMagmatic.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterResonant.copy(), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotEnderium", 'P', "ingotLumium", 'C', TEItems.powerCoilGold.copy(), 'G', "gearElectrum", 'D', TEItems.dynamoEnervation.copy(), 'S', TEItems.pneumaticServo.copy() }));

            if (SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new ShapedOreRecipe(armorPlatingIron.copy(), new Object[] { "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin" }));
            }

            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackLeadstone.copy(), new Object[] { "ICI", "ISI", 'I', "ingotLead", 'C', TEItems.cellBasic.copy(), 'S', leatherStrap.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackHardened.copy(), new Object[] { " I ", "ISI", " I ", 'I', "ingotInvar", 'S', fluxpackLeadstone.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackReinforced.copy(), new Object[] { " C ", "ISI", "LOL", 'I', "ingotElectrum", 'L', "ingotLead", 'C', TEItems.frameCellReinforcedFull.copy(), 'S', fluxpackHardened.copy(), 'O', TEItems.powerCoilElectrum }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackResonant.copy(), new Object[] { " I ", "ISI", " I ", 'I', "ingotEnderium", 'S', fluxpackReinforced.copy() }));

            GameRegistry.addRecipe(new ShapedOreRecipe(jetpackLeadstone.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic.copy(), 'T', thrusterLeadstone.copy(), 'J', leatherStrap.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackHardened.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened.copy(), 'T', thrusterHardened.copy(), 'J', jetpackLeadstone.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackReinforced.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced.copy(), 'T', thrusterReinforced.copy(), 'J', jetpackHardened.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackResonant.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant.copy(), 'T', thrusterResonant.copy(), 'J', jetpackReinforced.copy() }));

            if (raAvailable && SJConfig.enableCraftingFluxJetPlate) {
                GameRegistry.addRecipe(new ShapedOreRecipe(unitGlowstoneEmpty.copy(), new Object[] { "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.frameIlluminator.copy() }));
                GameRegistry.addRecipe(new ShapedOreRecipe(unitCryotheumEmpty.copy(), new Object[] { "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', TEItems.blockGlassHardened.copy() }));
                GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEnergetic.copy(), new Object[] { "FPF", "GRG", 'G', unitGlowstone.copy(), 'P', RAItems.plateFlux.copy(), 'R', thrusterResonant.copy(), 'F', "ingotElectrumFlux" }));
                ItemStack charger = SJConfig.fluxPlateHasCharger ? fluxpackResonantArmored.copy() : TEItems.cellResonant.copy();
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackFluxPlate.copy(), new Object[] { "PAP", "OJO", "TCT", 'A', RAItems.armorFluxPlate.copy(), 'J', jetpackResonantArmored.copy(), 'O', unitCryotheum.copy(), 'C', charger, 'T', thrusterEnergetic.copy(), 'P', RAItems.plateFlux.copy() }));
            }

            GameRegistry.addRecipe(new ShapedOreRecipe(particleDefault.copy(), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', Blocks.torch }));
            GameRegistry.addRecipe(new ShapedOreRecipe(particleNone.copy(), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', Blocks.glass }));
            GameRegistry.addRecipe(new ShapedOreRecipe(particleSmoke.copy(), new Object[] { " C ", "CCC", " C ", 'C', "dustCoal" }));
            GameRegistry.addRecipe(new ShapedOreRecipe(particleRainbowSmoke.copy(), new Object[] { " R ", " C ", "G B", 'C', "dustCoal", 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue" }));
        }
        for (int i = 0; i <= Jetpack.getHighestMeta(); i++) {
            Jetpack jetpack = Jetpack.getJetpack(i);
            if (jetpack != null && !(jetpack instanceof JetpackIcon)) {
                GameRegistry.addRecipe(new SJUpgradingRecipe(new ItemStack(jetpacks, 1, i), new Object[] { "J", "P", 'J', new ItemStack(jetpacks, 1, i), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
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
                    TERecipes.addSmelterRecipe(3200, armorPlatingIron.copy(), ingotBronze, armorPlatingTinkersAlloy.copy(), null, 0);
                }

                ItemStack ingotInvar;
                for (int i = 0; i < OreDictionary.getOres("ingotInvar").size(); i++) {
                    ingotInvar = OreDictionary.getOres("ingotInvar").get(i).copy();
                    ingotInvar.stackSize = 10;
                    TERecipes.addSmelterRecipe(4800, armorPlatingTinkersAlloy.copy(), ingotInvar, armorPlatingInvar.copy(), null, 0);
                }

                ItemStack ingotEnderium;
                for (int i = 0; i < OreDictionary.getOres("ingotEnderium").size(); i++) {
                    ingotEnderium = OreDictionary.getOres("ingotEnderium").get(i).copy();
                    ingotEnderium.stackSize = 10;
                    TERecipes.addSmelterRecipe(6400, armorPlatingInvar.copy(), ingotEnderium, armorPlatingEnderium.copy(), null, 0);
                }
            }

            if (raAvailable) {
                TERecipes.addTransposerFill(6400, unitGlowstoneEmpty.copy(), unitGlowstone.copy(), new FluidStack(FluidRegistry.getFluid("glowstone"), 4000), false);
                TERecipes.addTransposerFill(6400, unitCryotheumEmpty.copy(), unitCryotheum.copy(), new FluidStack(FluidRegistry.getFluid("cryotheum"), 4000), false);
            }
        }
    }

}
