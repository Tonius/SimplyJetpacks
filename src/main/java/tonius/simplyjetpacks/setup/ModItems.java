package tonius.simplyjetpacks.setup;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.integration.BCItems;
import tonius.simplyjetpacks.integration.BCRecipes;
import tonius.simplyjetpacks.integration.EIOItems;
import tonius.simplyjetpacks.integration.EIORecipes;
import tonius.simplyjetpacks.integration.ModType;
import tonius.simplyjetpacks.integration.RAItems;
import tonius.simplyjetpacks.integration.TDItems;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.integration.TERecipes;
import tonius.simplyjetpacks.item.ItemJetpackFueller;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.item.ItemMeta.MetaItem;
import tonius.simplyjetpacks.item.ItemMysteriousPotato;
import tonius.simplyjetpacks.item.ItemPack.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;
import cofh.lib.util.helpers.ItemHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ModItems {
    
    public static ItemJetpack jetpacksCommon = null;
    public static ItemFluxPack fluxPacksCommon = null;
    
    public static ItemJetpack jetpacksTE = null;
    public static ItemFluxPack fluxPacksTE = null;
    
    public static ItemJetpack jetpacksEIO = null;
    public static ItemFluxPack fluxPacksEIO = null;
    
    public static ItemJetpack jetpacksBC = null;
    
    public static ItemMeta components = null;
    public static ItemMeta armorPlatings = null;
    public static ItemMeta particleCustomizers = null;
    public static ItemJetpackFueller jetpackFueller = null;
    public static ItemMysteriousPotato mysteriousPotato = null;
    
    public static ItemStack jetpackPotato = null;
    public static ItemStack jetpackCreative = null;
    public static ItemStack fluxPackCreative = null;
    
    public static ItemStack jetpackTE1 = null;
    public static ItemStack jetpackTE1Armored = null;
    public static ItemStack jetpackTE2 = null;
    public static ItemStack jetpackTE2Armored = null;
    public static ItemStack jetpackTE3 = null;
    public static ItemStack jetpackTE3Armored = null;
    public static ItemStack jetpackTE4 = null;
    public static ItemStack jetpackTE4Armored = null;
    public static ItemStack jetpackTE5 = null;
    public static ItemStack fluxPackTE1 = null;
    public static ItemStack fluxPackTE2 = null;
    public static ItemStack fluxPackTE2Armored = null;
    public static ItemStack fluxPackTE3 = null;
    public static ItemStack fluxPackTE3Armored = null;
    public static ItemStack fluxPackTE4 = null;
    public static ItemStack fluxPackTE4Armored = null;
    
    public static ItemStack jetpackEIO1 = null;
    public static ItemStack jetpackEIO1Armored = null;
    public static ItemStack jetpackEIO2 = null;
    public static ItemStack jetpackEIO2Armored = null;
    public static ItemStack jetpackEIO3 = null;
    public static ItemStack jetpackEIO3Armored = null;
    public static ItemStack jetpackEIO4 = null;
    public static ItemStack jetpackEIO4Armored = null;
    public static ItemStack jetpackEIO5 = null;
    public static ItemStack fluxPackEIO1 = null;
    public static ItemStack fluxPackEIO2 = null;
    public static ItemStack fluxPackEIO2Armored = null;
    public static ItemStack fluxPackEIO3 = null;
    public static ItemStack fluxPackEIO3Armored = null;
    public static ItemStack fluxPackEIO4 = null;
    public static ItemStack fluxPackEIO4Armored = null;
    
    public static ItemStack jetpackBC1 = null;
    public static ItemStack jetpackBC1Armored = null;
    public static ItemStack jetpackBC2 = null;
    public static ItemStack jetpackBC2Armored = null;
    
    public static ItemStack leatherStrap = null;
    public static ItemStack jetpackIcon = null;
    public static ItemStack thrusterTE1 = null;
    public static ItemStack thrusterTE2 = null;
    public static ItemStack thrusterTE3 = null;
    public static ItemStack thrusterTE4 = null;
    public static ItemStack thrusterTE5 = null;
    public static ItemStack thrusterEIO1 = null;
    public static ItemStack thrusterEIO2 = null;
    public static ItemStack thrusterEIO3 = null;
    public static ItemStack thrusterEIO4 = null;
    public static ItemStack thrusterEIO5 = null;
    public static ItemStack thrusterBC1 = null;
    public static ItemStack thrusterBC2 = null;
    public static ItemStack unitGlowstoneEmpty = null;
    public static ItemStack unitGlowstone = null;
    public static ItemStack unitCryotheumEmpty = null;
    public static ItemStack unitCryotheum = null;
    public static ItemStack dustElectrumFlux = null;
    public static ItemStack ingotElectrumFlux = null;
    public static ItemStack nuggetElectrumFlux = null;
    public static ItemStack gemCrystalFlux = null;
    public static ItemStack plateFlux = null;
    public static ItemStack armorFluxPlate = null;
    public static ItemStack richSoularium = null;
    public static ItemStack reinforcedGliderWing = null;
    public static ItemStack unitFlightControlEmpty = null;
    public static ItemStack unitFlightControl = null;
    
    public static ItemStack armorPlatingTE1 = null;
    public static ItemStack armorPlatingTE2 = null;
    public static ItemStack armorPlatingTE3 = null;
    public static ItemStack armorPlatingTE4 = null;
    public static ItemStack armorPlatingEIO1 = null;
    public static ItemStack armorPlatingEIO2 = null;
    public static ItemStack armorPlatingEIO3 = null;
    public static ItemStack armorPlatingEIO4 = null;
    public static ItemStack armorPlatingBC1 = null;
    public static ItemStack armorPlatingBC2 = null;
    
    public static ItemStack particleDefault = null;
    public static ItemStack particleNone = null;
    public static ItemStack particleSmoke = null;
    public static ItemStack particleRainbowSmoke = null;
    
    private static boolean teAvailable = false;
    private static boolean tdAvailable = false;
    private static boolean raAvailable = false;
    private static boolean eioAvailable = false;
    private static boolean bcAvailable = false;
    
    public static void preInit() {
        teAvailable = ModType.THERMAL_EXPANSION.isLoaded() && Config.enableIntegrationTE;
        tdAvailable = ModType.THERMAL_DYNAMICS.isLoaded() && teAvailable;
        raAvailable = ModType.REDSTONE_ARSENAL.isLoaded() && teAvailable;
        eioAvailable = ModType.ENDER_IO.isLoaded() && Config.enableIntegrationEIO;
        bcAvailable = ModType.BUILDCRAFT.isLoaded() && Config.enableIntegrationBC;
        
        constructItems();
        registerItems();
    }
    
    public static void init() {
        if (teAvailable) {
            TEItems.init();
            if (tdAvailable) {
                TDItems.init();
            }
            if (raAvailable) {
                RAItems.init();
            }
        }
        if (eioAvailable) {
            EIOItems.init();
        }
        if (bcAvailable) {
            BCItems.init();
        }
        
        registerRecipes();
        doIMC();
    }
    
    private static void constructItems() {
        SimplyJetpacks.logger.info("Constructing items");
        
        jetpacksCommon = new ItemJetpack(ModType.SIMPLY_JETPACKS);
        jetpackPotato = jetpacksCommon.putPack(0, Packs.jetpackPotato, true);
        jetpackCreative = jetpacksCommon.putPack(9001, Packs.jetpackCreative);
        fluxPacksCommon = new ItemFluxPack(ModType.SIMPLY_JETPACKS);
        fluxPackCreative = fluxPacksCommon.putPack(9001, Packs.fluxPackCreative);
        
        if (teAvailable) {
            jetpacksTE = new ItemJetpack(ModType.THERMAL_EXPANSION);
            jetpackTE1 = jetpacksTE.putPack(1, Packs.jetpackTE1);
            jetpackTE1Armored = jetpacksTE.putPack(101, Packs.jetpackTE1Armored);
            jetpackTE2 = jetpacksTE.putPack(2, Packs.jetpackTE2);
            jetpackTE2Armored = jetpacksTE.putPack(102, Packs.jetpackTE2Armored);
            jetpackTE3 = jetpacksTE.putPack(3, Packs.jetpackTE3);
            jetpackTE3Armored = jetpacksTE.putPack(103, Packs.jetpackTE3Armored);
            jetpackTE4 = jetpacksTE.putPack(4, Packs.jetpackTE4);
            jetpackTE4Armored = jetpacksTE.putPack(104, Packs.jetpackTE4Armored);
            jetpackTE5 = jetpacksTE.putPack(5, Packs.jetpackTE5);
            fluxPacksTE = new ItemFluxPack(ModType.THERMAL_EXPANSION);
            fluxPackTE1 = fluxPacksTE.putPack(1, Packs.fluxPackTE1);
            fluxPackTE2 = fluxPacksTE.putPack(2, Packs.fluxPackTE2);
            fluxPackTE2Armored = fluxPacksTE.putPack(102, Packs.fluxPackTE2Armored);
            fluxPackTE3 = fluxPacksTE.putPack(3, Packs.fluxPackTE3);
            fluxPackTE3Armored = fluxPacksTE.putPack(103, Packs.fluxPackTE3Armored);
            fluxPackTE4 = fluxPacksTE.putPack(4, Packs.fluxPackTE4);
            fluxPackTE4Armored = fluxPacksTE.putPack(104, Packs.fluxPackTE4Armored);
        }
        if (eioAvailable) {
            jetpacksEIO = new ItemJetpack(ModType.ENDER_IO);
            jetpackEIO1 = jetpacksEIO.putPack(1, Packs.jetpackEIO1);
            jetpackEIO1Armored = jetpacksEIO.putPack(101, Packs.jetpackEIO1Armored);
            jetpackEIO2 = jetpacksEIO.putPack(2, Packs.jetpackEIO2);
            jetpackEIO2Armored = jetpacksEIO.putPack(102, Packs.jetpackEIO2Armored);
            jetpackEIO3 = jetpacksEIO.putPack(3, Packs.jetpackEIO3);
            jetpackEIO3Armored = jetpacksEIO.putPack(103, Packs.jetpackEIO3Armored);
            jetpackEIO4 = jetpacksEIO.putPack(4, Packs.jetpackEIO4);
            jetpackEIO4Armored = jetpacksEIO.putPack(104, Packs.jetpackEIO4Armored);
            jetpackEIO5 = jetpacksEIO.putPack(5, Packs.jetpackEIO5);
            fluxPacksEIO = new ItemFluxPack(ModType.ENDER_IO);
            fluxPackEIO1 = fluxPacksEIO.putPack(1, Packs.fluxPackEIO1);
            fluxPackEIO2 = fluxPacksEIO.putPack(2, Packs.fluxPackEIO2);
            fluxPackEIO2Armored = fluxPacksEIO.putPack(102, Packs.fluxPackEIO2Armored);
            fluxPackEIO3 = fluxPacksEIO.putPack(3, Packs.fluxPackEIO3);
            fluxPackEIO3Armored = fluxPacksEIO.putPack(103, Packs.fluxPackEIO3Armored);
            fluxPackEIO4 = fluxPacksEIO.putPack(4, Packs.fluxPackEIO4);
            fluxPackEIO4Armored = fluxPacksEIO.putPack(104, Packs.fluxPackEIO4Armored);
        }
        if (bcAvailable) {
            jetpacksBC = new ItemJetpack(ModType.BUILDCRAFT);
            if (Loader.isModLoaded("BuildCraft|Energy")) {
                jetpackBC1 = jetpacksBC.putPack(1, Packs.jetpackBC1);
                jetpackBC1Armored = jetpacksBC.putPack(101, Packs.jetpackBC1Armored);
            }
            jetpackBC2 = jetpacksBC.putPack(2, Packs.jetpackBC2);
            jetpackBC2Armored = jetpacksBC.putPack(102, Packs.jetpackBC2Armored);
        }
        
        components = new ItemMeta("components");
        armorPlatings = new ItemMeta("armorPlatings");
        particleCustomizers = new ItemMeta("particleCustomizers");
        jetpackFueller = new ItemJetpackFueller();
        mysteriousPotato = new ItemMysteriousPotato();
        
        leatherStrap = components.addMetaItem(0, new MetaItem("leatherStrap", null, EnumRarity.common), true, false);
        jetpackIcon = components.addMetaItem(1, new MetaItem("jetpack.icon", null, EnumRarity.common, false, true), false, false);
        
        if (teAvailable) {
            thrusterTE1 = components.addMetaItem(11, new MetaItem("thruster.te.1", null, EnumRarity.common), true, false);
            thrusterTE2 = components.addMetaItem(12, new MetaItem("thruster.te.2", null, EnumRarity.common), true, false);
            thrusterTE3 = components.addMetaItem(13, new MetaItem("thruster.te.3", null, EnumRarity.uncommon), true, false);
            thrusterTE4 = components.addMetaItem(14, new MetaItem("thruster.te.4", null, EnumRarity.rare), true, false);
            thrusterTE5 = components.addMetaItem(15, new MetaItem("thruster.te.5", null, EnumRarity.epic), true, false);
            unitGlowstoneEmpty = components.addMetaItem(60, new MetaItem("unitGlowstone.empty", null, EnumRarity.common), true, false);
            unitGlowstone = components.addMetaItem(61, new MetaItem("unitGlowstone", null, EnumRarity.uncommon), true, false);
            unitCryotheumEmpty = components.addMetaItem(62, new MetaItem("unitCryotheum.empty", null, EnumRarity.common), true, false);
            unitCryotheum = components.addMetaItem(63, new MetaItem("unitCryotheum", null, EnumRarity.rare), true, false);
            if (!raAvailable) {
                dustElectrumFlux = components.addMetaItem(64, new MetaItem("dustElectrumFlux", "raReplacement", EnumRarity.uncommon), true, true);
                ingotElectrumFlux = components.addMetaItem(65, new MetaItem("ingotElectrumFlux", "raReplacement", EnumRarity.uncommon), true, true);
                nuggetElectrumFlux = components.addMetaItem(66, new MetaItem("nuggetElectrumFlux", "raReplacement", EnumRarity.uncommon), true, true);
                gemCrystalFlux = components.addMetaItem(67, new MetaItem("gemCrystalFlux", "raReplacement", EnumRarity.uncommon), true, true);
                plateFlux = components.addMetaItem(68, new MetaItem("plateFlux", "raReplacement", EnumRarity.uncommon), true, false);
                armorFluxPlate = components.addMetaItem(69, new MetaItem("armorFluxPlate", "raReplacement", EnumRarity.uncommon), true, false);
            }
            
            armorPlatingTE1 = armorPlatings.addMetaItem(1, new MetaItem("armorPlating.te.1", null, EnumRarity.common), true, false);
            armorPlatingTE2 = armorPlatings.addMetaItem(2, new MetaItem("armorPlating.te.2", null, EnumRarity.common), true, false);
            armorPlatingTE3 = armorPlatings.addMetaItem(3, new MetaItem("armorPlating.te.3", null, EnumRarity.common), true, false);
            armorPlatingTE4 = armorPlatings.addMetaItem(4, new MetaItem("armorPlating.te.4", null, EnumRarity.rare), true, false);
        }
        if (eioAvailable) {
            thrusterEIO1 = components.addMetaItem(21, new MetaItem("thruster.eio.1", null, EnumRarity.common), true, false);
            thrusterEIO2 = components.addMetaItem(22, new MetaItem("thruster.eio.2", null, EnumRarity.common), true, false);
            thrusterEIO3 = components.addMetaItem(23, new MetaItem("thruster.eio.3", null, EnumRarity.uncommon), true, false);
            thrusterEIO4 = components.addMetaItem(24, new MetaItem("thruster.eio.4", null, EnumRarity.rare), true, false);
            thrusterEIO5 = components.addMetaItem(25, new MetaItem("thruster.eio.5", null, EnumRarity.epic), true, false);
            richSoularium = components.addMetaItem(70, new MetaItem("richSoularium", null, EnumRarity.uncommon, true, false), true, false);
            reinforcedGliderWing = components.addMetaItem(71, new MetaItem("reinforcedGliderWing", null, EnumRarity.uncommon), true, false);
            unitFlightControlEmpty = components.addMetaItem(72, new MetaItem("unitFlightControl.empty", null, EnumRarity.common), true, false);
            unitFlightControl = components.addMetaItem(73, new MetaItem("unitFlightControl", null, EnumRarity.uncommon), true, false);
            
            armorPlatingEIO1 = armorPlatings.addMetaItem(11, new MetaItem("armorPlating.eio.1", null, EnumRarity.common), true, false);
            armorPlatingEIO2 = armorPlatings.addMetaItem(12, new MetaItem("armorPlating.eio.2", null, EnumRarity.common), true, false);
            armorPlatingEIO3 = armorPlatings.addMetaItem(13, new MetaItem("armorPlating.eio.3", null, EnumRarity.common), true, false);
            armorPlatingEIO4 = armorPlatings.addMetaItem(14, new MetaItem("armorPlating.eio.4", null, EnumRarity.common), true, false);
        }
        if (bcAvailable) {
            if (Loader.isModLoaded("BuildCraft|Energy")) {
                thrusterBC1 = components.addMetaItem(31, new MetaItem("thruster.bc.1", null, EnumRarity.common), true, false);
            }
            thrusterBC2 = components.addMetaItem(32, new MetaItem("thruster.bc.2", null, EnumRarity.uncommon), true, false);
            
            armorPlatingBC1 = armorPlatings.addMetaItem(21, new MetaItem("armorPlating.bc.1", null, EnumRarity.common), true, false);
            armorPlatingBC2 = armorPlatings.addMetaItem(22, new MetaItem("armorPlating.bc.2", null, EnumRarity.uncommon), true, false);
        }
        
        particleDefault = particleCustomizers.addMetaItem(0, new MetaItem("particle.0", "particleCustomizers", EnumRarity.common), true, false);
        particleNone = particleCustomizers.addMetaItem(1, new MetaItem("particle.1", "particleCustomizers", EnumRarity.common), true, false);
        particleSmoke = particleCustomizers.addMetaItem(2, new MetaItem("particle.2", "particleCustomizers", EnumRarity.common), true, false);
        particleRainbowSmoke = particleCustomizers.addMetaItem(3, new MetaItem("particle.3", "particleCustomizers", EnumRarity.common), true, false);
    }
    
    private static void registerItems() {
        SimplyJetpacks.logger.info("Registering items");
        
        // For compatibility, do not change the following IDs until 1.8
        
        registerItem(jetpacksCommon, "jetpacksCommon");
        registerItem(fluxPacksCommon, "fluxpacksCommon");
        
        registerItem(jetpacksTE, "jetpacks");
        registerItem(fluxPacksTE, "fluxpacks");
        
        registerItem(jetpacksEIO, "jetpacksEIO");
        registerItem(fluxPacksEIO, "fluxpacksEIO");
        
        registerItem(jetpacksBC, "jetpacksBC");
        
        registerItem(components, "components");
        registerItem(armorPlatings, "armorPlatings");
        registerItem(particleCustomizers, "particleCustomizers");
        registerItem(jetpackFueller, "jetpackFueller");
        registerItem(mysteriousPotato, "mysteriousPotato");
    }
    
    private static void registerRecipes() {
        SimplyJetpacks.logger.info("Registering recipes");
        
        ItemHelper.addShapedOreRecipe(jetpackPotato, "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetGold", 'P', Items.potato, 'R', "dustRedstone");
        ItemHelper.addShapedOreRecipe(jetpackPotato, "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetGold", 'P', Items.poisonous_potato, 'R', "dustRedstone");
        GameRegistry.addRecipe(new UpgradingRecipe(jetpackCreative, "J", "P", 'J', jetpackCreative, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
        
        ItemHelper.addShapedOreRecipe(leatherStrap, "LIL", "LIL", 'L', Items.leather, 'I', "ingotIron");
        
        Object dustCoal = OreDictionary.getOres("dustCoal").size() > 0 ? "dustCoal" : new ItemStack(Items.coal);
        ItemHelper.addShapedOreRecipe(particleDefault, " D ", "DCD", " D ", 'C', dustCoal, 'D', Blocks.torch);
        ItemHelper.addShapedOreRecipe(particleNone, " D ", "DCD", " D ", 'C', dustCoal, 'D', "blockGlass");
        ItemHelper.addShapedOreRecipe(particleSmoke, " C ", "CCC", " C ", 'C', dustCoal);
        ItemHelper.addShapedOreRecipe(particleRainbowSmoke, " R ", " C ", "G B", 'C', dustCoal, 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue");
        
        ItemHelper.addShapedOreRecipe(jetpackFueller, "IY ", " IY", " SI", 'I', "ingotIron", 'Y', "dyeYellow", 'S', "stickWood");
        
        if (teAvailable) {
            if (!raAvailable) {
                ItemHelper.addReverseStorageRecipe(nuggetElectrumFlux, "ingotElectrumFlux");
                ItemHelper.addStorageRecipe(ingotElectrumFlux, "nuggetElectrumFlux");
                ItemHelper.addShapedOreRecipe(plateFlux, "NNN", "GIG", "NNN", 'G', "gemCrystalFlux", 'I', "ingotElectrumFlux", 'N', "nuggetElectrumFlux");
                ItemHelper.addShapedOreRecipe(armorFluxPlate, "I I", "III", "III", 'I', plateFlux);
            }
            
            Object ductFluxLeadstone = tdAvailable ? TDItems.ductFluxLeadstone : "blockGlass";
            Object ductFluxHardened = tdAvailable ? TDItems.ductFluxHardened : "blockGlass";
            Object ductFluxRedstoneEnergy = tdAvailable ? TDItems.ductFluxRedstoneEnergy : "blockGlassHardened";
            Object ductFluxResonant = tdAvailable ? TDItems.ductFluxResonant : "blockGlassHardened";
            
            ItemHelper.addShapedOreRecipe(thrusterTE1, "ICI", "PDP", "IRI", 'I', "ingotLead", 'P', ductFluxLeadstone, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoSteam, 'R', "dustRedstone");
            ItemHelper.addShapedOreRecipe(thrusterTE2, "ICI", "PDP", "IRI", 'I', "ingotInvar", 'P', ductFluxHardened, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoReactant, 'R', "dustRedstone");
            ItemHelper.addShapedOreRecipe(thrusterTE3, "ICI", "PDP", "IRI", 'I', "ingotElectrum", 'P', ductFluxRedstoneEnergy, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoMagmatic, 'R', "bucketRedstone");
            ItemHelper.addShapedOreRecipe(thrusterTE4, "ICI", "PDP", "IRI", 'I', "ingotEnderium", 'P', ductFluxResonant, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoEnervation, 'R', "bucketRedstone");
            
            ItemHelper.addShapedOreRecipe(armorPlatingTE1, "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin");
            
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE1, "ICI", "ISI", 'I', "ingotLead", 'C', TEItems.cellBasic, 'S', leatherStrap));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE2, " I ", "ISI", " I ", 'I', "ingotInvar", 'S', fluxPackTE1));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE3, " C ", "ISI", "LOL", 'I', "ingotElectrum", 'L', "ingotLead", 'C', TEItems.frameCellReinforcedFull, 'S', fluxPackTE2, 'O', TEItems.powerCoilElectrum));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE4, " I ", "ISI", " I ", 'I', "ingotEnderium", 'S', fluxPackTE3));
            
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE2Armored, "P", "J", 'J', fluxPackTE2, 'P', armorPlatingTE1));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE2, "J", 'J', fluxPackTE2Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE3Armored, "P", "J", 'J', fluxPackTE3, 'P', armorPlatingTE2));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE3, "J", 'J', fluxPackTE3Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE4Armored, "P", "J", 'J', fluxPackTE4, 'P', armorPlatingTE3));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE4, "J", 'J', fluxPackTE4Armored));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1, "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic, 'T', thrusterTE1, 'J', leatherStrap));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2, "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened, 'T', thrusterTE2, 'J', jetpackTE1));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3, "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', thrusterTE3, 'J', jetpackTE2));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4, "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', thrusterTE4, 'J', jetpackTE3));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1Armored, "P", "J", 'J', jetpackTE1, 'P', armorPlatingTE1));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1, "J", 'J', jetpackTE1Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2Armored, "P", "J", 'J', jetpackTE2, 'P', armorPlatingTE2));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2, "J", 'J', jetpackTE2Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3Armored, "P", "J", 'J', jetpackTE3, 'P', armorPlatingTE3));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3, "J", 'J', jetpackTE3Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4Armored, "P", "J", 'J', jetpackTE4, 'P', armorPlatingTE4));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4, "J", 'J', jetpackTE4Armored));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1, "J", "P", 'J', jetpackTE1, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2, "J", "P", 'J', jetpackTE2, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3, "J", "P", 'J', jetpackTE3, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4, "J", "P", 'J', jetpackTE4, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            
            ItemHelper.addShapedOreRecipe(unitGlowstoneEmpty, "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.frameIlluminator);
            ItemHelper.addShapedOreRecipe(unitCryotheumEmpty, "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', "blockGlassHardened");
            ItemHelper.addShapedOreRecipe(thrusterTE5, "FPF", "GRG", 'G', unitGlowstone, 'P', RAItems.plateFlux != null ? RAItems.plateFlux : plateFlux, 'R', thrusterTE4, 'F', "ingotElectrumFlux");
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE5, "PAP", "OJO", "TCT", 'A', RAItems.armorFluxPlate != null ? RAItems.armorFluxPlate : armorFluxPlate, 'J', jetpackTE4Armored, 'O', unitCryotheum, 'C', fluxPackTE4Armored, 'T', thrusterTE5, 'P', RAItems.plateFlux != null ? RAItems.plateFlux : plateFlux));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE5, "J", "P", 'J', jetpackTE5, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
        }
        
        if (eioAvailable) {
            ItemHelper.addShapedOreRecipe(thrusterEIO1, "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit, 'C', EIOItems.basicCapacitor, 'D', EIOItems.basicGear, 'S', "dustRedstone");
            ItemHelper.addShapedOreRecipe(thrusterEIO2, "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.machineChassis, 'S', "dustRedstone");
            ItemHelper.addShapedOreRecipe(thrusterEIO3, "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2, 'C', EIOItems.doubleCapacitor, 'D', EIOItems.pulsatingCrystal, 'S', "ingotRedstoneAlloy");
            ItemHelper.addShapedOreRecipe(thrusterEIO4, "ICI", "PCP", "DSD", 'I', "ingotPhasedGold", 'P', EIOItems.energyConduit3, 'C', EIOItems.octadicCapacitor, 'D', EIOItems.vibrantCrystal, 'S', "ingotRedstoneAlloy");
            
            ItemHelper.addShapedOreRecipe(armorPlatingEIO1, "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon");
            
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO1, "CIC", "ISI", "IPI", 'S', leatherStrap, 'C', EIOItems.basicCapacitor, 'I', "ingotConductiveIron", 'P', "dustCoal"));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2, "DCD", "ISI", "IPI", 'S', fluxPackEIO1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.doubleCapacitor, 'I', "ingotElectricalSteel", 'P', "dustGold"));
            if (EIOItems.capacitorBank != null && EIOItems.capacitorBank.getItem() != null) {
                GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBank, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
                GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "BCB", "ISI", "CPC", 'S', fluxPackEIO3, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal));
            } else {
                GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBankOld, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
                GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "CBC", "ISI", "BPB", 'S', fluxPackEIO3, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankOld, 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal));
            }
            
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2Armored, "P", "J", 'J', fluxPackEIO2, 'P', armorPlatingEIO1));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2, "J", 'J', fluxPackEIO2Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3Armored, "P", "J", 'J', fluxPackEIO3, 'P', armorPlatingEIO2));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "J", 'J', fluxPackEIO3Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4Armored, "P", "J", 'J', fluxPackEIO4, 'P', armorPlatingEIO3));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "J", 'J', fluxPackEIO4Armored));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotPhasedGold", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1Armored, "P", "J", 'J', jetpackEIO1, 'P', armorPlatingEIO1));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "J", 'J', jetpackEIO1Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2Armored, "P", "J", 'J', jetpackEIO2, 'P', armorPlatingEIO2));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "J", 'J', jetpackEIO2Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3Armored, "P", "J", 'J', jetpackEIO3, 'P', armorPlatingEIO3));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "J", 'J', jetpackEIO3Armored));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4Armored, "P", "J", 'J', jetpackEIO4, 'P', armorPlatingEIO4));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "J", 'J', jetpackEIO4Armored));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "J", "P", 'J', jetpackEIO1, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "J", "P", 'J', jetpackEIO2, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "J", "P", 'J', jetpackEIO3, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "J", "P", 'J', jetpackEIO4, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            
            ItemHelper.addShapedOreRecipe(unitFlightControlEmpty, "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', richSoularium, 'H', "blockGlassHardened");
            ItemHelper.addShapedOreRecipe(thrusterEIO5, "SES", "CTC", 'T', thrusterEIO4, 'S', richSoularium, 'E', unitFlightControl, 'C', EIOItems.octadicCapacitor);
            ItemHelper.addShapedOreRecipe(reinforcedGliderWing, "  S", " SP", "SPP", 'S', richSoularium, 'P', armorPlatingEIO2);
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', richSoularium, 'C', fluxPackEIO4Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWing));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5, "J", "P", 'J', jetpackEIO5, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
        }
        
        if (bcAvailable) {
            ItemHelper.addShapedOreRecipe(armorPlatingBC1, /* listen here u */"LIL"/* shit */, "ILI", "LIL", 'I', "ingotIron", 'L', Items.leather);
            ItemHelper.addSurroundRecipe(armorPlatingBC2, armorPlatingBC1, "gemDiamond");
            
            if (Loader.isModLoaded("BuildCraft|Energy")) {
                ItemHelper.addShapedOreRecipe(thrusterBC1, "IGI", "PEP", "IBI", 'I', "ingotIron", 'G', "gearIron", 'P', BCItems.pipeFluidStone, 'E', BCItems.engineCombustion, 'B', Blocks.iron_bars);
                
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC1, "IBI", "IJI", "T T", 'I', "ingotIron", 'B', BCItems.tank, 'T', thrusterBC1, 'J', leatherStrap));
                
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC1Armored, "P", "J", 'J', jetpackBC1, 'P', armorPlatingBC1));
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC1, "J", 'J', jetpackBC1Armored));
                
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC1, "J", "P", 'J', jetpackBC1, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            }
            
            Object jetpack = jetpackBC1 != null ? jetpackBC1 : leatherStrap;
            Object thruster = thrusterBC1 != null ? thrusterBC1 : "gearIron";
            if (Loader.isModLoaded("BuildCraft|Silicon")) {
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC2, "IBI", "IJI", "T T", 'I', "ingotGold", 'B', "crystalRedstone" /* BC7 */, 'T', thrusterBC2, 'J', jetpack));
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC2, "IBI", "IJI", "T T", 'I', "ingotGold", 'B', "redstoneCrystal" /* BC6 */, 'T', thrusterBC2, 'J', jetpack));
            } else {
                ItemHelper.addShapedOreRecipe(thrusterBC2, "IGI", "PEP", "IBI", 'I', "ingotGold", 'G', "gearGold", 'P', BCItems.pipeEnergyGold, 'E', thruster, 'B', Blocks.iron_bars);
                
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC2, "IBI", "IJI", "T T", 'I', "ingotGold", 'B', "gearDiamond", 'T', thrusterBC2, 'J', jetpack));
            }
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC2Armored, "P", "J", 'J', jetpackBC2, 'P', armorPlatingBC2));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC2, "J", 'J', jetpackBC2Armored));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackBC2, "J", "P", 'J', jetpackBC2, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
        }
    }
    
    private static void doIMC() {
        SimplyJetpacks.logger.info("Doing intermod communication");
        
        if (teAvailable) {
            if (!raAvailable) {
                TERecipes.addTransposerFill(8000, new ItemStack(Items.diamond), gemCrystalFlux, new FluidStack(FluidRegistry.getFluid("redstone"), 200), false);
                TERecipes.addTransposerFill(4000, OreDictionary.getOres("dustElectrum").get(0), dustElectrumFlux, new FluidStack(FluidRegistry.getFluid("redstone"), 200), false);
                TERecipes.addSmelterBlastOre("ElectrumFlux");
            }
            
            ItemStack i = OreDictionary.getOres("ingotBronze").get(0).copy();
            i.stackSize = 10;
            TERecipes.addSmelterRecipe(3200, armorPlatingTE1, i, armorPlatingTE2, null, 0);
            
            i = OreDictionary.getOres("ingotInvar").get(0).copy();
            i.stackSize = 10;
            TERecipes.addSmelterRecipe(4800, armorPlatingTE2, i, armorPlatingTE3, null, 0);
            
            i = OreDictionary.getOres("ingotEnderium").get(0).copy();
            i.stackSize = 10;
            TERecipes.addSmelterRecipe(6400, armorPlatingTE3, i, armorPlatingTE4, null, 0);
            
            TERecipes.addTransposerFill(6400, unitGlowstoneEmpty, unitGlowstone, new FluidStack(FluidRegistry.getFluid("glowstone"), 4000), false);
            TERecipes.addTransposerFill(6400, unitCryotheumEmpty, unitCryotheum, new FluidStack(FluidRegistry.getFluid("cryotheum"), 4000), false);
        }
        if (eioAvailable) {
            ItemStack ingotConductiveIron = OreDictionary.getOres("ingotConductiveIron").get(0).copy();
            ingotConductiveIron.stackSize = 10;
            EIORecipes.addAlloySmelterRecipe("Conductive Iron Armor Plating", 3200, armorPlatingEIO1, ingotConductiveIron, null, armorPlatingEIO2);
            
            ItemStack ingotElectricalSteel = OreDictionary.getOres("ingotElectricalSteel").get(0).copy();
            ingotElectricalSteel.stackSize = 10;
            EIORecipes.addAlloySmelterRecipe("Electrical Steel Armor Plating", 4800, armorPlatingEIO2, ingotElectricalSteel, null, armorPlatingEIO3);
            
            ItemStack ingotDarkSteel = OreDictionary.getOres("ingotDarkSteel").get(0).copy();
            ingotDarkSteel.stackSize = 10;
            EIORecipes.addAlloySmelterRecipe("Dark Steel Armor Plating", 6400, armorPlatingEIO3, ingotDarkSteel, null, armorPlatingEIO4);
            
            ItemStack ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0).copy();
            ingotDarkSteel.stackSize = 1;
            EIORecipes.addAlloySmelterRecipe("Enriched Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal, richSoularium);
            
            EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", unitFlightControlEmpty, unitFlightControl);
        }
        if (bcAvailable && Loader.isModLoaded("BuildCraft|Silicon")) {
            ItemStack pipeEnergyGold = BCItems.getStack(BCItems.pipeEnergyGold);
            pipeEnergyGold.stackSize = 2;
            ItemStack[] inputs;
            if (thrusterBC1 != null) {
                inputs = new ItemStack[] { thrusterBC1.copy(), new ItemStack(Items.gold_ingot, 4), pipeEnergyGold, BCItems.getStack(BCItems.chipsetGold) };
            } else {
                inputs = new ItemStack[] { BCItems.getStack(BCItems.engineCombustion), new ItemStack(Items.gold_ingot, 4), pipeEnergyGold, BCItems.getStack(BCItems.chipsetGold), new ItemStack(Blocks.iron_bars) };
            }
            BCRecipes.addAssemblyRecipe("kineticThruster", 1200000, inputs, thrusterBC2.copy());
        }
    }
    
    private static void registerItem(Item item, String name) {
        if (item != null) {
            GameRegistry.registerItem(item, name);
        }
    }
    
}
