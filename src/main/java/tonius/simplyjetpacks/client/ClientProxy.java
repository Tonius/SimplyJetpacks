package tonius.simplyjetpacks.client;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.client.handler.ClientTickHandler;
import tonius.simplyjetpacks.client.handler.HUDTickHandler;
import tonius.simplyjetpacks.client.util.ParticleUtils;
import tonius.simplyjetpacks.setup.ParticleType;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {
    
    private static Minecraft mc = Minecraft.getMinecraft();
    private static Random rand = new Random();
    
    @Override
    public void registerHandlers() {
        super.registerHandlers();
        
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        FMLCommonHandler.instance().bus().register(new HUDTickHandler());
    }
    
    @Override
    public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
        if (mc.gameSettings.particleSetting == 2) {
            return;
        } else if (mc.gameSettings.particleSetting == 0 || mc.gameSettings.particleSetting == 1 && mc.theWorld.getTotalWorldTime() % 4L == 0) {
            Vec3 userPos = Vec3.createVectorHelper(wearer.posX, wearer.posY, wearer.posZ);
            
            if (!wearer.equals(mc.thePlayer)) {
                userPos = userPos.addVector(0, 1.6D, 0);
            }
            
            Vec3 vLeft = Vec3.createVectorHelper(-0.28D, -0.95D, -0.38D);
            vLeft.rotateAroundY(-wearer.renderYawOffset * (float) Math.PI / 180F);
            
            Vec3 vRight = Vec3.createVectorHelper(0.28D, -0.95D, -0.38D);
            vRight.rotateAroundY(-wearer.renderYawOffset * (float) Math.PI / 180F);
            
            Vec3 vCenter = Vec3.createVectorHelper((rand.nextFloat() - 0.5F) * 0.25F, -0.95D, -0.38D);
            vCenter.rotateAroundY(-wearer.renderYawOffset * (float) Math.PI / 180F);
            
            vLeft = vLeft.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            vRight = vRight.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            vCenter = vCenter.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            
            Vec3 v = userPos.addVector(vLeft.xCoord, vLeft.yCoord, vLeft.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
            
            v = userPos.addVector(vRight.xCoord, vRight.yCoord, vRight.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
            
            v = userPos.addVector(vCenter.xCoord, vCenter.yCoord, vCenter.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
        }
    }
    
    @Override
    public void updateCustomKeybinds() {
        ClientTickHandler.updateCustomKeybinds();
    }
    
}
