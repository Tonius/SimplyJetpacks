package tonius.simplyjetpacks;

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
        return SJItems.jetpackTier4;
    }

}
