package tonius.simplyjetpacks.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFluxPack extends ModelBiped {

    ModelRenderer energyCell;

    public ModelFluxPack(float scale) {
        super(scale, 0, 64, 64);

        this.energyCell = new ModelRenderer(this, 0, 32);
        this.energyCell.addBox(-4F, 1F, 2F, 8, 8, 8);
        this.energyCell.setRotationPoint(0F, 0F, 0F);
        this.energyCell.setTextureSize(64, 64);
        this.energyCell.mirror = true;
        this.setRotation(energyCell, 0F, 0F, 0F);
        this.bipedBody.addChild(this.energyCell);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

}
