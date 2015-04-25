package tonius.simplyjetpacks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.setup.ModKey;

public interface IControllable {
    
    public void onKeyPressed(ItemStack itemStack, EntityPlayer player, ModKey key, boolean showInChat);
    
}
