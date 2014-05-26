package tonius.simplyjetpacks.client.event;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import tonius.simplyjetpacks.SimplyJetpacks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoundLoader {

    public static String[] sounds = { "rocket" };

    @SideOnly(Side.CLIENT)
    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent evt) {
        try {
            for (String sound : sounds) {
                SimplyJetpacks.logger.info("Loading sound: " + sound + ".ogg");
                evt.manager.addSound("simplyjetpacks:" + sound + ".ogg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
