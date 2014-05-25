package tonius.simplyjetpacks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySJUeberCharger extends TileEntity {

    @Override
    public void updateEntity() {
    }

    public boolean isActive() {
        return false;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        this.writeToNBT(tagCompound);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tagCompound);
    }

    @Override
    public void onDataPacket(INetworkManager manager, Packet132TileEntityData packet) {
        this.readFromNBT(packet.data);
    }

}
