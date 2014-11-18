package tonius.simplyjetpacks.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelJetpack extends ModelBiped {
    
    public static final ModelJetpack INSTANCE = new ModelJetpack(1.0F);
    
    private ModelRenderer middle;
    private ModelRenderer leftCanister;
    private ModelRenderer rightCanister;
    private ModelRenderer leftTip1;
    private ModelRenderer leftTip2;
    private ModelRenderer rightTip1;
    private ModelRenderer rightTip2;
    private ModelRenderer leftExhaust1;
    private ModelRenderer leftExhaust2;
    private ModelRenderer rightExhaust1;
    private ModelRenderer rightExhaust2;
    
    public ModelJetpack(float scale) {
        super(scale, 0, 64, 64);
        
        this.bipedBody.showModel = true;
        this.bipedRightArm.showModel = true;
        this.bipedLeftArm.showModel = true;
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedRightLeg.showModel = false;
        this.bipedLeftLeg.showModel = false;
        
        this.middle = new ModelRenderer(this, 0, 54).setTextureSize(64, 64);
        this.middle.addBox(-2F, 3F, 3.6F, 4, 5, 2);
        this.middle.setRotationPoint(0F, 0F, 0F);
        this.middle.mirror = true;
        this.setRotation(this.middle, 0F, 0F, 0F);
        
        this.leftCanister = new ModelRenderer(this, 0, 32).setTextureSize(64, 64);
        this.leftCanister.addBox(0.5F, 2F, 2.6F, 4, 7, 4);
        this.leftCanister.setRotationPoint(0F, 0F, 0F);
        this.leftCanister.mirror = true;
        this.setRotation(this.leftCanister, 0F, 0F, 0F);
        
        this.rightCanister = new ModelRenderer(this, 17, 32).setTextureSize(64, 64);
        this.rightCanister.addBox(-4.5F, 2F, 2.6F, 4, 7, 4);
        this.rightCanister.setRotationPoint(0F, 0F, 0F);
        this.rightCanister.mirror = true;
        this.setRotation(this.rightCanister, 0F, 0F, 0F);
        
        this.leftTip1 = new ModelRenderer(this, 0, 45).setTextureSize(64, 64);
        this.leftTip1.addBox(1F, 1F, 3.1F, 3, 1, 3);
        this.leftTip1.setRotationPoint(0F, 0F, 0F);
        this.leftTip1.mirror = true;
        this.setRotation(this.leftTip1, 0F, 0F, 0F);
        
        this.leftTip2 = new ModelRenderer(this, 0, 49).setTextureSize(64, 64);
        this.leftTip2.addBox(1.5F, -1F, 3.6F, 2, 2, 2);
        this.leftTip2.setRotationPoint(0F, 0F, 0F);
        this.leftTip2.mirror = true;
        this.setRotation(this.leftTip2, 0F, 0F, 0F);
        
        this.rightTip1 = new ModelRenderer(this, 17, 45).setTextureSize(64, 64);
        this.rightTip1.addBox(-4F, 1F, 3.1F, 3, 1, 3);
        this.rightTip1.setRotationPoint(0F, 0F, 0F);
        this.rightTip1.mirror = true;
        this.setRotation(this.rightTip1, 0F, 0F, 0F);
        
        this.rightTip2 = new ModelRenderer(this, 17, 49).setTextureSize(64, 64);
        this.rightTip2.addBox(-3.5F, -1F, 3.6F, 2, 2, 2);
        this.rightTip2.setRotationPoint(0F, 0F, 0F);
        this.rightTip2.mirror = true;
        this.setRotation(this.rightTip2, 0F, 0F, 0F);
        
        this.leftExhaust1 = new ModelRenderer(this, 35, 32).setTextureSize(64, 64);
        this.leftExhaust1.addBox(1F, 9F, 3.1F, 3, 1, 3);
        this.leftExhaust1.setRotationPoint(0F, 0F, 0F);
        this.leftExhaust1.mirror = true;
        this.setRotation(this.leftExhaust1, 0F, 0F, 0F);
        
        this.leftExhaust2 = new ModelRenderer(this, 35, 37).setTextureSize(64, 64);
        this.leftExhaust2.addBox(0.5F, 10F, 2.6F, 4, 3, 4);
        this.leftExhaust2.setRotationPoint(0F, 0F, 0F);
        this.leftExhaust2.mirror = true;
        this.setRotation(this.leftExhaust2, 0F, 0F, 0F);
        
        this.rightExhaust1 = new ModelRenderer(this, 48, 32).setTextureSize(64, 64);
        this.rightExhaust1.addBox(-4F, 9F, 3.1F, 3, 1, 3);
        this.rightExhaust1.setRotationPoint(0F, 0F, 0F);
        this.rightExhaust1.mirror = true;
        this.setRotation(this.rightExhaust1, 0F, 0F, 0F);
        
        this.rightExhaust2 = new ModelRenderer(this, 35, 45).setTextureSize(64, 64);
        this.rightExhaust2.addBox(-4.5F, 10F, 2.6F, 4, 3, 4);
        this.rightExhaust2.setRotationPoint(0F, 0F, 0F);
        this.rightExhaust2.mirror = true;
        this.setRotation(this.rightExhaust2, 0F, 0F, 0F);
        
        this.bipedBody.addChild(this.middle);
        this.bipedBody.addChild(this.leftCanister);
        this.bipedBody.addChild(this.rightCanister);
        this.bipedBody.addChild(this.leftTip1);
        this.bipedBody.addChild(this.leftTip2);
        this.bipedBody.addChild(this.rightTip1);
        this.bipedBody.addChild(this.rightTip2);
        this.bipedBody.addChild(this.leftExhaust1);
        this.bipedBody.addChild(this.leftExhaust2);
        this.bipedBody.addChild(this.rightExhaust1);
        this.bipedBody.addChild(this.rightExhaust2);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}
