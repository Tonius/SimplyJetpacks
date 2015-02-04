package tonius.simplyjetpacks.handler;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import tonius.simplyjetpacks.client.audio.SoundJetpack;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageConfigSync;
import tonius.simplyjetpacks.setup.ParticleType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class SyncHandler {
    
    private static final Map<EntityPlayer, Boolean> flyKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> descendKeyState = new HashMap<EntityPlayer, Boolean>();
    
    private static final Map<EntityPlayer, Boolean> forwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> backwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> leftKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> rightKeyState = new HashMap<EntityPlayer, Boolean>();
    
    private static final Map<Integer, ParticleType> jetpackState = new HashMap<Integer, ParticleType>();
    
    public static boolean isFlyKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || flyKeyState.containsKey(user) && flyKeyState.get(user);
    }
    
    public static boolean isDescendKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && descendKeyState.containsKey(user) && descendKeyState.get(user);
    }
    
    public static boolean isForwardKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || forwardKeyState.containsKey(user) && forwardKeyState.get(user);
    }
    
    public static boolean isBackwardKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && backwardKeyState.containsKey(user) && backwardKeyState.get(user);
    }
    
    public static boolean isLeftKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && leftKeyState.containsKey(user) && leftKeyState.get(user);
    }
    
    public static boolean isRightKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && rightKeyState.containsKey(user) && rightKeyState.get(user);
    }
    
    public static void processKeyUpdate(EntityPlayer player, boolean keyFly, boolean keyDescend, boolean keyForward, boolean keyBackward, boolean keyLeft, boolean keyRight) {
        flyKeyState.put(player, keyFly);
        descendKeyState.put(player, keyDescend);
        
        forwardKeyState.put(player, keyForward);
        backwardKeyState.put(player, keyBackward);
        leftKeyState.put(player, keyLeft);
        rightKeyState.put(player, keyRight);
    }
    
    public static void processJetpackUpdate(int entityId, ParticleType particleType) {
        if (particleType != null) {
            jetpackState.put(entityId, particleType);
        } else {
            jetpackState.remove(entityId);
        }
    }
    
    public static Map<Integer, ParticleType> getJetpackStates() {
        return jetpackState;
    }
    
    public static void clearAll() {
        flyKeyState.clear();
        forwardKeyState.clear();
        backwardKeyState.clear();
        leftKeyState.clear();
        rightKeyState.clear();
    }
    
    private static void removeFromAll(EntityPlayer player) {
        flyKeyState.remove(player);
        forwardKeyState.remove(player);
        backwardKeyState.remove(player);
        leftKeyState.remove(player);
        rightKeyState.remove(player);
    }
    
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent evt) {
        PacketHandler.instance.sendTo(new MessageConfigSync(), (EntityPlayerMP) evt.player);
    }
    
    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerLoggedOutEvent evt) {
        removeFromAll(evt.player);
    }
    
    @SubscribeEvent
    public void onDimChanged(PlayerChangedDimensionEvent evt) {
        removeFromAll(evt.player);
    }
    
    @SubscribeEvent
    public void onClientDisconnectedFromServer(ClientDisconnectionFromServerEvent evt) {
        SoundJetpack.clearPlayingFor();
        Config.onConfigChanged("simplyjetpacks");
    }
    
}
