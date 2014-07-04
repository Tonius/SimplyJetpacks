package tonius.simplyjetpacks;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Logger;

import tonius.simplyjetpacks.config.MainConfig;
import tonius.simplyjetpacks.config.TuningConfig;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "simplyjetpacks", name = "SimplyJetpacks", dependencies = "after:CoFHCore;after:ThermalExpansion")
public class SimplyJetpacks {

    @Instance("simplyjetpacks")
    public static SimplyJetpacks instance;

    @SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;
    public static KeyboardTracker keyboard;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        logger.info("Starting Simply Jetpacks");

        logger.info("Loading configuration files");
        MainConfig.loadConfig(new Configuration(new File(evt.getModConfigurationDirectory(), "simplyjetpacks/main.cfg")));
        TuningConfig.loadConfig(new Configuration(new File(evt.getModConfigurationDirectory(), "simplyjetpacks/tuning.cfg")));

        SJItems.preInit();

        logger.info("Registering handlers");
        proxy.registerHandlers();
    }

    @EventHandler
    public static void init(FMLInitializationEvent evt) {
        SJItems.init();
    }

}
