package tonius.simplyjetpacks.config;

import net.minecraftforge.common.Configuration;

public class TuningConfig {
    public static int jetpackTier0_maxEnergy;
    public static int jetpackTier0_energyUsage;
    public static double jetpackTier0_maxSpeed;
    public static double jetpackTier0_acceleration;

    public static int jetpackTier1_maxEnergy;
    public static int jetpackTier1_maxChargingRate;
    public static int jetpackTier1_energyUsage;
    public static double jetpackTier1_maxSpeed;
    public static double jetpackTier1_acceleration;
    public static double jetpackTier1_forwardThrust;
    public static double jetpackTier1_hoverModeIdleSpeed;
    public static double jetpackTier1_hoverModeSneakingSpeed;

    public static int jetpackArmoredTier1_armorDisplay;
    public static double jetpackArmoredTier1_armorAbsorption;

    public static int jetpackTier2_maxEnergy;
    public static int jetpackTier2_maxChargingRate;
    public static int jetpackTier2_energyUsage;
    public static double jetpackTier2_maxSpeed;
    public static double jetpackTier2_acceleration;
    public static double jetpackTier2_forwardThrust;
    public static double jetpackTier2_hoverModeIdleSpeed;
    public static double jetpackTier2_hoverModeSneakingSpeed;

    public static int jetpackArmoredTier2_armorDisplay;
    public static double jetpackArmoredTier2_armorAbsorption;

    public static int jetpackTier3_maxEnergy;
    public static int jetpackTier3_maxChargingRate;
    public static int jetpackTier3_energyUsage;
    public static double jetpackTier3_maxSpeed;
    public static double jetpackTier3_acceleration;
    public static double jetpackTier3_forwardThrust;
    public static double jetpackTier3_hoverModeIdleSpeed;
    public static double jetpackTier3_hoverModeSneakingSpeed;

    public static int jetpackArmoredTier3_armorDisplay;
    public static double jetpackArmoredTier3_armorAbsorption;

    public static int jetpackTier4_maxEnergy;
    public static int jetpackTier4_maxChargingRate;
    public static int jetpackTier4_energyUsage;
    public static double jetpackTier4_maxSpeed;
    public static double jetpackTier4_acceleration;
    public static double jetpackTier4_forwardThrust;
    public static double jetpackTier4_hoverModeIdleSpeed;
    public static double jetpackTier4_hoverModeSneakingSpeed;

    public static int jetpackArmoredTier4_armorDisplay;
    public static double jetpackArmoredTier4_armorAbsorption;

