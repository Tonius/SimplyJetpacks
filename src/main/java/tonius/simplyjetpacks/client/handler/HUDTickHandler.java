package tonius.simplyjetpacks.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.IEnergyHUDInfoProvider;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class HUDTickHandler {
    
    private static Minecraft mc = Minecraft.getMinecraft();
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt) {
        if (!Config.enableEnergyHUD && !Config.enableStateHUD) {
            return;
        } else if (evt.phase == Phase.END) {
            tickEnd();
        }
    }
    
    private static void tickEnd() {
        if (mc.thePlayer != null) {
            if ((mc.currentScreen == null || Config.showEnergyHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
                ItemStack chestplate = mc.thePlayer.getCurrentArmor(2);
                if (chestplate != null && chestplate.getItem() instanceof IEnergyHUDInfoProvider) {
                    IEnergyHUDInfoProvider provider = (IEnergyHUDInfoProvider) chestplate.getItem();
                    
                    mc.entityRenderer.setupOverlayRendering();
                    GL11.glScaled(Config.energyHUDScale, Config.energyHUDScale, 1.0D);
                    
                    String energyInfo = provider.getEnergyInfo(chestplate);
                    if (energyInfo != null) {
                        RenderUtils.drawStringAtHUDPosition(energyInfo, HUDPosition.values()[Config.energyHUDPosition], mc.fontRenderer, Config.energyHUDOffsetX, Config.energyHUDOffsetY, Config.energyHUDScale, 0xeeeeee, true, 0);
                    }
                    
                    if (Config.enableStateHUD) {
                        String statesInfo = provider.getStatesInfo(chestplate);
                        if (statesInfo != null) {
                            RenderUtils.drawStringAtHUDPosition(statesInfo, HUDPosition.values()[Config.energyHUDPosition], mc.fontRenderer, Config.energyHUDOffsetX, Config.energyHUDOffsetY, Config.energyHUDScale, 0xeeeeee, true, energyInfo != null ? 1 : 0);
                        }
                    }
                }
            }
        }
    }
    
}
