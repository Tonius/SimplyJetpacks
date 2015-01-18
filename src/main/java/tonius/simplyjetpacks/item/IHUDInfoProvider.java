package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IHUDInfoProvider {
    
    @SideOnly(Side.CLIENT)
    public List<String> getHUDInfo(ItemStack stack);
    
}
