package tonius.simplyjetpacks.client.particle;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityCustomFlameFX extends EntityFlameFX {

    public EntityCustomFlameFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        super(world, posX, posY, posZ, velX, velY, velZ);
        this.noClip = false;
    }

    @Override
    public int getBrightnessForRender(float p_70013_1_) {
        return 180;
    }

}
