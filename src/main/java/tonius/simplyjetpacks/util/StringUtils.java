package tonius.simplyjetpacks.util;

import java.text.DecimalFormat;

import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import tonius.simplyjetpacks.ConfigReader;

public final class StringUtils {

    private static DecimalFormat formatter = new DecimalFormat("###,###");

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

    public static String getChargeText(int charge, int total) {
        return ORANGE + translate("tooltip.charge") + ": " + LIGHT_GRAY + getFormattedNumber(charge) + " / " + getFormattedNumber(total) + " RF";
    }

    public static String getStateText(boolean state) {
        String onOrOff = state ? BRIGHT_GREEN + translate("tooltip.state.on") : LIGHT_RED + translate("tooltip.state.off");
        return ORANGE + translate("tooltip.state") + ": " + onOrOff;
    }

    public static String getHoverModeText(boolean state) {
        String enabledOrDisabled = state ? BRIGHT_GREEN + translate("tooltip.jetpack.hoverMode.enabled") : LIGHT_RED + translate("tooltip.jetpack.hoverMode.disabled");
        return ORANGE + translate("tooltip.jetpack.hoverMode") + ": " + enabledOrDisabled;
    }

    public static String getEnergyUsageText(int usage) {
        return ORANGE + translate("tooltip.energyUsage") + ": " + LIGHT_GRAY + usage + " RF/t";
    }

    public static String getArmorText(boolean isArmored) {
        if (isArmored) {
            return BRIGHT_BLUE + ITALIC + translate("tooltip.jetpack.armor.off");
        } else {
            return BRIGHT_BLUE + ITALIC + translate("tooltip.jetpack.armor.on");
        }
    }

    public static String getRequiredArmorText(int tier) {
        return BRIGHT_BLUE + ITALIC + translate("tooltip.jetpack.armor.requires") + ": " + YELLOW + ITALIC + translate("item.simplyjetpacks.metaItem1_" + (tier + 4) + ".name", false);
    }

    public static String getHUDEnergyText(int percent, int energy) {
        if (ConfigReader.showExactEnergyInHUD) {
            return translate("gui.hud.jetpack.energy") + ": " + getColoredPercent(percent) + "% (" + getFormattedNumber(energy) + " RF)";
        }
        return translate("gui.hud.jetpack.energy") + ": " + getColoredPercent(percent) + "%";
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

    public static String getHUDEnergyLowText() {
        return LIGHT_RED + translate("gui.hud.jetpack.warning.low");
    }

    public static String getHUDEnergyEmptyText() {
        return RED + translate("gui.hud.jetpack.warning.empty");
    }

    public static String getShiftText() {
        return LIGHT_GRAY + translate("tooltip.holdShift1") + " " + YELLOW + ITALIC + translate("tooltip.holdShift2") + " " + END + LIGHT_GRAY + translate("tooltip.holdShift3");
    }

    public static String translate(String unlocalized) {
        return translate(unlocalized, true);
    }

    public static String translate(String unlocalized, boolean prefix) {
        if (prefix) {
            return StatCollector.translateToLocal("simplyjetpacks." + unlocalized);
        }
        return StatCollector.translateToLocal(unlocalized);
    }

}
