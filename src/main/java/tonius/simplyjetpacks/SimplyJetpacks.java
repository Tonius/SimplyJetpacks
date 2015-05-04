package tonius.simplyjetpacks;

import java.util.Iterator;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

import org.apache.logging.log4j.Logger;

import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.Packs;
import cpw.mods.fml.client.CustomModLoadingErrorDisplayException;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = SimplyJetpacks.MODID, version = SimplyJetpacks.VERSION, dependencies = SimplyJetpacks.DEPENDENCIES, guiFactory = SimplyJetpacks.GUI_FACTORY)
public class SimplyJetpacks {
    
    public static final String MODID = "simplyjetpacks";
    public static final String VERSION = "1.5.0";
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    public static final String DEPENDENCIES = "required-after:Forge@[10.13.2.1291,);after:CoFHCore;after:ThermalExpansion;after:RedstoneArsenal;after:EnderIO;after:BuildCraft|Core";
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
        
        checkCoFHLib();
        
        Packs.preInit();
        Config.preInit(evt);
        ModItems.preInit();
    }
    
    @EventHandler
    public static void init(FMLInitializationEvent evt) {
        RecipeSorter.register(SimplyJetpacks.MODID + ":upgrading", UpgradingRecipe.class, Category.SHAPED, "after:minecraft:shaped");
        proxy.registerHandlers();
        PacketHandler.init();
        ModItems.init();
    }
    
    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent evt) {
        SyncHandler.clearAll();
    }
    
    private static void checkCoFHLib() {
        Iterable<? extends ModContainer> list = ModAPIManager.INSTANCE.getAPIList();
        for (Iterator<? extends ModContainer> itr = list.iterator(); itr.hasNext();) {
            ModContainer api = itr.next();
            if (api.getName().equals("API: CoFHLib")) {
                logger.info("Successfully found CoFHLib");
                return;
            }
        }
        logger.error("Could not find CoFHLib!");
        throw new CustomModLoadingErrorDisplayException() {
            
            @Override
            public void initGui(GuiErrorScreen errorScreen, FontRenderer fontRenderer) {
            }
            
            @Override
            public void drawScreen(GuiErrorScreen gui, FontRenderer fontRenderer, int mouseRelX, int mouseRelY, float tickTime) {
                gui.drawDefaultBackground();
                gui.drawCenteredString(fontRenderer, "Simply Jetpacks Error - CoFHLib Not Found", gui.width / 2, 85, 0xFF5555);
                
                gui.drawCenteredString(fontRenderer, "CoFHLib is not installed. Please install the latest version of CoFH Core,", gui.width / 2, 100, 0xFFFFFF);
                gui.drawCenteredString(fontRenderer, "or the latest build of standalone CoFHLib.", gui.width / 2, 110, 0xFFFFFF);
            }
            
        };
    }
    
}
