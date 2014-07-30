package tonius.simplyjetpacks.network;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.network.message.MessageJetpackSync;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;
import tonius.simplyjetpacks.network.message.MessageModKey;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel("SimplyJetpacks");

    public static void preInit() {
        SimplyJetpacks.logger.info("Registering network messages");
        instance.registerMessage(MessageJetpackSync.class, MessageJetpackSync.class, 0, Side.CLIENT);
        instance.registerMessage(MessageKeyboardSync.class, MessageKeyboardSync.class, 1, Side.SERVER);
        instance.registerMessage(MessageModKey.class, MessageModKey.class, 2, Side.SERVER);
    }
}
