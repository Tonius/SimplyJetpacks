package tonius.simplyjetpacks.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.IHUDInfoProvider;

public class HUDTickHandler {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    private static void tickEnd() {
        if (mc.thePlayer != null) {
            if ((mc.currentScreen == null || Config.showEnergyHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
                ItemStack chestplate = mc.thePlayer.getCurrentArmor(2);
                if (chestplate != null && chestplate.getItem() instanceof IHUDInfoProvider) {
                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();

                    List<String> info = new ArrayList<String>();
                    provider.addHUDInfo(chestplate, info);
                    if (info.isEmpty()) {
                        return;
                    }

                    GL11.glPushMatrix();
                    mc.entityRenderer.setupOverlayRendering();
                    GL11.glScaled(Config.energyHUDScale, Config.energyHUDScale, 1.0D);

                    int i = 0;
                    for (String s : info) {
                        RenderUtils.drawStringAtHUDPosition(s, HUDPosition.values()[Config.energyHUDPosition], mc.fontRenderer, Config.energyHUDOffsetX, Config.energyHUDOffsetY, Config.energyHUDScale, 0xeeeeee, true, i);
                        i++;
                    }

                    GL11.glPopMatrix();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt) {
        if (evt.phase == Phase.END && (Config.enableEnergyHUD || Config.enableStateHUD)) {
            tickEnd();
        }
    }
    
}
