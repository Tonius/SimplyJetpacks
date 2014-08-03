package tonius.simplyjetpacks.item.fluxpack;

import net.minecraft.item.EnumRarity;

public class FluxPackCreative extends FluxPack {

    public FluxPackCreative(int meta, boolean enchantable, int energyPerTick) {
        super(meta, 9001, enchantable, EnumRarity.epic, 9001, energyPerTick, false);
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

}
