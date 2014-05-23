package tonius.simplyjetpacks.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.item.ItemSJJetpack;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class RenderHUDTickHandler implements ITickHandler {

    private static Minecraft mc = Minecraft.getMinecraft();

    private static int fadeTimer = 70;
    private static boolean fadeDecrement = false;

    @Override
    public void tickStart(EnumSet type, Object... tickData) {
    }

    @Override
    public void tickEnd(EnumSet type, Object... tickData) {
        EntityPlayer player = mc.thePlayer;
        if (player != null) {
            if ((mc.currentScreen == null || mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI) {
                ItemStack chestplate = player.getCurrentArmor(2);
                if (chestplate != null && chestplate.getItem() instanceof ItemSJJetpack) {
                    int energy = ((ItemSJJetpack) chestplate.getItem()).getEnergyStored(chestplate);
                    int maxEnergy = ((ItemSJJetpack) chestplate.getItem()).getMaxEnergyStored(chestplate);
                    int percent = (int) Math.round(((double) energy / (double) maxEnergy) * 100D);
                    mc.entityRenderer.setupOverlayRendering();
                    mc.fontRenderer.drawString(StringUtils.getHUDEnergyText(percent, energy), 5, 5, 255 | 255 << 8 | 255 << 16, true);
                    if (percent > 0 && percent <= 10) {
                        mc.fontRenderer.drawString(StringUtils.getHUDEnergyLowText(), 5, 15, 255 | 255 << 8 | 255 << 16 | fadeTimer << 24, true);
                    } else if (percent == 0) {
                        mc.fontRenderer.drawString(StringUtils.getHUDEnergyEmptyText(), 5, 15, 255 | 255 << 8 | 255 << 16, true);
                    }
                }
            }
        }
    }

    public static void tickFadeTimer() {
        if (fadeDecrement) {
            fadeTimer -= 15;
            if (fadeTimer <= 70) {
                fadeDecrement = false;
            }
        } else {
            fadeTimer += 40;
            if (fadeTimer >= 215) {
                fadeDecrement = true;
            }
        }
    }

    @Override
    public EnumSet ticks() {
        return EnumSet.of(TickType.RENDER);
    }

    @Override
    public String getLabel() {
        return "SJRenderHUD";
    }

}
