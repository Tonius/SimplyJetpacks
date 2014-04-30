package tonius.simplyjetpacks;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class KeyboardTracker {

    private static Map<EntityPlayer, Boolean> jumpKeyState = new HashMap();
    private static Map<EntityPlayer, Boolean> forwardKeyState = new HashMap();

    public static boolean isJumpKeyDown(EntityPlayer player) {
        return jumpKeyState.containsKey(player) && jumpKeyState.get(player).booleanValue();
    }

    public static boolean isForwardKeyDown(EntityLivingBase user) {
        if (user instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) user;
            return forwardKeyState.containsKey(player) && forwardKeyState.get(player).booleanValue();
        }
        return true;
    }

    public static void processKeyUpdate(EntityPlayer player, boolean key1, boolean key2) {
        jumpKeyState.put(player, key1);
        forwardKeyState.put(player, key2);
    }

}
