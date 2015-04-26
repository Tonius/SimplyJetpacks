package tonius.simplyjetpacks.item.meta;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.setup.ModKey;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class JetPlate extends Jetpack {
    
    protected static final String TAG_CHARGER_ON = "JetPlateChargerOn";
    
    public JetPlate(int tier, EnumRarity rarity, String defaultConfigKey) {
        super(tier, rarity, defaultConfigKey);
        this.setIsArmored(true);
        this.setShowArmored(false);
    }
    
    @Override
    public void tickArmor(World world, EntityPlayer player, ItemStack stack, ItemPack item) {
        super.tickArmor(world, player, stack, item);
        if (this.isChargerOn(stack)) {
            this.chargeInventory(player, stack, item);
        }
    }
    
    public boolean isChargerOn(ItemStack stack) {
        return NBTHelper.getNBTBoolean(stack, TAG_CHARGER_ON, true);
    }
    
    public void toggleCharger(ItemStack stack, EntityPlayer player, boolean showInChat) {
        this.toggleState(this.isChargerOn(stack), stack, "jetplate.charger", TAG_CHARGER_ON, player, showInChat);
    }
    
    @Override
    public void toggleSecondary(ItemStack stack, EntityPlayer player, boolean showInChat) {
        this.toggleCharger(stack, player, showInChat);
    }
    
    @Override
    public ModKey[] getGuiControls() {
        if (this.emergencyHoverMode) {
            return new ModKey[] { ModKey.TOGGLE_PRIMARY, ModKey.MODE_PRIMARY, ModKey.TOGGLE_SECONDARY, ModKey.MODE_SECONDARY };
        } else {
            return new ModKey[] { ModKey.TOGGLE_PRIMARY, ModKey.MODE_PRIMARY, ModKey.TOGGLE_SECONDARY };
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addShiftInformation(ItemStack stack, ItemPack item, EntityPlayer player, List list) {
        list.add(SJStringHelper.getStateText(this.isOn(stack)));
        list.add(SJStringHelper.getHoverModeText(this.isHoverModeOn(stack)));
        list.add(SJStringHelper.getChargerStateText(this.isChargerOn(stack)));
        if (this.fuelUsage > 0) {
            list.add(SJStringHelper.getFuelUsageText(this.fuelType, this.fuelUsage));
        }
        list.add(SJStringHelper.getChargerRateText(this.fuelPerTickOut));
        list.add(SJStringHelper.getParticlesText(this.getParticleType(stack)));
        SJStringHelper.addDescriptionLines(list, "jetplate", StringHelper.BRIGHT_GREEN);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public String getHUDStatesInfo(ItemStack stack, ItemPack item) {
        Boolean engine = this.isOn(stack);
        Boolean hover = this.isHoverModeOn(stack);
        Boolean charger = this.isChargerOn(stack);
        return SJStringHelper.getHUDStateText(engine, hover, charger);
    }
    
}
