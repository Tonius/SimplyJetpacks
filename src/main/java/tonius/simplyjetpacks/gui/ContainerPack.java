package tonius.simplyjetpacks.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.item.meta.PackBase;
import cofh.api.energy.EnergyStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerPack extends Container {
    
    public final ItemStack chestplate;
    public final ItemPack packItem;
    public final PackBase pack;
    public final EnergyStorage energyStored;
    public final FluidTank fluidStored;
    
    private int lastFuel;
    private Short fuelFirstHalf = null;
    private Short fuelSecondHalf = null;
    
    public ContainerPack(ItemStack chestplate, ItemPack packItem, PackBase pack) {
        this.chestplate = chestplate;
        this.packItem = packItem;
        this.pack = pack;
        
        int fuel = packItem.getFuelStored(chestplate);
        int maxFuel = pack.tier > 9000 ? -1 : packItem.getMaxFuelStored(chestplate);
        switch (pack.fuelType) {
        case ENERGY:
            this.energyStored = new EnergyStorage(maxFuel);
            this.energyStored.setEnergyStored(fuel);
            this.fluidStored = null;
            break;
        case FLUID:
            this.energyStored = null;
            this.fluidStored = new FluidTank(FluidRegistry.getFluid(pack.fuelFluid), fuel, maxFuel);
            break;
        default:
            this.energyStored = null;
            this.fluidStored = null;
        }
        
        this.lastFuel = fuel;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return player.getCurrentArmor(2) == this.chestplate;
    }
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        
        int fuel = this.packItem.getFuelStored(this.chestplate);
        
        if (fuel != this.lastFuel) {
            for (int i = 0; i < this.crafters.size(); ++i) {
                ((ICrafting) this.crafters.get(i)).sendProgressBarUpdate(this, 0, (short) (fuel >> 16));
                ((ICrafting) this.crafters.get(i)).sendProgressBarUpdate(this, 1, (short) (fuel & 0xFFFF));
            }
            this.lastFuel = fuel;
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int barId, int value) {
        if (barId == 0) {
            this.fuelFirstHalf = (short) value;
        } else if (barId == 1) {
            this.fuelSecondHalf = (short) value;
        }
        if (this.fuelFirstHalf != null && this.fuelSecondHalf != null) {
            int fuel = this.fuelFirstHalf << 16 | this.fuelSecondHalf & 0xFFFF;
            this.fuelFirstHalf = null;
            this.fuelSecondHalf = null;
            
            if (this.energyStored != null) {
                this.energyStored.setEnergyStored(fuel);
            } else if (this.fluidStored != null) {
                this.fluidStored.setFluid(new FluidStack(FluidRegistry.getFluid(this.pack.fuelFluid), fuel));
            }
        }
    }
    
}
