package tonius.simplyjetpacks.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemSJJetpack;
import cofh.api.energy.IEnergyContainerItem;

public class JetpackUpgradingRecipe extends ShapedOreRecipe {

    private Item resultItem;
    private ItemStack result;

    public JetpackUpgradingRecipe(ItemStack result, Object[] recipe) {
        super(result, recipe);
        this.resultItem = result.getItem();
    }

    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        this.result = null;
        int resultEnergy = 0;

        ItemStack slotStack;
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            slotStack = inventoryCrafting.getStackInSlot(i);
            if (slotStack != null && slotStack.getItem() instanceof IEnergyContainerItem) {
                resultEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
            }
        }

        this.result = new ItemStack(resultItem);
        IEnergyContainerItem helperItem = (IEnergyContainerItem) this.resultItem;

        if (result.getTagCompound() == null) {
            result.setTagCompound(new NBTTagCompound());
        }

        if (resultEnergy > helperItem.getMaxEnergyStored(result)) {
            resultEnergy = helperItem.getMaxEnergyStored(result);
        }
        result.getTagCompound().setInteger("Energy", resultEnergy);
        ((ItemSJJetpack) result.getItem()).updateEnergyDisplay(result);

        return super.matches(inventoryCrafting, world);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        return result.copy();
    }

}
