package tonius.simplyjetpacks.setup;

import java.util.ArrayList;
import java.util.List;

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
import tonius.simplyjetpacks.item.ItemIndex;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.item.ItemMeta.MetaItem;
import tonius.simplyjetpacks.item.ItemMysteriousPotato;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.recipes.SJUpgradingRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class SJItems {
    
    public static ItemJetpack jetpacksCommon = null;
    public static ItemJetpack jetpacksTE = null;
    
    public static List<ItemJetpack> jetpacksesPerMod = new ArrayList<ItemJetpack>();
    
    public static ItemFluxPack fluxpacksCommon = null;
    public static ItemFluxPack fluxpacksTE = null;
    
    public static List<ItemFluxPack> fluxpacksesPerMod = new ArrayList<ItemFluxPack>();
    
    public static ItemMeta components = null;
    public static ItemMeta armorPlatings = null;
    public static ItemMeta particleCustomizers = null;
    public static ItemMysteriousPotato mysteriousPotato = null;
    
    public static ItemStack jetpackPotato = null;
    public static ItemStack jetpackCreative = null;
    
    public static ItemStack jetpackTE1 = null;
    public static ItemStack jetpackTE1Armored = null;
    public static ItemStack jetpackTE2 = null;
    public static ItemStack jetpackTE2Armored = null;
    public static ItemStack jetpackTE3 = null;
    public static ItemStack jetpackTE3Armored = null;
    public static ItemStack jetpackTE4 = null;
    public static ItemStack jetpackTE4Armored = null;
    public static ItemStack jetpackTE5 = null;
    
    public static ItemStack fluxpackCreative = null;
    
    public static ItemStack fluxpackTE1 = null;
    public static ItemStack fluxpackTE2 = null;
    public static ItemStack fluxpackTE2Armored = null;
    public static ItemStack fluxpackTE3 = null;
    public static ItemStack fluxpackTE3Armored = null;
    public static ItemStack fluxpackTE4 = null;
    public static ItemStack fluxpackTE4Armored = null;
    
    public static ItemStack leatherStrap = null;
    public static ItemStack thrusterTE1 = null;
    public static ItemStack thrusterTE2 = null;
    public static ItemStack thrusterTE3 = null;
    public static ItemStack thrusterTE4 = null;
    public static ItemStack thrusterTE5 = null;
    public static ItemStack unitGlowstoneEmpty = null;
    public static ItemStack unitGlowstone = null;
    public static ItemStack unitCryotheumEmpty = null;
    public static ItemStack unitCryotheum = null;
    
    public static ItemStack armorPlatingTE1 = null;
    public static ItemStack armorPlatingTE2 = null;
    public static ItemStack armorPlatingTE3 = null;
    public static ItemStack armorPlatingTE4 = null;
    
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
        jetpacksCommon = new ItemJetpack(ItemIndex.COMMON, ModType.COMMON);
        jetpacksTE = new ItemJetpack(ItemIndex.PER_MOD, ModType.THERMAL_EXPANSION);
        jetpacksesPerMod.add(jetpacksTE);
        
        FluxPack.reconstructFluxPacks();
        fluxpacksCommon = new ItemFluxPack(ItemIndex.COMMON, ModType.COMMON);
        fluxpacksTE = new ItemFluxPack(ItemIndex.PER_MOD, ModType.THERMAL_EXPANSION);
        fluxpacksesPerMod.add(fluxpacksTE);
        
        components = new ItemMeta("components");
        armorPlatings = new ItemMeta("armorPlatings");
        particleCustomizers = new ItemMeta("particleCustomizers");
        mysteriousPotato = new ItemMysteriousPotato();
        
        jetpackPotato = jetpacksCommon.getChargedItem(jetpacksCommon, 0);
        jetpackCreative = new ItemStack(jetpacksCommon, 1, 9001);
        
        jetpackTE1 = new ItemStack(jetpacksTE, 1, 1);
        jetpackTE1Armored = new ItemStack(jetpacksTE, 1, 1 + Jetpack.ARMORED_META_OFFSET);
        jetpackTE2 = new ItemStack(jetpacksTE, 1, 2);
        jetpackTE2Armored = new ItemStack(jetpacksTE, 1, 2 + Jetpack.ARMORED_META_OFFSET);
        jetpackTE3 = new ItemStack(jetpacksTE, 1, 3);
        jetpackTE3Armored = new ItemStack(jetpacksTE, 1, 3 + Jetpack.ARMORED_META_OFFSET);
        jetpackTE4 = new ItemStack(jetpacksTE, 1, 4);
        jetpackTE4Armored = new ItemStack(jetpacksTE, 1, 4 + Jetpack.ARMORED_META_OFFSET);
        jetpackTE5 = new ItemStack(jetpacksTE, 1, 5);
        
        fluxpackCreative = new ItemStack(fluxpacksCommon, 1, 9001);
        
        fluxpackTE1 = new ItemStack(fluxpacksTE, 1, 1);
        fluxpackTE2 = new ItemStack(fluxpacksTE, 1, 2);
        fluxpackTE2Armored = new ItemStack(fluxpacksTE, 1, 2 + FluxPack.ARMORED_META_OFFSET);
        fluxpackTE3 = new ItemStack(fluxpacksTE, 1, 3);
        fluxpackTE3Armored = new ItemStack(fluxpacksTE, 1, 3 + FluxPack.ARMORED_META_OFFSET);
        fluxpackTE4 = new ItemStack(fluxpacksTE, 1, 4);
        fluxpackTE4Armored = new ItemStack(fluxpacksTE, 1, 4 + FluxPack.ARMORED_META_OFFSET);
        
        leatherStrap = components.addMetaItem(0, new MetaItem("leatherStrap", null, EnumRarity.common), true, false);
        
        thrusterTE1 = components.addMetaItem(11, new MetaItem("thruster.te.1", null, EnumRarity.common), true, false);
        thrusterTE2 = components.addMetaItem(12, new MetaItem("thruster.te.2", null, EnumRarity.common), true, false);
        thrusterTE3 = components.addMetaItem(13, new MetaItem("thruster.te.3", null, EnumRarity.uncommon), true, false);
        thrusterTE4 = components.addMetaItem(14, new MetaItem("thruster.te.4", null, EnumRarity.rare), true, false);
        thrusterTE5 = components.addMetaItem(15, new MetaItem("thruster.te.5", null, EnumRarity.epic), true, false);
        
        unitGlowstoneEmpty = components.addMetaItem(60, new MetaItem("unitGlowstone.empty", null, EnumRarity.common), true, false);
        unitGlowstone = components.addMetaItem(61, new MetaItem("unitGlowstone", null, EnumRarity.uncommon), true, false);
        unitCryotheumEmpty = components.addMetaItem(62, new MetaItem("unitCryotheum.empty", null, EnumRarity.common), true, false);
        unitCryotheum = components.addMetaItem(63, new MetaItem("unitCryotheum", null, EnumRarity.rare), true, false);
        
        armorPlatingTE1 = armorPlatings.addMetaItem(1, new MetaItem("armorPlating.te.1", null, EnumRarity.common), true, false);
        armorPlatingTE2 = armorPlatings.addMetaItem(2, new MetaItem("armorPlating.te.2", null, EnumRarity.common), true, false);
        armorPlatingTE3 = armorPlatings.addMetaItem(3, new MetaItem("armorPlating.te.3", null, EnumRarity.common), true, false);
        armorPlatingTE4 = armorPlatings.addMetaItem(4, new MetaItem("armorPlating.te.4", null, EnumRarity.rare), true, false);
        
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
        
        GameRegistry.registerItem(jetpacksCommon, "jetpacksCommon");
        // For compatibility, do not change the following ID until 1.8
        GameRegistry.registerItem(jetpacksTE, "jetpacks");
        
        GameRegistry.registerItem(fluxpacksCommon, "fluxpacksCommon");
        // For compatibility, do not change the following ID until 1.8
        GameRegistry.registerItem(fluxpacksTE, "fluxpacks");
        
        GameRegistry.registerItem(components, "components");
        GameRegistry.registerItem(armorPlatings, "armorPlatings");
        GameRegistry.registerItem(particleCustomizers, "particleCustomizers");
        GameRegistry.registerItem(mysteriousPotato, "mysteriousPotato");
        
        GameRegistry.registerCustomItemStack("jetpack.potato", jetpackPotato);
        GameRegistry.registerCustomItemStack("jetpack.creative", jetpackCreative);
        
        GameRegistry.registerCustomItemStack("jetpack.te.1", jetpackTE1);
        GameRegistry.registerCustomItemStack("jetpack.te.1.armored", jetpackTE1Armored);
        GameRegistry.registerCustomItemStack("jetpack.te.2", jetpackTE2);
        GameRegistry.registerCustomItemStack("jetpack.te.2.armored", jetpackTE2Armored);
        GameRegistry.registerCustomItemStack("jetpack.te.3", jetpackTE3);
        GameRegistry.registerCustomItemStack("jetpack.te.3.armored", jetpackTE3Armored);
        GameRegistry.registerCustomItemStack("jetpack.te.4", jetpackTE4);
        GameRegistry.registerCustomItemStack("jetpack.te.4.armored", jetpackTE4Armored);
        GameRegistry.registerCustomItemStack("jetpack.te.5", jetpackTE5);
        
        GameRegistry.registerCustomItemStack("fluxpack.creative", fluxpackCreative);
        
        GameRegistry.registerCustomItemStack("fluxpack.te.1", fluxpackTE1);
        GameRegistry.registerCustomItemStack("fluxpack.te.2", fluxpackTE2);
        GameRegistry.registerCustomItemStack("fluxpack.te.2.armored", fluxpackTE2Armored);
        GameRegistry.registerCustomItemStack("fluxpack.te.3", fluxpackTE3);
        GameRegistry.registerCustomItemStack("fluxpack.te.3.armored", fluxpackTE3Armored);
        GameRegistry.registerCustomItemStack("fluxpack.te.4", fluxpackTE4);
        GameRegistry.registerCustomItemStack("fluxpack.te.4.armored", fluxpackTE4Armored);
    }
    
    private static void registerRecipes() {
        SimplyJetpacks.logger.info("Registering recipes");
        
        if (teAvailable) {
            if (SJConfig.enableCraftingPotatoJetpack) {
                GameRegistry.addRecipe(new ShapedOreRecipe(jetpacksCommon.getChargedItem(jetpacksCommon, 0), new Object[] { "S S", "NPN", "R R", 'S', Items.string, 'N', "nuggetTin", 'P', TEItems.capacitorPotato.copy(), 'R', "dustRedstone" }));
            }
            
            GameRegistry.addRecipe(new ShapedOreRecipe(leatherStrap.copy(), new Object[] { "LIL", "LIL", 'L', Items.leather, 'I', "ingotIron" }));
            
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
            
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE1.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic.copy(), 'T', thrusterTE1.copy(), 'J', leatherStrap.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE2.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened.copy(), 'T', thrusterTE2.copy(), 'J', jetpackTE1.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE3.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced.copy(), 'T', thrusterTE3.copy(), 'J', jetpackTE2.copy() }));
            GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE4.copy(), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant.copy(), 'T', thrusterTE4.copy(), 'J', jetpackTE3.copy() }));
            
            if (raAvailable && SJConfig.enableCraftingFluxJetPlate && SJConfig.enableCraftingArmorPlating) {
                GameRegistry.addRecipe(new ShapedOreRecipe(unitGlowstoneEmpty.copy(), new Object[] { "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.frameIlluminator.copy() }));
                GameRegistry.addRecipe(new ShapedOreRecipe(unitCryotheumEmpty.copy(), new Object[] { "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', TEItems.blockGlassHardened.copy() }));
                GameRegistry.addRecipe(new ShapedOreRecipe(thrusterTE5.copy(), new Object[] { "FPF", "GRG", 'G', unitGlowstone.copy(), 'P', RAItems.plateFlux.copy(), 'R', thrusterTE4.copy(), 'F', "ingotElectrumFlux" }));
                ItemStack charger = SJConfig.jetpackConfigs.get(5).chargerRate > 0 ? fluxpackTE4Armored.copy() : TEItems.cellResonant.copy();
                GameRegistry.addRecipe(new SJUpgradingRecipe(jetpackTE5.copy(), new Object[] { "PAP", "OJO", "TCT", 'A', new ItemStack(RAItems.armorFluxPlate.getItem(), 1, OreDictionary.WILDCARD_VALUE), 'J', jetpackTE4Armored.copy(), 'O', unitCryotheum.copy(), 'C', charger, 'T', thrusterTE5.copy(), 'P', RAItems.plateFlux.copy() }));
            }
            
            GameRegistry.addRecipe(new ShapedOreRecipe(particleDefault.copy(), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', Blocks.torch }));
            GameRegistry.addRecipe(new ShapedOreRecipe(particleNone.copy(), new Object[] { " D ", "DCD", " D ", 'C', "dustCoal", 'D', "blockGlass" }));
            GameRegistry.addRecipe(new ShapedOreRecipe(particleSmoke.copy(), new Object[] { " C ", "CCC", " C ", 'C', "dustCoal" }));
            GameRegistry.addRecipe(new ShapedOreRecipe(particleRainbowSmoke.copy(), new Object[] { " R ", " C ", "G B", 'C', "dustCoal", 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue" }));
        }
        
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
                GameRegistry.addRecipe(new SJUpgradingRecipe(new ItemStack(jetpacksTE, 1, i), new Object[] { "J", "P", 'J', new ItemStack(jetpacksTE, 1, i), 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE) }));
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
            
            if (raAvailable) {
                TERecipes.addTransposerFill(6400, unitGlowstoneEmpty.copy(), unitGlowstone.copy(), new FluidStack(FluidRegistry.getFluid("glowstone"), 4000), false);
                TERecipes.addTransposerFill(6400, unitCryotheumEmpty.copy(), unitCryotheum.copy(), new FluidStack(FluidRegistry.getFluid("cryotheum"), 4000), false);
            }
        }
    }
    
    public enum ModType {
        
        COMMON("", null), THERMAL_EXPANSION(".te", 0);
        
        public final String suffix;
        public final Integer platingOffset;
        
        private ModType(String suffix, Integer platingOffset) {
            this.suffix = suffix;
            this.platingOffset = platingOffset;
        }
        
    }
    
}
