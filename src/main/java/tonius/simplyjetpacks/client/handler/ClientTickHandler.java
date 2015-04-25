package tonius.simplyjetpacks.client.handler;

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.audio.SoundJetpack;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;
import tonius.simplyjetpacks.item.meta.Jetpack;
import tonius.simplyjetpacks.setup.ParticleType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class ClientTickHandler {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static ParticleType lastJetpackState = null;
    
    private static void tickStart() {
        if (mc.thePlayer != null) {
            ParticleType jetpackState = null;
            ItemStack armor = mc.thePlayer.getEquipmentInSlot(3);
            if (armor != null && armor.getItem() instanceof ItemJetpack) {
                Jetpack jetpack = ((ItemJetpack) armor.getItem()).getPack(armor);
                if (jetpack != null) {
                    jetpackState = jetpack.getDisplayParticleType(armor, (ItemJetpack) armor.getItem(), mc.thePlayer);
                }
            }
            
            if (jetpackState != lastJetpackState) {
                lastJetpackState = jetpackState;
                SyncHandler.processJetpackUpdate(mc.thePlayer.getEntityId(), jetpackState);
            }
        }
    }
    
    private static void tickEnd() {
        if (mc.thePlayer != null && mc.theWorld != null && !mc.isGamePaused()) {
            Iterator<Integer> itr = SyncHandler.getJetpackStates().keySet().iterator();
            int currentEntity;
            while (itr.hasNext()) {
                currentEntity = itr.next();
                Entity entity = mc.theWorld.getEntityByID(currentEntity);
                if (entity == null || !(entity instanceof EntityLivingBase) || entity.dimension != mc.thePlayer.dimension) {
                    itr.remove();
                } else {
                    ParticleType particle = SyncHandler.getJetpackStates().get(currentEntity);
                    if (particle != null) {
                        if (entity.isInWater() && particle != ParticleType.NONE) {
                            particle = ParticleType.BUBBLE;
                        }
                        SimplyJetpacks.proxy.showJetpackParticles(mc.theWorld, (EntityLivingBase) entity, particle);
                        if (Config.jetpackSounds && !SoundJetpack.isPlayingFor(entity.getEntityId())) {
                            Minecraft.getMinecraft().getSoundHandler().playSound(new SoundJetpack((EntityLivingBase) entity));
                        }
                    } else {
                        itr.remove();
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent evt) {
        if (evt.phase == Phase.START) {
            tickStart();
        } else {
            tickEnd();
        }
    }
    
}
