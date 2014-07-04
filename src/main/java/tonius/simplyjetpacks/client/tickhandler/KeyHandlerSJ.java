//package tonius.simplyjetpacks.client.tickhandler;
//
//import java.util.EnumSet;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.settings.KeyBinding;
//import net.minecraft.item.ItemStack;
//import tonius.simplyjetpacks.PacketHandler;
//import tonius.simplyjetpacks.SimplyJetpacks;
//import tonius.simplyjetpacks.item.ItemEnergyArmor;
//import tonius.simplyjetpacks.item.jetpack.ItemJetpack;
//
//public class KeyHandlerSJ extends KeyHandler {
//
//    private static Minecraft mc = Minecraft.getMinecraft();
//    private static KeyBinding keyMode = new KeyBinding("[SimplyJetpacks] Switch mode", 46);
//    private static KeyBinding keyToggle = new KeyBinding("[SimplyJetpacks] Turn on/off", 33);
//
//    public KeyHandlerSJ() {
//        super(new KeyBinding[] { keyMode, keyToggle }, new boolean[] { false, false });
//    }
//
//    @Override
//    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
//        if (tickEnd && mc.inGameHasFocus) {
//            ItemStack itemStack = mc.thePlayer.getCurrentItemOrArmor(3);
//            if (itemStack != null) {
//                if (kb == keyMode && itemStack.getItem() instanceof ItemJetpack) {
//                    SimplyJetpacks.proxy.sendPacketToServer(PacketHandler.KEY_MODE, 1);
//                } else if (kb == keyToggle && itemStack.getItem() instanceof ItemEnergyArmor) {
//                    SimplyJetpacks.proxy.sendPacketToServer(PacketHandler.KEY_TOGGLE, 1);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
//    }
//
//    @Override
//    public EnumSet<TickType> ticks() {
//        return EnumSet.of(TickType.CLIENT);
//    }
//
//    @Override
//    public String getLabel() {
//        return "SJKeyHandler";
//    }
//
// }
