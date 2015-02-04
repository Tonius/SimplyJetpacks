package tonius.simplyjetpacks.util;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ParticleType;

public abstract class StringUtils {
    
    public static final String BLACK = (char) 167 + "0";
    public static final String BLUE = (char) 167 + "1";
    public static final String GREEN = (char) 167 + "2";
    public static final String TEAL = (char) 167 + "3";
    public static final String RED = (char) 167 + "4";
    public static final String PURPLE = (char) 167 + "5";
    public static final String ORANGE = (char) 167 + "6";
    public static final String LIGHT_GRAY = (char) 167 + "7";
    public static final String GRAY = (char) 167 + "8";
    public static final String LIGHT_BLUE = (char) 167 + "9";
    public static final String BRIGHT_GREEN = (char) 167 + "a";
    public static final String BRIGHT_BLUE = (char) 167 + "b";
    public static final String LIGHT_RED = (char) 167 + "c";
    public static final String PINK = (char) 167 + "d";
    public static final String YELLOW = (char) 167 + "e";
    public static final String WHITE = (char) 167 + "f";
    public static final String OBFUSCATED = (char) 167 + "k";
    public static final String BOLD = (char) 167 + "l";
    public static final String STRIKETHROUGH = (char) 167 + "m";
    public static final String UNDERLINE = (char) 167 + "n";
    public static final String ITALIC = (char) 167 + "o";
    public static final String END = (char) 167 + "r";
    private static final DecimalFormat formatter = new DecimalFormat("###,###");
    
