package tonius.simplyjetpacks.client.particle;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;

public class EntityCustomFlameFX extends EntityFlameFX {

    public EntityCustomFlameFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        super(world, posX, posY, posZ, velX, velY, velZ);
        this.noClip = false;
    }

}
