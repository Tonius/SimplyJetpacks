package tonius.simplyjetpacks.client.tickhandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.IEnergyHUDInfoProvider;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HUDTickHandler {
    
    private static Minecraft mc = Minecraft.getMinecraft();
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt) {
        if (!SJConfig.enableEnergyHUD && !SJConfig.enableStateHUD) {
            return;
        } else if (evt.phase == Phase.END) {
            tickEnd();
        }
    }
    
    private static void tickEnd() {
        if (mc.thePlayer != null) {
            if ((mc.currentScreen == null || SJConfig.showEnergyHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI) {
                ItemStack chestplate = mc.thePlayer.getCurrentArmor(2);
                if (chestplate != null && chestplate.getItem() instanceof IEnergyHUDInfoProvider) {
                    IEnergyHUDInfoProvider provider = (IEnergyHUDInfoProvider) chestplate.getItem();
                    
                    mc.entityRenderer.setupOverlayRendering();
                    GL11.glScaled(SJConfig.energyHUDScale, SJConfig.energyHUDScale, 1.0D);
                    
                    String energyInfo = provider.getEnergyInfo(chestplate);
                    if (energyInfo != null) {
                        RenderUtils.drawStringAtHUDPosition(energyInfo, HUDPosition.values()[SJConfig.energyHUDPosition], mc.fontRenderer, SJConfig.energyHUDOffsetX, SJConfig.energyHUDOffsetY, SJConfig.energyHUDScale, 0xeeeeee, true, 0);
                    }
                    
                    if (SJConfig.enableStateHUD) {
                        String statesInfo = provider.getStatesInfo(chestplate);
                        if (statesInfo != null) {
                            RenderUtils.drawStringAtHUDPosition(statesInfo, HUDPosition.values()[SJConfig.energyHUDPosition], mc.fontRenderer, SJConfig.energyHUDOffsetX, SJConfig.energyHUDOffsetY, SJConfig.energyHUDScale, 0xeeeeee, true, energyInfo != null ? 1 : 0);
                        }
                    }
                }
            }
        }
    }
    
}
