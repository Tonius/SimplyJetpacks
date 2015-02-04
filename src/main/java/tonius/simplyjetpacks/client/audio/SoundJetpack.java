package tonius.simplyjetpacks.client.audio;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.handler.SyncHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SoundJetpack extends MovingSound {
    
    private static final ResourceLocation SOUND = new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "jetpack");
    private static final ResourceLocation SOUND_OTHER = new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "jetpack_other");
    
    private static final Map<Integer, SoundJetpack> playingFor = Collections.synchronizedMap(new HashMap<Integer, SoundJetpack>());
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    private final EntityLivingBase user;
    private int fadeOut = -1;
    
    public SoundJetpack(EntityLivingBase target) {
        super(target == mc.thePlayer ? SOUND : SOUND_OTHER);
        this.repeat = true;
        this.user = target;
        playingFor.put(target.getEntityId(), this);
    }
    
    public static boolean isPlayingFor(int entityId) {
        return playingFor.containsKey(entityId) && playingFor.get(entityId) != null && !playingFor.get(entityId).donePlaying;
    }
    
    public static void clearPlayingFor() {
        playingFor.clear();
    }
    
    @Override
    public void update() {
        this.xPosF = (float) this.user.posX;
        this.yPosF = (float) this.user.posY;
        this.zPosF = (float) this.user.posZ;
        
        if (this.fadeOut < 0 && !SyncHandler.getJetpackStates().keySet().contains(this.user.getEntityId())) {
            this.fadeOut = 0;
            synchronized (playingFor) {
                playingFor.remove(this.user.getEntityId());
            }
        } else if (this.fadeOut >= 5) {
            this.donePlaying = true;
        } else if (this.fadeOut >= 0) {
            this.volume = 1.0F - this.fadeOut / 5F;
            this.fadeOut++;
        }
    }
    
}
