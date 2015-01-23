package tonius.simplyjetpacks.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public abstract class RenderUtils {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    public static void drawStringLeft(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow) {
        fontRenderer.drawString(string, x, y, color, shadow);
    }
    
    public static void drawStringCenter(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow) {
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string) / 2, y, color, shadow);
    }
    
    public static void drawStringRight(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow) {
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string), y, color, shadow);
    }
    
    public static void drawStringAtHUDPosition(String string, HUDPosition position, FontRenderer fontRenderer, int xOffset, int yOffset, double scale, int color, boolean shadow, int lineOffset) {
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        
        int screenWidth = res.getScaledWidth();
        screenWidth /= scale;
        int screenHeight = res.getScaledHeight();
        screenHeight /= scale;
        
        switch (position) {
        case TOP_LEFT:
            yOffset += lineOffset * 9;
            drawStringLeft(string, fontRenderer, 2 + xOffset, 2 + yOffset, color, shadow);
            break;
        case TOP_CENTER:
            yOffset += lineOffset * 9;
            drawStringCenter(string, fontRenderer, screenWidth / 2 + xOffset, 2 + yOffset, color, shadow);
            break;
        case TOP_RIGHT:
            yOffset += lineOffset * 9;
            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, 2 + yOffset, color, shadow);
            break;
        case LEFT:
            yOffset += lineOffset * 9;
            drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight / 2 + yOffset, color, shadow);
            break;
        case RIGHT:
            yOffset += lineOffset * 9;
            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight / 2 + yOffset, color, shadow);
            break;
        case BOTTOM_LEFT:
            yOffset -= lineOffset * 9;
            drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight - 9 + yOffset, color, shadow);
            break;
        case BOTTOM_RIGHT:
            yOffset -= lineOffset * 9;
            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight - 9 + yOffset, color, shadow);
        }
    }
    
    public enum HUDPosition {
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT,
        LEFT,
        RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
    
}
