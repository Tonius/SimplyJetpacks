package tonius.simplyjetpacks.item.meta;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluxPack extends PackBase {
    
    public FluxPack(int tier, EnumRarity rarity, String defaultConfigKey) {
        super("fluxpack", tier, rarity, defaultConfigKey);
        this.setFuelType(FuelType.ENERGY);
        this.setArmorModel(PackModelType.FLUX_PACK);
    }
    
    @Override
    public void tickArmor(World world, EntityPlayer player, ItemStack stack, ItemPack item) {
        if (this.isOn(stack)) {
            this.chargeInventory(player, stack, item);
        }
    }
    
    @Override
    public String getGuiTitlePrefix() {
        return "gui.fluxpack";
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addShiftInformation(ItemStack stack, ItemPack item, EntityPlayer player, List list) {
        list.add(SJStringHelper.getStateText(this.isOn(stack)));
        list.add(SJStringHelper.getEnergySendText(this.fuelPerTickOut));
        if (this.fuelPerTickIn > 0) {
            list.add(SJStringHelper.getEnergyReceiveText(this.fuelPerTickIn));
        }
        SJStringHelper.addDescriptionLines(list, "fluxpack", StringHelper.BRIGHT_GREEN);
        String key = SimplyJetpacks.proxy.getPackGUIKey();
        if (key != null) {
            list.add(SJStringHelper.getPackGUIText(key));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public String getHUDStatesInfo(ItemStack stack, ItemPack item) {
        return SJStringHelper.getHUDStateText(null, null, this.isOn(stack));
    }
    
}
