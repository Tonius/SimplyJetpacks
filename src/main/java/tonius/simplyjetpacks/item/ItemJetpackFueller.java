package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.meta.PackBase;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.api.energy.IEnergyProvider;
import cofh.lib.util.helpers.BlockHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJetpackFueller extends Item {
    
    public ItemJetpackFueller() {
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + "jetpackFueller");
        this.setTextureName(SimplyJetpacks.RESOURCE_PREFIX + "jetpackFueller");
        this.setCreativeTab(ModCreativeTab.instance);
        this.setMaxStackSize(1);
        this.setFull3D();
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        MovingObjectPosition blockPos = BlockHelper.getCurrentMovingObjectPosition(player, true);
        if (blockPos != null && blockPos.sideHit >= 0) {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        return itemStack;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return Short.MAX_VALUE;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }
    
    @Override
    public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count) {
        MovingObjectPosition blockPos = BlockHelper.getCurrentMovingObjectPosition(player, true);
        if (blockPos == null || blockPos.sideHit < 0) {
            player.setItemInUse(null, 1);
        } else {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
            if (player.worldObj.isRemote) {
                return;
            }
            
            ItemStack chestplate = player.getCurrentArmor(2);
            if (chestplate == null || !(chestplate.getItem() instanceof ItemPack)) {
                return;
            }
            ItemPack packItem = (ItemPack) chestplate.getItem();
            PackBase pack = packItem.getPack(chestplate);
            if (pack == null) {
                return;
            }
            FuelType fuelType = pack.fuelType;
            
            ForgeDirection pullSide = ForgeDirection.values()[blockPos.sideHit];
            Block block = player.worldObj.getBlock(blockPos.blockX, blockPos.blockY, blockPos.blockZ);
            TileEntity tile = player.worldObj.getTileEntity(blockPos.blockX, blockPos.blockY, blockPos.blockZ);
            int toPull = Math.min(pack.fuelPerTickIn, packItem.getMaxFuelStored(chestplate) - packItem.getFuelStored(chestplate));
            int pulled = 0;
            
            if (fuelType == FuelType.ENERGY && tile instanceof IEnergyProvider) {
                IEnergyProvider energyTile = (IEnergyProvider) tile;
                pulled = energyTile.extractEnergy(pullSide, toPull, false);
                
            } else if (fuelType == FuelType.FLUID) {
                FluidStack fluid = null;
                if (tile instanceof IFluidHandler) {
                    IFluidHandler fluidTile = (IFluidHandler) tile;
                    fluid = fluidTile.drain(pullSide, toPull, false);
                    if (fluid == null || fluid.getFluid().getName().equals(pack.fuelFluid)) {
                        return;
                    }
                    fluid = fluidTile.drain(pullSide, toPull, true);
                    
                } else if (block instanceof IFluidBlock) {
                    IFluidBlock fluidBlock = (IFluidBlock) block;
                    if (!fluidBlock.canDrain(player.worldObj, blockPos.blockX, blockPos.blockY, blockPos.blockZ)) {
                        return;
                    }
                    if (fluidBlock.getFluid() == null || !fluidBlock.getFluid().getName().equals(pack.fuelFluid)) {
                        return;
                    }
                    fluid = fluidBlock.drain(player.worldObj, blockPos.blockX, blockPos.blockY, blockPos.blockZ, false);
                    if (fluid == null || fluid.amount > toPull) {
                        return;
                    }
                    fluid = fluidBlock.drain(player.worldObj, blockPos.blockX, blockPos.blockY, blockPos.blockZ, true);
                }
                
                if (fluid == null) {
                    return;
                }
                pulled = fluid.amount;
            }
            
            if (pulled > 0) {
                packItem.addFuel(chestplate, pulled, false);
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        if (SJStringHelper.canShowDetails()) {
            SJStringHelper.addDescriptionLines(list, "jetpackFueller");
        } else {
            list.add(StringHelper.shiftForDetails());
        }
    }
    
}
