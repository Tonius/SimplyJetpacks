package tonius.simplyjetpacks.config;

import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;

public class SJConfigDefaults {
    
    // controls
    public static final boolean customControls = false;
    public static final String flyKey = "SPACE";
    public static final String descendKey = "LSHIFT";
    public static final boolean invertHoverSneakingBehavior = false;
    
    // gui
    public static final boolean enableStateChatMessages = false;
    public static final boolean enableEnergyHUD = true;
    public static final boolean enableStateHUD = true;
    public static final int energyHUDOffsetX = 0;
    public static final int energyHUDOffsetY = 0;
    public static final int energyHUDPosition = HUDPosition.TOP_LEFT.ordinal();
    public static final double energyHUDScale = 1.0D;
    public static final boolean holdShiftForDetails = true;
    public static final boolean minimalEnergyHUD = false;
    public static final boolean showEnergyHUDWhileChatting = true;
    public static final boolean showExactEnergyInHUD = false;
    
    // item
    public static final int jetpackEnchantability = 16;
    public static final int fluxpackEnchantability = 8;
    
    // crafting
    public static final boolean enableCraftingArmorPlating = true;
    public static final boolean enableCraftingPotatoJetpack = true;
    public static final boolean enableCraftingFluxJetPlate = true;
    
    // tuningTuberous
    public static final int tuberousEnergyCapacity = 1200;
    public static final int tuberousEnergyPerTick = 45;
    public static final double tuberousSpeedVertical = 0.9D;
    public static final double tuberousAccelVertical = 0.5D;
    
    // tuningLeadstone
    public static final int leadstoneEnergyCapacity = 25000;
    public static final int leadstoneEnergyPerTick = 10;
    public static final double leadstoneSpeedVertical = 0.22D;
    public static final double leadstoneAccelVertical = 0.1D;
    public static final double leadstoneSpeedSideways = 0.0D;
    public static final double leadstoneSpeedVerticalHover = 0.18D;
    public static final double leadstoneSpeedVerticalHoverSlow = 0.14D;
    public static final boolean leadstoneEmergencyHoverMode = false;
    public static final int leadstoneArmorDisplay = 4;
    public static final double leadstoneArmorAbsorption = 0.3D;
    public static final int leadstoneArmorEnergyPerHit = 80;
    public static final boolean leadstoneEnchantable = false;
    
    // tuningHardened
    public static final int hardenedEnergyCapacity = 400000;
    public static final int hardenedEnergyPerTick = 50;
    public static final double hardenedSpeedVertical = 0.3D;
    public static final double hardenedAccelVertical = 0.12D;
    public static final double hardenedSpeedSideways = 0.08D;
    public static final double hardenedSpeedVerticalHover = 0.18D;
    public static final double hardenedSpeedVerticalHoverSlow = 0.1D;
    public static final boolean hardenedEmergencyHoverMode = false;
    public static final int hardenedArmorDisplay = 5;
    public static final double hardenedArmorAbsorption = 0.4D;
    public static final int hardenedArmorEnergyPerHit = 80;
    public static final boolean hardenedEnchantable = false;
    
    // tuningReinforced
    public static final int reinforcedEnergyCapacity = 2000000;
    public static final int reinforcedEnergyPerTick = 100;
    public static final double reinforcedSpeedVertical = 0.48D;
    public static final double reinforcedAccelVertical = 0.13D;
    public static final double reinforcedSpeedSideways = 0.14D;
    public static final double reinforcedSpeedVerticalHover = 0.34D;
    public static final double reinforcedSpeedVerticalHoverSlow = 0.03D;
    public static final boolean reinforcedEmergencyHoverMode = true;
    public static final int reinforcedArmorDisplay = 6;
    public static final double reinforcedArmorAbsorption = 0.5D;
    public static final int reinforcedArmorEnergyPerHit = 120;
    public static final boolean reinforcedEnchantable = true;
    
