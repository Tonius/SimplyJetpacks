package tonius.simplyjetpacks;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.event.JetpackMobHandlers;

public class CommonProxy {

    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new JetpackMobHandlers());
    }

    public void sendPacketToServer(int packetType, int int1) {
    }

    public void sendPacketToServer(int packetType, boolean key1, boolean key2) {
    }

    public void showJetpackParticles(World world, int entityID, boolean hoverMode) {
    }

}
