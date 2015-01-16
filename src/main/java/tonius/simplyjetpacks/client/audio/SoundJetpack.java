package tonius.simplyjetpacks.client.audio;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.SyncTracker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SoundJetpack extends MovingSound {
    
    private static final ResourceLocation SOUND = new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "jetpack");
    public static Set<Integer> playingFor = Collections.synchronizedSet(new HashSet<Integer>());
    
    private EntityLivingBase user;
    private int ticks = 0;
    
    public SoundJetpack(EntityLivingBase target) {
        super(SOUND);
        this.repeat = true;
        this.user = target;
        playingFor.add(target.getEntityId());
    }
    
    @Override
    public void update() {
        if (!SyncTracker.getJetpackStates().keySet().contains(this.user.getEntityId())) {
            this.donePlaying = true;
            synchronized (playingFor) {
                playingFor.remove(this.user.getEntityId());
            }
        }
        
        this.xPosF = (float) this.user.posX;
        this.yPosF = (float) this.user.posY;
        this.zPosF = (float) this.user.posZ;
        
        ticks++;
        this.volume = 1.0F * ticks >= 5 ? 1.0F : ticks / 5F;
    }
    
}
