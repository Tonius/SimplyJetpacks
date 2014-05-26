package tonius.simplyjetpacks;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tonius.simplyjetpacks.item.ItemEnergyArmor;
import tonius.simplyjetpacks.item.jetpack.ItemJetpack;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    // used for showing jetpack particles
    public static final int JETPACK_TICK = 0;
    // used for passing key states of vanilla keybinds
    public static final int KEY_STATE = 1;
    // used for handling the mode keybind
    public static final int KEY_MODE = 2;
    // used for handling the toggle keybind
    public static final int KEY_TOGGLE = 3;

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals("SmpJet") && packet.data != null) {
            DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
            EntityPlayer entityPlayer = (EntityPlayer) player;
            try {
                switch (dataStream.readInt()) {
                case JETPACK_TICK:
                    int entityID = dataStream.readInt();
                    boolean hoverMode = dataStream.readBoolean();
                    SimplyJetpacks.proxy.showJetpackParticles(entityPlayer.worldObj, entityID, hoverMode);
                    break;
                case KEY_STATE:
                    KeyboardTracker.processKeyUpdate(entityPlayer, dataStream.readBoolean(), dataStream.readBoolean());
                    break;
                case KEY_MODE:
                    dataStream.readInt();
                    ItemStack jetpack = entityPlayer.inventory.armorItemInSlot(2);
                    if (jetpack != null && jetpack.getItem() instanceof ItemJetpack) {
                        ((ItemJetpack) jetpack.getItem()).toggleHoverMode(jetpack, entityPlayer);
                    }
                    break;
                case KEY_TOGGLE:
                    dataStream.readInt();
                    ItemStack armorEnergy = entityPlayer.inventory.armorItemInSlot(2);
                    if (armorEnergy != null && armorEnergy.getItem() instanceof ItemEnergyArmor) {
                        ((ItemEnergyArmor) armorEnergy.getItem()).toggle(armorEnergy, entityPlayer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
