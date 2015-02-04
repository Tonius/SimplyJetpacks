package tonius.simplyjetpacks;

import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

import org.apache.logging.log4j.Logger;

import tonius.simplyjetpacks.command.CommandSwitch;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.Packs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = SimplyJetpacks.MODID, dependencies = SimplyJetpacks.DEPENDENCIES, guiFactory = SimplyJetpacks.GUI_FACTORY)
public class SimplyJetpacks {
    
    public static final String MODID = "simplyjetpacks";
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    public static final String DEPENDENCIES = "after:ThermalExpansion;after:RedstoneArsenal;after:EnderIO;after:BuildCraft|Core";
    public static final String GUI_FACTORY = "tonius.simplyjetpacks.config.ConfigGuiFactory";
    
    @Instance(MODID)
    public static SimplyJetpacks instance;
    @SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
    public static CommonProxy proxy;
    public static Logger logger;
    public static SyncHandler keyboard;
    
    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        logger.info("Starting Simply Jetpacks");
        
        Packs.preInit();
        Config.preInit(evt);
        
        RecipeSorter.register(SimplyJetpacks.MODID + ":upgrading", UpgradingRecipe.class, Category.SHAPED, "after:minecraft:shaped");
        ModItems.preInit();
        
        proxy.registerHandlers();
    }
    
    @EventHandler
    public static void init(FMLInitializationEvent evt) {
        ModItems.init();
        PacketHandler.init();
    }
    
    @EventHandler
    public static void serverStarting(FMLServerStartingEvent evt) {
        evt.registerServerCommand(new CommandSwitch());
    }
    
    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent evt) {
        SyncHandler.clearAll();
    }
    
}
