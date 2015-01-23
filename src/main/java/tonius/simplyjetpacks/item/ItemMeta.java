package tonius.simplyjetpacks.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.util.StringUtils;

public class ItemMeta extends Item {
    
    protected final Map<Integer, MetaItem> metaItems = new HashMap<Integer, MetaItem>();
    protected final Map<Integer, IIcon> icons = new HashMap<Integer, IIcon>();
    protected int highestMeta = 0;
    
    public ItemMeta(String fallbackName) {
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + fallbackName);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(ModCreativeTab.tab);
    }
    
    public MetaItem getMetaItem(ItemStack itemStack) {
        return this.metaItems.get(itemStack.getItemDamage());
    }
    
    public ItemStack addMetaItem(int index, MetaItem item, boolean registerCustomItemStack, boolean registerOreDict) {
        if (item == null) {
            return null;
        }
        
        this.metaItems.put(index, item);
        ItemStack itemStack = new ItemStack(this, 1, index);
        
        if (index > this.highestMeta) {
            this.highestMeta = index;
        }
        
        if (registerCustomItemStack) {
            GameRegistry.registerCustomItemStack(item.name, itemStack);
        }
        if (registerOreDict) {
            OreDictionary.registerOre(item.name, itemStack);
        }
        
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

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        MetaItem metaItem = this.getMetaItem(itemStack);
        if (metaItem != null && metaItem.tooltipKey != null) {
            if (StringUtils.canShowDetails()) {
                StringUtils.addDescriptionLines(list, metaItem.tooltipKey, StringUtils.LIGHT_GRAY);
            } else {
                list.add(StringUtils.getShiftText());
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= this.highestMeta; i++) {
            MetaItem metaItem = this.metaItems.get(i);
            if (metaItem != null && !metaItem.hidden) {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass) {
        if (pass == 0) {
            MetaItem metaItem = this.getMetaItem(itemStack);
            if (metaItem != null && metaItem.glow) {
                return true;
            }
        }
        return super.hasEffect(itemStack, pass);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i <= this.highestMeta; i++) {
            MetaItem metaItem = this.metaItems.get(i);
            if (metaItem != null) {
                this.icons.put(i, register.registerIcon(SimplyJetpacks.RESOURCE_PREFIX + metaItem.name));
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.icons.get(damage);
    }
    
    public static class MetaItem {
        
        public final String name;
        public final String tooltipKey;
        public final EnumRarity rarity;
        public final boolean glow;
        public final boolean hidden;
        
        public MetaItem(String name, String tooltipKey, EnumRarity rarity, boolean glow, boolean hidden) {
            this.name = name;
            this.tooltipKey = tooltipKey;
            this.rarity = rarity;
            this.glow = glow;
            this.hidden = hidden;
        }
        
        public MetaItem(String name, String tooltipKey, EnumRarity rarity) {
            this(name, tooltipKey, rarity, false, false);
        }
        
    }
    
}
