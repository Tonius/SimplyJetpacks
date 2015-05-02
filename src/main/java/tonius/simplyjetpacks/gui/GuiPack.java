package tonius.simplyjetpacks.gui;

import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.gui.element.ElementEnergyStoredAdv;
import tonius.simplyjetpacks.gui.element.ElementFluidTankAdv;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageModKey;
import tonius.simplyjetpacks.setup.ModKey;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementButton;

public class GuiPack extends GuiBase {
    
    private final ContainerPack container;
    
    public GuiPack(ContainerPack container) {
        super(container, new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "textures/gui/pack.png"));
        this.container = container;
        String unlocalizedTitle = "item." + SimplyJetpacks.PREFIX + container.pack.getBaseName(false) + container.packItem.modType.suffix + ".name";
        this.name = SJStringHelper.localize(unlocalizedTitle, false, (Object[]) null);
        this.drawInventory = false;
        this.width = 176;
        this.height = 100;
    }
    
    @Override
    public void initGui() {
        super.initGui();
        
        if (this.container.energyStored != null) {
            this.addElement(new ElementEnergyStoredAdv(this, 12, 28, this.container.energyStored));
        } else if (this.container.fluidStored != null) {
            this.addElement(new ElementFluidTankAdv(this, 12, 24, this.container.fluidStored).setAlwaysShow(true).setGauge(1));
        }
        
        ModKey[] controls = this.container.pack.getGuiControls();
        int startX = 76 + (4 - controls.length) * 22;
        for (int i = 0; i < controls.length; i++) {
            ModKey key = controls[i];
            ElementButton button = new ElementButton(this, startX + i * 22, 40, key.name, key.sheetX, key.sheetY, key.hoverX, key.hoverY, key.disabledX, key.disabledY, 20, 20, this.texture.toString());
            button.setToolTip(SJStringHelper.localize(String.format("%s.button.%s", this.container.pack.getGuiTitlePrefix(), key.name))).setToolTipLocalized(true);
            this.addElement(button);
        }
    }
    
    @Override
    public void handleElementButtonClick(String buttonName, int mouseButton) {
        GuiBase.playSound("random.click", 0.5F, 1.0F);
        if (buttonName.equals(ModKey.TOGGLE_PRIMARY.name)) {
            PacketHandler.instance.sendToServer(new MessageModKey(ModKey.TOGGLE_PRIMARY, true));
            this.container.pack.togglePrimary(this.container.chestplate, this.mc.thePlayer, false);
            
        } else if (buttonName.equals(ModKey.TOGGLE_SECONDARY.name)) {
            PacketHandler.instance.sendToServer(new MessageModKey(ModKey.TOGGLE_SECONDARY, true));
            this.container.pack.toggleSecondary(this.container.chestplate, this.mc.thePlayer, false);
            
        } else if (buttonName.equals(ModKey.MODE_PRIMARY.name)) {
            PacketHandler.instance.sendToServer(new MessageModKey(ModKey.MODE_PRIMARY, true));
            this.container.pack.switchModePrimary(this.container.chestplate, this.mc.thePlayer, false);
            
        } else if (buttonName.equals(ModKey.MODE_SECONDARY.name)) {
            PacketHandler.instance.sendToServer(new MessageModKey(ModKey.MODE_SECONDARY, true));
            this.container.pack.switchModeSecondary(this.container.chestplate, this.mc.thePlayer, false);
        }
    }
    
}
