package tonius.simplyjetpacks.client.tickhandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPosition;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.item.jetpack.JetpackFluxPlate;
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
        if (!SJConfig.enableEnergyHUD && !SJConfig.enableStateHUD) {
            return;
        } else if (evt.phase == Phase.END) {
            tickEnd();
        }
    }
    
    private static void tickEnd() {
        EntityPlayer player = mc.thePlayer;
        if (player != null) {
            if ((mc.currentScreen == null || SJConfig.showEnergyHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI) {
                ItemStack chestplate = player.getCurrentArmor(2);
                if (chestplate != null) {
                    if (chestplate.getItem() instanceof ItemJetpack) {
                        ItemJetpack item = (ItemJetpack) chestplate.getItem();
                        Jetpack jetpack = item.getJetpack(chestplate);
                        if (jetpack != null) {
                            mc.entityRenderer.setupOverlayRendering();
                            GL11.glScaled(SJConfig.energyHUDScale, SJConfig.energyHUDScale, 1.0D);
                            
                            if (SJConfig.enableEnergyHUD && jetpack.hasDamageBar()) {
                                int energy = item.getEnergyStored(chestplate);
                                int maxEnergy = item.getMaxEnergyStored(chestplate);
                                int percent = (int) Math.ceil((double) energy / (double) maxEnergy * 100D);
                                
                                RenderUtils.drawStringAtHUDPosition(StringUtils.getHUDEnergyText("jetpack", percent, energy), HUDPosition.values()[SJConfig.energyHUDPosition], mc.fontRenderer, SJConfig.energyHUDOffsetX, SJConfig.energyHUDOffsetY, SJConfig.energyHUDScale, 0xeeeeee, true, 0);
                            }
                            
                            if (SJConfig.enableStateHUD) {
                                Boolean engine = jetpack.isOn(chestplate);
                                Boolean hover = jetpack.isHoverModeOn(chestplate);
                                Boolean charger = null;
                                if (jetpack instanceof JetpackFluxPlate && ((JetpackFluxPlate) jetpack).allowCharger()) {
                                    charger = ((JetpackFluxPlate) jetpack).isChargerOn(chestplate);
                                }
                                
                                RenderUtils.drawStringAtHUDPosition(StringUtils.getHUDStateText(engine, hover, charger), HUDPosition.values()[SJConfig.energyHUDPosition], mc.fontRenderer, SJConfig.energyHUDOffsetX, SJConfig.energyHUDOffsetY, SJConfig.energyHUDScale, 0xeeeeee, true, jetpack.hasDamageBar() ? 1 : 0);
                            }
                        }
                    } else if (chestplate.getItem() instanceof ItemFluxPack) {
                        ItemFluxPack item = (ItemFluxPack) chestplate.getItem();
                        FluxPack fluxpack = item.getFluxPack(chestplate);
                        if (fluxpack != null) {
                            mc.entityRenderer.setupOverlayRendering();
                            GL11.glScaled(SJConfig.energyHUDScale, SJConfig.energyHUDScale, 1.0D);
                            
                            if (SJConfig.enableEnergyHUD && fluxpack.hasDamageBar()) {
                                int energy = item.getEnergyStored(chestplate);
                                int maxEnergy = item.getMaxEnergyStored(chestplate);
                                int percent = (int) Math.ceil((double) energy / (double) maxEnergy * 100D);
                                
                                RenderUtils.drawStringAtHUDPosition(StringUtils.getHUDEnergyText("fluxpack", percent, energy), HUDPosition.values()[SJConfig.energyHUDPosition], mc.fontRenderer, SJConfig.energyHUDOffsetX, SJConfig.energyHUDOffsetY, SJConfig.energyHUDScale, 0xffffff, true, 0);
                            }
                            
                            if (SJConfig.enableStateHUD) {
                                Boolean charger = fluxpack.isOn(chestplate);
                                RenderUtils.drawStringAtHUDPosition(StringUtils.getHUDStateText(null, null, charger), HUDPosition.values()[SJConfig.energyHUDPosition], mc.fontRenderer, SJConfig.energyHUDOffsetX, SJConfig.energyHUDOffsetY, SJConfig.energyHUDScale, 0xeeeeee, true, fluxpack.hasDamageBar() ? 1 : 0);
                            }
                        }
                    }
                }
            }
        }
    }
    
}
