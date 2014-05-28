package tonius.simplyjetpacks.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;
import tonius.simplyjetpacks.client.particle.EntityColoredSmokeFX;
import tonius.simplyjetpacks.client.particle.EntityCustomFlameFX;

public class ParticleUtils {

    private static Minecraft mc = Minecraft.getMinecraft();
    public static final int DEFAULT = 0;
    public static final int NONE = 1;
    public static final int SMOKE = 2;
    public static final int RAINBOW_SMOKE = 3;

    public static void spawnParticle(int particle, World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        switch (particle) {
        case NONE:
            return;
        case DEFAULT:
            mc.effectRenderer.addEffect(new EntityCustomFlameFX(world, posX, posY, posZ, velX, velY, velZ));
        case SMOKE:
            mc.effectRenderer.addEffect(new EntitySmokeFX(world, posX, posY, posZ, velX, velY - 0.1D, velZ));
            return;
        case RAINBOW_SMOKE:
            mc.effectRenderer.addEffect(EntityColoredSmokeFX.getRainbowSmoke(world, posX, posY, posZ, velX, velY - 0.1D, velZ));
            return;
        }
    }
}
