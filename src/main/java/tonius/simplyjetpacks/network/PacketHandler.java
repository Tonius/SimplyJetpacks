package tonius.simplyjetpacks.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.network.message.MessageConfigSync;
import tonius.simplyjetpacks.network.message.MessageJetpackSync;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;
import tonius.simplyjetpacks.network.message.MessageModKey;

public abstract class PacketHandler {
    
    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel("SimplyJetpacks");
    
    public static void init() {
        SimplyJetpacks.logger.info("Registering network messages");
        instance.registerMessage(MessageJetpackSync.class, MessageJetpackSync.class, 0, Side.CLIENT);
        instance.registerMessage(MessageConfigSync.class, MessageConfigSync.class, 1, Side.CLIENT);
        instance.registerMessage(MessageKeyboardSync.class, MessageKeyboardSync.class, 2, Side.SERVER);
        instance.registerMessage(MessageModKey.class, MessageModKey.class, 3, Side.SERVER);
    }
}