    public static void loadConfig(Configuration cfg) {
        try {
            cfg.load();

            jetpackTier0_maxEnergy = cfg.get("jetpackTier0", "maxEnergy", 1200).getInt();
            jetpackTier0_energyUsage = cfg.get("jetpackTier0", "energyUsage", 45).getInt();
            jetpackTier0_maxSpeed = cfg.get("jetpackTier0", "maxSpeed", 0.9D).getDouble(0.9D);
            jetpackTier0_acceleration = cfg.get("jetpackTier0", "acceleration", 0.5D).getDouble(0.5D);

            jetpackTier1_maxEnergy = cfg.get("jetpackTier1", "maxEnergy", 25000).getInt();
            jetpackTier1_maxChargingRate = cfg.get("jetpackTier1", "maxChargingRate", 800).getInt();
            jetpackTier1_energyUsage = cfg.get("jetpackTier1", "energyUsage", 20).getInt();
            jetpackTier1_maxSpeed = cfg.get("jetpackTier1", "maxSpeed", 0.22D).getDouble(0.22D);
            jetpackTier1_acceleration = cfg.get("jetpackTier1", "acceleration", 0.16D).getDouble(0.16D);
            jetpackTier1_forwardThrust = cfg.get("jetpackTier1", "forwardThrust", 0.0D).getDouble(0.0D);
            jetpackTier1_hoverModeIdleSpeed = cfg.get("jetpackTier1", "hoverModeIdleSpeed", 0.18D).getDouble(0.18D);
            jetpackTier1_hoverModeSneakingSpeed = cfg.get("jetpackTier1", "hoverModeSneakingSpeed", 0.2D).getDouble(0.2D);

            jetpackArmoredTier1_armorDisplay = cfg.get("jetpackArmoredTier1", "armorDisplay", 5).getInt();
            jetpackArmoredTier1_armorAbsorption = cfg.get("jetpackArmoredTier1", "armorAbsorption", 0.2D).getDouble(0.2D);

            jetpackTier2_maxEnergy = cfg.get("jetpackTier2", "maxEnergy", 400000).getInt();
            jetpackTier2_maxChargingRate = cfg.get("jetpackTier2", "maxChargingRate", 4000).getInt();
            jetpackTier2_energyUsage = cfg.get("jetpackTier2", "energyUsage", 75).getInt();
            jetpackTier2_maxSpeed = cfg.get("jetpackTier2", "maxSpeed", 0.3D).getDouble(0.3D);
            jetpackTier2_acceleration = cfg.get("jetpackTier2", "acceleration", 0.18D).getDouble(0.18D);
            jetpackTier2_forwardThrust = cfg.get("jetpackTier2", "forwardThrust", 0.04D).getDouble(0.04D);
            jetpackTier2_hoverModeIdleSpeed = cfg.get("jetpackTier2", "hoverModeIdleSpeed", 0.1D).getDouble(0.1D);
            jetpackTier2_hoverModeSneakingSpeed = cfg.get("jetpackTier2", "hoverModeSneakingSpeed", 0.2D).getDouble(0.2D);

            jetpackArmoredTier2_armorDisplay = cfg.get("jetpackArmoredTier2", "armorDisplay", 6).getInt();
            jetpackArmoredTier2_armorAbsorption = cfg.get("jetpackArmoredTier2", "armorAbsorption", 0.3D).getDouble(0.3D);

            jetpackTier3_maxEnergy = cfg.get("jetpackTier3", "maxEnergy", 2000000).getInt();
            jetpackTier3_maxChargingRate = cfg.get("jetpackTier3", "maxChargingRate", 20000).getInt();
            jetpackTier3_energyUsage = cfg.get("jetpackTier3", "energyUsage", 150).getInt();
            jetpackTier3_maxSpeed = cfg.get("jetpackTier3", "maxSpeed", 0.5D).getDouble(0.5D);
            jetpackTier3_acceleration = cfg.get("jetpackTier3", "acceleration", 0.2D).getDouble(0.2D);
            jetpackTier3_forwardThrust = cfg.get("jetpackTier3", "forwardThrust", 0.12D).getDouble(0.12D);
            jetpackTier3_hoverModeIdleSpeed = cfg.get("jetpackTier3", "hoverModeIdleSpeed", 0.03D).getDouble(0.03D);
            jetpackTier3_hoverModeSneakingSpeed = cfg.get("jetpackTier3", "hoverModeSneakingSpeed", 0.22D).getDouble(0.22D);

            jetpackArmoredTier3_armorDisplay = cfg.get("jetpackArmoredTier3", "armorDisplay", 7).getInt();
            jetpackArmoredTier3_armorAbsorption = cfg.get("jetpackArmoredTier3", "armorAbsorption", 0.4D).getDouble(0.4D);

            jetpackTier4_maxEnergy = cfg.get("jetpackTier4", "maxEnergy", 10000000).getInt();
            jetpackTier4_maxChargingRate = cfg.get("jetpackTier4", "maxChargingRate", 100000).getInt();
            jetpackTier4_energyUsage = cfg.get("jetpackTier4", "energyUsage", 400).getInt();
            jetpackTier4_maxSpeed = cfg.get("jetpackTier4", "maxSpeed", 0.75D).getDouble(0.75D);
            jetpackTier4_acceleration = cfg.get("jetpackTier4", "acceleration", 0.32D).getDouble(0.32D);
            jetpackTier4_forwardThrust = cfg.get("jetpackTier4", "forwardThrust", 0.20D).getDouble(0.20D);
            jetpackTier4_hoverModeIdleSpeed = cfg.get("jetpackTier4", "hoverModeIdleSpeed", 0.005D).getDouble(0.005D);
            jetpackTier4_hoverModeSneakingSpeed = cfg.get("jetpackTier4", "hoverModeSneakingSpeed", 0.24D).getDouble(0.24D);

            jetpackArmoredTier4_armorDisplay = cfg.get("jetpackArmoredTier4", "armorDisplay", 8).getInt();
            jetpackArmoredTier4_armorAbsorption = cfg.get("jetpackArmoredTier4", "armorAbsorption", 0.6D).getDouble(0.6D);
        } finally {
            if (cfg.hasChanged())
                cfg.save();
        }
    }
}
