package tonius.simplyjetpacks.client.tickhandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.item.jetpack.ItemJetpack;
import tonius.simplyjetpacks.util.StringUtils;
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
        if (evt.phase == Phase.END) {
            this.tickEnd();
        }
    }

    public void tickEnd() {
        EntityPlayer player = mc.thePlayer;
        if (player != null) {
            if ((mc.currentScreen == null || mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI) {
                ItemStack chestplate = player.getCurrentArmor(2);
                if (chestplate != null && chestplate.getItem() instanceof ItemJetpack) {
                    int tier = ((ItemJetpack) chestplate.getItem()).getTier();
                    if (tier < 1 || tier > 4) {
                        return;
                    }
                    int energy = ((ItemJetpack) chestplate.getItem()).getEnergyStored(chestplate);
                    int maxEnergy = ((ItemJetpack) chestplate.getItem()).getMaxEnergyStored(chestplate);
                    int percent = (int) Math.round(((double) energy / (double) maxEnergy) * 100D);
                    mc.entityRenderer.setupOverlayRendering();
                    mc.fontRenderer.drawString(StringUtils.getHUDEnergyText(percent, energy), 2, 2, 255 | 255 << 8 | 255 << 16, true);
                    if (percent > 0 && percent <= 10) {
                        mc.fontRenderer.drawString(StringUtils.getHUDEnergyLowText(), 2, 13, 255 | 255 << 8 | 255 << 16, true);
                    } else if (percent == 0) {
                        mc.fontRenderer.drawString(StringUtils.getHUDEnergyEmptyText(), 2, 13, 255 | 255 << 8 | 255 << 16, true);
                    }
                }
            }
        }
    }

}
