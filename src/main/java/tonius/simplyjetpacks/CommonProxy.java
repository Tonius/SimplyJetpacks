package tonius.simplyjetpacks;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.event.JetpackMobHandler;

public class CommonProxy {

    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new JetpackMobHandler());
    }

    public void registerEntities() {
    }

    public void sendPacketToServer(int packetType, int int1) {
    }

    public void sendPacketToServer(int packetType, boolean... booleans) {
    }

    public void showJetpackParticles(World world, int entityID, int particle) {
    }

}
