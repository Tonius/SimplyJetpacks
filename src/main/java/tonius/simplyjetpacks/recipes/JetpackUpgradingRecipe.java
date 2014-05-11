package tonius.simplyjetpacks.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemSJJetpack;
import cofh.api.energy.IEnergyContainerItem;

public class JetpackUpgradingRecipe extends ShapedOreRecipe {

    private ItemSJJetpack resultItem;

    public JetpackUpgradingRecipe(ItemStack result, Object[] recipe) {
        super(result, recipe);
        this.resultItem = (ItemSJJetpack) result.getItem();
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
        resultItem.addEnergy(result, resultEnergy, false);
        return result.copy();
    }

}