    // tuningResonant
    public static final int resonantEnergyCapacity = 10000000;
    public static final int resonantEnergyPerTick = 200;
    public static final double resonantSpeedVertical = 0.8D;
    public static final double resonantAccelVertical = 0.14D;
    public static final double resonantSpeedSideways = 0.19D;
    public static final double resonantSpeedVerticalHover = 0.4D;
    public static final double resonantSpeedVerticalHoverSlow = 0.005D;
    public static final boolean resonantEmergencyHoverMode = true;
    public static final int resonantArmorDisplay = 7;
    public static final double resonantArmorAbsorption = 0.6D;
    public static final int resonantArmorEnergyPerHit = 160;
    public static final boolean resonantEnchantable = true;
    
    // tuningFluxPlate
    public static final int fluxPlateEnergyCapacity = 50000000;
    public static final int fluxPlateEnergyPerTick = 400;
    public static final double fluxPlateSpeedVertical = 0.9D;
    public static final double fluxPlateAccelVertical = 0.15D;
    public static final double fluxPlateSpeedSideways = 0.21D;
    public static final double fluxPlateSpeedVerticalHover = 0.45D;
    public static final double fluxPlateSpeedVerticalHoverSlow = 0.0D;
    public static final boolean fluxPlateEmergencyHoverMode = true;
    public static final int fluxPlateArmorDisplay = 8;
    public static final double fluxPlateArmorAbsorption = 0.8D;
    public static final int fluxPlateArmorEnergyPerHit = 240;
    public static final boolean fluxPlateEnchantable = true;
    public static final boolean fluxPlateHasCharger = true;
    public static final int fluxPlateEnergyOutRate = 20000;
    
    // tuningCreative
    public static final double creativeSpeedVertical = 0.9D;
    public static final double creativeAccelVertical = 0.15D;
    public static final double creativeSpeedSideways = 0.20D;
    public static final double creativeSpeedVerticalHover = 0.42D;
    public static final double creativeSpeedVerticalHoverSlow = 0.0D;
    public static final boolean creativeEmergencyHoverMode = true;
    public static final int creativeArmorDisplay = 8;
    public static final double creativeArmorAbsorption = 0.8D;
    public static final boolean creativeEnchantable = true;
    public static final int creativeEnergyOutRate = 20000;
    
    // tuningFluxPackLeadstone
    public static final int fluxpackLeadstoneEnergyCapacity = 400000;
    public static final int fluxpackLeadstoneEnergyInRate = 80;
    public static final int fluxpackLeadstoneEnergyOutRate = 80;
    public static final boolean fluxpackLeadstoneEnchantable = false;
    
    // tuningFluxPackHardened
    public static final int fluxpackHardenedEnergyCapacity = 2000000;
    public static final int fluxpackHardenedEnergyInRate = 400;
    public static final int fluxpackHardenedEnergyOutRate = 400;
    public static final int fluxpackHardenedArmorDisplay = 3;
    public static final double fluxpackHardenedArmorAbsorption = 0.2D;
    public static final int fluxpackHardenedArmorEnergyPerHit = 80;
    public static final boolean fluxpackHardenedEnchantable = false;
    
    // tuningFluxPackRedstone
    public static final int fluxpackRedstoneEnergyCapacity = 10000000;
    public static final int fluxpackRedstoneEnergyInRate = 2000;
    public static final int fluxpackRedstoneEnergyOutRate = 2000;
    public static final int fluxpackRedstoneArmorDisplay = 4;
    public static final double fluxpackRedstoneArmorAbsorption = 0.3D;
    public static final int fluxpackRedstoneArmorEnergyPerHit = 120;
    public static final boolean fluxpackRedstoneEnchantable = true;
    
    // tuningFluxPackResonant
    public static final int fluxpackResonantEnergyCapacity = 50000000;
    public static final int fluxpackResonantEnergyInRate = 10000;
    public static final int fluxpackResonantEnergyOutRate = 10000;
    public static final int fluxpackResonantArmorDisplay = 5;
    public static final double fluxpackResonantArmorAbsorption = 0.4D;
    public static final int fluxpackResonantArmorEnergyPerHit = 160;
    public static final boolean fluxpackResonantEnchantable = true;
    
    // tuningFluxPackCreative
    public static final int fluxpackCreativeEnergyOutRate = 20000;
    public static final int fluxpackCreativeArmorDisplay = 6;
    public static final double fluxpackCreativeArmorAbsorption = 0.5D;
    public static final boolean fluxpackCreativeEnchantable = true;
    
}
