package tonius.simplyjetpacks.tickhandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageJetpackSync;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;

public class PlayerTickHandler {
    
    private static Map<Integer, JetpackParticleType> lastJetpackState = new HashMap<Integer, JetpackParticleType>();
    
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent evt) {
        if (evt.side == Side.SERVER && evt.phase == Phase.END) {
            tickEnd(evt.player);
        }
    }
    
    private static void tickEnd(EntityPlayer player) {
        JetpackParticleType jetpackState = null;
        ItemStack armor = player.getCurrentArmor(2);
        boolean foundJetpack = false;
        if (armor != null && armor.getItem() instanceof ItemJetpack) {
            Jetpack jetpack = ((ItemJetpack) armor.getItem()).getJetpack(armor);
            if (jetpack != null) {
                jetpackState = jetpack.particleToShow(armor, (ItemJetpack) armor.getItem(), player);
                foundJetpack = true;
            }
        }
        
        if (jetpackState != lastJetpackState.get(player.getEntityId())) {
            lastJetpackState.put(player.getEntityId(), jetpackState);
            PacketHandler.instance.sendToAllAround(new MessageJetpackSync(player.getEntityId(), jetpackState != null ? jetpackState.ordinal() : -1), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 256));
        } else if (foundJetpack && player.worldObj.getTotalWorldTime() % 160L == 0) {
            PacketHandler.instance.sendToAllAround(new MessageJetpackSync(player.getEntityId(), jetpackState != null ? jetpackState.ordinal() : -1), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 256));
        }
        
        if (player.worldObj.getTotalWorldTime() % 200L == 0) {
            Iterator<Integer> itr = lastJetpackState.keySet().iterator();
            while (itr.hasNext()) {
                int entityId = itr.next();
                if (player.worldObj.getEntityByID(entityId) == null) {
                    itr.remove();
                }
            }
        }
    }
    
}
