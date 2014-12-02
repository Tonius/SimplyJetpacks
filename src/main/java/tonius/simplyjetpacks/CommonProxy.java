package tonius.simplyjetpacks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.crafting.PlatingReturnHandler;
import tonius.simplyjetpacks.handler.EntityInteractHandler;
import tonius.simplyjetpacks.handler.LivingTickHandler;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {
    
    public void registerHandlers() {
        SimplyJetpacks.logger.info("Registering handlers");
        FMLCommonHandler.instance().bus().register(new SyncTracker());
        FMLCommonHandler.instance().bus().register(new PlatingReturnHandler());
        MinecraftForge.EVENT_BUS.register(new EntityInteractHandler());
        MinecraftForge.EVENT_BUS.register(new LivingTickHandler());
    }
    
    public void showJetpackParticles(World world, EntityLivingBase wearer, JetpackParticleType particle) {
    }
    
    public void updateCustomKeybinds() {
    }
    
}