    public static boolean isAltKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU);
    }
    
    public static boolean isControlKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
    }
    
    public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }
    
    public static String getScaledNumber(int number) {
        return getScaledNumber(number, 2);
    }
    
    public static String getScaledNumber(int number, int minDigits) {
        String numString = "";
        
        int numMod = 10 * minDigits;
        
        if (number > 100000 * numMod) {
            numString += number / 1000000 + "M";
        } else if (number > 100 * numMod) {
            numString += number / 1000 + "k";
        } else {
            numString += number;
        }
        return numString;
    }
    
    public static String getFormattedNumber(int number) {
        return formatter.format(number);
    }
    
    public static String getTierText(int tier) {
        return String.format(translate("tooltip.tier"), tier);
    }
    
    public static String getFuelText(FuelType fuelType, int amount, int max, boolean infinite) {
        if (infinite) {
            return LIGHT_GRAY + translate("tooltip.fuel.infinite");
        }
        return LIGHT_GRAY + getFormattedNumber(amount) + " / " + getFormattedNumber(max) + fuelType.suffix;
    }
    
    public static String getStateText(boolean state) {
        String onOrOff = state ? BRIGHT_GREEN + translate("tooltip.on") : LIGHT_RED + translate("tooltip.off");
        return ORANGE + translate("tooltip.state") + ": " + onOrOff;
    }
    
    public static String getHoverModeText(boolean state) {
        String enabledOrDisabled = state ? BRIGHT_GREEN + translate("tooltip.enabled") : LIGHT_RED + translate("tooltip.disabled");
        return ORANGE + translate("tooltip.hoverMode") + ": " + enabledOrDisabled;
    }
    
    public static String getChargerStateText(boolean state) {
        String onOrOff = state ? BRIGHT_GREEN + translate("tooltip.enabled") : LIGHT_RED + translate("tooltip.disabled");
        return ORANGE + translate("tooltip.chargerState") + ": " + onOrOff;
    }
    
    public static String getFuelUsageText(FuelType fuelType, int usage) {
        String usageText = getFormattedNumber(usage) + fuelType.suffix + "/t";
        return ORANGE + translate("tooltip.fuelUsage") + ": " + LIGHT_GRAY + usageText;
    }
    
    public static String getChargerRateText(int rate) {
        String rateText = rate > 0 ? getFormattedNumber(rate) + " RF/t" : translate("tooltip.energy.none");
        return ORANGE + translate("tooltip.chargerRate") + ": " + LIGHT_GRAY + rateText;
    }
    
    public static String getEnergySendText(int send) {
        return ORANGE + translate("tooltip.energySend") + ": " + LIGHT_GRAY + getFormattedNumber(send) + " RF/t";
    }
    
    public static String getEnergyReceiveText(int receive) {
        String usageText = receive < Integer.MAX_VALUE ? getFormattedNumber(receive) + " RF/t" : translate("tooltip.energy.none");
        return ORANGE + translate("tooltip.energyReceive") + ": " + LIGHT_GRAY + usageText;
    }
    
    public static String getArmoredText(boolean armored) {
        String yesOrNo = armored ? BRIGHT_GREEN + translate("tooltip.yes") : LIGHT_RED + translate("tooltip.no");
        return ORANGE + translate("tooltip.armored") + ": " + yesOrNo;
    }
    
    public static String getParticlesText(ParticleType particle) {
        return ORANGE + translate("tooltip.particles") + ": " + LIGHT_GRAY + translate("tooltip.particles." + particle.ordinal());
    }
    
    public static String getHUDFuelText(String prefix, int percent, FuelType fuelType, int fuel) {
        String text = "";
        if (!Config.minimalFuelHUD) {
            text += translate("gui.hud." + prefix + ".fuel") + ": ";
        }
        if (percent > 0) {
            text += getColoredPercent(percent) + "%";
        } else {
            text += RED + translate("gui.hud.fuel.depleted");
        }
        if (Config.showExactFuelInHUD) {
            text += " (" + getFormattedNumber(fuel) + fuelType.suffix + ")";
        }
        return text;
    }
    
    public static String getHUDStateText(Boolean engine, Boolean hover, Boolean charger) {
        String text = "";
        if (engine != null) {
            text += (engine ? BRIGHT_GREEN : RED) + translate("gui.hud.state.engine") + END;
        }
        if (hover != null) {
            if (engine != null) {
                text += LIGHT_GRAY + " - ";
            }
            text += (hover ? BRIGHT_GREEN : RED) + translate("gui.hud.state.hover") + END;
        }
        if (charger != null) {
            if (hover != null || engine != null) {
                text += LIGHT_GRAY + " - ";
            }
            text += (charger ? BRIGHT_GREEN : RED) + translate("gui.hud.state.charger");
        }
        return text;
    }
    
    public static String getColoredPercent(int percent) {
        if (percent > 70) {
            return BRIGHT_GREEN + percent;
        } else if (percent > 40 && percent <= 70) {
            return YELLOW + percent;
        } else if (percent > 10 && percent <= 40) {
            return ORANGE + percent;
        } else {
            return LIGHT_RED + percent;
        }
    }
    
    public static String getShiftText() {
        return LIGHT_GRAY + String.format(translate("tooltip.holdShift"), YELLOW + ITALIC + translate("tooltip.holdShift.shift") + END + LIGHT_GRAY);
    }
    
    public static boolean canShowDetails() {
        return !Config.holdShiftForDetails || isShiftKeyDown();
    }
    
    public static String translate(String unlocalized) {
        return translate(unlocalized, true);
    }
    
    public static String translate(String unlocalized, boolean prefix) {
        if (prefix) {
            return StatCollector.translateToLocal(SimplyJetpacks.PREFIX + unlocalized);
        }
        return StatCollector.translateToLocal(unlocalized);
    }
    
    public static void addDescriptionLines(List<String> list, String base, String color) {
        int i = 1;
        while (true) {
            String unlocalized = SimplyJetpacks.PREFIX + "tooltip." + base + ".description." + i;
            String localized = StatCollector.translateToLocal(unlocalized);
            if (unlocalized.equals(localized)) {
                break;
            }
            list.add(color + localized);
            i++;
        }
    }
    
}
