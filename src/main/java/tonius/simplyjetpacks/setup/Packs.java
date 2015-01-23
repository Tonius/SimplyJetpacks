package tonius.simplyjetpacks.setup;

import net.minecraft.item.EnumRarity;
import tonius.simplyjetpacks.config.PackDefaults;
import tonius.simplyjetpacks.item.meta.FluxPack;
import tonius.simplyjetpacks.item.meta.JetPlate;
import tonius.simplyjetpacks.item.meta.Jetpack;
import tonius.simplyjetpacks.item.meta.JetpackPotato;

/**
 * for default pack tuning refer to {@link PackDefaults}
 */
public class Packs {
    
    public static Jetpack jetpackPotato;
    public static Jetpack jetpackCreative;
    public static FluxPack fluxPackCreative;
    
    public static Jetpack jetpackTE1;
    public static Jetpack jetpackTE1Armored;
    public static Jetpack jetpackTE2;
    public static Jetpack jetpackTE2Armored;
    public static Jetpack jetpackTE3;
    public static Jetpack jetpackTE3Armored;
    public static Jetpack jetpackTE4;
    public static Jetpack jetpackTE4Armored;
    public static Jetpack jetpackTE5;
    public static FluxPack fluxPackTE1;
    public static FluxPack fluxPackTE2;
    public static FluxPack fluxPackTE2Armored;
    public static FluxPack fluxPackTE3;
    public static FluxPack fluxPackTE3Armored;
    public static FluxPack fluxPackTE4;
    public static FluxPack fluxPackTE4Armored;
    
    public static Jetpack jetpackEIO1;
    public static Jetpack jetpackEIO1Armored;
    public static Jetpack jetpackEIO2;
    public static Jetpack jetpackEIO2Armored;
    public static Jetpack jetpackEIO3;
    public static Jetpack jetpackEIO3Armored;
    public static Jetpack jetpackEIO4;
    public static Jetpack jetpackEIO4Armored;
    public static Jetpack jetpackEIO5;
    public static FluxPack fluxPackEIO1;
    public static FluxPack fluxPackEIO2;
    public static FluxPack fluxPackEIO2Armored;
    public static FluxPack fluxPackEIO3;
    public static FluxPack fluxPackEIO3Armored;
    public static FluxPack fluxPackEIO4;
    public static FluxPack fluxPackEIO4Armored;
    
    public static Jetpack jetpackBC1;
    public static Jetpack jetpackBC1Armored;
    public static Jetpack jetpackBC2;
    public static Jetpack jetpackBC2Armored;
    
