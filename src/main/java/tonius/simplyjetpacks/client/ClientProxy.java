package tonius.simplyjetpacks.client;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.client.tickhandler.ClientTickHandler;
import tonius.simplyjetpacks.client.tickhandler.HUDTickHandler;
import tonius.simplyjetpacks.client.tickhandler.KeyHandler;
import tonius.simplyjetpacks.client.util.ParticleUtils;
import tonius.simplyjetpacks.config.MainConfig;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import tonius.simplyjetpacks.util.Vector3;
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
        if (MainConfig.enableEnergyHUD) {
            FMLCommonHandler.instance().bus().register(new HUDTickHandler());
        }
    }

    @Override
    public void showJetpackParticles(World world, int entityID, JetpackParticleType particle) {
        if (MainConfig.enableJetpackParticles) {
            Entity entity = world.getEntityByID(entityID);
            if (entity != null && entity instanceof EntityLivingBase) {
                EntityLivingBase wearer = (EntityLivingBase) entity;
                Vector3 playerPos = new Vector3(wearer);

                if (!(wearer.equals(mc.thePlayer))) {
                    playerPos.translate(new Vector3(0, 1.50, 0));
                }

                Vector3 vLeft = new Vector3();
                vLeft.z -= 0.38;
                vLeft.x -= 0.28;
                vLeft.rotate(wearer.renderYawOffset);
                vLeft.y -= 1.0;

                Vector3 vRight = new Vector3();
                vRight.z -= 0.38;
                vRight.x += 0.28;
                vRight.rotate(wearer.renderYawOffset);
                vRight.y -= 1.0;

                Vector3 vCenter = new Vector3();
                vCenter.z -= 0.30;
                vCenter.x = (rand.nextFloat() - 0.5F) * 0.25F;
                vCenter.rotate(wearer.renderYawOffset);
                vCenter.y -= 1.05;

                vLeft = Vector3.translate(vLeft.clone(), new Vector3(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D));
                vRight = Vector3.translate(vRight.clone(), new Vector3(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D));
                vCenter = Vector3.translate(vCenter.clone(), new Vector3(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D));

                Vector3 v = new Vector3(playerPos).translate(vLeft);
                ParticleUtils.spawnParticle(particle, world, v.x, v.y, v.z, 0, -0.2, 0);

                v = new Vector3(playerPos).translate(vRight);
                ParticleUtils.spawnParticle(particle, world, v.x, v.y, v.z, 0, -0.2, 0);

                v = new Vector3(playerPos).translate(vCenter);
                ParticleUtils.spawnParticle(particle, world, v.x, v.y, v.z, 0, -0.2, 0);
            }
        }
    }

}
