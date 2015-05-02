package tonius.simplyjetpacks.gui.element;

import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import cofh.api.energy.IEnergyStorage;
import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementEnergyStored;

public class ElementEnergyStoredAdv extends ElementEnergyStored {
    
    public ElementEnergyStoredAdv(GuiBase gui, int posX, int posY, IEnergyStorage storage) {
        super(gui, posX, posY, storage);
        this.texture = new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "textures/gui/elements/energy.png");
    }
    
}
