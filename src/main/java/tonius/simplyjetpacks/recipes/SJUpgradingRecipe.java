package tonius.simplyjetpacks.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import tonius.simplyjetpacks.setup.SJItems;
import tonius.simplyjetpacks.util.StackUtils;
import cofh.api.energy.IEnergyContainerItem;

public class SJUpgradingRecipe extends ShapedOreRecipe {
    
    private IEnergyContainerItem resultItem;
    private int resultMeta;
    
    public SJUpgradingRecipe(ItemStack result, Object[] recipe) {
        super(result, recipe);
        this.resultItem = (IEnergyContainerItem) result.getItem();
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
                    tags = (NBTTagCompound) StackUtils.getNBT(slotStack).copy();
                }
                if (slotStack.getItem() instanceof IEnergyContainerItem) {
                    addedEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
                } else if (slotStack.getItem() == SJItems.particleCustomizers) {
                    particleType = JetpackParticleType.values()[slotStack.getItemDamage()];
                }
            }
        }
        
        ItemStack result = new ItemStack((Item) this.resultItem, 1, this.resultMeta);
        
        if (tags != null) {
            result.setTagCompound(tags);
        }
        
        StackUtils.getNBT(result).setInteger("Energy", Math.min(addedEnergy, this.resultItem.getMaxEnergyStored(result)));
        
        if (this.resultItem instanceof ItemJetpack && particleType != null) {
            ((ItemJetpack) this.resultItem).getJetpack(result).setParticleType(result, particleType);
        }
        
        return result;
    }
    
}
