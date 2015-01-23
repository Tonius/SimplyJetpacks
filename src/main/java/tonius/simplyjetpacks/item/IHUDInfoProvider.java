package tonius.simplyjetpacks.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.item.ItemStack;

public interface IHUDInfoProvider {
    
    @SideOnly(Side.CLIENT)
    public void addHUDInfo(ItemStack stack, List<String> list);
    
}
