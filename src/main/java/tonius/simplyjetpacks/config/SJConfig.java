package tonius.simplyjetpacks.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
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
    public static final ConfigSection sectionTuningFluxPackLeadstone = new ConfigSection("Tuning - Leadstone Flux Pack", "tuningFluxPackLeadstone");
    public static final ConfigSection sectionTuningFluxPackHardened = new ConfigSection("Tuning - Hardened Flux Pack", "tuningFluxPackHardened");
    public static final ConfigSection sectionTuningFluxPackRedstone = new ConfigSection("Tuning - Redstone Flux Pack", "tuningFluxPackRedstone");
    public static final ConfigSection sectionTuningFluxPackResonant = new ConfigSection("Tuning - Resonant Flux Pack", "tuningFluxPackResonant");
    public static final ConfigSection sectionTuningFluxPackCreative = new ConfigSection("Tuning - Creative Flux Pack", "tuningFluxPackCreative");

    // item
    public static boolean enableJetpackParticles = SJConfigDefaults.enableJetpackParticles;
    public static boolean invertHoverSneakingBehavior = SJConfigDefaults.invertHoverSneakingBehavior;
    public static int jetpackEnchantability = SJConfigDefaults.jetpackEnchantability;
    public static int fluxpackEnchantability = SJConfigDefaults.fluxpackEnchantability;

    // crafting
    public static boolean enableCraftingArmorPlating = SJConfigDefaults.enableCraftingArmorPlating;
    public static boolean enableCraftingPotatoJetpack = SJConfigDefaults.enableCraftingPotatoJetpack;

    // gui
    public static boolean enableStateChatMessages = SJConfigDefaults.enableStateChatMessages;
    public static boolean enableEnergyHUD = SJConfigDefaults.enableEnergyHUD;
    public static boolean enableStateHUD = SJConfigDefaults.enableStateHUD;
    public static int energyHUDOffsetX = SJConfigDefaults.energyHUDOffsetX;
    public static int energyHUDOffsetY = SJConfigDefaults.energyHUDOffsetY;
    public static int energyHUDPosition = SJConfigDefaults.energyHUDPosition;
    public static double energyHUDScale = SJConfigDefaults.energyHUDScale;
    public static boolean holdShiftForDetails = SJConfigDefaults.holdShiftForDetails;
    public static boolean minimalEnergyHUD = SJConfigDefaults.minimalEnergyHUD;
    public static boolean showEnergyHUDWhileChatting = SJConfigDefaults.showEnergyHUDWhileChatting;
    public static boolean showExactEnergyInHUD = SJConfigDefaults.showExactEnergyInHUD;

    // tuningTuberous
    public static int tuberousEnergyCapacity = SJConfigDefaults.tuberousEnergyCapacity;
    public static int tuberousEnergyPerTick = SJConfigDefaults.tuberousEnergyPerTick;
    public static double tuberousSpeedVertical = SJConfigDefaults.tuberousSpeedVertical;
    public static double tuberousAccelVertical = SJConfigDefaults.tuberousAccelVertical;

    // tuningLeadstone
    public static int leadstoneEnergyCapacity = SJConfigDefaults.leadstoneEnergyCapacity;
    public static int leadstoneEnergyPerTick = SJConfigDefaults.leadstoneEnergyPerTick;
    public static double leadstoneSpeedVertical = SJConfigDefaults.leadstoneSpeedVertical;
    public static double leadstoneAccelVertical = SJConfigDefaults.leadstoneAccelVertical;
    public static double leadstoneSpeedSideways = SJConfigDefaults.leadstoneSpeedSideways;
    public static double leadstoneSpeedVerticalHover = SJConfigDefaults.leadstoneSpeedVerticalHover;
    public static double leadstoneSpeedVerticalHoverSlow = SJConfigDefaults.leadstoneSpeedVerticalHoverSlow;
    public static boolean leadstoneEmergencyHoverMode = SJConfigDefaults.leadstoneEmergencyHoverMode;
    public static int leadstoneArmorDisplay = SJConfigDefaults.leadstoneArmorDisplay;
    public static double leadstoneArmorAbsorption = SJConfigDefaults.leadstoneArmorAbsorption;
    public static int leadstoneArmorEnergyPerHit = SJConfigDefaults.leadstoneArmorEnergyPerHit;
    public static boolean leadstoneEnchantable = SJConfigDefaults.leadstoneEnchantable;

    // tuningHardened
    public static int hardenedEnergyCapacity = SJConfigDefaults.hardenedEnergyCapacity;
    public static int hardenedEnergyPerTick = SJConfigDefaults.hardenedEnergyPerTick;
    public static double hardenedSpeedVertical = SJConfigDefaults.hardenedSpeedVertical;
    public static double hardenedAccelVertical = SJConfigDefaults.hardenedAccelVertical;
    public static double hardenedSpeedSideways = SJConfigDefaults.hardenedSpeedSideways;
    public static double hardenedSpeedVerticalHover = SJConfigDefaults.hardenedSpeedVerticalHover;
    public static double hardenedSpeedVerticalHoverSlow = SJConfigDefaults.hardenedSpeedVerticalHoverSlow;
    public static boolean hardenedEmergencyHoverMode = SJConfigDefaults.hardenedEmergencyHoverMode;
    public static int hardenedArmorDisplay = SJConfigDefaults.hardenedArmorDisplay;
    public static double hardenedArmorAbsorption = SJConfigDefaults.hardenedArmorAbsorption;
    public static int hardenedArmorEnergyPerHit = SJConfigDefaults.hardenedArmorEnergyPerHit;
    public static boolean hardenedEnchantable = SJConfigDefaults.hardenedEnchantable;

    // tuningReinforced
    public static int reinforcedEnergyCapacity = SJConfigDefaults.reinforcedEnergyCapacity;
    public static int reinforcedEnergyPerTick = SJConfigDefaults.reinforcedEnergyPerTick;
    public static double reinforcedSpeedVertical = SJConfigDefaults.reinforcedSpeedVertical;
    public static double reinforcedAccelVertical = SJConfigDefaults.reinforcedAccelVertical;
    public static double reinforcedSpeedSideways = SJConfigDefaults.reinforcedSpeedSideways;
    public static double reinforcedSpeedVerticalHover = SJConfigDefaults.reinforcedSpeedVerticalHover;
    public static double reinforcedSpeedVerticalHoverSlow = SJConfigDefaults.reinforcedSpeedVerticalHoverSlow;
    public static boolean reinforcedEmergencyHoverMode = SJConfigDefaults.reinforcedEmergencyHoverMode;
    public static int reinforcedArmorDisplay = SJConfigDefaults.reinforcedArmorDisplay;
    public static double reinforcedArmorAbsorption = SJConfigDefaults.reinforcedArmorAbsorption;
    public static int reinforcedArmorEnergyPerHit = SJConfigDefaults.reinforcedArmorEnergyPerHit;
    public static boolean reinforcedEnchantable = SJConfigDefaults.reinforcedEnchantable;

    // tuningResonant
    public static int resonantEnergyCapacity = SJConfigDefaults.resonantEnergyCapacity;
    public static int resonantEnergyPerTick = SJConfigDefaults.resonantEnergyPerTick;
    public static double resonantSpeedVertical = SJConfigDefaults.resonantSpeedVertical;
    public static double resonantAccelVertical = SJConfigDefaults.resonantAccelVertical;
    public static double resonantSpeedSideways = SJConfigDefaults.resonantSpeedSideways;
    public static double resonantSpeedVerticalHover = SJConfigDefaults.resonantSpeedVerticalHover;
    public static double resonantSpeedVerticalHoverSlow = SJConfigDefaults.resonantSpeedVerticalHoverSlow;
    public static boolean resonantEmergencyHoverMode = SJConfigDefaults.resonantEmergencyHoverMode;
    public static int resonantArmorDisplay = SJConfigDefaults.resonantArmorDisplay;
    public static double resonantArmorAbsorption = SJConfigDefaults.resonantArmorAbsorption;
    public static int resonantArmorEnergyPerHit = SJConfigDefaults.resonantArmorEnergyPerHit;
    public static boolean resonantEnchantable = SJConfigDefaults.resonantEnchantable;

    // tuningFluxPlate
    public static int fluxPlateEnergyCapacity = SJConfigDefaults.fluxPlateEnergyCapacity;
    public static int fluxPlateEnergyPerTick = SJConfigDefaults.fluxPlateEnergyPerTick;
    public static double fluxPlateSpeedVertical = SJConfigDefaults.fluxPlateSpeedVertical;
    public static double fluxPlateAccelVertical = SJConfigDefaults.fluxPlateAccelVertical;
    public static double fluxPlateSpeedSideways = SJConfigDefaults.fluxPlateSpeedSideways;
    public static double fluxPlateSpeedVerticalHover = SJConfigDefaults.fluxPlateSpeedVerticalHover;
    public static double fluxPlateSpeedVerticalHoverSlow = SJConfigDefaults.fluxPlateSpeedVerticalHoverSlow;
    public static boolean fluxPlateEmergencyHoverMode = SJConfigDefaults.fluxPlateEmergencyHoverMode;
    public static int fluxPlateArmorDisplay = SJConfigDefaults.fluxPlateArmorDisplay;
    public static double fluxPlateArmorAbsorption = SJConfigDefaults.fluxPlateArmorAbsorption;
    public static int fluxPlateArmorEnergyPerHit = SJConfigDefaults.fluxPlateArmorEnergyPerHit;
    public static boolean fluxPlateEnchantable = SJConfigDefaults.fluxPlateEnchantable;

    // tuningCreative
    public static double creativeSpeedVertical = SJConfigDefaults.creativeSpeedVertical;
    public static double creativeAccelVertical = SJConfigDefaults.creativeAccelVertical;
    public static double creativeSpeedSideways = SJConfigDefaults.creativeSpeedSideways;
    public static double creativeSpeedVerticalHover = SJConfigDefaults.creativeSpeedVerticalHover;
    public static double creativeSpeedVerticalHoverSlow = SJConfigDefaults.creativeSpeedVerticalHoverSlow;
    public static boolean creativeEmergencyHoverMode = SJConfigDefaults.creativeEmergencyHoverMode;
    public static int creativeArmorDisplay = SJConfigDefaults.creativeArmorDisplay;
    public static double creativeArmorAbsorption = SJConfigDefaults.creativeArmorAbsorption;
    public static boolean creativeEnchantable = SJConfigDefaults.creativeEnchantable;

    // tuningFluxPackLeadstone
    public static int fluxpackLeadstoneEnergyCapacity = SJConfigDefaults.fluxpackLeadstoneEnergyCapacity;
    public static int fluxpackLeadstoneEnergyInRate = SJConfigDefaults.fluxpackLeadstoneEnergyInRate;
    public static int fluxpackLeadstoneEnergyOutRate = SJConfigDefaults.fluxpackLeadstoneEnergyOutRate;
    public static boolean fluxpackLeadstoneEnchantable = SJConfigDefaults.fluxpackLeadstoneEnchantable;

    // tuningFluxPackHardened
    public static int fluxpackHardenedEnergyCapacity = SJConfigDefaults.fluxpackHardenedEnergyCapacity;
    public static int fluxpackHardenedEnergyInRate = SJConfigDefaults.fluxpackHardenedEnergyInRate;
    public static int fluxpackHardenedEnergyOutRate = SJConfigDefaults.fluxpackHardenedEnergyOutRate;
    public static int fluxpackHardenedArmorDisplay = SJConfigDefaults.fluxpackHardenedArmorDisplay;
    public static double fluxpackHardenedArmorAbsorption = SJConfigDefaults.fluxpackHardenedArmorAbsorption;
    public static int fluxpackHardenedArmorEnergyPerHit = SJConfigDefaults.fluxpackHardenedArmorEnergyPerHit;
    public static boolean fluxpackHardenedEnchantable = SJConfigDefaults.fluxpackHardenedEnchantable;

    // tuningFluxPackRedstone
    public static int fluxpackRedstoneEnergyCapacity = SJConfigDefaults.fluxpackRedstoneEnergyCapacity;
    public static int fluxpackRedstoneEnergyInRate = SJConfigDefaults.fluxpackRedstoneEnergyInRate;
    public static int fluxpackRedstoneEnergyOutRate = SJConfigDefaults.fluxpackRedstoneEnergyOutRate;
    public static int fluxpackRedstoneArmorDisplay = SJConfigDefaults.fluxpackRedstoneArmorDisplay;
    public static double fluxpackRedstoneArmorAbsorption = SJConfigDefaults.fluxpackRedstoneArmorAbsorption;
    public static int fluxpackRedstoneArmorEnergyPerHit = SJConfigDefaults.fluxpackRedstoneArmorEnergyPerHit;
    public static boolean fluxpackRedstoneEnchantable = SJConfigDefaults.fluxpackRedstoneEnchantable;

    // tuningFluxPackResonant
    public static int fluxpackResonantEnergyCapacity = SJConfigDefaults.fluxpackResonantEnergyCapacity;
    public static int fluxpackResonantEnergyInRate = SJConfigDefaults.fluxpackResonantEnergyInRate;
    public static int fluxpackResonantEnergyOutRate = SJConfigDefaults.fluxpackResonantEnergyOutRate;
    public static int fluxpackResonantArmorDisplay = SJConfigDefaults.fluxpackResonantArmorDisplay;
    public static double fluxpackResonantArmorAbsorption = SJConfigDefaults.fluxpackResonantArmorAbsorption;
    public static int fluxpackResonantArmorEnergyPerHit = SJConfigDefaults.fluxpackResonantArmorEnergyPerHit;
    public static boolean fluxpackResonantEnchantable = SJConfigDefaults.fluxpackResonantEnchantable;

    // tuningFluxPackCreative
    public static int fluxpackCreativeEnergyOutRate = SJConfigDefaults.fluxpackCreativeEnergyOutRate;
    public static int fluxpackCreativeArmorDisplay = SJConfigDefaults.fluxpackCreativeArmorDisplay;
    public static double fluxpackCreativeArmorAbsorption = SJConfigDefaults.fluxpackCreativeArmorAbsorption;
    public static boolean fluxpackCreativeEnchantable = SJConfigDefaults.fluxpackCreativeEnchantable;

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
            FluxPack.reconstructFluxPacks();
        }
    }

    public static void processConfig() {
        enableJetpackParticles = config.get(sectionItem.name, "Enable jetpack particles", SJConfigDefaults.enableJetpackParticles, "When enabled, jetpacks will emit particles when active.").getBoolean(SJConfigDefaults.enableJetpackParticles);
        invertHoverSneakingBehavior = config.get(sectionItem.name, "Invert Hover Mode sneaking behavior", SJConfigDefaults.invertHoverSneakingBehavior, "Invert Hover Mode sneaking behavior").getBoolean(SJConfigDefaults.invertHoverSneakingBehavior);
        jetpackEnchantability = config.get(sectionItem.name, "Jetpack Enchantability", SJConfigDefaults.jetpackEnchantability, "The enchantability of the jetpacks. Note that specific jetpacks may be set not to be enchantable.").setMinValue(0).getInt(SJConfigDefaults.jetpackEnchantability);
        fluxpackEnchantability = config.get(sectionItem.name, "Flux Pack Enchantability", SJConfigDefaults.fluxpackEnchantability, "The enchantability of the flux packs. Note that specific flux packs may be set not to be enchantable.").setMinValue(0).getInt(SJConfigDefaults.fluxpackEnchantability);

        enableCraftingArmorPlating = config.get(sectionCrafting.name, "Armor Plating craftable", SJConfigDefaults.enableCraftingArmorPlating, "When enabled, Armor Plating items will be craftable, and thus armored jetpacks are available.").setRequiresMcRestart(true).getBoolean(SJConfigDefaults.enableCraftingArmorPlating);
        enableCraftingPotatoJetpack = config.get(sectionCrafting.name, "Potato Jetpack craftable", SJConfigDefaults.enableCraftingPotatoJetpack, "When enabled, the Potato Jetpack will be craftable.").setRequiresMcRestart(true).getBoolean(SJConfigDefaults.enableCraftingPotatoJetpack);

        enableStateChatMessages = config.get(sectionGui.name, "Enable State Chat Messages", SJConfigDefaults.enableStateChatMessages, "When enabled, switching jetpacks on or off will display chat messages.").getBoolean(SJConfigDefaults.enableStateChatMessages);
        enableEnergyHUD = config.get(sectionGui.name, "Enable Energy HUD", SJConfigDefaults.enableEnergyHUD, "When enabled, a HUD that displays your current jetpack's energy level will show.").getBoolean(SJConfigDefaults.enableEnergyHUD);
        enableStateHUD = config.get(sectionGui.name, "Enable State HUD", SJConfigDefaults.enableStateHUD, "When enabled, a HUD that displays your current jetpack's engine and hover mode state will show.").getBoolean(SJConfigDefaults.enableStateHUD);
        energyHUDOffsetX = config.get(sectionGui.name, "Energy HUD Offset - X", SJConfigDefaults.energyHUDOffsetX, "The energy HUD display will be shifted horizontally by this value. This value may be negative.").getInt(SJConfigDefaults.energyHUDOffsetX);
        energyHUDOffsetY = config.get(sectionGui.name, "Energy HUD Offset - Y", SJConfigDefaults.energyHUDOffsetY, "The energy HUD display will be shifted vertically by this value. This value may be negative.").getInt(SJConfigDefaults.energyHUDOffsetY);
        energyHUDPosition = config.get(sectionGui.name, "Energy HUD Base Position", SJConfigDefaults.energyHUDPosition, "The base position of the energy HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").setMinValue(0).setMaxValue(HUDPosition.values().length - 1).getInt(SJConfigDefaults.energyHUDPosition);
        energyHUDScale = Math.abs(config.get(sectionGui.name, "Energy HUD Scale", SJConfigDefaults.energyHUDScale, "How large the energy HUD will be rendered. Default is 1.0, can be bigger or smaller").setMinValue(0).getDouble(SJConfigDefaults.energyHUDScale));
        holdShiftForDetails = config.get(sectionGui.name, "Hold Shift for Details", SJConfigDefaults.holdShiftForDetails, "When enabled, item details are only shown in the tooltip when holding Shift.").getBoolean(SJConfigDefaults.holdShiftForDetails);
        minimalEnergyHUD = config.get(sectionGui.name, "Minimal Energy HUD", SJConfigDefaults.minimalEnergyHUD, "When enabled, only the actual power amounts will be rendered on the energy HUD.").getBoolean(SJConfigDefaults.minimalEnergyHUD);
        showEnergyHUDWhileChatting = config.get(sectionGui.name, "Show Energy HUD while chatting", SJConfigDefaults.showEnergyHUDWhileChatting, "When enabled, the energy HUD will display even when the chat window is opened.").getBoolean(SJConfigDefaults.showEnergyHUDWhileChatting);
        showExactEnergyInHUD = config.get(sectionGui.name, "Exact energy amounts in Energy HUD", SJConfigDefaults.showExactEnergyInHUD, "When enabled, the energy HUD will display the exact amount of RF other than just a percentage.").getBoolean(SJConfigDefaults.showExactEnergyInHUD);

        tuberousEnergyCapacity = config.get(sectionTuningTuberous.name, "Energy Capacity", SJConfigDefaults.tuberousEnergyCapacity, "The maximum amount of energy that the jetpack can hold.").getInt(SJConfigDefaults.tuberousEnergyCapacity);
        tuberousEnergyPerTick = config.get(sectionTuningTuberous.name, "Energy Usage per Tick", SJConfigDefaults.tuberousEnergyPerTick, "The amount of energy that the jetpack uses every tick when flying.").getInt(SJConfigDefaults.tuberousEnergyPerTick);
        tuberousSpeedVertical = config.get(sectionTuningTuberous.name, "Vertical Speed", SJConfigDefaults.tuberousSpeedVertical, "The maximum vertical speed of the jetpack when flying.").getDouble(SJConfigDefaults.tuberousSpeedVertical);
        tuberousAccelVertical = config.get(sectionTuningTuberous.name, "Vertical Acceleration", SJConfigDefaults.tuberousAccelVertical, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(SJConfigDefaults.tuberousAccelVertical);

        leadstoneEnergyCapacity = config.get(sectionTuningLeadstone.name, "Energy Capacity", SJConfigDefaults.leadstoneEnergyCapacity, "The maximum amount of energy that the jetpack can hold.").getInt(SJConfigDefaults.leadstoneEnergyCapacity);
        leadstoneEnergyPerTick = config.get(sectionTuningLeadstone.name, "Energy Usage per Tick", SJConfigDefaults.leadstoneEnergyPerTick, "The amount of energy that the jetpack uses every tick when flying.").getInt(SJConfigDefaults.leadstoneEnergyPerTick);
        leadstoneSpeedVertical = config.get(sectionTuningLeadstone.name, "Vertical Speed", SJConfigDefaults.leadstoneSpeedVertical, "The maximum vertical speed of the jetpack when flying.").getDouble(SJConfigDefaults.leadstoneSpeedVertical);
        leadstoneAccelVertical = config.get(sectionTuningLeadstone.name, "Vertical Acceleration", SJConfigDefaults.leadstoneAccelVertical, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(SJConfigDefaults.leadstoneAccelVertical);
        leadstoneSpeedSideways = config.get(sectionTuningLeadstone.name, "Sideways Speed", SJConfigDefaults.leadstoneSpeedSideways, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(SJConfigDefaults.leadstoneSpeedSideways);
        leadstoneSpeedVerticalHover = config.get(sectionTuningLeadstone.name, "Vertical Speed (Hover Mode)", SJConfigDefaults.leadstoneSpeedVerticalHover, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(SJConfigDefaults.leadstoneSpeedVerticalHover);
        leadstoneSpeedVerticalHoverSlow = config.get(sectionTuningLeadstone.name, "Vertical Speed (Hover Mode Slow Fall)", SJConfigDefaults.leadstoneSpeedVerticalHoverSlow, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(SJConfigDefaults.leadstoneSpeedVerticalHoverSlow);
        leadstoneEmergencyHoverMode = config.get(sectionTuningLeadstone.name, "Emergency Hover Mode", SJConfigDefaults.leadstoneEmergencyHoverMode, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(SJConfigDefaults.leadstoneEmergencyHoverMode);
        leadstoneArmorDisplay = config.get(sectionTuningLeadstone.name, "Armor Display", SJConfigDefaults.leadstoneArmorDisplay, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.leadstoneArmorDisplay);
        leadstoneArmorAbsorption = config.get(sectionTuningLeadstone.name, "Armor Absorption", SJConfigDefaults.leadstoneArmorAbsorption, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(SJConfigDefaults.leadstoneArmorAbsorption);
        int leadstoneArmorEnergyPerHit_temp = config.get(sectionTuningLeadstone.name, "Armor Energy Per Hit", SJConfigDefaults.leadstoneArmorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(SJConfigDefaults.leadstoneArmorEnergyPerHit);
        leadstoneArmorEnergyPerHit = leadstoneArmorEnergyPerHit_temp > 0 ? leadstoneArmorEnergyPerHit_temp : 1;
        leadstoneEnchantable = config.get(sectionTuningLeadstone.name, "Enchantable", SJConfigDefaults.leadstoneEnchantable, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.leadstoneEnchantable);

        hardenedEnergyCapacity = config.get(sectionTuningHardened.name, "Energy Capacity", SJConfigDefaults.hardenedEnergyCapacity, "The maximum amount of energy that the jetpack can hold.").getInt(SJConfigDefaults.hardenedEnergyCapacity);
        hardenedEnergyPerTick = config.get(sectionTuningHardened.name, "Energy Usage per Tick", SJConfigDefaults.hardenedEnergyPerTick, "The amount of energy that the jetpack uses every tick when flying.").getInt(SJConfigDefaults.hardenedEnergyPerTick);
        hardenedSpeedVertical = config.get(sectionTuningHardened.name, "Vertical Speed", SJConfigDefaults.hardenedSpeedVertical, "The maximum vertical speed of the jetpack when flying.").getDouble(SJConfigDefaults.hardenedSpeedVertical);
        hardenedAccelVertical = config.get(sectionTuningHardened.name, "Vertical Acceleration", SJConfigDefaults.hardenedAccelVertical, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(SJConfigDefaults.hardenedAccelVertical);
        hardenedSpeedSideways = config.get(sectionTuningHardened.name, "Sideways Speed", SJConfigDefaults.hardenedSpeedSideways, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(SJConfigDefaults.hardenedSpeedSideways);
        hardenedSpeedVerticalHover = config.get(sectionTuningHardened.name, "Vertical Speed (Hover Mode)", SJConfigDefaults.hardenedSpeedVerticalHover, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(SJConfigDefaults.hardenedSpeedVerticalHover);
        hardenedSpeedVerticalHoverSlow = config.get(sectionTuningHardened.name, "Vertical Speed (Hover Mode Slow Fall)", SJConfigDefaults.hardenedSpeedVerticalHoverSlow, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(SJConfigDefaults.hardenedSpeedVerticalHoverSlow);
        hardenedEmergencyHoverMode = config.get(sectionTuningHardened.name, "Emergency Hover Mode", SJConfigDefaults.hardenedEmergencyHoverMode, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(SJConfigDefaults.hardenedEmergencyHoverMode);
        hardenedArmorDisplay = config.get(sectionTuningHardened.name, "Armor Display", SJConfigDefaults.hardenedArmorDisplay, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.hardenedArmorDisplay);
        hardenedArmorAbsorption = config.get(sectionTuningHardened.name, "Armor Absorption", SJConfigDefaults.hardenedArmorAbsorption, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(SJConfigDefaults.hardenedArmorAbsorption);
        int hardenedArmorEnergyPerHit_temp = config.get(sectionTuningHardened.name, "Armor Energy Per Hit", SJConfigDefaults.hardenedArmorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(SJConfigDefaults.hardenedArmorEnergyPerHit);
        hardenedArmorEnergyPerHit = hardenedArmorEnergyPerHit_temp > 0 ? hardenedArmorEnergyPerHit_temp : 1;
        hardenedEnchantable = config.get(sectionTuningHardened.name, "Enchantable", SJConfigDefaults.hardenedEnchantable, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.hardenedEnchantable);

        reinforcedEnergyCapacity = config.get(sectionTuningReinforced.name, "Energy Capacity", SJConfigDefaults.reinforcedEnergyCapacity, "The maximum amount of energy that the jetpack can hold.").getInt(SJConfigDefaults.reinforcedEnergyCapacity);
        reinforcedEnergyPerTick = config.get(sectionTuningReinforced.name, "Energy Usage per Tick", SJConfigDefaults.reinforcedEnergyPerTick, "The amount of energy that the jetpack uses every tick when flying.").getInt(SJConfigDefaults.reinforcedEnergyPerTick);
        reinforcedSpeedVertical = config.get(sectionTuningReinforced.name, "Vertical Speed", SJConfigDefaults.reinforcedSpeedVertical, "The maximum vertical speed of the jetpack when flying.").getDouble(SJConfigDefaults.reinforcedSpeedVertical);
        reinforcedAccelVertical = config.get(sectionTuningReinforced.name, "Vertical Acceleration", SJConfigDefaults.reinforcedAccelVertical, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(SJConfigDefaults.reinforcedAccelVertical);
        reinforcedSpeedSideways = config.get(sectionTuningReinforced.name, "Sideways Speed", SJConfigDefaults.reinforcedSpeedSideways, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(SJConfigDefaults.reinforcedSpeedSideways);
        reinforcedSpeedVerticalHover = config.get(sectionTuningReinforced.name, "Vertical Speed (Hover Mode)", SJConfigDefaults.reinforcedSpeedVerticalHover, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(SJConfigDefaults.reinforcedSpeedVerticalHover);
        reinforcedSpeedVerticalHoverSlow = config.get(sectionTuningReinforced.name, "Vertical Speed (Hover Mode Slow Fall)", SJConfigDefaults.reinforcedSpeedVerticalHoverSlow, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(SJConfigDefaults.reinforcedSpeedVerticalHoverSlow);
        reinforcedEmergencyHoverMode = config.get(sectionTuningReinforced.name, "Emergency Hover Mode", SJConfigDefaults.reinforcedEmergencyHoverMode, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(SJConfigDefaults.reinforcedEmergencyHoverMode);
        reinforcedArmorDisplay = config.get(sectionTuningReinforced.name, "Armor Display", SJConfigDefaults.reinforcedArmorDisplay, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.reinforcedArmorDisplay);
        reinforcedArmorAbsorption = config.get(sectionTuningReinforced.name, "Armor Absorption", SJConfigDefaults.reinforcedArmorAbsorption, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(SJConfigDefaults.reinforcedArmorAbsorption);
        int reinforcedArmorEnergyPerHit_temp = config.get(sectionTuningReinforced.name, "Armor Energy Per Hit", SJConfigDefaults.reinforcedArmorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(SJConfigDefaults.reinforcedArmorEnergyPerHit);
        reinforcedArmorEnergyPerHit = reinforcedArmorEnergyPerHit_temp > 0 ? reinforcedArmorEnergyPerHit_temp : 1;
        reinforcedEnchantable = config.get(sectionTuningReinforced.name, "Enchantable", SJConfigDefaults.reinforcedEnchantable, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.reinforcedEnchantable);

        resonantEnergyCapacity = config.get(sectionTuningResonant.name, "Energy Capacity", SJConfigDefaults.resonantEnergyCapacity, "The maximum amount of energy that the jetpack can hold.").getInt(SJConfigDefaults.resonantEnergyCapacity);
        resonantEnergyPerTick = config.get(sectionTuningResonant.name, "Energy Usage per Tick", SJConfigDefaults.resonantEnergyPerTick, "The amount of energy that the jetpack uses every tick when flying.").getInt(SJConfigDefaults.resonantEnergyPerTick);
        resonantSpeedVertical = config.get(sectionTuningResonant.name, "Vertical Speed", SJConfigDefaults.resonantSpeedVertical, "The maximum vertical speed of the jetpack when flying.").getDouble(SJConfigDefaults.resonantSpeedVertical);
        resonantAccelVertical = config.get(sectionTuningResonant.name, "Vertical Acceleration", SJConfigDefaults.resonantAccelVertical, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(SJConfigDefaults.resonantAccelVertical);
        resonantSpeedSideways = config.get(sectionTuningResonant.name, "Sideways Speed", SJConfigDefaults.resonantSpeedSideways, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(SJConfigDefaults.resonantSpeedSideways);
        resonantSpeedVerticalHover = config.get(sectionTuningResonant.name, "Vertical Speed (Hover Mode)", SJConfigDefaults.resonantSpeedVerticalHover, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(SJConfigDefaults.resonantSpeedVerticalHover);
        resonantSpeedVerticalHoverSlow = config.get(sectionTuningResonant.name, "Vertical Speed (Hover Mode Slow Fall)", SJConfigDefaults.resonantSpeedVerticalHoverSlow, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(SJConfigDefaults.resonantSpeedVerticalHoverSlow);
        resonantEmergencyHoverMode = config.get(sectionTuningResonant.name, "Emergency Hover Mode", SJConfigDefaults.resonantEmergencyHoverMode, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(SJConfigDefaults.resonantEmergencyHoverMode);
        resonantArmorDisplay = config.get(sectionTuningResonant.name, "Armor Display", SJConfigDefaults.resonantArmorDisplay, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.resonantArmorDisplay);
        resonantArmorAbsorption = config.get(sectionTuningResonant.name, "Armor Absorption", SJConfigDefaults.resonantArmorAbsorption, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(SJConfigDefaults.resonantArmorAbsorption);
        int resonantArmorEnergyPerHit_temp = config.get(sectionTuningResonant.name, "Armor Energy Per Hit", SJConfigDefaults.resonantArmorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(SJConfigDefaults.resonantArmorEnergyPerHit);
        resonantArmorEnergyPerHit = resonantArmorEnergyPerHit_temp > 0 ? resonantArmorEnergyPerHit_temp : 1;
        resonantEnchantable = config.get(sectionTuningResonant.name, "Enchantable", SJConfigDefaults.resonantEnchantable, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.resonantEnchantable);

        fluxPlateEnergyCapacity = config.get(sectionTuningFluxPlate.name, "Energy Capacity", SJConfigDefaults.fluxPlateEnergyCapacity, "The maximum amount of energy that the jetpack can hold.").getInt(SJConfigDefaults.fluxPlateEnergyCapacity);
        fluxPlateEnergyPerTick = config.get(sectionTuningFluxPlate.name, "Energy Usage per Tick", SJConfigDefaults.fluxPlateEnergyPerTick, "The amount of energy that the jetpack uses every tick when flying.").getInt(SJConfigDefaults.fluxPlateEnergyPerTick);
        fluxPlateSpeedVertical = config.get(sectionTuningFluxPlate.name, "Vertical Speed", SJConfigDefaults.fluxPlateSpeedVertical, "The maximum vertical speed of the jetpack when flying.").getDouble(SJConfigDefaults.fluxPlateSpeedVertical);
        fluxPlateAccelVertical = config.get(sectionTuningFluxPlate.name, "Vertical Acceleration", SJConfigDefaults.fluxPlateAccelVertical, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(SJConfigDefaults.fluxPlateAccelVertical);
        fluxPlateSpeedSideways = config.get(sectionTuningFluxPlate.name, "Sideways Speed", SJConfigDefaults.fluxPlateSpeedSideways, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(SJConfigDefaults.fluxPlateSpeedSideways);
        fluxPlateSpeedVerticalHover = config.get(sectionTuningFluxPlate.name, "Vertical Speed (Hover Mode)", SJConfigDefaults.fluxPlateSpeedVerticalHover, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(SJConfigDefaults.fluxPlateSpeedVerticalHover);
        fluxPlateSpeedVerticalHoverSlow = config.get(sectionTuningFluxPlate.name, "Vertical Speed (Hover Mode Slow Fall)", SJConfigDefaults.fluxPlateSpeedVerticalHoverSlow, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(SJConfigDefaults.fluxPlateSpeedVerticalHoverSlow);
        fluxPlateEmergencyHoverMode = config.get(sectionTuningFluxPlate.name, "Emergency Hover Mode", SJConfigDefaults.fluxPlateEmergencyHoverMode, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(SJConfigDefaults.fluxPlateEmergencyHoverMode);
        fluxPlateArmorDisplay = config.get(sectionTuningFluxPlate.name, "Armor Display", SJConfigDefaults.fluxPlateArmorDisplay, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.fluxPlateArmorDisplay);
        fluxPlateArmorAbsorption = config.get(sectionTuningFluxPlate.name, "Armor Absorption", SJConfigDefaults.fluxPlateArmorAbsorption, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(SJConfigDefaults.fluxPlateArmorAbsorption);
        int fluxPlateArmorEnergyPerHit_temp = config.get(sectionTuningFluxPlate.name, "Armor Energy Per Hit", SJConfigDefaults.fluxPlateArmorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the jetpack when getting hit. This value will be multiplied by the amount of damage done.").getInt(SJConfigDefaults.fluxPlateArmorEnergyPerHit);
        fluxPlateArmorEnergyPerHit = fluxPlateArmorEnergyPerHit_temp > 0 ? fluxPlateArmorEnergyPerHit_temp : 1;
        fluxPlateEnchantable = config.get(sectionTuningFluxPlate.name, "Enchantable", SJConfigDefaults.fluxPlateEnchantable, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.fluxPlateEnchantable);

        creativeSpeedVertical = config.get(sectionTuningCreative.name, "Vertical Speed", SJConfigDefaults.creativeSpeedVertical, "The maximum vertical speed of the jetpack when flying.").getDouble(SJConfigDefaults.creativeSpeedVertical);
        creativeAccelVertical = config.get(sectionTuningCreative.name, "Vertical Acceleration", SJConfigDefaults.creativeAccelVertical, "The vertical acceleration of the jetpack when flying; every tick, this amount of vertical speed will be added until the jetpack reaches the maximum speed.").getDouble(SJConfigDefaults.creativeAccelVertical);
        creativeSpeedSideways = config.get(sectionTuningCreative.name, "Sideways Speed", SJConfigDefaults.creativeSpeedSideways, "The speed of the jetpack when flying sideways. This is mostly noticeable in hover mode.").getDouble(SJConfigDefaults.creativeSpeedSideways);
        creativeSpeedVerticalHover = config.get(sectionTuningCreative.name, "Vertical Speed (Hover Mode)", SJConfigDefaults.creativeSpeedVerticalHover, "The maximum vertical speed of the jetpack when flying in hover mode.").getDouble(SJConfigDefaults.creativeSpeedVerticalHover);
        creativeSpeedVerticalHoverSlow = config.get(sectionTuningCreative.name, "Vertical Speed (Hover Mode Slow Fall)", SJConfigDefaults.creativeSpeedVerticalHoverSlow, "The maximum vertical speed of the jetpack when slowly descending in hover mode.").getDouble(SJConfigDefaults.creativeSpeedVerticalHoverSlow);
        creativeEmergencyHoverMode = config.get(sectionTuningCreative.name, "Emergency Hover Mode", SJConfigDefaults.creativeEmergencyHoverMode, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(SJConfigDefaults.creativeEmergencyHoverMode);
        creativeArmorDisplay = config.get(sectionTuningCreative.name, "Armor Display", SJConfigDefaults.creativeArmorDisplay, "How powerful the ARMORED version of the jetpack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.creativeArmorDisplay);
        creativeArmorAbsorption = config.get(sectionTuningCreative.name, "Armor Absorption", SJConfigDefaults.creativeArmorAbsorption, "The relative amount of damage that the ARMORED version of the jetpack will absorb when getting hit.").getDouble(SJConfigDefaults.creativeArmorAbsorption);
        creativeEnchantable = config.get(sectionTuningCreative.name, "Enchantable", SJConfigDefaults.creativeEnchantable, "When enabled, this jetpack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.creativeEnchantable);

        fluxpackLeadstoneEnergyCapacity = config.get(sectionTuningFluxPackLeadstone.name, "Energy Capacity", SJConfigDefaults.fluxpackLeadstoneEnergyCapacity, "The maximum amount of energy that this flux pack can hold.").getInt(SJConfigDefaults.fluxpackLeadstoneEnergyCapacity);
        fluxpackLeadstoneEnergyInRate = config.get(sectionTuningFluxPackLeadstone.name, "Energy In Rate", SJConfigDefaults.fluxpackLeadstoneEnergyInRate, "The rate, in RF per tick, at which this flux pack can be charged.").getInt(SJConfigDefaults.fluxpackLeadstoneEnergyInRate);
        fluxpackLeadstoneEnergyOutRate = config.get(sectionTuningFluxPackLeadstone.name, "Energy Out Rate", SJConfigDefaults.fluxpackLeadstoneEnergyOutRate, "The rate, in RF per tick, at which this flux pack can charge other items.").getInt(SJConfigDefaults.fluxpackLeadstoneEnergyOutRate);
        fluxpackLeadstoneEnchantable = config.get(sectionTuningFluxPackLeadstone.name, "Enchantable", SJConfigDefaults.fluxpackLeadstoneEnchantable, "When enabled, this flux pack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.fluxpackLeadstoneEnchantable);

        fluxpackHardenedEnergyCapacity = config.get(sectionTuningFluxPackHardened.name, "Energy Capacity", SJConfigDefaults.fluxpackHardenedEnergyCapacity, "The maximum amount of energy that this flux pack can hold.").getInt(SJConfigDefaults.fluxpackHardenedEnergyCapacity);
        fluxpackHardenedEnergyInRate = config.get(sectionTuningFluxPackHardened.name, "Energy In Rate", SJConfigDefaults.fluxpackHardenedEnergyInRate, "The rate, in RF per tick, at which this flux pack can be charged.").getInt(SJConfigDefaults.fluxpackHardenedEnergyInRate);
        fluxpackHardenedEnergyOutRate = config.get(sectionTuningFluxPackHardened.name, "Energy Out Rate", SJConfigDefaults.fluxpackHardenedEnergyOutRate, "The rate, in RF per tick, at which this flux pack can charge other items.").getInt(SJConfigDefaults.fluxpackHardenedEnergyOutRate);
        fluxpackHardenedArmorDisplay = config.get(sectionTuningFluxPackHardened.name, "Armor Display", SJConfigDefaults.fluxpackHardenedArmorDisplay, "How powerful the ARMORED version of the flux pack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.fluxpackHardenedArmorDisplay);
        fluxpackHardenedArmorAbsorption = config.get(sectionTuningFluxPackHardened.name, "Armor Absorption", SJConfigDefaults.fluxpackHardenedArmorAbsorption, "The relative amount of damage that the ARMORED version of the flux pack will absorb when getting hit.").getDouble(SJConfigDefaults.fluxpackHardenedArmorAbsorption);
        int fluxpackHardenedArmorEnergyPerHit_temp = config.get(sectionTuningFluxPackHardened.name, "Armor Energy Per Hit", SJConfigDefaults.fluxpackHardenedArmorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the flux pack when getting hit. This value will be multiplied by the amount of damage done.").getInt(SJConfigDefaults.fluxpackHardenedArmorEnergyPerHit);
        fluxpackHardenedArmorEnergyPerHit = fluxpackHardenedArmorEnergyPerHit_temp > 0 ? fluxpackHardenedArmorEnergyPerHit_temp : 1;
        fluxpackHardenedEnchantable = config.get(sectionTuningFluxPackHardened.name, "Enchantable", SJConfigDefaults.fluxpackHardenedEnchantable, "When enabled, this flux pack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.fluxpackHardenedEnchantable);

        fluxpackRedstoneEnergyCapacity = config.get(sectionTuningFluxPackRedstone.name, "Energy Capacity", SJConfigDefaults.fluxpackRedstoneEnergyCapacity, "The maximum amount of energy that this flux pack can hold.").getInt(SJConfigDefaults.fluxpackRedstoneEnergyCapacity);
        fluxpackRedstoneEnergyInRate = config.get(sectionTuningFluxPackRedstone.name, "Energy In Rate", SJConfigDefaults.fluxpackRedstoneEnergyInRate, "The rate, in RF per tick, at which this flux pack can be charged.").getInt(SJConfigDefaults.fluxpackRedstoneEnergyInRate);
        fluxpackRedstoneEnergyOutRate = config.get(sectionTuningFluxPackRedstone.name, "Energy Out Rate", SJConfigDefaults.fluxpackRedstoneEnergyOutRate, "The rate, in RF per tick, at which this flux pack can charge other items.").getInt(SJConfigDefaults.fluxpackRedstoneEnergyOutRate);
        fluxpackRedstoneArmorDisplay = config.get(sectionTuningFluxPackRedstone.name, "Armor Display", SJConfigDefaults.fluxpackRedstoneArmorDisplay, "How powerful the ARMORED version of the flux pack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.fluxpackRedstoneArmorDisplay);
        fluxpackRedstoneArmorAbsorption = config.get(sectionTuningFluxPackRedstone.name, "Armor Absorption", SJConfigDefaults.fluxpackRedstoneArmorAbsorption, "The relative amount of damage that the ARMORED version of the flux pack will absorb when getting hit.").getDouble(SJConfigDefaults.fluxpackRedstoneArmorAbsorption);
        int fluxpackRedstoneArmorEnergyPerHit_temp = config.get(sectionTuningFluxPackRedstone.name, "Armor Energy Per Hit", SJConfigDefaults.fluxpackRedstoneArmorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the flux pack when getting hit. This value will be multiplied by the amount of damage done.").getInt(SJConfigDefaults.fluxpackRedstoneArmorEnergyPerHit);
        fluxpackRedstoneArmorEnergyPerHit = fluxpackRedstoneArmorEnergyPerHit_temp > 0 ? fluxpackRedstoneArmorEnergyPerHit_temp : 1;
        fluxpackRedstoneEnchantable = config.get(sectionTuningFluxPackRedstone.name, "Enchantable", SJConfigDefaults.fluxpackRedstoneEnchantable, "When enabled, this flux pack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.fluxpackRedstoneEnchantable);

        fluxpackResonantEnergyCapacity = config.get(sectionTuningFluxPackResonant.name, "Energy Capacity", SJConfigDefaults.fluxpackResonantEnergyCapacity, "The maximum amount of energy that this flux pack can hold.").getInt(SJConfigDefaults.fluxpackResonantEnergyCapacity);
        fluxpackResonantEnergyInRate = config.get(sectionTuningFluxPackResonant.name, "Energy In Rate", SJConfigDefaults.fluxpackResonantEnergyInRate, "The rate, in RF per tick, at which this flux pack can be charged.").getInt(SJConfigDefaults.fluxpackResonantEnergyInRate);
        fluxpackResonantEnergyOutRate = config.get(sectionTuningFluxPackResonant.name, "Energy Out Rate", SJConfigDefaults.fluxpackResonantEnergyOutRate, "The rate, in RF per tick, at which this flux pack can charge other items.").getInt(SJConfigDefaults.fluxpackResonantEnergyOutRate);
        fluxpackResonantArmorDisplay = config.get(sectionTuningFluxPackResonant.name, "Armor Display", SJConfigDefaults.fluxpackResonantArmorDisplay, "How powerful the ARMORED version of the flux pack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.fluxpackResonantArmorDisplay);
        fluxpackResonantArmorAbsorption = config.get(sectionTuningFluxPackResonant.name, "Armor Absorption", SJConfigDefaults.fluxpackResonantArmorAbsorption, "The relative amount of damage that the ARMORED version of the flux pack will absorb when getting hit.").getDouble(SJConfigDefaults.fluxpackResonantArmorAbsorption);
        int fluxpackResonantArmorEnergyPerHit_temp = config.get(sectionTuningFluxPackResonant.name, "Armor Energy Per Hit", SJConfigDefaults.fluxpackResonantArmorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the flux pack when getting hit. This value will be multiplied by the amount of damage done.").getInt(SJConfigDefaults.fluxpackResonantArmorEnergyPerHit);
        fluxpackResonantArmorEnergyPerHit = fluxpackResonantArmorEnergyPerHit_temp > 0 ? fluxpackResonantArmorEnergyPerHit_temp : 1;
        fluxpackResonantEnchantable = config.get(sectionTuningFluxPackResonant.name, "Enchantable", SJConfigDefaults.fluxpackResonantEnchantable, "When enabled, this flux pack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.fluxpackResonantEnchantable);

        fluxpackCreativeEnergyOutRate = config.get(sectionTuningFluxPackCreative.name, "Energy Out Rate", SJConfigDefaults.fluxpackCreativeEnergyOutRate, "The rate, in RF per tick, at which this flux pack can charge other items.").getInt(SJConfigDefaults.fluxpackCreativeEnergyOutRate);
        fluxpackCreativeArmorDisplay = config.get(sectionTuningFluxPackCreative.name, "Armor Display", SJConfigDefaults.fluxpackCreativeArmorDisplay, "How powerful the ARMORED version of the flux pack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(SJConfigDefaults.fluxpackCreativeArmorDisplay);
        fluxpackCreativeArmorAbsorption = config.get(sectionTuningFluxPackCreative.name, "Armor Absorption", SJConfigDefaults.fluxpackCreativeArmorAbsorption, "The relative amount of damage that the ARMORED version of the flux pack will absorb when getting hit.").getDouble(SJConfigDefaults.fluxpackCreativeArmorAbsorption);
        fluxpackCreativeEnchantable = config.get(sectionTuningFluxPackCreative.name, "Enchantable", SJConfigDefaults.fluxpackCreativeEnchantable, "When enabled, this flux pack will be enchantable using enchantment tables or anvils.").getBoolean(SJConfigDefaults.fluxpackCreativeEnchantable);
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
