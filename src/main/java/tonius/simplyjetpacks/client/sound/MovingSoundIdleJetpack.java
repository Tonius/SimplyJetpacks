package tonius.simplyjetpacks.client.sound;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.SyncTracker;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;

@SideOnly(Side.CLIENT)
public class MovingSoundIdleJetpack extends MovingSound {

    private static ResourceLocation       location   = new ResourceLocation((SimplyJetpacks.RESOURCE_PREFIX + "jetpack_flight_idle"));
    public static  List<EntityLivingBase> playingFor = new ArrayList<EntityLivingBase>();

    private EntityLivingBase user;
    private int left = 126;

    public MovingSoundIdleJetpack(EntityLivingBase target) {
        super(location);
        playingFor.add(user);
        this.user = target;
    }

    @Override
    public void update() {
        ItemStack armor = user.getEquipmentInSlot(3);
        Jetpack jet = ((ItemJetpack)armor.getItem()).getJetpack(armor);
        if(this.user.isDead || this.left-- == 0 && jet.isOn(armor) && (SyncTracker.isFlyKeyDown(this.user) || jet.isHoverModeOn(armor) && !this.user.onGround)) {
            this.donePlaying = true;
            playingFor.remove(this.user);
        }

        this.xPosF = (float)this.user.posX;
        this.yPosF = (float)this.user.posY;
        this.zPosF = (float)this.user.posZ;
    }

}
