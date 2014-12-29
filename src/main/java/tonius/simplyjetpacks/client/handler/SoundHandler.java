package tonius.simplyjetpacks.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;

public class SoundHandler {
    
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onSoundReceived(PlaySoundAtEntityEvent event) {
        // This is for killing idle flight sounds that server sends to client, if client has them disabled.
        if (!Config.enableFlightSounds && (SimplyJetpacks.RESOURCE_PREFIX + "jetpack_flight_idle").equals(event.name)) {
            event.setCanceled(true);
        }
    }
    
}
