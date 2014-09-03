package tonius.simplyjetpacks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IModeSwitchable {
    
    public void switchMode(ItemStack itemStack, EntityPlayer player, boolean showInChat);
}