    public static void preInit() {
        jetpackPotato = new JetpackPotato(0, EnumRarity.common, "jetpackPotato");
        jetpackCreative = (Jetpack) new JetPlate(9001, EnumRarity.epic, "jetpackCreative").setDefaultParticleType(ParticleType.RAINBOW_SMOKE).setUsesFuel(false).setHasFuelIndicator(false).setShowEmptyInCreativeTab(false);
        fluxPackCreative = (FluxPack) new FluxPack(9001, EnumRarity.epic, "fluxPackCreative").setUsesFuel(false).setHasFuelIndicator(false).setShowEmptyInCreativeTab(false).setIsArmored(true).setShowArmored(false);
        
        if (ModType.THERMAL_EXPANSION.isLoaded()) {
            jetpackTE1 = new Jetpack(1, EnumRarity.common, "jetpackTE1");
            jetpackTE1Armored = (Jetpack) new Jetpack(1, EnumRarity.common, "jetpackTE1").setIsArmored(true).setPlatingMeta(1);
            jetpackTE2 = new Jetpack(2, EnumRarity.common, "jetpackTE2");
            jetpackTE2Armored = (Jetpack) new Jetpack(2, EnumRarity.common, "jetpackTE2").setIsArmored(true).setPlatingMeta(2);
            jetpackTE3 = new Jetpack(3, EnumRarity.uncommon, "jetpackTE3");
            jetpackTE3Armored = (Jetpack) new Jetpack(3, EnumRarity.uncommon, "jetpackTE3").setIsArmored(true).setPlatingMeta(3);
            jetpackTE4 = new Jetpack(4, EnumRarity.rare, "jetpackTE4");
            jetpackTE4Armored = (Jetpack) new Jetpack(4, EnumRarity.rare, "jetpackTE4").setIsArmored(true).setPlatingMeta(4);
            jetpackTE5 = (Jetpack) new JetPlate(5, EnumRarity.epic, "jetpackTE5").setFluxBased(true);
            fluxPackTE1 = new FluxPack(1, EnumRarity.common, "fluxPackTE1");
            fluxPackTE2 = new FluxPack(2, EnumRarity.common, "fluxPackTE2");
            fluxPackTE2Armored = (FluxPack) new FluxPack(2, EnumRarity.common, "fluxPackTE2").setIsArmored(true).setPlatingMeta(1);
            fluxPackTE3 = new FluxPack(3, EnumRarity.uncommon, "fluxPackTE3");
            fluxPackTE3Armored = (FluxPack) new FluxPack(3, EnumRarity.uncommon, "fluxPackTE3").setIsArmored(true).setPlatingMeta(2);
            fluxPackTE4 = new FluxPack(4, EnumRarity.rare, "fluxPackTE4");
            fluxPackTE4Armored = (FluxPack) new FluxPack(4, EnumRarity.rare, "fluxPackTE4").setIsArmored(true).setPlatingMeta(3);
        }
        
        if (ModType.ENDER_IO.isLoaded()) {
            jetpackEIO1 = new Jetpack(1, EnumRarity.common, "jetpackEIO1");
            jetpackEIO1Armored = (Jetpack) new Jetpack(1, EnumRarity.common, "jetpackEIO1").setIsArmored(true).setPlatingMeta(11);
            jetpackEIO2 = new Jetpack(2, EnumRarity.common, "jetpackEIO2");
            jetpackEIO2Armored = (Jetpack) new Jetpack(2, EnumRarity.common, "jetpackEIO2").setIsArmored(true).setPlatingMeta(12);
            jetpackEIO3 = new Jetpack(3, EnumRarity.uncommon, "jetpackEIO3");
            jetpackEIO3Armored = (Jetpack) new Jetpack(3, EnumRarity.uncommon, "jetpackEIO3").setIsArmored(true).setPlatingMeta(13);
            jetpackEIO4 = new Jetpack(4, EnumRarity.rare, "jetpackEIO4");
            jetpackEIO4Armored = (Jetpack) new Jetpack(4, EnumRarity.rare, "jetpackEIO4").setIsArmored(true).setPlatingMeta(14);
            jetpackEIO5 = new JetPlate(5, EnumRarity.epic, "jetpackEIO5");
            fluxPackEIO1 = new FluxPack(1, EnumRarity.common, "fluxPackEIO1");
            fluxPackEIO2 = new FluxPack(2, EnumRarity.common, "fluxPackEIO2");
            fluxPackEIO2Armored = (FluxPack) new FluxPack(2, EnumRarity.common, "fluxPackEIO2").setIsArmored(true).setPlatingMeta(11);
            fluxPackEIO3 = new FluxPack(3, EnumRarity.uncommon, "fluxPackEIO3");
            fluxPackEIO3Armored = (FluxPack) new FluxPack(3, EnumRarity.uncommon, "fluxPackEIO3").setIsArmored(true).setPlatingMeta(12);
            fluxPackEIO4 = new FluxPack(4, EnumRarity.rare, "fluxPackEIO4");
            fluxPackEIO4Armored = (FluxPack) new FluxPack(4, EnumRarity.rare, "fluxPackEIO4").setIsArmored(true).setPlatingMeta(13);
        }
        
        if (ModType.BUILDCRAFT.isLoaded()) {
            jetpackBC1 = (Jetpack) new Jetpack(1, EnumRarity.common, "jetpackBC1").setFuelFluid("fuel");
            jetpackBC1Armored = (Jetpack) new Jetpack(1, EnumRarity.common, "jetpackBC1").setFuelFluid("fuel").setIsArmored(true).setPlatingMeta(21);
            jetpackBC2 = new Jetpack(2, EnumRarity.uncommon, "jetpackBC2");
            jetpackBC2Armored = (Jetpack) new Jetpack(2, EnumRarity.uncommon, "jetpackBC2").setIsArmored(true).setPlatingMeta(22);
        }
    }
    
}
