package tonius.simplyjetpacks.crafting;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.StackUtils;

public class UpgradingRecipe extends ShapedOreRecipe {
    
    private final IEnergyContainerItem resultItem;
    private final int resultMeta;
    
    public UpgradingRecipe(ItemStack result, Object... recipe) {
        super(result, recipe);
        this.resultItem = (IEnergyContainerItem) result.getItem();
        this.resultMeta = result.getItemDamage();
        result.getEnchantmentTagList();
    }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        int addedEnergy = 0;
        ParticleType particleType = null;
        NBTTagCompound tags = null;
        
        ItemStack slotStack;
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            slotStack = inventoryCrafting.getStackInSlot(i);
            if (slotStack != null) {
                if (slotStack.getItem() instanceof ItemPack) {
                    tags = (NBTTagCompound) StackUtils.getNBT(slotStack).copy();
                }
                if (slotStack.getItem() instanceof IEnergyContainerItem) {
                    addedEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
                } else if (slotStack.getItem() == ModItems.particleCustomizers) {
                    particleType = ParticleType.values()[slotStack.getItemDamage()];
                }
            }
        }
        
        ItemStack result = new ItemStack((Item) this.resultItem, 1, this.resultMeta);
        
        if (tags != null) {
            result.setTagCompound(tags);
        }
        
        StackUtils.getNBT(result).setInteger("Energy", Math.min(addedEnergy, this.resultItem.getMaxEnergyStored(result)));
        
        if (this.resultItem instanceof ItemJetpack && particleType != null) {
            ((ItemJetpack) this.resultItem).getPack(result).setParticleType(result, particleType);
        }
        
        return result;
    }
    
}
