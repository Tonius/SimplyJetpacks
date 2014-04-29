package tonius.simplyjetpacks.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJArmor extends ItemArmor {

    protected String name;
    protected String textureName;

    public ItemSJArmor(int id, EnumArmorMaterial material, int renderIndex, int armorType, String name, String textureName) {
        super(id, material, renderIndex, armorType);
        setUnlocalizedName("simplyjetpacks." + name);
        this.name = name;
        this.textureName = textureName;
        setCreativeTab(SimplyJetpacks.creativeTab);
        SimplyJetpacks.logger.info("Registering armor: " + name);
        GameRegistry.registerItem(this, name);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        itemIcon = register.registerIcon("simplyjetpacks:" + name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "simplyjetpacks:textures/armor/" + textureName + "_layer_" + (slot == 2 ? "1" : "0") + ".png";
    }
}
