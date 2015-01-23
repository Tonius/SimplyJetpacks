package tonius.simplyjetpacks.client.handler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.audio.SoundJetpack;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.item.IControllable;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;
import tonius.simplyjetpacks.item.meta.Jetpack;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;
import tonius.simplyjetpacks.network.message.MessageModKey;
import tonius.simplyjetpacks.setup.ModControls;
import tonius.simplyjetpacks.setup.ParticleType;

public class ClientTickHandler {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final KeyBinding keyToggle = new KeyBinding(SimplyJetpacks.PREFIX + "keybind.toggle", Keyboard.KEY_F, "Simply Jetpacks");
    private static final KeyBinding keyMode = new KeyBinding(SimplyJetpacks.PREFIX + "keybind.mode", Keyboard.KEY_C, "Simply Jetpacks");
    private static int flyKey;
    private static int descendKey;
    private static boolean lastFlyState = false;
    private static boolean lastDescendState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;
    private static ParticleType lastJetpackState = null;
    
    public ClientTickHandler() {
        ClientRegistry.registerKeyBinding(keyToggle);
        ClientRegistry.registerKeyBinding(keyMode);
    }
    
    public static void updateCustomKeybinds() {
        flyKey = Keyboard.getKeyIndex(Config.flyKey);
        descendKey = Keyboard.getKeyIndex(Config.descendKey);
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

            ParticleType jetpackState = null;
            ItemStack armor = mc.thePlayer.getEquipmentInSlot(3);
            if (armor != null && armor.getItem() instanceof ItemJetpack) {
                Jetpack jetpack = ((ItemJetpack) armor.getItem()).getPack(armor);
                if (jetpack != null) {
                    jetpackState = jetpack.getDisplayParticleType(armor, (ItemJetpack) armor.getItem(), mc.thePlayer);
                }
            }

            if (jetpackState != lastJetpackState) {
                lastJetpackState = jetpackState;
                SyncHandler.processJetpackUpdate(mc.thePlayer.getEntityId(), jetpackState);
            }
        }
    }
    
    private static void tickEnd() {
        if (mc.thePlayer != null && mc.theWorld != null && !mc.isGamePaused()) {
            Iterator<Integer> itr = SyncHandler.getJetpackStates().keySet().iterator();
            int currentEntity;
            while (itr.hasNext()) {
                currentEntity = itr.next();
                Entity entity = mc.theWorld.getEntityByID(currentEntity);
                if (entity == null || !(entity instanceof EntityLivingBase) || entity.dimension != mc.thePlayer.dimension) {
                    itr.remove();
                } else {
                    ParticleType particle = SyncHandler.getJetpackStates().get(currentEntity);
                    if (particle != null) {
                        SimplyJetpacks.proxy.showJetpackParticles(mc.theWorld, (EntityLivingBase) entity, particle);
                        if (Config.jetpackSounds && !SoundJetpack.isPlayingFor(entity.getEntityId())) {
                            Minecraft.getMinecraft().getSoundHandler().playSound(new SoundJetpack((EntityLivingBase) entity));
                        }
                    } else {
                        itr.remove();
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent evt) {
        if (evt.phase == Phase.START) {
            tickStart();
        } else {
            tickEnd();
        }
    }
    
    @SubscribeEvent
    public void onKey(InputEvent evt) {
        boolean toggle = keyToggle.isPressed();
        boolean mode = keyMode.isPressed();
        if (toggle || mode) {
            if (mc.inGameHasFocus) {
                ItemStack itemStack = mc.thePlayer.getEquipmentInSlot(3);
                if (itemStack != null && itemStack.getItem() instanceof IControllable) {
                    if (toggle) {
                        PacketHandler.instance.sendToServer(new MessageModKey(ModControls.TOGGLE, Config.sneakChangesToggleBehavior, Config.enableStateChatMessages));
                    } else {
                        PacketHandler.instance.sendToServer(new MessageModKey(ModControls.MODE, Config.sneakChangesToggleBehavior, Config.enableStateChatMessages));
                    }
                }
            }
        }
    }
    
}
