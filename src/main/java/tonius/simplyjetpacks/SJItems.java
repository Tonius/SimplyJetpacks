package tonius.simplyjetpacks;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemSJJetpack;
import tonius.simplyjetpacks.item.ItemSJJetpackArmored;
import tonius.simplyjetpacks.item.ItemSJJetpackPotato;
import tonius.simplyjetpacks.item.ItemSJMeta1;
import tonius.simplyjetpacks.item.ItemSJSimpleMeta;
import tonius.simplyjetpacks.recipes.JetpackUpgradingRecipe;
import tonius.simplyjetpacks.util.TE3Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJItems {

    public static EnumArmorMaterial enumArmorJetpack = EnumArmorMaterial.IRON;

    public static ItemSJJetpack[] jetpacks = null;
    public static ItemSJJetpack[] armoredJetpacks = null;

    public static ItemSJJetpack jetpackTier0 = null;
    public static ItemSJJetpack jetpackTier1 = null;
    public static ItemSJJetpack jetpackArmoredTier1 = null;
    public static ItemSJJetpack jetpackTier2 = null;
    public static ItemSJJetpack jetpackArmoredTier2 = null;
    public static ItemSJJetpack jetpackTier3 = null;
    public static ItemSJJetpack jetpackArmoredTier3 = null;
    public static ItemSJJetpack jetpackTier4 = null;
    public static ItemSJJetpack jetpackArmoredTier4 = null;
    public static ItemSJSimpleMeta metaItem1 = null;

    public static void preInit() {
        registerItems();
    }

    public static void init() {
        registerRecipes();
        doIMC();
    }

    private static void registerItems() {
        SimplyJetpacks.logger.info("Registering items");
        jetpackTier0 = new ItemSJJetpackPotato(ConfigReader.jetpackTier0ID, enumArmorJetpack);
        jetpackTier1 = new ItemSJJetpack(ConfigReader.jetpackTier1ID, enumArmorJetpack, "jetpackTier1", ConfigReader.jetpackTier1_maxEnergy, ConfigReader.jetpackTier1_maxChargingRate, 1, ConfigReader.jetpackTier1_energyUsage, ConfigReader.jetpackTier1_maxSpeed, ConfigReader.jetpackTier1_acceleration, ConfigReader.jetpackTier1_forwardThrust, ConfigReader.jetpackTier1_hoverModeIdleSpeed, ConfigReader.jetpackTier1_hoverModeSneakingSpeed);
        jetpackArmoredTier1 = new ItemSJJetpackArmored(ConfigReader.jetpackArmoredTier1ID, enumArmorJetpack, "jetpackArmoredTier1", ConfigReader.jetpackTier1_maxEnergy, ConfigReader.jetpackTier1_maxChargingRate, 1, ConfigReader.jetpackTier1_energyUsage, ConfigReader.jetpackTier1_maxSpeed, ConfigReader.jetpackTier1_acceleration, ConfigReader.jetpackTier1_forwardThrust, ConfigReader.jetpackTier1_hoverModeIdleSpeed, ConfigReader.jetpackTier1_hoverModeSneakingSpeed, ConfigReader.jetpackArmoredTier1_armorDisplay, ConfigReader.jetpackArmoredTier1_armorAbsorption, ConfigReader.jetpackArmoredTier1_damageEnergy);
        jetpackTier2 = new ItemSJJetpack(ConfigReader.jetpackTier2ID, enumArmorJetpack, "jetpackTier2", ConfigReader.jetpackTier2_maxEnergy, ConfigReader.jetpackTier2_maxChargingRate, 2, ConfigReader.jetpackTier2_energyUsage, ConfigReader.jetpackTier2_maxSpeed, ConfigReader.jetpackTier2_acceleration, ConfigReader.jetpackTier2_forwardThrust, ConfigReader.jetpackTier2_hoverModeIdleSpeed, ConfigReader.jetpackTier2_hoverModeSneakingSpeed);
        jetpackArmoredTier2 = new ItemSJJetpackArmored(ConfigReader.jetpackArmoredTier2ID, enumArmorJetpack, "jetpackArmoredTier2", ConfigReader.jetpackTier2_maxEnergy, ConfigReader.jetpackTier2_maxChargingRate, 2, ConfigReader.jetpackTier2_energyUsage, ConfigReader.jetpackTier2_maxSpeed, ConfigReader.jetpackTier2_acceleration, ConfigReader.jetpackTier2_forwardThrust, ConfigReader.jetpackTier2_hoverModeIdleSpeed, ConfigReader.jetpackTier2_hoverModeSneakingSpeed, ConfigReader.jetpackArmoredTier2_armorDisplay, ConfigReader.jetpackArmoredTier2_armorAbsorption, ConfigReader.jetpackArmoredTier2_damageEnergy);
        jetpackTier3 = new ItemSJJetpack(ConfigReader.jetpackTier3ID, enumArmorJetpack, "jetpackTier3", ConfigReader.jetpackTier3_maxEnergy, ConfigReader.jetpackTier3_maxChargingRate, 3, ConfigReader.jetpackTier3_energyUsage, ConfigReader.jetpackTier3_maxSpeed, ConfigReader.jetpackTier3_acceleration, ConfigReader.jetpackTier3_forwardThrust, ConfigReader.jetpackTier3_hoverModeIdleSpeed, ConfigReader.jetpackTier3_hoverModeSneakingSpeed);
        jetpackArmoredTier3 = new ItemSJJetpackArmored(ConfigReader.jetpackArmoredTier3ID, enumArmorJetpack, "jetpackArmoredTier3", ConfigReader.jetpackTier3_maxEnergy, ConfigReader.jetpackTier3_maxChargingRate, 3, ConfigReader.jetpackTier3_energyUsage, ConfigReader.jetpackTier3_maxSpeed, ConfigReader.jetpackTier3_acceleration, ConfigReader.jetpackTier3_forwardThrust, ConfigReader.jetpackTier3_hoverModeIdleSpeed, ConfigReader.jetpackTier3_hoverModeSneakingSpeed, ConfigReader.jetpackArmoredTier3_armorDisplay, ConfigReader.jetpackArmoredTier3_armorAbsorption, ConfigReader.jetpackArmoredTier3_damageEnergy);
        jetpackTier4 = new ItemSJJetpack(ConfigReader.jetpackTier4ID, enumArmorJetpack, "jetpackTier4", ConfigReader.jetpackTier4_maxEnergy, ConfigReader.jetpackTier4_maxChargingRate, 4, ConfigReader.jetpackTier4_energyUsage, ConfigReader.jetpackTier4_maxSpeed, ConfigReader.jetpackTier4_acceleration, ConfigReader.jetpackTier4_forwardThrust, ConfigReader.jetpackTier4_hoverModeIdleSpeed, ConfigReader.jetpackTier4_hoverModeSneakingSpeed);
        jetpackArmoredTier4 = new ItemSJJetpackArmored(ConfigReader.jetpackArmoredTier4ID, enumArmorJetpack, "jetpackArmoredTier4", ConfigReader.jetpackTier4_maxEnergy, ConfigReader.jetpackTier4_maxChargingRate, 4, ConfigReader.jetpackTier4_energyUsage, ConfigReader.jetpackTier4_maxSpeed, ConfigReader.jetpackTier4_acceleration, ConfigReader.jetpackTier4_forwardThrust, ConfigReader.jetpackTier4_hoverModeIdleSpeed, ConfigReader.jetpackTier4_hoverModeSneakingSpeed, ConfigReader.jetpackArmoredTier4_armorDisplay, ConfigReader.jetpackArmoredTier4_armorAbsorption, ConfigReader.jetpackArmoredTier4_damageEnergy);

        jetpacks = new ItemSJJetpack[] { jetpackTier0, jetpackTier1, jetpackTier2, jetpackTier3, jetpackTier4 };
        armoredJetpacks = new ItemSJJetpack[] { null, jetpackArmoredTier1, jetpackArmoredTier2, jetpackArmoredTier3, jetpackArmoredTier4 };

        metaItem1 = new ItemSJMeta1(ConfigReader.metaItem1ID);
    }

    private static void registerRecipes() {
        SimplyJetpacks.logger.info("Registering recipes");

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
            GameRegistry.addRecipe(new ShapedOreRecipe(jetpackTier0.getChargedItem(jetpackTier0).copy(), new Object[] { "S S", "NPN", "R R", 'S', Item.silk, 'N', "nuggetTin", 'P', capacitorPotato, 'R', "dustRedstone" }));
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 0), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotLead", 'P', conduitItemOpaque, 'C', powerCoilGold, 'G', "gearCopper", 'D', dynamoSteam, 'S', pneumaticServo }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 1), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotInvar", 'P', conduitItemFastOpaque, 'C', powerCoilGold, 'G', "gearBronze", 'D', dynamoCompression, 'S', pneumaticServo }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 2), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotElectrum", 'P', conduitEnergyReinforcedEmpty, 'C', powerCoilGold, 'G', "gearInvar", 'D', dynamoReactant, 'S', pneumaticServo }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 3), new Object[] { "ICI", "PGP", "DSD", 'I', "ingotEnderium", 'P', conduitEnergyReinforced, 'C', powerCoilGold, 'G', "gearElectrum", 'D', dynamoMagmatic, 'S', pneumaticServo }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 4), new Object[] { "LIL", "LIL", 'L', Item.leather, 'I', "ingotIron" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(metaItem1, 1, 5), new Object[] { "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin" }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier1), new Object[] { "IBI", "IJI", "T T", 'I', "ingotLead", 'B', capacitorBasic, 'T', new ItemStack(metaItem1, 1, 0), 'J', new ItemStack(metaItem1, 1, 4) }));
        GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier2), new Object[] { "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', capacitorHardened, 'T', new ItemStack(metaItem1, 1, 1), 'J', new ItemStack(jetpackTier1, 1, OreDictionary.WILDCARD_VALUE) }));
        GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', capacitorReinforced, 'T', new ItemStack(metaItem1, 1, 2), 'J', new ItemStack(jetpackTier2, 1, OreDictionary.WILDCARD_VALUE) }));
        GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', capacitorResonant, 'T', new ItemStack(metaItem1, 1, 3), 'J', new ItemStack(jetpackTier3, 1, OreDictionary.WILDCARD_VALUE) }));

        if (!ConfigReader.upgradingRecipesOnly) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier2), new Object[] { "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', capacitorHardened, 'T', new ItemStack(metaItem1, 1, 1), 'J', new ItemStack(metaItem1, 1, 4) }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', capacitorReinforced, 'T', new ItemStack(metaItem1, 1, 2), 'J', new ItemStack(metaItem1, 1, 4) }));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', capacitorResonant, 'T', new ItemStack(metaItem1, 1, 3), 'J', new ItemStack(metaItem1, 1, 4) }));

            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier3), new Object[] { "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', capacitorReinforced, 'T', new ItemStack(metaItem1, 1, 2), 'J', new ItemStack(jetpackTier1, 1, OreDictionary.WILDCARD_VALUE) }));
            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', capacitorResonant, 'T', new ItemStack(metaItem1, 1, 3), 'J', new ItemStack(jetpackTier1, 1, OreDictionary.WILDCARD_VALUE) }));
            GameRegistry.addRecipe(new JetpackUpgradingRecipe(new ItemStack(jetpackTier4), new Object[] { "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', capacitorResonant, 'T', new ItemStack(metaItem1, 1, 3), 'J', new ItemStack(jetpackTier2, 1, OreDictionary.WILDCARD_VALUE) }));
        }
    }

    private static void doIMC() {
        SimplyJetpacks.logger.info("Doing intermod communication");
        ItemStack ingotBronze;
        for (int i = 0; i < OreDictionary.getOres("ingotBronze").size(); i++) {
            ingotBronze = OreDictionary.getOres("ingotBronze").get(i);
            ingotBronze.stackSize = 8;
            TE3Utils.addSmelterRecipe(3200, new ItemStack(metaItem1, 1, 5), ingotBronze.copy(), new ItemStack(metaItem1, 1, 6), null, 0);
        }

        ItemStack ingotInvar;
        for (int i = 0; i < OreDictionary.getOres("ingotInvar").size(); i++) {
            ingotInvar = OreDictionary.getOres("ingotInvar").get(i);
            ingotInvar.stackSize = 8;
            TE3Utils.addSmelterRecipe(4800, new ItemStack(metaItem1, 1, 6), ingotInvar.copy(), new ItemStack(metaItem1, 1, 7), null, 0);
        }

        ItemStack ingotEnderium;
        for (int i = 0; i < OreDictionary.getOres("ingotEnderium").size(); i++) {
            ingotEnderium = OreDictionary.getOres("ingotEnderium").get(i);
            ingotEnderium.stackSize = 8;
            TE3Utils.addSmelterRecipe(6400, new ItemStack(metaItem1, 1, 7), ingotEnderium.copy(), new ItemStack(metaItem1, 1, 8), null, 0);
        }
    }

}
