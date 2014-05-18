package tonius.simplyjetpacks.util;

import org.lwjgl.input.Keyboard;

public final class StringUtils {

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

    public static String getChargeText(int charge, int total) {
        return ORANGE + LangUtils.translate("tooltip.charge") + ": " + LIGHT_GRAY + getScaledNumber(charge) + " / " + getScaledNumber(total) + " RF";
    }

    public static String getStateText(boolean state) {
        String onOrOff = state ? BRIGHT_GREEN + LangUtils.translate("tooltip.state.on") : LIGHT_RED + LangUtils.translate("tooltip.state.off");
        return ORANGE + LangUtils.translate("tooltip.state") + ": " + onOrOff;
    }

    public static String getHoverModeText(boolean state) {
        String enabledOrDisabled = state ? BRIGHT_GREEN + LangUtils.translate("tooltip.jetpack.hoverMode.enabled") : LIGHT_RED + LangUtils.translate("tooltip.jetpack.hoverMode.disabled");
        return ORANGE + LangUtils.translate("tooltip.jetpack.hoverMode") + ": " + enabledOrDisabled;
    }

    public static String getEnergyUsageText(int usage) {
        return ORANGE + LangUtils.translate("tooltip.energyUsage") + ": " + LIGHT_GRAY + usage + " RF/t";
    }

    public static String getShiftText() {
        return LIGHT_GRAY + LangUtils.translate("tooltip.holdShift1") + " " + YELLOW + ITALIC + LangUtils.translate("tooltip.holdShift2") + " " + END + LIGHT_GRAY + LangUtils.translate("tooltip.holdShift3");
    }

}
