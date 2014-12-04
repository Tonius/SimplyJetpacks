package tonius.simplyjetpacks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IToggleable {
    
    public void toggle(ItemStack itemStack, EntityPlayer player, boolean sneakChangesToggleBehavior, boolean showInChat);
    
}
