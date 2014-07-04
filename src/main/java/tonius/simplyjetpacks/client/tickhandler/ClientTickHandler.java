package tonius.simplyjetpacks.client.tickhandler;

import net.minecraft.client.Minecraft;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageVanillaKeys;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientTickHandler {

    private static Minecraft mc = Minecraft.getMinecraft();
    private static boolean lastJumpState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;

    @SubscribeEvent
    public void onClientTick(ClientTickEvent evt) {
        if (evt.phase == Phase.START) {
            this.tickStart();
        }
    }

    public void tickStart() {
        if (mc.thePlayer != null) {
            boolean jumpState = mc.gameSettings.keyBindJump.getIsKeyPressed();
            boolean forwardState = mc.gameSettings.keyBindForward.getIsKeyPressed();
            boolean backwardState = mc.gameSettings.keyBindBack.getIsKeyPressed();
            boolean leftState = mc.gameSettings.keyBindLeft.getIsKeyPressed();
            boolean rightState = mc.gameSettings.keyBindRight.getIsKeyPressed();

            if (jumpState != lastJumpState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
                lastJumpState = jumpState;
                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;
                PacketHandler.instance.sendToServer(new MessageVanillaKeys(jumpState, forwardState, backwardState, leftState, rightState));
                KeyboardTracker.processKeyUpdate(mc.thePlayer, jumpState, forwardState, backwardState, leftState, rightState);
            }
        }
    }

}
