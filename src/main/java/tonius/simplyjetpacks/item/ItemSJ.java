package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tonius.simplyjetpacks.setup.SJCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJ extends Item {

    protected String name;
    protected int amount;
    private IIcon[] metaIcons;

    public ItemSJ(String name, int amount) {
        this.name = name;
        this.amount = amount;
        this.metaIcons = new IIcon[amount];
        this.setUnlocalizedName("simplyjetpacks." + name);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(SJCreativeTab.tab);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < amount; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName() + "_" + itemStack.getItemDamage();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i < amount; i++) {
            metaIcons[i] = register.registerIcon("simplyjetpacks:" + name + "_" + i);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta < amount) {
            return metaIcons[meta];
        }
        return null;
    }

}
