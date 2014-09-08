package tonius.simplyjetpacks.item;

import net.minecraft.item.ItemStack;

public interface IEnergyHUDInfoProvider {
    
    public String getEnergyInfo(ItemStack stack);
    
    public String getStatesInfo(ItemStack stack);
    
}
