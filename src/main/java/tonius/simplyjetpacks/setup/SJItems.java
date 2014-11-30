package tonius.simplyjetpacks.setup;

import java.util.ArrayList;
import java.util.List;

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
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.crafting.SJUpgradingRecipe;
import tonius.simplyjetpacks.integration.EIOItems;
import tonius.simplyjetpacks.integration.EIORecipes;
import tonius.simplyjetpacks.integration.RAItems;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.integration.TERecipes;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemIndex;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.item.ItemMeta.MetaItem;
import tonius.simplyjetpacks.item.ItemMysteriousPotato;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class SJItems {
    
    public static ItemJetpack jetpacksCommon = null;
    public static ItemJetpack jetpacksTE = null;
    public static ItemJetpack jetpacksEIO = null;
    
    public static List<ItemJetpack> jetpacksesPerMod = new ArrayList<ItemJetpack>();
    
    public static ItemFluxPack fluxpacksCommon = null;
    public static ItemFluxPack fluxpacksTE = null;
    public static ItemFluxPack fluxpacksEIO = null;
    
    public static List<ItemFluxPack> fluxpacksesPerMod = new ArrayList<ItemFluxPack>();
    
    public static ItemMeta components = null;
    public static ItemMeta armorPlatings = null;
    public static ItemMeta particleCustomizers = null;
    public static ItemMysteriousPotato mysteriousPotato = null;
    
    public static ItemStack jetpackPotato = null;
    public static ItemStack jetpackCreative = null;
    
    public static ItemStack fluxpackCreative = null;
    
    public static ItemStack jetpackTE1 = null;
    public static ItemStack jetpackTE1Armored = null;
    public static ItemStack jetpackTE2 = null;
    public static ItemStack jetpackTE2Armored = null;
    public static ItemStack jetpackTE3 = null;
    public static ItemStack jetpackTE3Armored = null;
    public static ItemStack jetpackTE4 = null;
    public static ItemStack jetpackTE4Armored = null;
    public static ItemStack jetpackTE5 = null;
    
    public static ItemStack fluxpackTE1 = null;
    public static ItemStack fluxpackTE2 = null;
    public static ItemStack fluxpackTE2Armored = null;
    public static ItemStack fluxpackTE3 = null;
    public static ItemStack fluxpackTE3Armored = null;
    public static ItemStack fluxpackTE4 = null;
    public static ItemStack fluxpackTE4Armored = null;
    
    public static ItemStack jetpackEIO1 = null;
    public static ItemStack jetpackEIO1Armored = null;
    public static ItemStack jetpackEIO2 = null;
    public static ItemStack jetpackEIO2Armored = null;
    public static ItemStack jetpackEIO3 = null;
    public static ItemStack jetpackEIO3Armored = null;
    public static ItemStack jetpackEIO4 = null;
    public static ItemStack jetpackEIO4Armored = null;
    public static ItemStack jetpackEIO5 = null;
    
    public static ItemStack fluxpackEIO1 = null;
    public static ItemStack fluxpackEIO2 = null;
    public static ItemStack fluxpackEIO2Armored = null;
    public static ItemStack fluxpackEIO3 = null;
    public static ItemStack fluxpackEIO3Armored = null;
    public static ItemStack fluxpackEIO4 = null;
    public static ItemStack fluxpackEIO4Armored = null;
    
    public static ItemStack leatherStrap = null;
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
    
    public static ItemStack particleDefault = null;
    public static ItemStack particleNone = null;
    public static ItemStack particleSmoke = null;
    public static ItemStack particleRainbowSmoke = null;
    
    private static boolean teAvailable = false;
    private static boolean raAvailable = false;
    private static boolean eioAvailable = false;
    
    public static void preInit() {
        teAvailable = Loader.isModLoaded("ThermalExpansion") && SJConfig.enableIntegrationTE;
        raAvailable = Loader.isModLoaded("RedstoneArsenal");
        eioAvailable = Loader.isModLoaded("EnderIO") && SJConfig.enableIntegrationEIO;
        
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
        
        Jetpack.reconstructJetpacks();
        jetpacksCommon = new ItemJetpack(ItemIndex.COMMON, ModType.COMMON);
        
        FluxPack.reconstructFluxPacks();
        fluxpacksCommon = new ItemFluxPack(ItemIndex.COMMON, ModType.COMMON);
        
        if (teAvailable) {
            jetpacksTE = new ItemJetpack(ItemIndex.PER_MOD, ModType.THERMAL_EXPANSION);
            jetpacksesPerMod.add(jetpacksTE);
            
            fluxpacksTE = new ItemFluxPack(ItemIndex.PER_MOD, ModType.THERMAL_EXPANSION);
            fluxpacksesPerMod.add(fluxpacksTE);
        }
        if (eioAvailable) {
            jetpacksEIO = new ItemJetpack(ItemIndex.PER_MOD, ModType.ENDER_IO);
            jetpacksesPerMod.add(jetpacksEIO);
            
            fluxpacksEIO = new ItemFluxPack(ItemIndex.PER_MOD, ModType.ENDER_IO);
            fluxpacksesPerMod.add(fluxpacksEIO);
        }
        
        components = new ItemMeta("components");
        armorPlatings = new ItemMeta("armorPlatings");
        particleCustomizers = new ItemMeta("particleCustomizers");
        mysteriousPotato = new ItemMysteriousPotato();
        
        jetpackPotato = jetpacksCommon.getChargedItem(jetpacksCommon, 0);
        jetpackCreative = new ItemStack(jetpacksCommon, 1, 9001);
        
        fluxpackCreative = new ItemStack(fluxpacksCommon, 1, 9001);
        
        if (teAvailable) {
            jetpackTE1 = new ItemStack(jetpacksTE, 1, 1);
            jetpackTE1Armored = new ItemStack(jetpacksTE, 1, 1 + Jetpack.ARMORED_META_OFFSET);
            jetpackTE2 = new ItemStack(jetpacksTE, 1, 2);
            jetpackTE2Armored = new ItemStack(jetpacksTE, 1, 2 + Jetpack.ARMORED_META_OFFSET);
            jetpackTE3 = new ItemStack(jetpacksTE, 1, 3);
            jetpackTE3Armored = new ItemStack(jetpacksTE, 1, 3 + Jetpack.ARMORED_META_OFFSET);
            jetpackTE4 = new ItemStack(jetpacksTE, 1, 4);
            jetpackTE4Armored = new ItemStack(jetpacksTE, 1, 4 + Jetpack.ARMORED_META_OFFSET);
            jetpackTE5 = new ItemStack(jetpacksTE, 1, 5);
            
            fluxpackTE1 = new ItemStack(fluxpacksTE, 1, 1);
            fluxpackTE2 = new ItemStack(fluxpacksTE, 1, 2);
            fluxpackTE2Armored = new ItemStack(fluxpacksTE, 1, 2 + FluxPack.ARMORED_META_OFFSET);
            fluxpackTE3 = new ItemStack(fluxpacksTE, 1, 3);
            fluxpackTE3Armored = new ItemStack(fluxpacksTE, 1, 3 + FluxPack.ARMORED_META_OFFSET);
            fluxpackTE4 = new ItemStack(fluxpacksTE, 1, 4);
            fluxpackTE4Armored = new ItemStack(fluxpacksTE, 1, 4 + FluxPack.ARMORED_META_OFFSET);
        }
        if (eioAvailable) {
            jetpackEIO1 = new ItemStack(jetpacksEIO, 1, 1);
            jetpackEIO1Armored = new ItemStack(jetpacksEIO, 1, 1 + Jetpack.ARMORED_META_OFFSET);
            jetpackEIO2 = new ItemStack(jetpacksEIO, 1, 2);
            jetpackEIO2Armored = new ItemStack(jetpacksEIO, 1, 2 + Jetpack.ARMORED_META_OFFSET);
            jetpackEIO3 = new ItemStack(jetpacksEIO, 1, 3);
            jetpackEIO3Armored = new ItemStack(jetpacksEIO, 1, 3 + Jetpack.ARMORED_META_OFFSET);
            jetpackEIO4 = new ItemStack(jetpacksEIO, 1, 4);
            jetpackEIO4Armored = new ItemStack(jetpacksEIO, 1, 4 + Jetpack.ARMORED_META_OFFSET);
            jetpackEIO5 = new ItemStack(jetpacksEIO, 1, 5);
            
            fluxpackEIO1 = new ItemStack(fluxpacksEIO, 1, 1);
            fluxpackEIO2 = new ItemStack(fluxpacksEIO, 1, 2);
            fluxpackEIO2Armored = new ItemStack(fluxpacksEIO, 1, 2 + FluxPack.ARMORED_META_OFFSET);
            fluxpackEIO3 = new ItemStack(fluxpacksEIO, 1, 3);
            fluxpackEIO3Armored = new ItemStack(fluxpacksEIO, 1, 3 + FluxPack.ARMORED_META_OFFSET);
            fluxpackEIO4 = new ItemStack(fluxpacksEIO, 1, 4);
            fluxpackEIO4Armored = new ItemStack(fluxpacksEIO, 1, 4 + FluxPack.ARMORED_META_OFFSET);
        }
        
        leatherStrap = components.addMetaItem(0, new MetaItem("leatherStrap", null, EnumRarity.common), true, false);
        
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
        }
        if (eioAvailable) {
            thrusterEIO1 = components.addMetaItem(21, new MetaItem("thruster.eio.1", null, EnumRarity.common), true, false);
            thrusterEIO2 = components.addMetaItem(22, new MetaItem("thruster.eio.2", null, EnumRarity.common), true, false);
            thrusterEIO3 = components.addMetaItem(23, new MetaItem("thruster.eio.3", null, EnumRarity.uncommon), true, false);
            thrusterEIO4 = components.addMetaItem(24, new MetaItem("thruster.eio.4", null, EnumRarity.rare), true, false);
            thrusterEIO5 = components.addMetaItem(25, new MetaItem("thruster.eio.5", null, EnumRarity.epic), true, false);
            richSoularium = components.addMetaItem(70, new MetaItem("richSoularium", null, EnumRarity.uncommon, true), true, false);
            reinforcedGliderWing = components.addMetaItem(71, new MetaItem("reinforcedGliderWing", null, EnumRarity.uncommon), true, false);
            unitFlightControlEmpty = components.addMetaItem(72, new MetaItem("unitFlightControl.empty", null, EnumRarity.common), true, false);
            unitFlightControl = components.addMetaItem(73, new MetaItem("unitFlightControl", null, EnumRarity.uncommon), true, false);
        }
        
        if (teAvailable) {
            armorPlatingTE1 = armorPlatings.addMetaItem(1, new MetaItem("armorPlating.te.1", null, EnumRarity.common), true, false);
            armorPlatingTE2 = armorPlatings.addMetaItem(2, new MetaItem("armorPlating.te.2", null, EnumRarity.common), true, false);
            armorPlatingTE3 = armorPlatings.addMetaItem(3, new MetaItem("armorPlating.te.3", null, EnumRarity.common), true, false);
            armorPlatingTE4 = armorPlatings.addMetaItem(4, new MetaItem("armorPlating.te.4", null, EnumRarity.rare), true, false);
        }
        if (eioAvailable) {
            armorPlatingEIO1 = armorPlatings.addMetaItem(11, new MetaItem("armorPlating.eio.1", null, EnumRarity.common), true, false);
            armorPlatingEIO2 = armorPlatings.addMetaItem(12, new MetaItem("armorPlating.eio.2", null, EnumRarity.common), true, false);
            armorPlatingEIO3 = armorPlatings.addMetaItem(13, new MetaItem("armorPlating.eio.3", null, EnumRarity.common), true, false);
            armorPlatingEIO4 = armorPlatings.addMetaItem(14, new MetaItem("armorPlating.eio.4", null, EnumRarity.common), true, false);
        }
        
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
        
        registerItem(jetpacksCommon, "jetpacksCommon");
        // For compatibility, do not change the following ID until 1.8
        registerItem(jetpacksTE, "jetpacks");
        registerItem(jetpacksEIO, "jetpacksEIO");
        
        registerItem(fluxpacksCommon, "fluxpacksCommon");
        // For compatibility, do not change the following ID until 1.8
        registerItem(fluxpacksTE, "fluxpacks");
        registerItem(fluxpacksEIO, "fluxpacksEIO");
        
        registerItem(components, "components");
        registerItem(armorPlatings, "armorPlatings");
        registerItem(particleCustomizers, "particleCustomizers");
        registerItem(mysteriousPotato, "mysteriousPotato");
        
        registerCustomItemStack("jetpack.potato", jetpackPotato);
        registerCustomItemStack("jetpack.creative", jetpackCreative);
        
        registerCustomItemStack("fluxpack.creative", fluxpackCreative);
        
        registerCustomItemStack("jetpack.te.1", jetpackTE1);
        registerCustomItemStack("jetpack.te.1.armored", jetpackTE1Armored);
        registerCustomItemStack("jetpack.te.2", jetpackTE2);
        registerCustomItemStack("jetpack.te.2.armored", jetpackTE2Armored);
        registerCustomItemStack("jetpack.te.3", jetpackTE3);
        registerCustomItemStack("jetpack.te.3.armored", jetpackTE3Armored);
        registerCustomItemStack("jetpack.te.4", jetpackTE4);
        registerCustomItemStack("jetpack.te.4.armored", jetpackTE4Armored);
        registerCustomItemStack("jetpack.te.5", jetpackTE5);
        registerCustomItemStack("jetpack.eio.1", jetpackEIO1);
        registerCustomItemStack("jetpack.eio.1.armored", jetpackEIO1Armored);
        registerCustomItemStack("jetpack.eio.2", jetpackEIO2);
        registerCustomItemStack("jetpack.eio.2.armored", jetpackEIO2Armored);
        registerCustomItemStack("jetpack.eio.3", jetpackEIO3);
        registerCustomItemStack("jetpack.eio.3.armored", jetpackEIO3Armored);
        registerCustomItemStack("jetpack.eio.4", jetpackEIO4);
        registerCustomItemStack("jetpack.eio.4.armored", jetpackEIO4Armored);
        registerCustomItemStack("jetpack.eio.5", jetpackEIO5);
        
        registerCustomItemStack("fluxpack.te.1", fluxpackTE1);
        registerCustomItemStack("fluxpack.te.2", fluxpackTE2);
        registerCustomItemStack("fluxpack.te.2.armored", fluxpackTE2Armored);
        registerCustomItemStack("fluxpack.te.3", fluxpackTE3);
        registerCustomItemStack("fluxpack.te.3.armored", fluxpackTE3Armored);
        registerCustomItemStack("fluxpack.te.4", fluxpackTE4);
        registerCustomItemStack("fluxpack.te.4.armored", fluxpackTE4Armored);
        registerCustomItemStack("fluxpack.eio.1", fluxpackEIO1);
        registerCustomItemStack("fluxpack.eio.2", fluxpackEIO2);
        registerCustomItemStack("fluxpack.eio.2.armored", fluxpackEIO2Armored);
        registerCustomItemStack("fluxpack.eio.3", fluxpackEIO3);
        registerCustomItemStack("fluxpack.eio.3.armored", fluxpackEIO3Armored);
        registerCustomItemStack("fluxpack.eio.4", fluxpackEIO4);
        registerCustomItemStack("fluxpack.eio.4.armored", fluxpackEIO4Armored);
    }
    
    private static void registerRecipes() {
        SimplyJetpacks.logger.info("Registering recipes");
        
        if (SJConfig.enableCraftingPotatoJetpack) {
            GameRegistry.addRecipe(new ShapedOreRecipe(jetpacksCommon.getChargedItem(jetpacksCommon, 0), new Object[] { "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetGold", 'P', Items.poisonous_potato, 'R', "dustRedstone" }));
        }
        
        GameRegistry.addRecipe(new ShapedOreRecipe(leatherStrap.copy(), new Object[] { "LIL", "LIL", 'L', Items.leather, 'I', "ingotIron" }));
        
        if (teAvailable) {
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE1.copy(), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotLead", 'P', "blockGlass", 'C', TEItems.powerCoilGold.copy(), 'G', "gearCopper", 'D', TEItems.dynamoSteam.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE2.copy(), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotInvar", 'P', Blocks.redstone_block, 'C', TEItems.powerCoilGold.copy(), 'G', "gearBronze", 'D', TEItems.dynamoReactant.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE3.copy(), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotElectrum", 'P', "ingotSignalum", 'C', TEItems.powerCoilGold.copy(), 'G', "gearInvar", 'D', TEItems.dynamoMagmatic.copy(), 'S', TEItems.pneumaticServo.copy() }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE4.copy(), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotEnderium", 'P', "ingotLumium", 'C', TEItems.powerCoilGold.copy(), 'G', "gearElectrum", 'D', TEItems.dynamoEnervation.copy(), 'S', TEItems.pneumaticServo.copy() }));
            
            if (SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new ShapedOreRecipe(armorPlatingTE1.copy(), new Object[] { "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin" }));
            }
            
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE1.copy(), new Object[] { "ICI", "ISI", 'I', "ingotLead", 'C', TEItems.cellBasic.copy(), 'S', leatherStrap.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE2.copy(), new Object[] { " I ", "ISI", " I ", 'I', "ingotInvar", 'S', fluxpackTE1.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE3.copy(), new Object[] { " C ", "ISI", "LOL", 'I', "ingotElectrum", 'L', "ingotLead", 'C', TEItems.frameCellReinforcedFull.copy(), 'S', fluxpackTE2.copy(), 'O', TEItems.powerCoilElectrum }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE4.copy(), new Object[] { " I ", "ISI", " I ", 'I', "ingotEnderium", 'S', fluxpackTE3.copy() }));
            
            if (SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE2Armored.copy(), new Object[] { "P", "J", 'J', fluxpackTE2.copy(), 'P', armorPlatingTE1.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE2.copy(), new Object[] { "J", 'J', fluxpackTE2Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE3Armored.copy(), new Object[] { "P", "J", 'J', fluxpackTE3.copy(), 'P', armorPlatingTE2.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE3.copy(), new Object[] { "J", 'J', fluxpackTE3Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE4Armored.copy(), new Object[] { "P", "J", 'J', fluxpackTE4.copy(), 'P', armorPlatingTE3.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackTE4.copy(), new Object[] { "J", 'J', fluxpackTE4Armored.copy() }));
            }
            
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE1.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic.copy(), 'T', thrusterTE1.copy(), 'J', leatherStrap.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE2.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened.copy(), 'T', thrusterTE2.copy(), 'J', jetpackTE1.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE3.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced.copy(), 'T', thrusterTE3.copy(), 'J', jetpackTE2.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE4.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant.copy(), 'T', thrusterTE4.copy(), 'J', jetpackTE3.copy() }));
            
            if (SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE1Armored.copy(), new Object[] { "P", "J", 'J', jetpackTE1.copy(), 'P', armorPlatingTE1.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE1.copy(), new Object[] { "J", 'J', jetpackTE1Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE2Armored.copy(), new Object[] { "P", "J", 'J', jetpackTE2.copy(), 'P', armorPlatingTE2.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE2.copy(), new Object[] { "J", 'J', jetpackTE2Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE3Armored.copy(), new Object[] { "P", "J", 'J', jetpackTE3.copy(), 'P', armorPlatingTE3.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE3.copy(), new Object[] { "J", 'J', jetpackTE3Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE4Armored.copy(), new Object[] { "P", "J", 'J', jetpackTE4.copy(), 'P', armorPlatingTE4.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE4.copy(), new Object[] { "J", 'J', jetpackTE4Armored.copy() }));
            }
            
            if (raAvailable && SJConfig.enableCraftingJetPlate && SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new ShapedOreRecipe(unitGlowstoneEmpty.copy(), new Object[] { "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.frameIlluminator.copy() }));
                GameRegistry.addRecipe(new ShapedOreRecipe(unitCryotheumEmpty.copy(), new Object[] { "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', "blockGlassHardened" }));
                GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE5.copy(), new Object[] { "FPF", "GRG", 'G', unitGlowstone.copy(), 'P', RAItems.plateFlux.copy(), 'R', thrusterTE4.copy(), 'F', "ingotElectrumFlux" }));
                ItemStack charger = SJConfig.jetpackConfigs.get(5).chargerRate > 0 ? fluxpackTE4Armored.copy() : TEItems.cellResonant.copy();
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE5.copy(), new Object[] { "PAP", "OJO", "TCT", 'A', new ItemStack(RAItems.armorFluxPlate.getItem(), 1, OreDictionary.WILDCARD_VALUE), 'J', jetpackTE4Armored.copy(), 'O', unitCryotheum.copy(), 'C', charger, 'T', thrusterTE5.copy(), 'P', RAItems.plateFlux.copy() }));
            }
        }
        if (eioAvailable) {
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO1.copy(), new Object[] { "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit.copy(), 'C', EIOItems.basicCapacitor.copy(), 'D', EIOItems.basicGear.copy(), 'S', "dustRedstone" }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO2.copy(), new Object[] { "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1.copy(), 'C', EIOItems.basicCapacitor.copy(), 'D', EIOItems.machineChassis.copy(), 'S', "dustRedstone" }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO3.copy(), new Object[] { "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2.copy(), 'C', EIOItems.doubleCapacitor.copy(), 'D', EIOItems.pulsatingCrystal.copy(), 'S', "ingotRedstoneAlloy" }));
            GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO4.copy(), new Object[] { "ICI", "PCP", "DSD", 'I', "ingotPhasedGold", 'P', EIOItems.energyConduit3.copy(), 'C', EIOItems.octadicCapacitor.copy(), 'D', EIOItems.vibrantCrystal.copy(), 'S', "ingotRedstoneAlloy" }));
            
            if (SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new ShapedOreRecipe(armorPlatingEIO1.copy(), new Object[] { "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon" }));
            }
            
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO1.copy(), new Object[] { "CIC", "ISI", "IPI", 'S', leatherStrap.copy(), 'C', EIOItems.basicCapacitor.copy(), 'I', "ingotConductiveIron", 'P', "dustCoal" }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO2.copy(), new Object[] { "DCD", "ISI", "IPI", 'S', fluxpackEIO1.copy(), 'C', EIOItems.basicCapacitor.copy(), 'D', EIOItems.doubleCapacitor.copy(), 'I', "ingotElectricalSteel", 'P', "dustGold" }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO3.copy(), new Object[] { "CBC", "ISI", "IPI", 'S', fluxpackEIO2.copy(), 'C', EIOItems.doubleCapacitor.copy(), 'B', EIOItems.capacitorBank.copy(), 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO4.copy(), new Object[] { "CBC", "ISI", "BPB", 'S', fluxpackEIO3.copy(), 'C', EIOItems.octadicCapacitor.copy(), 'B', EIOItems.capacitorBank.copy(), 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal.copy() }));
            
            if (SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO2Armored.copy(), new Object[] { "P", "J", 'J', fluxpackEIO2.copy(), 'P', armorPlatingEIO1.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO2.copy(), new Object[] { "J", 'J', fluxpackEIO2Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO3Armored.copy(), new Object[] { "P", "J", 'J', fluxpackEIO3.copy(), 'P', armorPlatingEIO2.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO3.copy(), new Object[] { "J", 'J', fluxpackEIO3Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO4Armored.copy(), new Object[] { "P", "J", 'J', fluxpackEIO4.copy(), 'P', armorPlatingEIO3.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(fluxpackEIO4.copy(), new Object[] { "J", 'J', fluxpackEIO4Armored.copy() }));
            }
            
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO1.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor.copy(), 'T', thrusterEIO1.copy(), 'J', leatherStrap.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO2.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor.copy(), 'T', thrusterEIO2.copy(), 'J', jetpackEIO1.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO3.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor.copy(), 'T', thrusterEIO3.copy(), 'J', jetpackEIO2.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO4.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotPhasedGold", 'B', EIOItems.octadicCapacitor.copy(), 'T', thrusterEIO4.copy(), 'J', jetpackEIO3.copy() }));
            
            if (SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO1Armored.copy(), new Object[] { "P", "J", 'J', jetpackEIO1.copy(), 'P', armorPlatingEIO1.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO1.copy(), new Object[] { "J", 'J', jetpackEIO1Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO2Armored.copy(), new Object[] { "P", "J", 'J', jetpackEIO2.copy(), 'P', armorPlatingEIO2.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO2.copy(), new Object[] { "J", 'J', jetpackEIO2Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO3Armored.copy(), new Object[] { "P", "J", 'J', jetpackEIO3.copy(), 'P', armorPlatingEIO3.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO3.copy(), new Object[] { "J", 'J', jetpackEIO3Armored.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO4Armored.copy(), new Object[] { "P", "J", 'J', jetpackEIO4.copy(), 'P', armorPlatingEIO4.copy() }));
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO4.copy(), new Object[] { "J", 'J', jetpackEIO4Armored.copy() }));
            }
            
            if (SJConfig.enableCraftingJetPlate && SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new ShapedOreRecipe(unitFlightControlEmpty.copy(), new Object[] { "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', richSoularium.copy(), 'H', "blockGlassHardened" }));
                GameRegistry.addRecipe(new ShapedOreRecipe(thrusterEIO5.copy(), new Object[] { "SES", "CTC", 'T', thrusterEIO4.copy(), 'S', richSoularium.copy(), 'E', unitFlightControl.copy(), 'C', EIOItems.octadicCapacitor.copy() }));
                GameRegistry.addRecipe(new ShapedOreRecipe(reinforcedGliderWing.copy(), new Object[] { "  S", " SP", "SPP", 'S', richSoularium.copy(), 'P', armorPlatingEIO2.copy() }));
                ItemStack charger = SJConfig.jetpackConfigs.get(5).chargerRate > 0 ? fluxpackEIO4Armored.copy() : EIOItems.capacitorBank.copy();
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackEIO5.copy(), new Object[] { "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal.copy(), 'J', jetpackEIO4Armored.copy(), 'O', richSoularium.copy(), 'C', charger, 'T', thrusterEIO5.copy(), 'P', reinforcedGliderWing.copy() }));
            }
        }
        
        GameRegistry.addRecipe(new ShapedOreRecipe(particleDefault.copy(), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', Blocks.torch }));
        GameRegistry.addRecipe(new ShapedOreRecipe(particleNone.copy(), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', "blockGlass" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(particleSmoke.copy(), new Object[] { " C ", "CCC", " C ", 'C', "dustCoal" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(particleRainbowSmoke.copy(), new Object[] { " R ", " C ", "G B", 'C', "dustCoal", 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue" }));
        
        Jetpack jetpack;
        for (int i = 0; i <= Jetpack.getHighestMeta(ItemIndex.COMMON); i++) {
            jetpack = Jetpack.getJetpack(ItemIndex.COMMON, i);
            if (jetpack != null && !(jetpack instanceof JetpackIcon)) {
                GameRegistry.addRecipe(new SJUpgradingRecipe(new ItemStack(jetpacksCommon, 1, i), new Object[] { "J", "P", 'J', new ItemStack(jetpacksCommon, 1, i), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
            }
        }
        for (int i = 0; i <= Jetpack.getHighestMeta(ItemIndex.PER_MOD); i++) {
            jetpack = Jetpack.getJetpack(ItemIndex.PER_MOD, i);
            if (jetpack != null) {
                for (ItemJetpack jetpacksItem : jetpacksesPerMod) {
                    GameRegistry.addRecipe(new SJUpgradingRecipe(new ItemStack(jetpacksItem, 1, i), new Object[] { "J", "P", 'J', new ItemStack(jetpacksItem, 1, i), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
                }
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
            }
            
            if (raAvailable && SJConfig.enableCraftingJetPlate && SJConfig.enableCraftingArmorPlating) {
                TERecipes.addTransposerFill(6400, unitGlowstoneEmpty.copy(), unitGlowstone.copy(), new FluidStack(FluidRegistry.getFluid("glowstone"), 4000), false);
                TERecipes.addTransposerFill(6400, unitCryotheumEmpty.copy(), unitCryotheum.copy(), new FluidStack(FluidRegistry.getFluid("cryotheum"), 4000), false);
            }
        }
        if (eioAvailable) {
            if (SJConfig.enableCraftingArmorPlating) {
                ItemStack ingotConductiveIron = OreDictionary.getOres("ingotConductiveIron").get(0).copy();
                ingotConductiveIron.stackSize = 10;
                EIORecipes.addAlloySmelterRecipe("Conductive Iron Armor Plating", 3200, armorPlatingEIO1.copy(), ingotConductiveIron, null, armorPlatingEIO2.copy());
                
                ItemStack ingotElectricalSteel = OreDictionary.getOres("ingotElectricalSteel").get(0).copy();
                ingotElectricalSteel.stackSize = 10;
                EIORecipes.addAlloySmelterRecipe("Electrical Steel Armor Plating", 4800, armorPlatingEIO2.copy(), ingotElectricalSteel, null, armorPlatingEIO3.copy());
                
                ItemStack ingotDarkSteel = OreDictionary.getOres("ingotDarkSteel").get(0).copy();
                ingotDarkSteel.stackSize = 10;
                EIORecipes.addAlloySmelterRecipe("Dark Steel Armor Plating", 6400, armorPlatingEIO3.copy(), ingotDarkSteel, null, armorPlatingEIO4.copy());
                
                if (SJConfig.enableCraftingJetPlate && SJConfig.enableCraftingArmorPlating) {
                    ItemStack ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0).copy();
                    ingotDarkSteel.stackSize = 1;
                    EIORecipes.addAlloySmelterRecipe("Enriched Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal.copy(), richSoularium.copy());
                    
                    EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", unitFlightControlEmpty.copy(), unitFlightControl.copy());
                }
            }
        }
    }
    
    private static void registerItem(Item item, String name) {
        if (item != null) {
            GameRegistry.registerItem(item, name);
        }
    }
    
    private static void registerCustomItemStack(String name, ItemStack itemStack) {
        if (itemStack != null) {
            GameRegistry.registerCustomItemStack(name, itemStack);
        }
    }
    
    public enum ModType {
        
        COMMON("", null), THERMAL_EXPANSION(".te", 0), ENDER_IO(".eio", 10);
        
        public final String suffix;
        public final Integer platingOffset;
        
        private ModType(String suffix, Integer platingOffset) {
            this.suffix = suffix;
            this.platingOffset = platingOffset;
        }
        
    }
    
}
