package tonius.simplyjetpacks.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import tonius.simplyjetpacks.setup.SJItems;
import tonius.simplyjetpacks.util.StackUtils;
import cofh.api.energy.IEnergyContainerItem;

public class JetpackUpgradingRecipe extends ShapedOreRecipe {

    private ItemJetpack resultItem;
    private int resultMeta;

    public JetpackUpgradingRecipe(ItemStack result, Object[] recipe) {
        super(result, recipe);
        this.resultItem = (ItemJetpack) result.getItem();
        this.resultMeta = result.getItemDamage();
        result.getEnchantmentTagList();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        int addedEnergy = 0;
        JetpackParticleType particleType = null;
        NBTTagCompound tags = null;

        ItemStack slotStack;
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            slotStack = inventoryCrafting.getStackInSlot(i);
            if (slotStack != null) {
                if (slotStack.getItem() instanceof ItemJetpack) {
                    tags = StackUtils.getNBT(slotStack);
                } else if (slotStack.getItem() instanceof IEnergyContainerItem) {
                    addedEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
                } else if (slotStack.getItem() == SJItems.particleCustomizers) {
                    particleType = JetpackParticleType.values()[slotStack.getItemDamage()];
                }
            }
        }

        ItemStack result = new ItemStack(resultItem, 1, this.resultMeta);

        if (tags != null) {
            result.setTagCompound(tags);
        }

        resultItem.receiveEnergy(result, addedEnergy, false);

        if (particleType != null) {
            resultItem.getJetpack(result).setParticleType(result, particleType);
        }

        return result;
    }

}
