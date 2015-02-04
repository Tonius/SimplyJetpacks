package tonius.simplyjetpacks.client.particle;

import java.awt.Color;
import java.util.Random;

import net.minecraft.world.World;

public class EntityColoredSmokeFX extends EntityCustomSmokeFX {
    
    private static final Random rand = new Random();
    
    public EntityColoredSmokeFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ, float red, float green, float blue) {
        super(world, posX, posY, posZ, velX, velY, velZ);
        this.particleRed = red;
        this.particleGreen = green;
        this.particleBlue = blue;
    }
    
    public static EntityColoredSmokeFX getRainbowSmoke(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        Color color = new Color(Color.HSBtoRGB(rand.nextFloat() * 360, rand.nextFloat() * 0.15F + 0.8F, 0.85F));
        float red = color.getRed() / 255.0F;
        float green = color.getGreen() / 255.0F;
        float blue = color.getBlue() / 255.0F;
        return new EntityColoredSmokeFX(world, posX, posY, posZ, velX, velY, velZ, red, green, blue);
    }
    
}
