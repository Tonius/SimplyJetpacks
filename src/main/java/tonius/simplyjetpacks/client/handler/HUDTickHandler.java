package tonius.simplyjetpacks.client.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPositions;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.IHUDInfoProvider;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class HUDTickHandler {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    private static void tickEnd() {
        if (mc.thePlayer != null) {
            if ((mc.currentScreen == null || Config.showHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
                ItemStack chestplate = mc.thePlayer.getCurrentArmor(2);
                if (chestplate != null && chestplate.getItem() instanceof IHUDInfoProvider) {
                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();
                    
                    List<String> info = new ArrayList<String>();
                    provider.addHUDInfo(info, chestplate, Config.enableFuelHUD, Config.enableStateHUD);
                    if (info.isEmpty()) {
                        return;
                    }
                    
                    GL11.glPushMatrix();
                    mc.entityRenderer.setupOverlayRendering();
                    GL11.glScaled(Config.HUDScale, Config.HUDScale, 1.0D);
                    
                    int i = 0;
                    for (String s : info) {
                        RenderUtils.drawStringAtHUDPosition(s, HUDPositions.values()[Config.HUDPosition], mc.fontRenderer, Config.HUDOffsetX, Config.HUDOffsetY, Config.HUDScale, 0xeeeeee, true, i);
                        i++;
                    }
                    
                    GL11.glPopMatrix();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt) {
        if (evt.phase == Phase.END && (Config.enableFuelHUD || Config.enableStateHUD)) {
            tickEnd();
        }
    }
    
}
