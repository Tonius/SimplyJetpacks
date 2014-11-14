package tonius.simplyjetpacks.client.tickhandler;

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.SyncTracker;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.IModeSwitchable;
import tonius.simplyjetpacks.item.IToggleable;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;
import tonius.simplyjetpacks.network.message.MessageModKey;
import tonius.simplyjetpacks.setup.SJControls;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class ClientTickHandler {
    
    private static Minecraft mc = Minecraft.getMinecraft();
    
    private static int flyKey;
    private static int descendKey;
    
    private static boolean lastFlyState = false;
    private static boolean lastDescendState = false;
    
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;
    
    private static JetpackParticleType lastJetpackState = null;
    
    private static KeyBinding keyToggle = new KeyBinding("Turn on/off", Keyboard.KEY_F, "Simply Jetpacks");
    private static KeyBinding keyMode = new KeyBinding("Switch mode", Keyboard.KEY_C, "Simply Jetpacks");
    
    public ClientTickHandler() {
        ClientRegistry.registerKeyBinding(keyToggle);
        ClientRegistry.registerKeyBinding(keyMode);
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent evt) {
        if (evt.phase == Phase.START) {
            tickStart();
        } else {
            tickEnd();
        }
    }
    
    public static void updateCustomKeybinds() {
        flyKey = Keyboard.getKeyIndex(SJConfig.flyKey);
        descendKey = Keyboard.getKeyIndex(SJConfig.descendKey);
    }
    
    private static void tickStart() {
        if (mc.thePlayer != null) {
            boolean flyState;
            boolean descendState;
            if (SJConfig.customControls) {
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
                SyncTracker.processKeyUpdate(mc.thePlayer, flyState, descendState, forwardState, backwardState, leftState, rightState);
            }
            
            JetpackParticleType jetpackState = null;
            ItemStack armor = mc.thePlayer.getEquipmentInSlot(3);
            if (armor != null && armor.getItem() instanceof ItemJetpack) {
                Jetpack jetpack = ((ItemJetpack) armor.getItem()).getJetpack(armor);
                if (jetpack != null) {
                    jetpackState = jetpack.particleToShow(armor, (ItemJetpack) armor.getItem(), mc.thePlayer);
                }
            }
            
            if (jetpackState != lastJetpackState) {
                lastJetpackState = jetpackState;
                SyncTracker.processJetpackUpdate(mc.thePlayer.getEntityId(), jetpackState);
            }
        }
    }
    
    private static void tickEnd() {
        if (mc.thePlayer != null && mc.theWorld != null && !mc.isGamePaused()) {
            Iterator<Integer> itr = SyncTracker.getJetpackStates().keySet().iterator();
            int currentEntity;
            while (itr.hasNext()) {
                currentEntity = itr.next();
                Entity entity = mc.theWorld.getEntityByID(currentEntity);
                if (entity == null || !(entity instanceof EntityLivingBase) || entity.dimension != mc.thePlayer.dimension) {
                    itr.remove();
                } else {
                    JetpackParticleType particle = SyncTracker.getJetpackStates().get(currentEntity);
                    if (particle != null) {
                        SimplyJetpacks.proxy.showJetpackParticles(mc.theWorld, (EntityLivingBase) entity, particle);
                    } else {
                        itr.remove();
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onKey(InputEvent evt) {
        boolean toggle = keyToggle.getIsKeyPressed();
        boolean mode = keyMode.getIsKeyPressed();
        if (toggle || mode) {
            if (mc.inGameHasFocus) {
                ItemStack itemStack = mc.thePlayer.getEquipmentInSlot(3);
                if (itemStack != null) {
                    if (toggle && itemStack.getItem() instanceof IToggleable) {
                        PacketHandler.instance.sendToServer(new MessageModKey(SJControls.TOGGLE, SJConfig.enableStateChatMessages));
                        ((IToggleable) itemStack.getItem()).toggle(itemStack, mc.thePlayer, SJConfig.enableStateChatMessages);
                    } else if (mode && itemStack.getItem() instanceof IModeSwitchable) {
                        PacketHandler.instance.sendToServer(new MessageModKey(SJControls.MODE, SJConfig.enableStateChatMessages));
                        ((IModeSwitchable) itemStack.getItem()).switchMode(itemStack, mc.thePlayer, SJConfig.enableStateChatMessages);
                    }
                }
            }
        }
    }
    
}
