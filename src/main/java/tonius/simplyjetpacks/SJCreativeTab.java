package tonius.simplyjetpacks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SJCreativeTab extends CreativeTabs {

    private static ItemStack display;
    private static final SJCreativeTab tab = new SJCreativeTab();

    private SJCreativeTab() {
        super("tabSimplyJetpacks");
    }

    @Override
    public ItemStack getIconItemStack() {
        if (display == null) {
            display = new ItemStack(SJItems.jetpackTier4);
            display.setItemDamage(Short.MAX_VALUE);
        }
        return display;
    }

    public static SJCreativeTab tab() {
        return tab;
    }

}
