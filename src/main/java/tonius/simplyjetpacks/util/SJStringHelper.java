package tonius.simplyjetpacks.util;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidRegistry;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ParticleType;
import cofh.lib.util.helpers.StringHelper;

public abstract class SJStringHelper {
    
    private static final DecimalFormat formatter = new DecimalFormat("###,###");
    
    public static String getFormattedNumber(int number) {
        return formatter.format(number);
    }
    
    public static String getTierText(int tier) {
        return String.format(localize("tooltip.tier"), tier);
    }
    
    public static String getFuelText(FuelType fuelType, int amount, int max, boolean infinite) {
        if (infinite) {
            return StringHelper.LIGHT_GRAY + localize("tooltip.fuel.infinite");
        }
        return StringHelper.LIGHT_GRAY + getFormattedNumber(amount) + " / " + getFormattedNumber(max) + fuelType.suffix;
    }
    
    public static String getStateText(boolean state) {
        String onOrOff = state ? StringHelper.BRIGHT_GREEN + localize("tooltip.on") : StringHelper.LIGHT_RED + localize("tooltip.off");
        return StringHelper.ORANGE + localize("tooltip.state") + ": " + onOrOff;
    }
    
    public static String getHoverModeText(boolean state) {
        String enabledOrDisabled = state ? StringHelper.BRIGHT_GREEN + localize("tooltip.enabled") : StringHelper.LIGHT_RED + localize("tooltip.disabled");
        return StringHelper.ORANGE + localize("tooltip.hoverMode") + ": " + enabledOrDisabled;
    }
    
    public static String getChargerStateText(boolean state) {
        String onOrOff = state ? StringHelper.BRIGHT_GREEN + localize("tooltip.enabled") : StringHelper.LIGHT_RED + localize("tooltip.disabled");
        return StringHelper.ORANGE + localize("tooltip.chargerState") + ": " + onOrOff;
    }
    
    public static String getFuelFluidText(String fluidName) {
        fluidName = localize(FluidRegistry.getFluid(fluidName).getUnlocalizedName(), false, (Object[]) null);
        return StringHelper.ORANGE + localize("tooltip.fuelFluid") + ": " + StringHelper.LIGHT_GRAY + fluidName;
    }
    
    public static String getFuelUsageText(FuelType fuelType, int usage) {
        String usageText = getFormattedNumber(usage) + fuelType.suffix + "/t";
        return StringHelper.ORANGE + localize("tooltip.fuelUsage") + ": " + StringHelper.LIGHT_GRAY + usageText;
    }
    
    public static String getChargerRateText(int rate) {
        String rateText = rate > 0 ? getFormattedNumber(rate) + " RF/t" : localize("tooltip.energy.none");
        return StringHelper.ORANGE + localize("tooltip.chargerRate") + ": " + StringHelper.LIGHT_GRAY + rateText;
    }
    
    public static String getEnergySendText(int send) {
        return StringHelper.ORANGE + localize("tooltip.energySend") + ": " + StringHelper.LIGHT_GRAY + getFormattedNumber(send) + " RF/t";
    }
    
    public static String getEnergyReceiveText(int receive) {
        String usageText = receive < Integer.MAX_VALUE ? getFormattedNumber(receive) + " RF/t" : localize("tooltip.energy.none");
        return StringHelper.ORANGE + localize("tooltip.energyReceive") + ": " + StringHelper.LIGHT_GRAY + usageText;
    }
    
    public static String getParticlesText(ParticleType particle) {
        return StringHelper.ORANGE + localize("tooltip.particles") + ": " + StringHelper.LIGHT_GRAY + localize("tooltip.particles." + particle.ordinal());
    }
    
    public static String getHUDFuelText(String prefix, int percent, FuelType fuelType, int fuel) {
        String text = "";
        if (!Config.minimalFuelHUD) {
            text += localize("gui.hud." + prefix + ".fuel") + ": ";
        }
        if (percent > 0) {
            text += getColoredPercent(percent) + "%";
        } else {
            text += StringHelper.RED + localize("gui.hud.fuel.depleted");
        }
        if (Config.showExactFuelInHUD) {
            text += " (" + getFormattedNumber(fuel) + fuelType.suffix + ")";
        }
        return text;
    }
    
    public static String getHUDStateText(Boolean engine, Boolean hover, Boolean charger) {
        String text = "";
        if (engine != null) {
            text += (engine ? StringHelper.BRIGHT_GREEN : StringHelper.RED) + localize("gui.hud.state.engine") + StringHelper.END;
        }
        if (hover != null) {
            if (engine != null) {
                text += StringHelper.LIGHT_GRAY + " - ";
            }
            text += (hover ? StringHelper.BRIGHT_GREEN : StringHelper.RED) + localize("gui.hud.state.hover") + StringHelper.END;
        }
        if (charger != null) {
            if (hover != null || engine != null) {
                text += StringHelper.LIGHT_GRAY + " - ";
            }
            text += (charger ? StringHelper.BRIGHT_GREEN : StringHelper.RED) + localize("gui.hud.state.charger");
        }
        return text;
    }
    
    public static String getColoredPercent(int percent) {
        if (percent > 70) {
            return StringHelper.BRIGHT_GREEN + percent;
        } else if (percent > 40 && percent <= 70) {
            return StringHelper.YELLOW + percent;
        } else if (percent > 10 && percent <= 40) {
            return StringHelper.ORANGE + percent;
        } else {
            return StringHelper.LIGHT_RED + percent;
        }
    }
    
    public static boolean canShowDetails() {
        return !Config.holdShiftForDetails || StringHelper.isShiftKeyDown();
    }
    
    public static String localize(String unlocalized, Object... args) {
        return localize(unlocalized, true, args);
    }
    
    public static String localize(String unlocalized, boolean prefix, Object... args) {
        String toLocalize = (prefix ? SimplyJetpacks.PREFIX : "") + unlocalized;
        if (args != null && args.length > 0) {
            return StatCollector.translateToLocalFormatted(toLocalize, args);
        } else {
            return StatCollector.translateToLocal(toLocalize);
        }
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
    
    public static void addDescriptionLines(List<String> list, String base) {
        addDescriptionLines(list, base, "");
    }
    
}
