package tonius.simplyjetpacks.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SJConfig {

    public static Configuration config;
    public static List<ConfigSection> configSections = new ArrayList<ConfigSection>();

    public static final ConfigSection sectionItem = new ConfigSection("Item Settings", "item");
    public static final ConfigSection sectionCrafting = new ConfigSection("Crafting Settings", "crafting");
    public static final ConfigSection sectionGui = new ConfigSection("GUI Settings", "gui");

    public static final ConfigSection sectionTuningTuberous = new ConfigSection("Tuning - Tuberous Jetpack", "tuningTuberous");
    public static final ConfigSection sectionTuningLeadstone = new ConfigSection("Tuning - Leadstone Jetpack", "tuningLeadstone");
    public static final ConfigSection sectionTuningHardened = new ConfigSection("Tuning - Hardened Jetpack", "tuningHardened");
    public static final ConfigSection sectionTuningReinforced = new ConfigSection("Tuning - Reinforced Jetpack", "tuningReinforced");
    public static final ConfigSection sectionTuningResonant = new ConfigSection("Tuning - Resonant Jetpack", "tuningResonant");
    public static final ConfigSection sectionTuningFluxPlate = new ConfigSection("Tuning - Flux-Infused JetPlate", "tuningFluxPlate");
    public static final ConfigSection sectionTuningCreative = new ConfigSection("Tuning - Creative Jetpack", "tuningCreative");

    // item default
    public static final boolean enableJetpackParticles_default = true;
    public static final boolean invertHoverSneakingBehavior_default = false;
    public static final int jetpackEnchantability_default = 20;

    // crafting default
    public static final boolean enableCraftingArmorPlating_default = true;
    public static final boolean enableCraftingPotatoJetpack_default = true;

    // gui default
    public static final boolean enableEnergyHUD_default = true;
    public static final int energyHUDOffsetX_default = 0;
    public static final int energyHUDOffsetY_default = 0;
    public static final int energyHUDPosition_default = HUDPosition.TOP_LEFT.ordinal();
    public static final double energyHUDScale_default = 1.0F;
    public static final boolean holdShiftForDetails_default = true;
    public static final boolean minimalEnergyHUD_default = false;
    public static final boolean showEnergyHUDWhileChatting_default = true;
    public static final boolean showExactEnergyInHUD_default = false;

    // tuningTuberous default
    public static final int tuberousEnergyCapacity_default = 1200;
    public static final int tuberousEnergyPerTick_default = 45;
    public static final double tuberousSpeedVertical_default = 0.9D;
    public static final double tuberousAccelVertical_default = 0.5D;

    // tuningLeadstone default
    public static final int leadstoneEnergyCapacity_default = 25000;
    public static final int leadstoneEnergyPerTick_default = 20;
    public static final double leadstoneSpeedVertical_default = 0.22D;
    public static final double leadstoneAccelVertical_default = 0.1D;
    public static final double leadstoneSpeedSideways_default = 0.0D;
    public static final double leadstoneSpeedVerticalHover_default = 0.18D;
    public static final double leadstoneSpeedVerticalHoverSlow_default = 0.14D;
    public static final boolean leadstoneEmergencyHoverMode_default = false;
    public static final int leadstoneArmorDisplay_default = 4;
    public static final double leadstoneArmorAbsorption_default = 0.2D;
    public static final int leadstoneArmorEnergyPerHit_default = 100;
    public static final boolean leadstoneEnchantable_default = false;

    // tuningHardened default
    public static final int hardenedEnergyCapacity_default = 400000;
    public static final int hardenedEnergyPerTick_default = 75;
    public static final double hardenedSpeedVertical_default = 0.3D;
    public static final double hardenedAccelVertical_default = 0.11D;
    public static final double hardenedSpeedSideways_default = 0.04D;
    public static final double hardenedSpeedVerticalHover_default = 0.18D;
    public static final double hardenedSpeedVerticalHoverSlow_default = 0.1D;
    public static final boolean hardenedEmergencyHoverMode_default = false;
    public static final int hardenedArmorDisplay_default = 5;
    public static final double hardenedArmorAbsorption_default = 0.3D;
    public static final int hardenedArmorEnergyPerHit_default = 95;
    public static final boolean hardenedEnchantable_default = false;

    // tuningReinforced default
    public static final int reinforcedEnergyCapacity_default = 2000000;
    public static final int reinforcedEnergyPerTick_default = 150;
    public static final double reinforcedSpeedVertical_default = 0.48D;
    public static final double reinforcedAccelVertical_default = 0.12D;
    public static final double reinforcedSpeedSideways_default = 0.14D;
    public static final double reinforcedSpeedVerticalHover_default = 0.34D;
    public static final double reinforcedSpeedVerticalHoverSlow_default = 0.03D;
    public static final boolean reinforcedEmergencyHoverMode_default = true;
    public static final int reinforcedArmorDisplay_default = 6;
    public static final double reinforcedArmorAbsorption_default = 0.4D;
    public static final int reinforcedArmorEnergyPerHit_default = 90;
    public static final boolean reinforcedEnchantable_default = true;

    // tuningResonant default
    public static final int resonantEnergyCapacity_default = 10000000;
    public static final int resonantEnergyPerTick_default = 400;
    public static final double resonantSpeedVertical_default = 0.8D;
    public static final double resonantAccelVertical_default = 0.15D;
    public static final double resonantSpeedSideways_default = 0.19D;
    public static final double resonantSpeedVerticalHover_default = 0.4D;
    public static final double resonantSpeedVerticalHoverSlow_default = 0.005D;
    public static final boolean resonantEmergencyHoverMode_default = true;
    public static final int resonantArmorDisplay_default = 7;
    public static final double resonantArmorAbsorption_default = 0.6D;
    public static final int resonantArmorEnergyPerHit_default = 85;
    public static final boolean resonantEnchantable_default = true;

    // tuningFluxPlate default
    public static final int fluxPlateEnergyCapacity_default = 50000000;
    public static final int fluxPlateEnergyPerTick_default = 525;
    public static final double fluxPlateSpeedVertical_default = 0.8D;
    public static final double fluxPlateAccelVertical_default = 0.15D;
    public static final double fluxPlateSpeedSideways_default = 0.19D;
    public static final double fluxPlateSpeedVerticalHover_default = 0.4D;
    public static final double fluxPlateSpeedVerticalHoverSlow_default = 0.0D;
    public static final boolean fluxPlateEmergencyHoverMode_default = true;
    public static final int fluxPlateArmorDisplay_default = 8;
    public static final double fluxPlateArmorAbsorption_default = 0.8D;
    public static final int fluxPlateArmorEnergyPerHit_default = 80;
    public static final boolean fluxPlateEnchantable_default = true;

    // tuningCreative default
    public static final double creativeSpeedVertical_default = 0.8D;
    public static final double creativeAccelVertical_default = 0.15D;
    public static final double creativeSpeedSideways_default = 0.19D;
    public static final double creativeSpeedVerticalHover_default = 0.4D;
    public static final double creativeSpeedVerticalHoverSlow_default = 0.0D;
    public static final boolean creativeEmergencyHoverMode_default = true;
    public static final int creativeArmorDisplay_default = 8;
    public static final double creativeArmorAbsorption_default = 0.6D;
    public static final boolean creativeEnchantable_default = true;

    // item
    public static boolean enableJetpackParticles = enableJetpackParticles_default;
    public static boolean invertHoverSneakingBehavior = invertHoverSneakingBehavior_default;
    public static int jetpackEnchantability = jetpackEnchantability_default;

    // crafting
    public static boolean enableCraftingArmorPlating = enableCraftingArmorPlating_default;
    public static boolean enableCraftingPotatoJetpack = enableCraftingPotatoJetpack_default;

    // gui
    public static boolean enableEnergyHUD = enableEnergyHUD_default;
    public static int energyHUDOffsetX = energyHUDOffsetX_default;
    public static int energyHUDOffsetY = energyHUDOffsetY_default;
    public static int energyHUDPosition = energyHUDPosition_default;
    public static double energyHUDScale = energyHUDScale_default;
    public static boolean holdShiftForDetails = holdShiftForDetails_default;
    public static boolean minimalEnergyHUD = minimalEnergyHUD_default;
    public static boolean showEnergyHUDWhileChatting = showEnergyHUDWhileChatting_default;
    public static boolean showExactEnergyInHUD = showExactEnergyInHUD_default;

    // tuningTuberous
    public static int tuberousEnergyCapacity = tuberousEnergyCapacity_default;
    public static int tuberousEnergyPerTick = tuberousEnergyPerTick_default;
    public static double tuberousSpeedVertical = tuberousSpeedVertical_default;
    public static double tuberousAccelVertical = tuberousAccelVertical_default;

    // tuningLeadstone
    public static int leadstoneEnergyCapacity = leadstoneEnergyCapacity_default;
    public static int leadstoneEnergyPerTick = leadstoneEnergyPerTick_default;
    public static double leadstoneSpeedVertical = leadstoneSpeedVertical_default;
    public static double leadstoneAccelVertical = leadstoneAccelVertical_default;
    public static double leadstoneSpeedSideways = leadstoneSpeedSideways_default;
    public static double leadstoneSpeedVerticalHover = leadstoneSpeedVerticalHover_default;
    public static double leadstoneSpeedVerticalHoverSlow = leadstoneSpeedVerticalHoverSlow_default;
    public static boolean leadstoneEmergencyHoverMode = leadstoneEmergencyHoverMode_default;
    public static int leadstoneArmorDisplay = leadstoneArmorDisplay_default;
    public static double leadstoneArmorAbsorption = leadstoneArmorAbsorption_default;
    public static int leadstoneArmorEnergyPerHit = leadstoneArmorEnergyPerHit_default;
    public static boolean leadstoneEnchantable = leadstoneEnchantable_default;

    // tuningHardened
    public static int hardenedEnergyCapacity = hardenedEnergyCapacity_default;
    public static int hardenedEnergyPerTick = hardenedEnergyPerTick_default;
    public static double hardenedSpeedVertical = hardenedSpeedVertical_default;
    public static double hardenedAccelVertical = hardenedAccelVertical_default;
    public static double hardenedSpeedSideways = hardenedSpeedSideways_default;
    public static double hardenedSpeedVerticalHover = hardenedSpeedVerticalHover_default;
    public static double hardenedSpeedVerticalHoverSlow = hardenedSpeedVerticalHoverSlow_default;
    public static boolean hardenedEmergencyHoverMode = hardenedEmergencyHoverMode_default;
    public static int hardenedArmorDisplay = hardenedArmorDisplay_default;
    public static double hardenedArmorAbsorption = hardenedArmorAbsorption_default;
    public static int hardenedArmorEnergyPerHit = hardenedArmorEnergyPerHit_default;
    public static boolean hardenedEnchantable = hardenedEnchantable_default;

    // tuningReinforced
    public static int reinforcedEnergyCapacity = reinforcedEnergyCapacity_default;
    public static int reinforcedEnergyPerTick = reinforcedEnergyPerTick_default;
    public static double reinforcedSpeedVertical = reinforcedSpeedVertical_default;
    public static double reinforcedAccelVertical = reinforcedAccelVertical_default;
    public static double reinforcedSpeedSideways = reinforcedSpeedSideways_default;
    public static double reinforcedSpeedVerticalHover = reinforcedSpeedVerticalHover_default;
    public static double reinforcedSpeedVerticalHoverSlow = reinforcedSpeedVerticalHoverSlow_default;
    public static boolean reinforcedEmergencyHoverMode = reinforcedEmergencyHoverMode_default;
    public static int reinforcedArmorDisplay = reinforcedArmorDisplay_default;
    public static double reinforcedArmorAbsorption = reinforcedArmorAbsorption_default;
    public static int reinforcedArmorEnergyPerHit = reinforcedArmorEnergyPerHit_default;
    public static boolean reinforcedEnchantable = reinforcedEnchantable_default;

    // tuningResonant
    public static int resonantEnergyCapacity = resonantEnergyCapacity_default;
    public static int resonantEnergyPerTick = resonantEnergyPerTick_default;
    public static double resonantSpeedVertical = resonantSpeedVertical_default;
    public static double resonantAccelVertical = resonantAccelVertical_default;
    public static double resonantSpeedSideways = resonantSpeedSideways_default;
    public static double resonantSpeedVerticalHover = resonantSpeedVerticalHover_default;
    public static double resonantSpeedVerticalHoverSlow = resonantSpeedVerticalHoverSlow_default;
    public static boolean resonantEmergencyHoverMode = resonantEmergencyHoverMode_default;
    public static int resonantArmorDisplay = resonantArmorDisplay_default;
    public static double resonantArmorAbsorption = resonantArmorAbsorption_default;
    public static int resonantArmorEnergyPerHit = resonantArmorEnergyPerHit_default;
    public static boolean resonantEnchantable = resonantEnchantable_default;

    // tuningFluxPlate
    public static int fluxPlateEnergyCapacity = fluxPlateEnergyCapacity_default;
    public static int fluxPlateEnergyPerTick = fluxPlateEnergyPerTick_default;
    public static double fluxPlateSpeedVertical = fluxPlateSpeedVertical_default;
    public static double fluxPlateAccelVertical = fluxPlateAccelVertical_default;
    public static double fluxPlateSpeedSideways = fluxPlateSpeedSideways_default;
    public static double fluxPlateSpeedVerticalHover = fluxPlateSpeedVerticalHover_default;
    public static double fluxPlateSpeedVerticalHoverSlow = fluxPlateSpeedVerticalHoverSlow_default;
    public static boolean fluxPlateEmergencyHoverMode = fluxPlateEmergencyHoverMode_default;
    public static int fluxPlateArmorDisplay = fluxPlateArmorDisplay_default;
    public static double fluxPlateArmorAbsorption = fluxPlateArmorAbsorption_default;
    public static int fluxPlateArmorEnergyPerHit = fluxPlateArmorEnergyPerHit_default;
    public static boolean fluxPlateEnchantable = fluxPlateEnchantable_default;

    // tuningCreative
    public static double creativeSpeedVertical = creativeSpeedVertical_default;
    public static double creativeAccelVertical = creativeAccelVertical_default;
    public static double creativeSpeedSideways = creativeSpeedSideways_default;
    public static double creativeSpeedVerticalHover = creativeSpeedVerticalHover_default;
    public static double creativeSpeedVerticalHoverSlow = creativeSpeedVerticalHoverSlow_default;
    public static boolean creativeEmergencyHoverMode = creativeEmergencyHoverMode_default;
    public static int creativeArmorDisplay = creativeArmorDisplay_default;
    public static double creativeArmorAbsorption = creativeArmorAbsorption_default;
    public static boolean creativeEnchantable = creativeEnchantable_default;

    public static void preInit(FMLPreInitializationEvent evt) {
        FMLCommonHandler.instance().bus().register(new SJConfig());
        config = new Configuration(evt.getSuggestedConfigurationFile());
        SimplyJetpacks.logger.info("Loading configuration file");
        syncConfig();
    }

    public static void syncConfig() {
        try {
            processConfig();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent evt) {
        if (evt.modID.equals("simplyjetpacks")) {
            SimplyJetpacks.logger.info("Updating configuration file");
            syncConfig();
            Jetpack.reconstructJetpacks();
        }
    }

    public static void processConfig() {
        enableJetpackParticles = config.get(sectionItem.name, "Enable jetpack particles", enableJetpackParticles_default, "When enabled, jetpacks will emit particles when active.").getBoolean(enableJetpackParticles_default);
        invertHoverSneakingBehavior = config.get(sectionItem.name, "Invert Hover Mode sneaking behavior", invertHoverSneakingBehavior_default, "Invert Hover Mode sneaking behavior").getBoolean(invertHoverSneakingBehavior_default);
        jetpackEnchantability = config.get(sectionItem.name, "Jetpack Enchantability", jetpackEnchantability_default, "The enchantability of the jetpacks. Note that specific jetpacks may be set not to be enchantable.").getInt(jetpackEnchantability_default);

        enableCraftingArmorPlating = config.get(sectionCrafting.name, "Armor Plating craftable", enableCraftingArmorPlating_default, "When enabled, Armor Plating items will be craftable, and thus armored jetpacks are available.").setRequiresMcRestart(true).getBoolean(enableCraftingArmorPlating_default);
        enableCraftingPotatoJetpack = config.get(sectionCrafting.name, "Potato Jetpack craftable", enableCraftingPotatoJetpack_default, "When enabled, the Potato Jetpack will be craftable.").setRequiresMcRestart(true).getBoolean(enableCraftingPotatoJetpack_default);

        enableEnergyHUD = config.get(sectionGui.name, "Enable Energy HUD", enableEnergyHUD_default, "When enabled, a HUD that displays your current jetpack's energy level will show.").getBoolean(enableEnergyHUD_default);
        energyHUDOffsetX = config.get(sectionGui.name, "Energy HUD Offset - X", energyHUDOffsetX_default, "The energy HUD display will be shifted horizontally by this value. This value may be negative.").getInt(energyHUDOffsetX_default);
        energyHUDOffsetY = config.get(sectionGui.name, "Energy HUD Offset - Y", energyHUDOffsetY_default, "The energy HUD display will be shifted vertically by this value. This value may be negative.").getInt(energyHUDOffsetY_default);
        int energyHUDPosition_temp = config.get(sectionGui.name, "Energy HUD Base Position", energyHUDPosition_default, "The base position of the energy HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").getInt(energyHUDPosition_default);
        energyHUDPosition = energyHUDPosition_temp < HUDPosition.values().length ? energyHUDPosition_temp : 0;
        energyHUDScale = Math.abs(config.get(sectionGui.name, "Energy HUD Scale", energyHUDScale_default, "How large the energy HUD will be rendered. Default is 1.0, can be bigger or smaller").getDouble(energyHUDScale_default));
        holdShiftForDetails = config.get(sectionGui.name, "Hold Shift for Details", holdShiftForDetails_default, "When enabled, item details are only shown in the tooltip when holding Shift.").getBoolean(holdShiftForDetails_default);
        minimalEnergyHUD = config.get(sectionGui.name, "Minimal Energy HUD", minimalEnergyHUD_default, "When enabled, only the actual power amounts will be rendered on the energy HUD.").getBoolean(minimalEnergyHUD_default);
        showEnergyHUDWhileChatting = config.get(sectionGui.name, "Show Energy HUD while chatting", showEnergyHUDWhileChatting_default, "When enabled, the energy HUD will display even when the chat window is opened.").getBoolean(showEnergyHUDWhileChatting_default);
        showExactEnergyInHUD = config.get(sectionGui.name, "Exact energy amounts in Energy HUD", showExactEnergyInHUD_default, "When enabled, the energy HUD will display the exact amount of RF other than just a percentage.").getBoolean(showExactEnergyInHUD_default);

        tuberousEnergyCapacity = config.get(sectionTuningTuberous.name, "Energy Capacity", tuberousEnergyCapacity_default, "The maximum amount of energy that the jetpack can hold.").getInt(tuberousEnergyCapacity_default);
        tuberousEnergyPerTick = config.get(sectionTuningTuberous.name, "Energy Usage per Tick", tuberousEnergyPerTick_default, "The amount of energy that the jetpack uses every tick when flying. In hover mode, this amount will be 1.5 times less.").getInt(tuberousEnergyPerTick_default);
        tuberousSpeedVertical = config.get(sectionTuningTuberous.name, "Vertical Speed", tuberousSpeedVertical_default, "The maximum vertical speed of the jetpack when flying.").getDouble(tuberousSpeedVertical_default);
        tuberousAccelVertical = config.get(sectionTuningTuberous.name, "Vertical Acceleration", tuberousAccelVertical_default, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(tuberousAccelVertical_default);

        leadstoneEnergyCapacity = config.get(sectionTuningLeadstone.name, "Energy Capacity", leadstoneEnergyCapacity_default, "The maximum amount of energy that the jetpack can hold.").getInt(leadstoneEnergyCapacity_default);
        leadstoneEnergyPerTick = config.get(sectionTuningLeadstone.name, "Energy Usage per Tick", leadstoneEnergyPerTick_default, "The amount of energy that the jetpack uses every tick when flying. In hover mode, this amount will be 1.5 times less.").getInt(leadstoneEnergyPerTick_default);
        leadstoneSpeedVertical = config.get(sectionTuningLeadstone.name, "Vertical Speed", leadstoneSpeedVertical_default, "The maximum vertical speed of the jetpack when flying.").getDouble(leadstoneSpeedVertical_default);
        leadstoneAccelVertical = config.get(sectionTuningLeadstone.name, "Vertical Acceleration", leadstoneAccelVertical_default, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(leadstoneAccelVertical_default);
        leadstoneSpeedSideways = config.get(sectionTuningLeadstone.name, "Sideways Speed", leadstoneSpeedSideways_default, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(leadstoneSpeedSideways_default);
        leadstoneSpeedVerticalHover = config.get(sectionTuningLeadstone.name, "Vertical Speed (Hover Mode)", leadstoneSpeedVerticalHover_default, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(leadstoneSpeedVerticalHover_default);
        leadstoneSpeedVerticalHoverSlow = config.get(sectionTuningLeadstone.name, "Vertical Speed (Hover Mode Slow Fall)", leadstoneSpeedVerticalHoverSlow_default, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(leadstoneSpeedVerticalHoverSlow_default);
        leadstoneEmergencyHoverMode = config.get(sectionTuningLeadstone.name, "Emergency Hover Mode", leadstoneEmergencyHoverMode_default, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(leadstoneEmergencyHoverMode_default);
        leadstoneArmorDisplay = config.get(sectionTuningLeadstone.name, "Armor Display", leadstoneArmorDisplay_default, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(leadstoneArmorDisplay_default);
        leadstoneArmorAbsorption = config.get(sectionTuningLeadstone.name, "Armor Absorption", leadstoneArmorAbsorption_default, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(leadstoneArmorAbsorption_default);
        int leadstoneArmorEnergyPerHit_temp = config.get(sectionTuningLeadstone.name, "Armor Energy Per Hit", leadstoneArmorEnergyPerHit_default, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(leadstoneArmorEnergyPerHit_default);
        leadstoneArmorEnergyPerHit = leadstoneArmorEnergyPerHit_temp > 0 ? leadstoneArmorEnergyPerHit_temp : 1;
        leadstoneEnchantable = config.get(sectionTuningLeadstone.name, "Enchantable", leadstoneEnchantable_default, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(leadstoneEnchantable_default);

        hardenedEnergyCapacity = config.get(sectionTuningHardened.name, "Energy Capacity", hardenedEnergyCapacity_default, "The maximum amount of energy that the jetpack can hold.").getInt(hardenedEnergyCapacity_default);
        hardenedEnergyPerTick = config.get(sectionTuningHardened.name, "Energy Usage per Tick", hardenedEnergyPerTick_default, "The amount of energy that the jetpack uses every tick when flying. In hover mode, this amount will be 1.5 times less.").getInt(hardenedEnergyPerTick_default);
        hardenedSpeedVertical = config.get(sectionTuningHardened.name, "Vertical Speed", hardenedSpeedVertical_default, "The maximum vertical speed of the jetpack when flying.").getDouble(hardenedSpeedVertical_default);
        hardenedAccelVertical = config.get(sectionTuningHardened.name, "Vertical Acceleration", hardenedAccelVertical_default, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(hardenedAccelVertical_default);
        hardenedSpeedSideways = config.get(sectionTuningHardened.name, "Sideways Speed", hardenedSpeedSideways_default, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(hardenedSpeedSideways_default);
        hardenedSpeedVerticalHover = config.get(sectionTuningHardened.name, "Vertical Speed (Hover Mode)", hardenedSpeedVerticalHover_default, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(hardenedSpeedVerticalHover_default);
        hardenedSpeedVerticalHoverSlow = config.get(sectionTuningHardened.name, "Vertical Speed (Hover Mode Slow Fall)", hardenedSpeedVerticalHoverSlow_default, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(hardenedSpeedVerticalHoverSlow_default);
        hardenedEmergencyHoverMode = config.get(sectionTuningHardened.name, "Emergency Hover Mode", hardenedEmergencyHoverMode_default, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(hardenedEmergencyHoverMode_default);
        hardenedArmorDisplay = config.get(sectionTuningHardened.name, "Armor Display", hardenedArmorDisplay_default, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(hardenedArmorDisplay_default);
        hardenedArmorAbsorption = config.get(sectionTuningHardened.name, "Armor Absorption", hardenedArmorAbsorption_default, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(hardenedArmorAbsorption_default);
        int hardenedArmorEnergyPerHit_temp = config.get(sectionTuningHardened.name, "Armor Energy Per Hit", hardenedArmorEnergyPerHit_default, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(hardenedArmorEnergyPerHit_default);
        hardenedArmorEnergyPerHit = hardenedArmorEnergyPerHit_temp > 0 ? hardenedArmorEnergyPerHit_temp : 1;
        hardenedEnchantable = config.get(sectionTuningHardened.name, "Enchantable", hardenedEnchantable_default, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(hardenedEnchantable_default);

        reinforcedEnergyCapacity = config.get(sectionTuningReinforced.name, "Energy Capacity", reinforcedEnergyCapacity_default, "The maximum amount of energy that the jetpack can hold.").getInt(reinforcedEnergyCapacity_default);
        reinforcedEnergyPerTick = config.get(sectionTuningReinforced.name, "Energy Usage per Tick", reinforcedEnergyPerTick_default, "The amount of energy that the jetpack uses every tick when flying. In hover mode, this amount will be 1.5 times less.").getInt(reinforcedEnergyPerTick_default);
        reinforcedSpeedVertical = config.get(sectionTuningReinforced.name, "Vertical Speed", reinforcedSpeedVertical_default, "The maximum vertical speed of the jetpack when flying.").getDouble(reinforcedSpeedVertical_default);
        reinforcedAccelVertical = config.get(sectionTuningReinforced.name, "Vertical Acceleration", reinforcedAccelVertical_default, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(reinforcedAccelVertical_default);
        reinforcedSpeedSideways = config.get(sectionTuningReinforced.name, "Sideways Speed", reinforcedSpeedSideways_default, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(reinforcedSpeedSideways_default);
        reinforcedSpeedVerticalHover = config.get(sectionTuningReinforced.name, "Vertical Speed (Hover Mode)", reinforcedSpeedVerticalHover_default, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(reinforcedSpeedVerticalHover_default);
        reinforcedSpeedVerticalHoverSlow = config.get(sectionTuningReinforced.name, "Vertical Speed (Hover Mode Slow Fall)", reinforcedSpeedVerticalHoverSlow_default, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(reinforcedSpeedVerticalHoverSlow_default);
        reinforcedEmergencyHoverMode = config.get(sectionTuningReinforced.name, "Emergency Hover Mode", reinforcedEmergencyHoverMode_default, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(reinforcedEmergencyHoverMode_default);
        reinforcedArmorDisplay = config.get(sectionTuningReinforced.name, "Armor Display", reinforcedArmorDisplay_default, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(reinforcedArmorDisplay_default);
        reinforcedArmorAbsorption = config.get(sectionTuningReinforced.name, "Armor Absorption", reinforcedArmorAbsorption_default, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(reinforcedArmorAbsorption_default);
        int reinforcedArmorEnergyPerHit_temp = config.get(sectionTuningReinforced.name, "Armor Energy Per Hit", reinforcedArmorEnergyPerHit_default, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(reinforcedArmorEnergyPerHit_default);
        reinforcedArmorEnergyPerHit = reinforcedArmorEnergyPerHit_temp > 0 ? reinforcedArmorEnergyPerHit_temp : 1;
        reinforcedEnchantable = config.get(sectionTuningReinforced.name, "Enchantable", reinforcedEnchantable_default, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(reinforcedEnchantable_default);

        resonantEnergyCapacity = config.get(sectionTuningResonant.name, "Energy Capacity", resonantEnergyCapacity_default, "The maximum amount of energy that the jetpack can hold.").getInt(resonantEnergyCapacity_default);
        resonantEnergyPerTick = config.get(sectionTuningResonant.name, "Energy Usage per Tick", resonantEnergyPerTick_default, "The amount of energy that the jetpack uses every tick when flying. In hover mode, this amount will be 1.5 times less.").getInt(resonantEnergyPerTick_default);
        resonantSpeedVertical = config.get(sectionTuningResonant.name, "Vertical Speed", resonantSpeedVertical_default, "The maximum vertical speed of the jetpack when flying.").getDouble(resonantSpeedVertical_default);
        resonantAccelVertical = config.get(sectionTuningResonant.name, "Vertical Acceleration", resonantAccelVertical_default, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(resonantAccelVertical_default);
        resonantSpeedSideways = config.get(sectionTuningResonant.name, "Sideways Speed", resonantSpeedSideways_default, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(resonantSpeedSideways_default);
        resonantSpeedVerticalHover = config.get(sectionTuningResonant.name, "Vertical Speed (Hover Mode)", resonantSpeedVerticalHover_default, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(resonantSpeedVerticalHover_default);
        resonantSpeedVerticalHoverSlow = config.get(sectionTuningResonant.name, "Vertical Speed (Hover Mode Slow Fall)", resonantSpeedVerticalHoverSlow_default, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(resonantSpeedVerticalHoverSlow_default);
        resonantEmergencyHoverMode = config.get(sectionTuningResonant.name, "Emergency Hover Mode", resonantEmergencyHoverMode_default, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(resonantEmergencyHoverMode_default);
        resonantArmorDisplay = config.get(sectionTuningResonant.name, "Armor Display", resonantArmorDisplay_default, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(resonantArmorDisplay_default);
        resonantArmorAbsorption = config.get(sectionTuningResonant.name, "Armor Absorption", resonantArmorAbsorption_default, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(resonantArmorAbsorption_default);
        int resonantArmorEnergyPerHit_temp = config.get(sectionTuningResonant.name, "Armor Energy Per Hit", resonantArmorEnergyPerHit_default, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(resonantArmorEnergyPerHit_default);
        resonantArmorEnergyPerHit = resonantArmorEnergyPerHit_temp > 0 ? resonantArmorEnergyPerHit_temp : 1;
        resonantEnchantable = config.get(sectionTuningResonant.name, "Enchantable", resonantEnchantable_default, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(resonantEnchantable_default);

        fluxPlateEnergyCapacity = config.get(sectionTuningFluxPlate.name, "Energy Capacity", fluxPlateEnergyCapacity_default, "The maximum amount of energy that the jetpack can hold.").getInt(fluxPlateEnergyCapacity_default);
        fluxPlateEnergyPerTick = config.get(sectionTuningFluxPlate.name, "Energy Usage per Tick", fluxPlateEnergyPerTick_default, "The amount of energy that the jetpack uses every tick when flying. In hover mode, this amount will be 1.5 times less.").getInt(fluxPlateEnergyPerTick_default);
        fluxPlateSpeedVertical = config.get(sectionTuningFluxPlate.name, "Vertical Speed", fluxPlateSpeedVertical_default, "The maximum vertical speed of the jetpack when flying.").getDouble(fluxPlateSpeedVertical_default);
        fluxPlateAccelVertical = config.get(sectionTuningFluxPlate.name, "Vertical Acceleration", fluxPlateAccelVertical_default, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(fluxPlateAccelVertical_default);
        fluxPlateSpeedSideways = config.get(sectionTuningFluxPlate.name, "Sideways Speed", fluxPlateSpeedSideways_default, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(fluxPlateSpeedSideways_default);
        fluxPlateSpeedVerticalHover = config.get(sectionTuningFluxPlate.name, "Vertical Speed (Hover Mode)", fluxPlateSpeedVerticalHover_default, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(fluxPlateSpeedVerticalHover_default);
        fluxPlateSpeedVerticalHoverSlow = config.get(sectionTuningFluxPlate.name, "Vertical Speed (Hover Mode Slow Fall)", fluxPlateSpeedVerticalHoverSlow_default, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(fluxPlateSpeedVerticalHoverSlow_default);
        fluxPlateEmergencyHoverMode = config.get(sectionTuningFluxPlate.name, "Emergency Hover Mode", fluxPlateEmergencyHoverMode_default, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(fluxPlateEmergencyHoverMode_default);
        fluxPlateArmorDisplay = config.get(sectionTuningFluxPlate.name, "Armor Display", fluxPlateArmorDisplay_default, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(fluxPlateArmorDisplay_default);
        fluxPlateArmorAbsorption = config.get(sectionTuningFluxPlate.name, "Armor Absorption", fluxPlateArmorAbsorption_default, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(fluxPlateArmorAbsorption_default);
        int fluxPlateArmorEnergyPerHit_temp = config.get(sectionTuningFluxPlate.name, "Armor Energy Per Hit", fluxPlateArmorEnergyPerHit_default, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(fluxPlateArmorEnergyPerHit_default);
        fluxPlateArmorEnergyPerHit = fluxPlateArmorEnergyPerHit_temp > 0 ? fluxPlateArmorEnergyPerHit_temp : 1;
        fluxPlateEnchantable = config.get(sectionTuningFluxPlate.name, "Enchantable", fluxPlateEnchantable_default, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(fluxPlateEnchantable_default);

        creativeSpeedVertical = config.get(sectionTuningCreative.name, "Vertical Speed", creativeSpeedVertical_default, "The maximum vertical speed of the jetpack when flying.").getDouble(creativeSpeedVertical_default);
        creativeAccelVertical = config.get(sectionTuningCreative.name, "Vertical Acceleration", creativeAccelVertical_default, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(creativeAccelVertical_default);
        creativeSpeedSideways = config.get(sectionTuningCreative.name, "Sideways Speed", creativeSpeedSideways_default, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(creativeSpeedSideways_default);
        creativeSpeedVerticalHover = config.get(sectionTuningCreative.name, "Vertical Speed (Hover Mode)", creativeSpeedVerticalHover_default, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(creativeSpeedVerticalHover_default);
        creativeSpeedVerticalHoverSlow = config.get(sectionTuningCreative.name, "Vertical Speed (Hover Mode Slow Fall)", creativeSpeedVerticalHoverSlow_default, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(creativeSpeedVerticalHoverSlow_default);
        creativeEmergencyHoverMode = config.get(sectionTuningCreative.name, "Emergency Hover Mode", creativeEmergencyHoverMode_default, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(creativeEmergencyHoverMode_default);
        creativeArmorDisplay = config.get(sectionTuningCreative.name, "Armor Display", creativeArmorDisplay_default, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(creativeArmorDisplay_default);
        creativeArmorAbsorption = config.get(sectionTuningCreative.name, "Armor Absorption", creativeArmorAbsorption_default, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(creativeArmorAbsorption_default);
        creativeEnchantable = config.get(sectionTuningCreative.name, "Enchantable", creativeEnchantable_default, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(creativeEnchantable_default);
    }

    public static class ConfigSection {

        public String name;
        public String id;

        public ConfigSection(String name, String id) {
            this.name = name;
            this.id = id;
            SJConfig.configSections.add(this);
        }

        public String toLowerCase() {
            return this.name.toLowerCase();
        }

    }

}
