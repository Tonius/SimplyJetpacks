package tonius.simplyjetpacks.setup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SJCreativeTab extends CreativeTabs {

    private static ItemStack display;
    public static final SJCreativeTab tab = new SJCreativeTab();

    private SJCreativeTab() {
        super("tabSimplyJetpacks");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return SJItems.jetpacks;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        if (display == null) {
            display = new ItemStack(this.getTabIconItem(), 1, 9002);
        }
        return display;
    }

}
