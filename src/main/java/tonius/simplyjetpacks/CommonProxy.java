package tonius.simplyjetpacks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.crafting.PlatingReturnHandler;
import tonius.simplyjetpacks.handler.EntityInteractHandler;
import tonius.simplyjetpacks.handler.GuiHandler;
import tonius.simplyjetpacks.handler.LivingTickHandler;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ParticleType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {
    
    public void registerHandlers() {
        SimplyJetpacks.logger.info("Registering handlers");
        NetworkRegistry.INSTANCE.registerGuiHandler(SimplyJetpacks.instance, new GuiHandler());
        FMLCommonHandler.instance().bus().register(new SyncHandler());
        FMLCommonHandler.instance().bus().register(new PlatingReturnHandler());
        MinecraftForge.EVENT_BUS.register(new EntityInteractHandler());
        MinecraftForge.EVENT_BUS.register(new LivingTickHandler());
    }
    
    public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
    }
    
    public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
    }
    
    public void throwCoFHLibException() {
        throw new RuntimeException("CoFHLib is not installed or not up to date. Please install CoFH Core 3.0.2 or newer, or CoFHLib standalone 1.0.2 or newer.");
    }
    
}
