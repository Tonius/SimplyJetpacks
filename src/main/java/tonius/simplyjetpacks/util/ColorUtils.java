package tonius.simplyjetpacks.util;

import java.awt.Color;
import java.util.Random;

public abstract class ColorUtils {
    
    private static Random rand = new Random();
    
    public static Color getRandomColor() {
        return new Color(Color.HSBtoRGB(rand.nextFloat() * 360, rand.nextFloat() * 0.15F + 0.8F, 0.85F));
    }
    
}
