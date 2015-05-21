package tonius.simplyjetpacks.client.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;
import tonius.simplyjetpacks.setup.ModKey;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class KeyHandler {
    
    static final Minecraft mc = Minecraft.getMinecraft();
    static final List<SJKeyBinding> keyBindings = new ArrayList<SJKeyBinding>();
    
    public static final SJKeyBinding keyTogglePrimary = new SJKeyBinding("toggle.primary", Keyboard.KEY_F, ModKey.TOGGLE_PRIMARY);
    public static final SJKeyBinding keyToggleSecondary = new SJKeyBinding("toggle.secondary", Keyboard.KEY_NONE, ModKey.TOGGLE_SECONDARY);
    public static final SJKeyBinding keyModePrimary = new SJKeyBinding("mode.primary", Keyboard.KEY_C, ModKey.MODE_PRIMARY);
    public static final SJKeyBinding keyModeSecondary = new SJKeyBinding("mode.secondary", Keyboard.KEY_NONE, ModKey.MODE_SECONDARY);
    public static final SJKeyBinding keyOpenPackGUI = new SJKeyBinding("openPackGUI", Keyboard.KEY_U, ModKey.OPEN_PACK_GUI);
    
    private static int flyKey;
    private static int descendKey;
    private static boolean lastFlyState = false;
    private static boolean lastDescendState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;
    
    public static void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
        flyKey = Keyboard.getKeyIndex(flyKeyName);
        descendKey = Keyboard.getKeyIndex(descendKeyName);
    }
    
    private static void tickStart() {
        if (mc.thePlayer != null) {
            boolean flyState;
            boolean descendState;
            if (Config.customControls) {
                flyState = mc.inGameHasFocus && Keyboard.isKeyDown(flyKey);
                descendState = mc.inGameHasFocus && Keyboard.isKeyDown(descendKey);
            } else {
                flyState = mc.gameSettings.keyBindJump.getIsKeyPressed();
                descendState = mc.gameSettings.keyBindSneak.getIsKeyPressed();
            }
            
            boolean forwardState = mc.gameSettings.keyBindForward.getIsKeyPressed();
            boolean backwardState = mc.gameSettings.keyBindBack.getIsKeyPressed();
            boolean leftState = mc.gameSettings.keyBindLeft.getIsKeyPressed();
            boolean rightState = mc.gameSettings.keyBindRight.getIsKeyPressed();
            
            if (flyState != lastFlyState || descendState != lastDescendState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
                lastFlyState = flyState;
                lastDescendState = descendState;
                
                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;
                PacketHandler.instance.sendToServer(new MessageKeyboardSync(flyState, descendState, forwardState, backwardState, leftState, rightState));
                SyncHandler.processKeyUpdate(mc.thePlayer, flyState, descendState, forwardState, backwardState, leftState, rightState);
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent evt) {
        if (evt.phase == Phase.START) {
            tickStart();
        }
    }
    
    @SubscribeEvent
    public void onKey(InputEvent evt) {
        if (!mc.inGameHasFocus) {
            return;
        }
        for (SJKeyBinding key : keyBindings) {
            if (key.isPressed()) {
                key.handleKeyPress();
            }
        }
    }
}
