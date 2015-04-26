package tonius.simplyjetpacks.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tonius.simplyjetpacks.gui.ContainerPack;
import tonius.simplyjetpacks.gui.GuiPack;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.item.meta.PackBase;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    
    public static final int PACK = 0;
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
        case PACK:
            ItemStack chestplate = player.getCurrentArmor(2);
            if (chestplate != null && chestplate.getItem() instanceof ItemPack) {
                ItemPack packItem = (ItemPack) chestplate.getItem();
                PackBase pack = packItem.getPack(chestplate);
                if (pack != null) {
                    return new ContainerPack(chestplate, packItem, pack);
                }
            }
        }
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
        case PACK:
            ItemStack chestplate = player.getCurrentArmor(2);
            if (chestplate != null && chestplate.getItem() instanceof ItemPack) {
                ItemPack packItem = (ItemPack) chestplate.getItem();
                PackBase pack = packItem.getPack(chestplate);
                if (pack != null) {
                    return new GuiPack(new ContainerPack(chestplate, packItem, pack));
                }
            }
        }
        return null;
    }
    
}
