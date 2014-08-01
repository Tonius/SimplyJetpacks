package tonius.simplyjetpacks.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.setup.SJCreativeTab;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMeta extends Item {

    protected Map<Integer, MetaItem> metaItems = new HashMap<Integer, MetaItem>();
    protected int highestMeta = 0;
    protected final String fallbackName;
    protected Map<Integer, IIcon> icons = new HashMap<Integer, IIcon>();

    public ItemMeta(String fallbackName) {
        this.fallbackName = fallbackName;

        this.setUnlocalizedName("simplyjetpacks." + fallbackName);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(SJCreativeTab.tab);
    }

    public MetaItem getMetaItem(ItemStack itemStack) {
        return this.metaItems.get(itemStack.getItemDamage());
    }

    public ItemStack addMetaItem(int index, MetaItem item, boolean registerCustomItemStack, boolean registerOreDict) {
        if (item == null)
            return null;

        this.metaItems.put(index, item);
        ItemStack itemStack = new ItemStack(this, 1, index);

        if (index > this.highestMeta)
            this.highestMeta = index;

        if (registerCustomItemStack)
            GameRegistry.registerCustomItemStack(item.name, itemStack);
        if (registerOreDict)
            OreDictionary.registerOre(item.name, itemStack);

        return itemStack;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        MetaItem metaItem = this.getMetaItem(itemStack);
        if (metaItem != null) {
            return "item.simplyjetpacks." + metaItem.name;
        }
        return super.getUnlocalizedName();
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        MetaItem metaItem = this.getMetaItem(itemStack);
        if (metaItem != null) {
            return metaItem.rarity;
        }
        return super.getRarity(itemStack);
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
                this.icons.put(i, register.registerIcon("simplyjetpacks:" + metaItem.name));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.icons.get(damage);
    }

    public static class MetaItem {

        public String name;
        public String[] tooltipLines;
        public EnumRarity rarity;

        public MetaItem(String name, String[] tooltipLines, EnumRarity rarity) {
            this.name = name;
            this.tooltipLines = tooltipLines != null ? tooltipLines : new String[0];
            this.rarity = rarity;
        }

    }

}
