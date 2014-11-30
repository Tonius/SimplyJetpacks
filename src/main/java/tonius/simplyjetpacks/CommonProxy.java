package tonius.simplyjetpacks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.crafting.SJCraftingHandler;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import tonius.simplyjetpacks.tickhandler.LivingTickHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {
    
    public void registerHandlers() {
        SimplyJetpacks.logger.info("Registering handlers");
        FMLCommonHandler.instance().bus().register(new SyncTracker());
        FMLCommonHandler.instance().bus().register(new SJCraftingHandler());
        MinecraftForge.EVENT_BUS.register(new LivingTickHandler());
    }
    
    public void showJetpackParticles(World world, EntityLivingBase wearer, JetpackParticleType particle) {
    }
    
    public void updateCustomKeybinds() {
    }
    
}
