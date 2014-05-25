package tonius.simplyjetpacks;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.event.JetpackMobHandlers;
import tonius.simplyjetpacks.tileentity.TileEntitySJUeberCharger;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new JetpackMobHandlers());
    }

    public void registerEntities() {
        GameRegistry.registerTileEntity(TileEntitySJUeberCharger.class, "simplyjetpacks.ueberCharger");
    }

    public void sendPacketToServer(int packetType, int int1) {
    }

    public void sendPacketToServer(int packetType, boolean bool1, boolean bool2) {
    }

    public void showJetpackParticles(World world, int entityID, boolean hoverMode) {
    }

}
