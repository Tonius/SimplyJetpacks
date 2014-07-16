package tonius.simplyjetpacks.client.tickhandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import tonius.simplyjetpacks.item.ItemJetpack;
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
        boolean toggle = keyToggle.isPressed();
        boolean mode = keyMode.isPressed();
        if (toggle || mode) {
            if (mc.inGameHasFocus) {
                ItemStack itemStack = mc.thePlayer.getEquipmentInSlot(3);
                if (itemStack != null && itemStack.getItem() instanceof ItemJetpack) {
                    if (toggle) {
                        PacketHandler.instance.sendToServer(new MessageModKey(SJKey.TOGGLE));
                    } else if (mode) {
                        PacketHandler.instance.sendToServer(new MessageModKey(SJKey.MODE));
                    }
                }
            }
        }
    }

}
