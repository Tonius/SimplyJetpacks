package tonius.simplyjetpacks;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.event.JetpackMobHandler;
import tonius.simplyjetpacks.tileentity.TileEntityUeberCharger;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new JetpackMobHandler());
    }

    public void registerEntities() {
        GameRegistry.registerTileEntity(TileEntityUeberCharger.class, "simplyjetpacks.ueberCharger");
    }

    public void sendPacketToServer(int packetType, int int1) {
    }

    public void sendPacketToServer(int packetType, boolean bool1, boolean bool2) {
    }

    public void showJetpackParticles(World world, int entityID, boolean hoverMode) {
    }

}
