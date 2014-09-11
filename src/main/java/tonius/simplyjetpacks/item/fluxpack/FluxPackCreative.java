package tonius.simplyjetpacks.item.fluxpack;

import net.minecraft.item.EnumRarity;

public class FluxPackCreative extends FluxPackArmored {
    
    public FluxPackCreative(int meta, boolean enchantable, int enchantability, int energyPerTickOut, int armorDisplay, double armorAbsorption) {
        super(meta, 9001, enchantable, enchantability, EnumRarity.epic, 9001, Integer.MAX_VALUE, energyPerTickOut, armorDisplay, armorAbsorption, 0);
    }
    
    @Override
    public String getBaseName() {
        return "fluxpack.creative";
    }
    
    @Override
    public boolean hasEmptyItem() {
        return false;
    }
    
    @Override
    public boolean hasDamageBar() {
        return false;
    }
    
    @Override
    public boolean hasArmoredVersion() {
        return false;
    }
    
    @Override
    public boolean consumesEnergy() {
        return false;
    }
    
}
