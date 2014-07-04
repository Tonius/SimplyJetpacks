package tonius.simplyjetpacks.network;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.network.message.MessageJetpackParticles;
import tonius.simplyjetpacks.network.message.MessageModKey;
import tonius.simplyjetpacks.network.message.MessageVanillaKeys;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel("SimplyJetpacks");

    public static void preInit() {
        SimplyJetpacks.logger.info("Registering network messages");
        instance.registerMessage(MessageJetpackParticles.class, MessageJetpackParticles.class, 0, Side.CLIENT);
        instance.registerMessage(MessageVanillaKeys.class, MessageVanillaKeys.class, 1, Side.SERVER);
        instance.registerMessage(MessageModKey.class, MessageModKey.class, 2, Side.SERVER);
    }
}
