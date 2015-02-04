package tonius.simplyjetpacks.setup;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.integration.EIOItems;
import tonius.simplyjetpacks.integration.EIORecipes;
import tonius.simplyjetpacks.integration.RAItems;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.integration.TERecipes;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.item.ItemMeta.MetaItem;
import tonius.simplyjetpacks.item.ItemMysteriousPotato;
import tonius.simplyjetpacks.item.ItemPack.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;
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
    private static boolean raAvailable = false;
    private static boolean eioAvailable = false;
    private static boolean bcAvailable = false;
    
    public static void preInit() {
        teAvailable = ModType.THERMAL_EXPANSION.isLoaded() && Config.enableIntegrationTE;
        raAvailable = ModType.REDSTONE_ARSENAL.isLoaded() && teAvailable;
        eioAvailable = ModType.ENDER_IO.isLoaded() && Config.enableIntegrationEIO;
        bcAvailable = ModType.BUILDCRAFT.isLoaded() && Config.enableIntegrationBC;
        
        constructItems();
        registerItems();
    }
    
    public static void init() {
        if (teAvailable) {
            TEItems.init();
            if (raAvailable) {
                RAItems.init();
            }
        }
        if (eioAvailable) {
            EIOItems.init();
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
            if (raAvailable) {
                jetpackTE5 = jetpacksTE.putPack(5, Packs.jetpackTE5);
            }
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
            jetpackBC1 = jetpacksBC.putPack(1, Packs.jetpackBC1);
            jetpackBC1Armored = jetpacksBC.putPack(101, Packs.jetpackBC1Armored);
            jetpackBC2 = jetpacksBC.putPack(2, Packs.jetpackBC2);
            jetpackBC2Armored = jetpacksBC.putPack(102, Packs.jetpackBC2Armored);
        }
        
        components = new ItemMeta("components");
        armorPlatings = new ItemMeta("armorPlatings");
        particleCustomizers = new ItemMeta("particleCustomizers");
        mysteriousPotato = new ItemMysteriousPotato();
        
        leatherStrap = components.addMetaItem(0, new MetaItem("leatherStrap", null, EnumRarity.common), true, false);
        jetpackIcon = components.addMetaItem(1, new MetaItem("jetpack.icon", null, EnumRarity.common, false, true), false, false);
        
        if (teAvailable) {
            thrusterTE1 = components.addMetaItem(11, new MetaItem("thruster.te.1", null, EnumRarity.common), true, false);
            thrusterTE2 = components.addMetaItem(12, new MetaItem("thruster.te.2", null, EnumRarity.common), true, false);
            thrusterTE3 = components.addMetaItem(13, new MetaItem("thruster.te.3", null, EnumRarity.uncommon), true, false);
            thrusterTE4 = components.addMetaItem(14, new MetaItem("thruster.te.4", null, EnumRarity.rare), true, false);
            if (raAvailable) {
                thrusterTE5 = components.addMetaItem(15, new MetaItem("thruster.te.5", null, EnumRarity.epic), true, false);
                unitGlowstoneEmpty = components.addMetaItem(60, new MetaItem("unitGlowstone.empty", null, EnumRarity.common), true, false);
                unitGlowstone = components.addMetaItem(61, new MetaItem("unitGlowstone", null, EnumRarity.uncommon), true, false);
                unitCryotheumEmpty = components.addMetaItem(62, new MetaItem("unitCryotheum.empty", null, EnumRarity.common), true, false);
                unitCryotheum = components.addMetaItem(63, new MetaItem("unitCryotheum", null, EnumRarity.rare), true, false);
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
            thrusterBC1 = components.addMetaItem(31, new MetaItem("thruster.bc.1", null, EnumRarity.common), true, false);
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
        registerItem(mysteriousPotato, "mysteriousPotato");
    }
    
    private static void registerRecipes() {
        SimplyJetpacks.logger.info("Registering recipes");
        
        GameRegistry.addRecipe(new ShapedOreRecipe(jetpackPotato.copy(), "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetGold", 'P', Items.potato, 'R', "dustRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(jetpackPotato.copy(), "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetGold", 'P', Items.poisonous_potato, 'R', "dustRedstone"));
        GameRegistry.addRecipe(new UpgradingRecipe(jetpackCreative.copy(), "J", "P", 'J', jetpackCreative.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(leatherStrap.copy(), "LIL", "LIL", 'L', Items.leather, 'I', "ingotIron"));
        
        Object dustCoal = OreDictionary.getOres("dustCoal").size() > 0 ? "dustCoal" : new ItemStack(Items.coal);
        GameRegistry.addRecipe(new ShapedOreRecipe(particleDefault.copy(), " D ", "DCD", " D ", 'C', dustCoal, 'D', Blocks.torch));
        GameRegistry.addRecipe(new ShapedOreRecipe(particleNone.copy(), " D ", "DCD", " D ", 'C', dustCoal, 'D', "blockGlass"));
        GameRegistry.addRecipe(new ShapedOreRecipe(particleSmoke.copy(), " C ", "CCC", " C ", 'C', dustCoal));
        GameRegistry.addRecipe(new ShapedOreRecipe(particleRainbowSmoke.copy(), " R ", " C ", "G B", 'C', dustCoal, 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue"));
        
        if (teAvailable) {
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE1.copy(), "ICI", "PGP", "DSD", 'I', "ingotLead", 'P', "blockGlass", 'C', TEItems.powerCoilGold.copy(), 'G', "gearCopper", 'D', TEItems.dynamoSteam.copy(), 'S', TEItems.pneumaticServo.copy()));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE2.copy(), "ICI", "PGP", "DSD", 'I', "ingotInvar", 'P', Blocks.redstone_block, 'C', TEItems.powerCoilGold.copy(), 'G', "gearBronze", 'D', TEItems.dynamoReactant.copy(), 'S', TEItems.pneumaticServo.copy()));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE3.copy(), "ICI", "PGP", "DSD", 'I', "ingotElectrum", 'P', "ingotSignalum", 'C', TEItems.powerCoilGold.copy(), 'G', "gearInvar", 'D', TEItems.dynamoMagmatic.copy(), 'S', TEItems.pneumaticServo.copy()));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE4.copy(), "ICI", "PGP", "DSD", 'I', "ingotEnderium", 'P', "ingotLumium", 'C', TEItems.powerCoilGold.copy(), 'G', "gearElectrum", 'D', TEItems.dynamoEnervation.copy(), 'S', TEItems.pneumaticServo.copy()));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(armorPlatingTE1.copy(), "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin"));
            
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE1.copy(), "ICI", "ISI", 'I', "ingotLead", 'C', TEItems.cellBasic.copy(), 'S', leatherStrap.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE2.copy(), " I ", "ISI", " I ", 'I', "ingotInvar", 'S', fluxPackTE1.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE3.copy(), " C ", "ISI", "LOL", 'I', "ingotElectrum", 'L', "ingotLead", 'C', TEItems.frameCellReinforcedFull.copy(), 'S', fluxPackTE2.copy(), 'O', TEItems.powerCoilElectrum));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE4.copy(), " I ", "ISI", " I ", 'I', "ingotEnderium", 'S', fluxPackTE3.copy()));
            
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE2Armored.copy(), "P", "J", 'J', fluxPackTE2.copy(), 'P', armorPlatingTE1.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE2.copy(), "J", 'J', fluxPackTE2Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE3Armored.copy(), "P", "J", 'J', fluxPackTE3.copy(), 'P', armorPlatingTE2.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE3.copy(), "J", 'J', fluxPackTE3Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE4Armored.copy(), "P", "J", 'J', fluxPackTE4.copy(), 'P', armorPlatingTE3.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackTE4.copy(), "J", 'J', fluxPackTE4Armored.copy()));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1.copy(), "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic.copy(), 'T', thrusterTE1.copy(), 'J', leatherStrap.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2.copy(), "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened.copy(), 'T', thrusterTE2.copy(), 'J', jetpackTE1.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3.copy(), "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced.copy(), 'T', thrusterTE3.copy(), 'J', jetpackTE2.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4.copy(), "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant.copy(), 'T', thrusterTE4.copy(), 'J', jetpackTE3.copy()));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1Armored.copy(), "P", "J", 'J', jetpackTE1.copy(), 'P', armorPlatingTE1.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1.copy(), "J", 'J', jetpackTE1Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2Armored.copy(), "P", "J", 'J', jetpackTE2.copy(), 'P', armorPlatingTE2.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2.copy(), "J", 'J', jetpackTE2Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3Armored.copy(), "P", "J", 'J', jetpackTE3.copy(), 'P', armorPlatingTE3.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3.copy(), "J", 'J', jetpackTE3Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4Armored.copy(), "P", "J", 'J', jetpackTE4.copy(), 'P', armorPlatingTE4.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4.copy(), "J", 'J', jetpackTE4Armored.copy()));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1.copy(), "J", "P", 'J', jetpackTE1.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2.copy(), "J", "P", 'J', jetpackTE2.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3.copy(), "J", "P", 'J', jetpackTE3.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4.copy(), "J", "P", 'J', jetpackTE4.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            
            if (raAvailable) {
                GameRegistry.addRecipe(new ShapedOreRecipe(unitGlowstoneEmpty.copy(), "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.frameIlluminator.copy()));
                GameRegistry.addRecipe(new ShapedOreRecipe(unitCryotheumEmpty.copy(), "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', "blockGlassHardened"));
                GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE5.copy(), "FPF", "GRG", 'G', unitGlowstone.copy(), 'P', RAItems.plateFlux.copy(), 'R', thrusterTE4.copy(), 'F', "ingotElectrumFlux"));
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE5.copy(), "PAP", "OJO", "TCT", 'A', new ItemStack(RAItems.armorFluxPlate.getItem(), 1, OreDictionary.WILDCARD_VALUE), 'J', jetpackTE4Armored.copy(), 'O', unitCryotheum.copy(), 'C', fluxPackTE4Armored.copy(), 'T', thrusterTE5.copy(), 'P', RAItems.plateFlux.copy()));
                
                GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE5.copy(), "J", "P", 'J', jetpackTE5.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            }
        }
        
        if (eioAvailable) {
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO1.copy(), "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit.copy(), 'C', EIOItems.basicCapacitor.copy(), 'D', EIOItems.basicGear.copy(), 'S', "dustRedstone"));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO2.copy(), "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1.copy(), 'C', EIOItems.basicCapacitor.copy(), 'D', EIOItems.machineChassis.copy(), 'S', "dustRedstone"));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO3.copy(), "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2.copy(), 'C', EIOItems.doubleCapacitor.copy(), 'D', EIOItems.pulsatingCrystal.copy(), 'S', "ingotRedstoneAlloy"));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO4.copy(), "ICI", "PCP", "DSD", 'I', "ingotPhasedGold", 'P', EIOItems.energyConduit3.copy(), 'C', EIOItems.octadicCapacitor.copy(), 'D', EIOItems.vibrantCrystal.copy(), 'S', "ingotRedstoneAlloy"));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(armorPlatingEIO1.copy(), "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon"));
            
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO1.copy(), "CIC", "ISI", "IPI", 'S', leatherStrap.copy(), 'C', EIOItems.basicCapacitor.copy(), 'I', "ingotConductiveIron", 'P', "dustCoal"));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2.copy(), "DCD", "ISI", "IPI", 'S', fluxPackEIO1.copy(), 'C', EIOItems.basicCapacitor.copy(), 'D', EIOItems.doubleCapacitor.copy(), 'I', "ingotElectricalSteel", 'P', "dustGold"));
            if (EIOItems.capacitorBank != null && EIOItems.capacitorBank.getItem() != null) {
                GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3.copy(), "CBC", "ISI", "IPI", 'S', fluxPackEIO2.copy(), 'C', EIOItems.doubleCapacitor.copy(), 'B', EIOItems.capacitorBank.copy(), 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal.copy()));
                GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4.copy(), "BCB", "ISI", "CPC", 'S', fluxPackEIO3.copy(), 'C', EIOItems.octadicCapacitor.copy(), 'B', EIOItems.capacitorBankVibrant.copy(), 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal.copy()));
            } else {
                GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3.copy(), "CBC", "ISI", "IPI", 'S', fluxPackEIO2.copy(), 'C', EIOItems.doubleCapacitor.copy(), 'B', EIOItems.capacitorBankOld.copy(), 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal.copy()));
                GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4.copy(), "CBC", "ISI", "BPB", 'S', fluxPackEIO3.copy(), 'C', EIOItems.octadicCapacitor.copy(), 'B', EIOItems.capacitorBankOld.copy(), 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal.copy()));
            }
            
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2Armored.copy(), "P", "J", 'J', fluxPackEIO2.copy(), 'P', armorPlatingEIO1.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2.copy(), "J", 'J', fluxPackEIO2Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3Armored.copy(), "P", "J", 'J', fluxPackEIO3.copy(), 'P', armorPlatingEIO2.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3.copy(), "J", 'J', fluxPackEIO3Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4Armored.copy(), "P", "J", 'J', fluxPackEIO4.copy(), 'P', armorPlatingEIO3.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4.copy(), "J", 'J', fluxPackEIO4Armored.copy()));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1.copy(), "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor.copy(), 'T', thrusterEIO1.copy(), 'J', leatherStrap.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2.copy(), "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor.copy(), 'T', thrusterEIO2.copy(), 'J', jetpackEIO1.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3.copy(), "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor.copy(), 'T', thrusterEIO3.copy(), 'J', jetpackEIO2.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4.copy(), "IBI", "IJI", "T T", 'I', "ingotPhasedGold", 'B', EIOItems.octadicCapacitor.copy(), 'T', thrusterEIO4.copy(), 'J', jetpackEIO3.copy()));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1Armored.copy(), "P", "J", 'J', jetpackEIO1.copy(), 'P', armorPlatingEIO1.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1.copy(), "J", 'J', jetpackEIO1Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2Armored.copy(), "P", "J", 'J', jetpackEIO2.copy(), 'P', armorPlatingEIO2.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2.copy(), "J", 'J', jetpackEIO2Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3Armored.copy(), "P", "J", 'J', jetpackEIO3.copy(), 'P', armorPlatingEIO3.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3.copy(), "J", 'J', jetpackEIO3Armored.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4Armored.copy(), "P", "J", 'J', jetpackEIO4.copy(), 'P', armorPlatingEIO4.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4.copy(), "J", 'J', jetpackEIO4Armored.copy()));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1.copy(), "J", "P", 'J', jetpackEIO1.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2.copy(), "J", "P", 'J', jetpackEIO2.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3.copy(), "J", "P", 'J', jetpackEIO3.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4.copy(), "J", "P", 'J', jetpackEIO4.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
            
            GameRegistry.addRecipe(new ShapedOreRecipe(unitFlightControlEmpty.copy(), "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', richSoularium.copy(), 'H', "blockGlassHardened"));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO5.copy(), "SES", "CTC", 'T', thrusterEIO4.copy(), 'S', richSoularium.copy(), 'E', unitFlightControl.copy(), 'C', EIOItems.octadicCapacitor.copy()));
            GameRegistry.addRecipe(new ShapedOreRecipe(reinforcedGliderWing.copy(), "  S", " SP", "SPP", 'S', richSoularium.copy(), 'P', armorPlatingEIO2.copy()));
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5.copy(), "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal.copy(), 'J', jetpackEIO4Armored.copy(), 'O', richSoularium.copy(), 'C', fluxPackEIO4Armored.copy(), 'T', thrusterEIO5.copy(), 'P', reinforcedGliderWing.copy()));
            
            GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5.copy(), "J", "P", 'J', jetpackEIO5.copy(), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
        }
    }
    
    private static void doIMC() {
        SimplyJetpacks.logger.info("Doing intermod communication");
        
        if (teAvailable) {
            ItemStack ingotBronze;
            for (int i = 0; i < OreDictionary.getOres("ingotBronze").size(); i++) {
                ingotBronze = OreDictionary.getOres("ingotBronze").get(i).copy();
                ingotBronze.stackSize = 10;
                TERecipes.addSmelterRecipe(3200, armorPlatingTE1.copy(), ingotBronze, armorPlatingTE2.copy(), null, 0);
            }
            
            ItemStack ingotInvar;
            for (int i = 0; i < OreDictionary.getOres("ingotInvar").size(); i++) {
                ingotInvar = OreDictionary.getOres("ingotInvar").get(i).copy();
                ingotInvar.stackSize = 10;
                TERecipes.addSmelterRecipe(4800, armorPlatingTE2.copy(), ingotInvar, armorPlatingTE3.copy(), null, 0);
            }
            
            ItemStack ingotEnderium;
            for (int i = 0; i < OreDictionary.getOres("ingotEnderium").size(); i++) {
                ingotEnderium = OreDictionary.getOres("ingotEnderium").get(i).copy();
                ingotEnderium.stackSize = 10;
                TERecipes.addSmelterRecipe(6400, armorPlatingTE3.copy(), ingotEnderium, armorPlatingTE4.copy(), null, 0);
            }
            
            if (raAvailable) {
                TERecipes.addTransposerFill(6400, unitGlowstoneEmpty.copy(), unitGlowstone.copy(), new FluidStack(FluidRegistry.getFluid("glowstone"), 4000), false);
                TERecipes.addTransposerFill(6400, unitCryotheumEmpty.copy(), unitCryotheum.copy(), new FluidStack(FluidRegistry.getFluid("cryotheum"), 4000), false);
            }
        }
        if (eioAvailable) {
            ItemStack ingotConductiveIron = OreDictionary.getOres("ingotConductiveIron").get(0).copy();
            ingotConductiveIron.stackSize = 10;
            EIORecipes.addAlloySmelterRecipe("Conductive Iron Armor Plating", 3200, armorPlatingEIO1.copy(), ingotConductiveIron, null, armorPlatingEIO2.copy());
            
            ItemStack ingotElectricalSteel = OreDictionary.getOres("ingotElectricalSteel").get(0).copy();
            ingotElectricalSteel.stackSize = 10;
            EIORecipes.addAlloySmelterRecipe("Electrical Steel Armor Plating", 4800, armorPlatingEIO2.copy(), ingotElectricalSteel, null, armorPlatingEIO3.copy());
            
            ItemStack ingotDarkSteel = OreDictionary.getOres("ingotDarkSteel").get(0).copy();
            ingotDarkSteel.stackSize = 10;
            EIORecipes.addAlloySmelterRecipe("Dark Steel Armor Plating", 6400, armorPlatingEIO3.copy(), ingotDarkSteel, null, armorPlatingEIO4.copy());
            
            ItemStack ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0).copy();
            ingotDarkSteel.stackSize = 1;
            EIORecipes.addAlloySmelterRecipe("Enriched Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal.copy(), richSoularium.copy());
            
            EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", unitFlightControlEmpty.copy(), unitFlightControl.copy());
        }
    }
    
    private static void registerItem(Item item, String name) {
        if (item != null) {
            GameRegistry.registerItem(item, name);
        }
    }
    
}
