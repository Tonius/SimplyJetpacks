package tonius.simplyjetpacks.client.tickhandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.IModeSwitchable;
import tonius.simplyjetpacks.item.IToggleable;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageModKey;
import tonius.simplyjetpacks.setup.SJKey;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyHandler {
    
    private static Minecraft mc = Minecraft.getMinecraft();
    private static KeyBinding keyToggle = new KeyBinding("Turn on/off", Keyboard.KEY_F, "Simply Jetpacks");
    private static KeyBinding keyMode = new KeyBinding("Switch mode", Keyboard.KEY_C, "Simply Jetpacks");
    
    public KeyHandler() {
        ClientRegistry.registerKeyBinding(keyToggle);
        ClientRegistry.registerKeyBinding(keyMode);
    }
    
    @SubscribeEvent
    public void onKey(KeyInputEvent evt) {
        boolean toggle = keyToggle.getIsKeyPressed();
        boolean mode = keyMode.getIsKeyPressed();
        if (toggle || mode) {
            if (mc.inGameHasFocus) {
                ItemStack itemStack = mc.thePlayer.getEquipmentInSlot(3);
                if (itemStack != null) {
                    if (toggle && itemStack.getItem() instanceof IToggleable) {
                        PacketHandler.instance.sendToServer(new MessageModKey(SJKey.TOGGLE, SJConfig.enableStateChatMessages));
                        ((IToggleable) itemStack.getItem()).toggle(itemStack, mc.thePlayer, SJConfig.enableStateChatMessages);
                    } else if (mode && itemStack.getItem() instanceof IModeSwitchable) {
                        PacketHandler.instance.sendToServer(new MessageModKey(SJKey.MODE, SJConfig.enableStateChatMessages));
                        ((IModeSwitchable) itemStack.getItem()).switchMode(itemStack, mc.thePlayer, SJConfig.enableStateChatMessages);
                    }
                }
            }
        }
    }
    
}
