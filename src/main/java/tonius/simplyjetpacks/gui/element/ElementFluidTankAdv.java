package tonius.simplyjetpacks.gui.element;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import tonius.simplyjetpacks.SimplyJetpacks;
import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementFluidTank;
import cofh.lib.render.RenderHelper;

public class ElementFluidTankAdv extends ElementFluidTank {
    
    public ElementFluidTankAdv(GuiBase gui, int posX, int posY, IFluidTank tank) {
        super(gui, posX, posY, tank);
        this.texture = new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "textures/gui/elements/fluidTank.png");
    }
    
    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks) {
        RenderHelper.bindTexture(this.texture);
        this.drawTexturedModalRect(this.posX - 1, this.posY - 1, 0, 0, this.sizeX + 2, this.sizeY + 2);
        super.drawBackground(mouseX, mouseY, gameTicks);
    }
    
}
