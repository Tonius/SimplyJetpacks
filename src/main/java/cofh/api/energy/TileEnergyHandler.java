package cofh.api.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Reference implementation of {@link IEnergyHandler}. Use/extend this or
 * implement your own.
 * 
 * @author King Lemming
 * 
 */
public class TileEnergyHandler extends TileEntity implements IEnergyHandler {
    
    protected EnergyStorage storage = new EnergyStorage(32000);
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        
        super.readFromNBT(nbt);
        this.storage.readFromNBT(nbt);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        
        super.writeToNBT(nbt);
        this.storage.writeToNBT(nbt);
    }
    
    /* IEnergyConnection */
    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        
        return true;
    }
    
    /* IEnergyReceiver */
    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        
        return this.storage.receiveEnergy(maxReceive, simulate);
    }
    
    /* IEnergyProvider */
    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        
        return this.storage.extractEnergy(maxExtract, simulate);
    }
    
    /* IEnergyReceiver and IEnergyProvider */
    @Override
    public int getEnergyStored(ForgeDirection from) {
        
        return this.storage.getEnergyStored();
    }
    
    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        
        return this.storage.getMaxEnergyStored();
    }
    
}
