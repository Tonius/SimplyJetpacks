package tonius.simplyjetpacks.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelFluxPack extends ModelBiped {
    
    public static final ModelFluxPack INSTANCE = new ModelFluxPack(1.0F);
    
    private ModelRenderer energyCell;
    
    public ModelFluxPack(float scale) {
        super(scale, 0, 64, 64);
        
        this.bipedBody.showModel = true;
        this.bipedRightArm.showModel = true;
        this.bipedLeftArm.showModel = true;
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedRightLeg.showModel = false;
        this.bipedLeftLeg.showModel = false;
        
        this.energyCell = new ModelRenderer(this, 0, 32).setTextureSize(64, 64);
        this.energyCell.addBox(-4F, 1.5F, 2F, 8, 8, 8);
        this.energyCell.setRotationPoint(0F, 0F, 0F);
        this.energyCell.mirror = true;
        this.setRotation(this.energyCell, 0F, 0F, 0F);
        
        this.bipedBody.addChild(this.energyCell);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}
