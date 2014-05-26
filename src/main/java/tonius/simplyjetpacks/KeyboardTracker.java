package tonius.simplyjetpacks;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

public class KeyboardTracker {

    private static Map<EntityPlayer, Boolean> jumpKeyState = new HashMap();

    private static Map<EntityPlayer, Boolean> forwardKeyState = new HashMap();
    private static Map<EntityPlayer, Boolean> backwardKeyState = new HashMap();
    private static Map<EntityPlayer, Boolean> leftKeyState = new HashMap();
    private static Map<EntityPlayer, Boolean> rightKeyState = new HashMap();

    public static boolean isJumpKeyDown(EntityPlayer player) {
        return jumpKeyState.containsKey(player) && jumpKeyState.get(player).booleanValue();
    }

    public static boolean isForwardKeyDown(EntityPlayer player) {
        return forwardKeyState.containsKey(player) && forwardKeyState.get(player).booleanValue();
    }

    public static boolean isBackwardKeyDown(EntityPlayer player) {
        return backwardKeyState.containsKey(player) && backwardKeyState.get(player).booleanValue();
    }

    public static boolean isLeftKeyDown(EntityPlayer player) {
        return leftKeyState.containsKey(player) && leftKeyState.get(player).booleanValue();
    }

    public static boolean isRightKeyDown(EntityPlayer player) {
        return rightKeyState.containsKey(player) && rightKeyState.get(player).booleanValue();
    }

    public static void processKeyUpdate(EntityPlayer player, boolean keyJump, boolean keyForward, boolean keyBackward, boolean keyLeft, boolean keyRight) {
        jumpKeyState.put(player, keyJump);
        forwardKeyState.put(player, keyForward);
        backwardKeyState.put(player, keyBackward);
        leftKeyState.put(player, keyLeft);
        rightKeyState.put(player, keyRight);
    }

}
