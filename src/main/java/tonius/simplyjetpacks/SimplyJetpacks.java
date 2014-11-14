package tonius.simplyjetpacks;

import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

import org.apache.logging.log4j.Logger;

import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.setup.SJItems;
import tonius.simplyjetpacks.setup.SJUpgradingRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = SimplyJetpacks.MODID, dependencies = SimplyJetpacks.DEPENDENCIES, guiFactory = SimplyJetpacks.GUI_FACTORY)
public class SimplyJetpacks {
    
    public static final String MODID = "simplyjetpacks";
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    public static final String DEPENDENCIES = "after:ThermalExpansion;after:RedstoneArsenal;after:EnderIO";
    public static final String GUI_FACTORY = "tonius.simplyjetpacks.config.ConfigGuiFactorySJ";
    
    @Instance(MODID)
    public static SimplyJetpacks instance;
    @SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
    public static CommonProxy proxy;
    public static Logger logger;
    public static SyncTracker keyboard;
    
    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        logger.info("Starting Simply Jetpacks");
        
        SJConfig.preInit(evt);
        
        RecipeSorter.register(SimplyJetpacks.MODID + ":upgrading", SJUpgradingRecipe.class, Category.SHAPED, "after:minecraft:shaped");
        SJItems.preInit();
        
        proxy.registerHandlers();
    }
    
    @EventHandler
    public static void init(FMLInitializationEvent evt) {
        SJItems.init();
        PacketHandler.init();
    }
    
    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent evt) {
        SyncTracker.clearAll();
    }
    
}
