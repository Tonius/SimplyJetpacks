package tonius.simplyjetpacks.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelJetpack extends ModelBiped {
    
    public static final ModelJetpack INSTANCE = new ModelJetpack();
    
    public ModelJetpack() {
        super(1.0F, 0, 64, 64);
        
        this.bipedBody.showModel = true;
        this.bipedRightArm.showModel = true;
        this.bipedLeftArm.showModel = true;
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedRightLeg.showModel = false;
        this.bipedLeftLeg.showModel = false;
        
        ModelRenderer middle = new ModelRenderer(this, 0, 54).setTextureSize(64, 64);
        middle.addBox(-2F, 3F, 3.6F, 4, 5, 2);
        middle.setRotationPoint(0F, 0F, 0F);
        middle.mirror = true;
        this.setRotation(middle, 0F, 0F, 0F);
        
        ModelRenderer leftCanister = new ModelRenderer(this, 0, 32).setTextureSize(64, 64);
        leftCanister.addBox(0.5F, 2F, 2.6F, 4, 7, 4);
        leftCanister.setRotationPoint(0F, 0F, 0F);
        leftCanister.mirror = true;
        this.setRotation(leftCanister, 0F, 0F, 0F);
        
        ModelRenderer rightCanister = new ModelRenderer(this, 17, 32).setTextureSize(64, 64);
        rightCanister.addBox(-4.5F, 2F, 2.6F, 4, 7, 4);
        rightCanister.setRotationPoint(0F, 0F, 0F);
        rightCanister.mirror = true;
        this.setRotation(rightCanister, 0F, 0F, 0F);
        
        ModelRenderer leftTip1 = new ModelRenderer(this, 0, 45).setTextureSize(64, 64);
        leftTip1.addBox(1F, 1F, 3.1F, 3, 1, 3);
        leftTip1.setRotationPoint(0F, 0F, 0F);
        leftTip1.mirror = true;
        this.setRotation(leftTip1, 0F, 0F, 0F);
        
        ModelRenderer leftTip2 = new ModelRenderer(this, 0, 49).setTextureSize(64, 64);
        leftTip2.addBox(1.5F, -1F, 3.6F, 2, 2, 2);
        leftTip2.setRotationPoint(0F, 0F, 0F);
        leftTip2.mirror = true;
        this.setRotation(leftTip2, 0F, 0F, 0F);
        
        ModelRenderer rightTip1 = new ModelRenderer(this, 17, 45).setTextureSize(64, 64);
        rightTip1.addBox(-4F, 1F, 3.1F, 3, 1, 3);
        rightTip1.setRotationPoint(0F, 0F, 0F);
        rightTip1.mirror = true;
        this.setRotation(rightTip1, 0F, 0F, 0F);
        
        ModelRenderer rightTip2 = new ModelRenderer(this, 17, 49).setTextureSize(64, 64);
        rightTip2.addBox(-3.5F, -1F, 3.6F, 2, 2, 2);
        rightTip2.setRotationPoint(0F, 0F, 0F);
        rightTip2.mirror = true;
        this.setRotation(rightTip2, 0F, 0F, 0F);
        
        ModelRenderer leftExhaust1 = new ModelRenderer(this, 35, 32).setTextureSize(64, 64);
        leftExhaust1.addBox(1F, 9F, 3.1F, 3, 1, 3);
        leftExhaust1.setRotationPoint(0F, 0F, 0F);
        leftExhaust1.mirror = true;
        this.setRotation(leftExhaust1, 0F, 0F, 0F);
        
        ModelRenderer leftExhaust2 = new ModelRenderer(this, 35, 37).setTextureSize(64, 64);
        leftExhaust2.addBox(0.5F, 10F, 2.6F, 4, 3, 4);
        leftExhaust2.setRotationPoint(0F, 0F, 0F);
        leftExhaust2.mirror = true;
        this.setRotation(leftExhaust2, 0F, 0F, 0F);
        
        ModelRenderer rightExhaust1 = new ModelRenderer(this, 48, 32).setTextureSize(64, 64);
        rightExhaust1.addBox(-4F, 9F, 3.1F, 3, 1, 3);
        rightExhaust1.setRotationPoint(0F, 0F, 0F);
        rightExhaust1.mirror = true;
        this.setRotation(rightExhaust1, 0F, 0F, 0F);
        
        ModelRenderer rightExhaust2 = new ModelRenderer(this, 35, 45).setTextureSize(64, 64);
        rightExhaust2.addBox(-4.5F, 10F, 2.6F, 4, 3, 4);
        rightExhaust2.setRotationPoint(0F, 0F, 0F);
        rightExhaust2.mirror = true;
        this.setRotation(rightExhaust2, 0F, 0F, 0F);
        
        this.bipedBody.addChild(middle);
        this.bipedBody.addChild(leftCanister);
        this.bipedBody.addChild(rightCanister);
        this.bipedBody.addChild(leftTip1);
        this.bipedBody.addChild(leftTip2);
        this.bipedBody.addChild(rightTip1);
        this.bipedBody.addChild(rightTip2);
        this.bipedBody.addChild(leftExhaust1);
        this.bipedBody.addChild(leftExhaust2);
        this.bipedBody.addChild(rightExhaust1);
        this.bipedBody.addChild(rightExhaust2);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}
