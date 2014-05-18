package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.util.LangUtils;
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

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        if (StringUtils.isShiftKeyDown()) {
            switch (itemStack.getItemDamage()) {
            case 0:
            case 1:
            case 2:
            case 3:
                list.add(StringUtils.BRIGHT_GREEN + LangUtils.translate("tooltip.thruster.description.1"));
                list.add(StringUtils.BRIGHT_GREEN + LangUtils.translate("tooltip.thruster.description.2"));
            }
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

}
