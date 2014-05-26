package tonius.simplyjetpacks.client.tickhandler;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.PacketHandler;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

    private static Minecraft mc = Minecraft.getMinecraft();
    private static boolean lastJumpState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;

    @Override
    public void tickStart(EnumSet type, Object... tickData) {
        if (mc.thePlayer != null) {
            boolean jumpState = mc.gameSettings.keyBindJump.pressed;
            boolean forwardState = mc.gameSettings.keyBindForward.pressed;
            boolean backwardState = mc.gameSettings.keyBindBack.pressed;
            boolean leftState = mc.gameSettings.keyBindLeft.pressed;
            boolean rightState = mc.gameSettings.keyBindRight.pressed;

            if (jumpState != lastJumpState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
                lastJumpState = jumpState;
                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;
                SimplyJetpacks.proxy.sendPacketToServer(PacketHandler.KEY_STATE, jumpState, forwardState, backwardState, leftState, rightState);
                KeyboardTracker.processKeyUpdate(mc.thePlayer, jumpState, forwardState, backwardState, leftState, rightState);
            }
        }
    }

    @Override
    public void tickEnd(EnumSet type, Object... tickData) {
    }

    @Override
    public EnumSet ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return "SJClient";
    }

}
