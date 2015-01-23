package tonius.simplyjetpacks.crafting;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.item.meta.PackBase;
import tonius.simplyjetpacks.setup.ModItems;

public class PlatingReturnHandler {
    
    @SubscribeEvent
    public void onItemCrafted(ItemCraftedEvent evt) {
        if (!evt.player.worldObj.isRemote && evt.crafting.getItem() instanceof ItemPack) {
            PackBase outputPack = ((ItemPack) evt.crafting.getItem()).getPack(evt.crafting);
            if (!outputPack.isArmored) {
                for (int i = 0; i < evt.craftMatrix.getSizeInventory(); i++) {
                    ItemStack input = evt.craftMatrix.getStackInSlot(i);
                    if (input != null && input.getItem() instanceof ItemPack) {
                        PackBase inputPack = ((ItemPack) input.getItem()).getPack(input);
                        if (inputPack != null && inputPack.isArmored && inputPack.platingMeta != null) {
                            EntityItem item = evt.player.entityDropItem(new ItemStack(ModItems.armorPlatings, 1, inputPack.platingMeta), 0.0F);
                            item.delayBeforeCanPickup = 0;
                            break;
                        }
                    }
                }
            }
        }
    }
    
}
