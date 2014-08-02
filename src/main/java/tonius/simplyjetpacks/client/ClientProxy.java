package tonius.simplyjetpacks.client;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.SyncTracker;
import tonius.simplyjetpacks.client.tickhandler.ClientTickHandler;
import tonius.simplyjetpacks.client.tickhandler.HUDTickHandler;
import tonius.simplyjetpacks.client.tickhandler.KeyHandler;
import tonius.simplyjetpacks.client.util.ParticleUtils;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private static Minecraft mc = Minecraft.getMinecraft();
    private static Random rand = new Random();

    @Override
    public void registerHandlers() {
        super.registerHandlers();

        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        FMLCommonHandler.instance().bus().register(new KeyHandler());
        FMLCommonHandler.instance().bus().register(new HUDTickHandler());
        FMLCommonHandler.instance().bus().register(new SyncTracker());
    }

    @Override
    public void showJetpackParticles(World world, EntityLivingBase wearer, JetpackParticleType particle) {
        if (SJConfig.enableJetpackParticles) {
            Vec3 userPos = Vec3.createVectorHelper(wearer.posX, wearer.posY, wearer.posZ);

            if (!(wearer.equals(mc.thePlayer))) {
                userPos = userPos.addVector(0, 1.6D, 0);
            }

            Vec3 vLeft = Vec3.createVectorHelper(-0.28D, -1.0D, -0.38D);
            vLeft.rotateAroundY(-wearer.renderYawOffset * (float) Math.PI / 180F);

            Vec3 vRight = Vec3.createVectorHelper(0.28D, -1.0D, -0.38D);
            vRight.rotateAroundY(-wearer.renderYawOffset * (float) Math.PI / 180F);

            Vec3 vCenter = Vec3.createVectorHelper((rand.nextFloat() - 0.5F) * 0.25F, -1.0D, -0.38D);
            vCenter.rotateAroundY(-wearer.renderYawOffset * (float) Math.PI / 180F);

            vLeft = vLeft.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            vRight = vRight.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            vCenter = vCenter.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);

            Vec3 v = userPos.addVector(vLeft.xCoord, vLeft.yCoord, vLeft.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, 0, -0.2, 0);

            v = userPos.addVector(vRight.xCoord, vRight.yCoord, vRight.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, 0, -0.2, 0);

            v = userPos.addVector(vCenter.xCoord, vCenter.yCoord, vCenter.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, 0, -0.2, 0);
        }
    }

}
