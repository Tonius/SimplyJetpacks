package tonius.simplyjetpacks.item;

import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJMaterials extends ItemSJSimpleMeta {

    public ItemSJMaterials(int id) {
        super(id, "materials", 3);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        switch (itemStack.getItemDamage()) {
        case 0:
        case 1:
            return StringUtils.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
        case 2:
            return StringUtils.PINK + super.getItemDisplayName(itemStack);
        }
        return super.getItemDisplayName(itemStack);
    }

}
