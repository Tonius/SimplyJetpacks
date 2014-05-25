package tonius.simplyjetpacks.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tonius.simplyjetpacks.tileentity.TileEntitySJUeberCharger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSJUeberCharger extends BlockSJ implements ITileEntityProvider {

    private static final String NAME = "ueberCharger";
    private static final Material MATERIAL = Material.rock;

    private static Icon[] iconsActive = new Icon[6];
    private static Icon[] iconsIdle = new Icon[6];

    public BlockSJUeberCharger(int id) {
        super(id, MATERIAL, NAME);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        iconsActive[0] = register.registerIcon("simplyjetpacks:" + name + "_active_bottom");
        iconsActive[1] = register.registerIcon("simplyjetpacks:" + name + "_active_top");
        iconsActive[2] = register.registerIcon("simplyjetpacks:" + name + "_active_front");
        iconsActive[3] = register.registerIcon("simplyjetpacks:" + name + "_active_back");
        iconsActive[4] = register.registerIcon("simplyjetpacks:" + name + "_active_left");
        iconsActive[5] = register.registerIcon("simplyjetpacks:" + name + "_active_right");
        iconsIdle[0] = register.registerIcon("simplyjetpacks:" + name + "_idle_bottom");
        iconsIdle[1] = register.registerIcon("simplyjetpacks:" + name + "_idle_top");
        iconsIdle[2] = register.registerIcon("simplyjetpacks:" + name + "_idle_front");
        iconsIdle[3] = register.registerIcon("simplyjetpacks:" + name + "_idle_back");
        iconsIdle[4] = register.registerIcon("simplyjetpacks:" + name + "_idle_left");
        iconsIdle[5] = register.registerIcon("simplyjetpacks:" + name + "_idle_right");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        TileEntity tile = blockAccess.getBlockTileEntity(posX, posY, posZ);
        if (tile instanceof TileEntitySJUeberCharger) {
            if (((TileEntitySJUeberCharger) tile).isActive()) {
                return iconsActive[side];
            }
        }
        return iconsIdle[side];
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntitySJUeberCharger();
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

}
