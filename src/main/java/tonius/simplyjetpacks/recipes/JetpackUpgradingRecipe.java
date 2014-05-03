package tonius.simplyjetpacks.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemSJJetpack;
import cofh.api.energy.IEnergyContainerItem;

public class JetpackUpgradingRecipe extends ShapedOreRecipe {

    private Item resultItem;

    public JetpackUpgradingRecipe(ItemStack result, Object[] recipe) {
        super(result, recipe);
        this.resultItem = result.getItem();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        int resultEnergy = 0;

        ItemStack slotStack;
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            slotStack = inventoryCrafting.getStackInSlot(i);
            if (slotStack != null && slotStack.getItem() instanceof IEnergyContainerItem) {
                resultEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
            }
        }

        ItemStack result = new ItemStack(resultItem);
        IEnergyContainerItem helperItem = (IEnergyContainerItem) this.resultItem;

        if (resultEnergy > helperItem.getMaxEnergyStored(result)) {
            resultEnergy = helperItem.getMaxEnergyStored(result);
        }

        if (result.stackTagCompound == null) {
            result.stackTagCompound = new NBTTagCompound();
        }
        result.stackTagCompound.setInteger("Energy", resultEnergy);
        ((ItemSJJetpack) result.getItem()).updateEnergyDisplay(result);

        return result.copy();
    }

}
