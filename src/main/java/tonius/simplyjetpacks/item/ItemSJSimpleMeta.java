package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJSimpleMeta extends Item {

    protected String name;
    private Icon[] metaIcons;
    private String[] englishNames;

    public ItemSJSimpleMeta(int id, String name, String[] englishNames) {
        super(id);
        setUnlocalizedName("simplyjetpacks." + name);
        this.name = name;
        setCreativeTab(SimplyJetpacks.creativeTab);
        SimplyJetpacks.logger.info("Registering item: " + name);
        GameRegistry.registerItem(this, name);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.englishNames = englishNames;
        this.metaIcons = new Icon[englishNames.length];
    }

    @Override
    public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < englishNames.length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        return englishNames[itemStack.getItemDamage()];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        for (int i = 0; i < englishNames.length; i++) {
            metaIcons[i] = register.registerIcon("simplyjetpacks:" + name + "_" + i);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int meta) {
        return metaIcons[meta];
    }

}
