package tonius.simplyjetpacks.item;

import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJMeta1 extends ItemSJSimpleMeta {

    public ItemSJMeta1(int id) {
        super(id, "metaItem1", 4);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        switch (itemStack.getItemDamage()) {
        case 2:
            return StringUtils.YELLOW + super.getItemDisplayName(itemStack);
        case 3:
            return StringUtils.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
        }
        return super.getItemDisplayName(itemStack);
    }

}
