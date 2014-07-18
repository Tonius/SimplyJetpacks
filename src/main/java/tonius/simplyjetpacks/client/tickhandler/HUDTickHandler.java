package tonius.simplyjetpacks.client.tickhandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
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
        if (SJConfig.enableEnergyHUD && evt.phase == Phase.END) {
            this.tickEnd();
        }
    }

    public void tickEnd() {
        EntityPlayer player = mc.thePlayer;
        if (player != null) {
            if ((mc.currentScreen == null || (SJConfig.showEnergyHUDWhileChatting && mc.currentScreen instanceof GuiChat)) && !mc.gameSettings.hideGUI) {
                ItemStack chestplate = player.getCurrentArmor(2);
                if (chestplate != null && chestplate.getItem() instanceof ItemJetpack) {
                    ItemJetpack item = (ItemJetpack) chestplate.getItem();
                    Jetpack jetpack = item.getJetpack(chestplate);
                    if (jetpack != null) {
                        int tier = jetpack.tier;
                        if (!jetpack.hasDamageBar()) {
                            return;
                        }
                        int energy = item.getEnergyStored(chestplate);
                        int maxEnergy = item.getMaxEnergyStored(chestplate);
                        int percent = (int) Math.round(((double) energy / (double) maxEnergy) * 100D);
                        mc.entityRenderer.setupOverlayRendering();
                        RenderUtils.drawStringAtHUDPosition(StringUtils.getHUDEnergyText(percent, energy), HUDPosition.values()[SJConfig.energyHUDPosition], mc.fontRenderer, SJConfig.energyHUDOffsetX, SJConfig.energyHUDOffsetY, SJConfig.energyHUDScale, 255 | 255 << 8 | 255 << 16, true);
                    }
                }
            }
        }
    }

}
