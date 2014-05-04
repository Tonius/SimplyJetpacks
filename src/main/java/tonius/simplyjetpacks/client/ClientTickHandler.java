package tonius.simplyjetpacks.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.PacketHandler;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

    public static Minecraft mc = Minecraft.getMinecraft();
    public static boolean lastJumpState = false;
    public static boolean lastForwardState = false;

    @Override
    public void tickStart(EnumSet type, Object... tickData) {
        if (mc.thePlayer != null) {
            boolean jumpState = mc.gameSettings.keyBindJump.pressed;
            boolean forwardState = mc.gameSettings.keyBindForward.pressed;

            if (jumpState != lastJumpState || forwardState != lastForwardState) {
                lastJumpState = jumpState;
                lastForwardState = forwardState;
                SimplyJetpacks.proxy.sendPacketToServer(PacketHandler.KEY_STATE, lastJumpState, lastForwardState);
                KeyboardTracker.processKeyUpdate(mc.thePlayer, lastJumpState, lastForwardState);
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
