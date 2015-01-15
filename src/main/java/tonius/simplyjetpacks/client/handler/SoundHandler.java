package tonius.simplyjetpacks.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.sound.MovingSoundIdleJetpack;
import tonius.simplyjetpacks.config.Config;

public class SoundHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onSoundReceived(PlaySoundAtEntityEvent event) {
        // This is for spawning actual jetpack sounds if client has them enabled.
        if((SimplyJetpacks.RESOURCE_PREFIX + "jetpack_flight_idle").equals(event.name)) {
            event.setCanceled(true);
            if(Config.enableFlightSounds && event.entity instanceof EntityLivingBase && !MovingSoundIdleJetpack.playingFor.contains(event.entity)) {
                Minecraft.getMinecraft().getSoundHandler().playSound(new MovingSoundIdleJetpack((EntityLivingBase)event.entity));
            }
        }
    }
    
}
