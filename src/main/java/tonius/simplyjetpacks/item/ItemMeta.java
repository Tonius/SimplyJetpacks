package tonius.simplyjetpacks.item;

import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tonius.simplyjetpacks.setup.SJCreativeTab;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMeta extends Item {

    protected final Map<Integer, MetaItem> metaItems;
    protected final int highestMeta;
    protected final String fallbackName;
    protected IIcon[] icons = null;

    public ItemMeta(Map<Integer, MetaItem> names, String fallbackName) {
        this.metaItems = names;
        int highestMeta = 0;
        for (int i : names.keySet()) {
            if (i > highestMeta) {
                highestMeta = i;
            }
        }
        this.highestMeta = highestMeta;
        this.fallbackName = fallbackName;
        this.icons = new IIcon[highestMeta + 1];

        this.setUnlocalizedName("simplyjetpacks." + fallbackName);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(SJCreativeTab.tab);
    }

    public MetaItem getMetaItem(ItemStack itemStack) {
        return this.metaItems.get(itemStack.getItemDamage());
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        MetaItem metaItem = this.getMetaItem(itemStack);
        if (metaItem != null) {
            return "item.simplyjetpacks." + metaItem.name;
        }
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        MetaItem metaItem = this.getMetaItem(itemStack);
        if (metaItem != null) {
            return metaItem.colorPrefix + super.getItemStackDisplayName(itemStack);
        }
        return super.getItemStackDisplayName(itemStack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        MetaItem metaItem = this.getMetaItem(itemStack);
        if (metaItem != null && metaItem.tooltipLines.length > 0) {
            if (StringUtils.canShowDetails()) {
                for (int i = 0; i < metaItem.tooltipLines.length; i++) {
                    list.add(StringUtils.translate(metaItem.tooltipLines[i]));
                }
            } else {
                list.add(StringUtils.getShiftText());
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= this.highestMeta; i++) {
            if (this.metaItems.get(i) != null) {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i <= this.highestMeta; i++) {
            MetaItem metaItem = this.metaItems.get(i);
            if (metaItem != null) {
                this.icons[i] = register.registerIcon("simplyjetpacks:" + metaItem.name);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return damage < this.icons.length ? this.icons[damage] : null;
    }

}
