package tonius.simplyjetpacks;

import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

import org.apache.logging.log4j.Logger;

import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.recipes.SJUpgradingRecipe;
import tonius.simplyjetpacks.setup.SJItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = SimplyJetpacks.modid, name = SimplyJetpacks.name, dependencies = SimplyJetpacks.dependencies, guiFactory = "tonius.simplyjetpacks.config.ConfigGuiFactorySJ")
public class SimplyJetpacks {

    @Instance("simplyjetpacks")
    public static SimplyJetpacks instance;

    public static final String modid = "simplyjetpacks";
    public static final String name = "SimplyJetpacks";
    public static final String dependencies = "after:ThermalExpansion;after:RedstoneArsenal";

    @SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;
    public static SyncTracker keyboard;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        logger.info("Starting Simply Jetpacks");

        SJConfig.preInit(evt);

        RecipeSorter.register("simplyjetpacks:upgrading", SJUpgradingRecipe.class, Category.SHAPED, "after:minecraft:shaped");
        SJItems.preInit();

        PacketHandler.preInit();

        proxy.registerHandlers();
    }

    @EventHandler
    public static void init(FMLInitializationEvent evt) {
        SJItems.init();
    }

    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent evt) {
        SyncTracker.clearAll();
    }

}
