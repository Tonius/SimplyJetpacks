package tonius.simplyjetpacks.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemSJArmorEnergy;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeUpgradeLeadstoneJetpack implements IRecipe {
    private ItemStack result;

    @Override
    public boolean matches(InventoryCrafting inventorycrafting, World world) {
        result = null;
        ItemStack jetpack = null;
        int capacitorEnergy = 0;
        
        // recipe needs at least a 3x3 grid so check if there is enough space
        if(inventorycrafting.getSizeInventory() < 9) {
            return false;
        }
        
        // check if recipe is correct
        ItemStack slotStack;
        ItemStack capacitorNeeded = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        for(int i = 0; i < inventorycrafting.getSizeInventory(); i++) {
            slotStack = inventorycrafting.getStackInSlot(i);

            switch(i) {
                case 0:
                case 2:
                case 3:
                case 5:
                    if(slotStack == null || OreDictionary.getOreName(OreDictionary.getOreID(slotStack)) != "ingotInvar") {
                        return false;
                    }
                    break;
                case 1:
                    if(slotStack == null) {
                        return false;
                    }
                    if((slotStack.getItem() == capacitorNeeded.getItem() && slotStack.getItemDamage() == capacitorNeeded.getItemDamage())) {
                        capacitorEnergy = ((IEnergyContainerItem)slotStack.getItem()).getEnergyStored(slotStack);
                    } else {
                        return false;
                    }
                    break;
                case 4:
                    if(slotStack == null) {
                        return false;
                    }
                    if(slotStack.getItem() == SimplyJetpacks.jetpackTier1) {
                        jetpack = slotStack.copy();
                    } else {
                        return false;
                    }
                    break;
                case 7:
                    if(slotStack == null || (slotStack.getItem() != SimplyJetpacks.metaItem1 && slotStack.getItemDamage() != 1)) {
                        return false;
                    }
                    break;
                case 6:
                case 8:
                    if(slotStack != null) {
                        return false;
                    }
                    break;
            }
        }
        
        // get result item
        result = new ItemStack(SimplyJetpacks.jetpackTier2);
        // calculate energy
        IEnergyContainerItem helperItem = (IEnergyContainerItem)result.getItem();
        int energy = helperItem.getEnergyStored(jetpack) + capacitorEnergy;
        if(energy > helperItem.getMaxEnergyStored(result)) {
            energy = helperItem.getMaxEnergyStored(result);
        }
        // check NBTTagCompound
        if(result.getTagCompound() == null) {
            result.setTagCompound(new NBTTagCompound());
        }
        // set NBT values
        result.getTagCompound().setInteger("Energy", energy);
        if(jetpack.getTagCompound() != null && jetpack.getTagCompound().hasKey("HoverModeActive")) {
            result.getTagCompound().setBoolean("HoverModeActive", jetpack.getTagCompound().getBoolean("HoverModeActive"));
        }
        if(jetpack.getTagCompound() != null && jetpack.getTagCompound().hasKey("On")) {
            result.getTagCompound().setBoolean("On", jetpack.getTagCompound().getBoolean("On"));
        }
        // set correct damage value (energy level)
        ((ItemSJArmorEnergy)result.getItem()).updateEnergyDisplay(result);
        
        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventorycrafting) {
        return result.copy();
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result;
    }
}
