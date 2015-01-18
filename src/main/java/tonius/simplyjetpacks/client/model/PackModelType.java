package tonius.simplyjetpacks.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public enum PackModelType {
    
    FLAT(null),
    JETPACK(ModelJetpack.INSTANCE),
    FLUX_PACK(ModelFluxPack.INSTANCE);
    
    private final ModelBiped model;
    
    private PackModelType(ModelBiped model) {
        this.model = model;
    }
    
    public ModelBiped get(EntityLivingBase entity) {
        if (this.model == null) {
            return null;
        }
        this.model.isSneak = entity.isSneaking();
        this.model.isRiding = entity.isRiding();
        this.model.isChild = entity.isChild();
        this.model.heldItemRight = entity.getEquipmentInSlot(0) != null ? 1 : 0;
        if (entity instanceof EntityPlayer) {
            this.model.aimedBow = ((EntityPlayer) entity).getItemInUseDuration() > 2;
        }
        return this.model;
    }
    
}
