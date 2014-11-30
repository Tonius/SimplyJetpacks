package tonius.simplyjetpacks.crafting;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.setup.SJItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class SJCraftingHandler {
    
    @SubscribeEvent
    public void onItemCrafted(ItemCraftedEvent evt) {
        if (evt.crafting.getItem() instanceof ItemJetpack) {
            Jetpack jetpackResult = ((ItemJetpack) evt.crafting.getItem()).getJetpack(evt.crafting);
            if (jetpackResult != null && !jetpackResult.isArmored()) {
                for (int i = 0; i < evt.craftMatrix.getSizeInventory(); i++) {
                    ItemStack input = evt.craftMatrix.getStackInSlot(i);
                    if (input != null && input.getItem() instanceof ItemJetpack) {
                        ItemJetpack jetpackInputItem = (ItemJetpack) input.getItem();
                        Jetpack jetpackInput = jetpackInputItem.getJetpack(input);
                        if (jetpackInput != null && jetpackInput.isArmored()) {
                            returnPlating(evt.player, jetpackInput.tier, jetpackInputItem.modType.platingOffset);
                            break;
                        }
                    }
                }
            }
        } else if (evt.crafting.getItem() instanceof ItemFluxPack) {
            FluxPack fluxpackResult = ((ItemFluxPack) evt.crafting.getItem()).getFluxPack(evt.crafting);
            if (fluxpackResult != null && !fluxpackResult.isArmored()) {
                for (int i = 0; i < evt.craftMatrix.getSizeInventory(); i++) {
                    ItemStack input = evt.craftMatrix.getStackInSlot(i);
                    if (input != null && input.getItem() instanceof ItemFluxPack) {
                        ItemFluxPack fluxpackInputItem = (ItemFluxPack) input.getItem();
                        FluxPack fluxpackInput = fluxpackInputItem.getFluxPack(input);
                        if (fluxpackInput != null && fluxpackInput.isArmored()) {
                            returnPlating(evt.player, fluxpackInput.tier, fluxpackInputItem.modType.platingOffset);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private static void returnPlating(EntityPlayer player, int tier, int platingOffset) {
        if (!player.worldObj.isRemote) {
            EntityItem item = player.entityDropItem(new ItemStack(SJItems.armorPlatings, 1, tier + platingOffset), 0.0F);
            item.delayBeforeCanPickup = 0;
        }
    }
    
}
