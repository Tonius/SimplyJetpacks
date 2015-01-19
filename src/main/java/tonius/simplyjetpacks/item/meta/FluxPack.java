package tonius.simplyjetpacks.item.meta;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluxPack extends PackBase {
    
    public FluxPack(int tier, EnumRarity rarity, String defaultConfigKey) {
        super("fluxpack", tier, rarity, defaultConfigKey);
        this.setFuelType(FuelType.ENERGY);
        this.setOutputIsUsage(true);
        this.setArmorModel(PackModelType.FLUX_PACK);
    }
    
    @Override
    public void tickArmor(World world, EntityPlayer player, ItemStack stack, ItemPack item) {
        if (this.isOn(stack)) {
            this.chargeInventory(player, stack, item);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addShiftInformation(ItemStack stack, EntityPlayer player, List list) {
        list.add(StringUtils.getStateText(this.isOn(stack)));
        list.add(StringUtils.getEnergySendText(this.fuelPerTickOut));
        if (this.fuelPerTickIn > 0) {
            list.add(StringUtils.getEnergyReceiveText(this.fuelPerTickIn));
        }
        list.add(StringUtils.getArmoredText(this.isArmored));
        StringUtils.addDescriptionLines(list, "fluxpack", StringUtils.BRIGHT_GREEN);
    }
    
}
