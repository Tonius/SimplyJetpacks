package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJMeta1 extends ItemSJSimpleMeta {

    public ItemSJMeta1(int id) {
        super(id, "metaItem1", 9);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        switch (itemStack.getItemDamage()) {
        case 2:
            return StringUtils.YELLOW + super.getItemDisplayName(itemStack);
        case 3:
        case 8:
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
                list.add(StringUtils.translate("tooltip.thruster.description.1"));
                list.add(StringUtils.translate("tooltip.thruster.description.2"));
                break;
            case 4:
                list.add(StringUtils.translate("tooltip.leatherStrap.description.1"));
                list.add(StringUtils.translate("tooltip.leatherStrap.description.2"));
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                list.add(StringUtils.translate("tooltip.armorPlating.description.1"));
                list.add(StringUtils.translate("tooltip.armorPlating.description.2"));
            }
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

}
