package tonius.simplyjetpacks.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;

public class EntityInteractHandler {
    
    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent evt) {
        if (evt.entityPlayer.isSneaking() && (evt.target instanceof EntityZombie || evt.target instanceof EntitySkeleton)) {
            EntityLiving target = (EntityLiving) evt.target;
            ItemStack heldStack = evt.entityPlayer.getHeldItem();
            if (heldStack != null && heldStack.getItem() instanceof ItemJetpack) {
                if (!target.worldObj.isRemote) {
                    target.dropEquipment(true, 3);
                }
                target.setCurrentItemOrArmor(3, heldStack.copy());
                target.equipmentDropChances[3] = 2.0F;
                target.persistenceRequired = true;
                evt.entityPlayer.getHeldItem().stackSize--;
            }
        }
    }
    
}
