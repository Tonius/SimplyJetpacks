package tonius.simplyjetpacks.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.ItemParticleCustomizers;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import cofh.api.energy.IEnergyContainerItem;

public class JetpackUpgradingRecipe extends ShapedOreRecipe {

    private ItemJetpack resultItem;
    private int resultMeta;

    public JetpackUpgradingRecipe(ItemStack result, Object[] recipe) {
        super(result, recipe);
        this.resultItem = (ItemJetpack) result.getItem();
        this.resultMeta = result.getItemDamage();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        int resultEnergy = 0;
        JetpackParticleType particleType = null;

        ItemStack slotStack;
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            slotStack = inventoryCrafting.getStackInSlot(i);
            if (slotStack != null) {
                if (slotStack.getItem() instanceof ItemJetpack) {
                    particleType = resultItem.getJetpack(slotStack).getParticleType(slotStack);
                }
                if (slotStack.getItem() instanceof IEnergyContainerItem) {
                    resultEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
                } else if (slotStack.getItem() instanceof ItemParticleCustomizers) {
                    particleType = JetpackParticleType.values()[slotStack.getItemDamage()];
                }
            }
        }

        ItemStack result = new ItemStack(resultItem, 1, this.resultMeta);
        resultItem.receiveEnergy(result, resultEnergy, false);

        if (particleType != null) {
            resultItem.getJetpack(result).setParticleType(result, particleType);
        }

        return result.copy();
    }

}
