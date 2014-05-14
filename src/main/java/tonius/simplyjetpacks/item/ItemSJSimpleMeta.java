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
    protected int amount;
    private Icon[] metaIcons;

    public ItemSJSimpleMeta(int id, String name, int amount) {
        super(id);
        setUnlocalizedName("simplyjetpacks." + name);
        this.name = name;
        this.amount = amount;
        setCreativeTab(SimplyJetpacks.creativeTab);
        SimplyJetpacks.logger.info("Registering item: " + name);
        GameRegistry.registerItem(this, name);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.metaIcons = new Icon[amount];
    }

    @Override
    public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < amount; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName() + "_" + itemStack.getItemDamage();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        for (int i = 0; i < amount; i++) {
            metaIcons[i] = register.registerIcon("simplyjetpacks:" + name + "_" + i);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int meta) {
        return metaIcons[meta];
    }

}
