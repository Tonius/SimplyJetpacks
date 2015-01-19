package tonius.simplyjetpacks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IControllable {
    
    public void toggle(ItemStack itemStack, EntityPlayer player, boolean sneakChangesToggleBehavior, boolean showInChat);
    
    public void switchMode(ItemStack itemStack, EntityPlayer player, boolean sneakChangesToggleBehavior, boolean showInChat);
    
}
