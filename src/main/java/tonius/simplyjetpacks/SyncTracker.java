package tonius.simplyjetpacks;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;

public class SyncTracker {

    private static Map<EntityPlayer, Boolean> jumpKeyState = new HashMap<EntityPlayer, Boolean>();

    private static Map<EntityPlayer, Boolean> forwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static Map<EntityPlayer, Boolean> backwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static Map<EntityPlayer, Boolean> leftKeyState = new HashMap<EntityPlayer, Boolean>();
    private static Map<EntityPlayer, Boolean> rightKeyState = new HashMap<EntityPlayer, Boolean>();

    private static Map<EntityLivingBase, JetpackParticleType> jetpackState = new HashMap<EntityLivingBase, JetpackParticleType>();

    public static boolean isJumpKeyDown(EntityPlayer player) {
        return jumpKeyState.containsKey(player) && jumpKeyState.get(player);
    }

    public static boolean isForwardKeyDown(EntityPlayer player) {
        return forwardKeyState.containsKey(player) && forwardKeyState.get(player);
    }

    public static boolean isBackwardKeyDown(EntityPlayer player) {
        return backwardKeyState.containsKey(player) && backwardKeyState.get(player);
    }

    public static boolean isLeftKeyDown(EntityPlayer player) {
        return leftKeyState.containsKey(player) && leftKeyState.get(player);
    }

    public static boolean isRightKeyDown(EntityPlayer player) {
        return rightKeyState.containsKey(player) && rightKeyState.get(player);
    }

    public static void processKeyUpdate(EntityPlayer player, boolean keyJump, boolean keyForward, boolean keyBackward, boolean keyLeft, boolean keyRight) {
        jumpKeyState.put(player, keyJump);
        forwardKeyState.put(player, keyForward);
        backwardKeyState.put(player, keyBackward);
        leftKeyState.put(player, keyLeft);
        rightKeyState.put(player, keyRight);
    }

    public static void processJetpackUpdate(EntityLivingBase entityLiving, JetpackParticleType particleType) {
        jetpackState.put(entityLiving, particleType);
    }

    public static JetpackParticleType isJetpackOn(EntityLivingBase entityLiving) {
        return jetpackState.get(entityLiving);
    }

}
