package tonius.simplyjetpacks.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import org.lwjgl.opengl.GL11;

public class RenderUtils {

    private static Minecraft mc = Minecraft.getMinecraft();

    public static void drawStringLeft(String string, FontRenderer fontRenderer, int x, int y, double scale, int color, boolean shadow) {
        GL11.glScaled(scale, scale, 1.0D);
        fontRenderer.drawString(string, x, y, color, shadow);
    }

    public static void drawStringCenter(String string, FontRenderer fontRenderer, int x, int y, double scale, int color, boolean shadow) {
        GL11.glScaled(scale, scale, 1.0D);
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string) / 2, y, color, shadow);
    }

    public static void drawStringRight(String string, FontRenderer fontRenderer, int x, int y, double scale, int color, boolean shadow) {
        GL11.glScaled(scale, scale, 1.0D);
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string), y, color, shadow);
    }

    public static void drawStringAtHUDPosition(String string, HUDPosition position, FontRenderer fontRenderer, int xOffset, int yOffset, double scale, int color, boolean shadow) {
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        int screenWidth = res.getScaledWidth();
        screenWidth /= scale;
        int screenHeight = res.getScaledHeight();
        screenHeight /= scale;

        switch (position) {
        case TOP_LEFT:
            drawStringLeft(string, fontRenderer, 2 + xOffset, 2 + yOffset, scale, color, shadow);
            break;
        case TOP_CENTER:
            drawStringCenter(string, fontRenderer, screenWidth / 2 + xOffset, 2 + yOffset, scale, color, shadow);
            break;
        case TOP_RIGHT:
            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, 2 + yOffset, scale, color, shadow);
            break;
        case LEFT:
            drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight / 2 + yOffset, scale, color, shadow);
            break;
        case RIGHT:
            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight / 2 + yOffset, scale, color, shadow);
            break;
        case BOTTOM_LEFT:
            drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight - 9 + yOffset, scale, color, shadow);
            break;
        case BOTTOM_RIGHT:
            drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight - 9 + yOffset, scale, color, shadow);
        }
    }

    public enum HUDPosition {
        TOP_LEFT, TOP_CENTER, TOP_RIGHT, LEFT, RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

}
