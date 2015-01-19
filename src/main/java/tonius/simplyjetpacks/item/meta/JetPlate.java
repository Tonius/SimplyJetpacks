package tonius.simplyjetpacks.item.meta;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
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
        return StackUtils.getNBTBoolean(stack, TAG_CHARGER_ON, true);
    }
    
    public void toggleCharger(ItemStack stack, EntityPlayer player, boolean showInChat) {
        this.toggleState(this.isChargerOn(stack), stack, "jetplate.charger", TAG_CHARGER_ON, player, showInChat);
    }
    
    @Override
    public void toggleOn(ItemStack stack, EntityPlayer player, boolean sneakChangesToggleBehavior, boolean showInChat) {
        if (sneakChangesToggleBehavior && player.isSneaking()) {
            this.toggleCharger(stack, player, showInChat);
        } else {
            super.toggleOn(stack, player, sneakChangesToggleBehavior, showInChat);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addShiftInformation(ItemStack stack, ItemPack item, EntityPlayer player, List list) {
        list.add(StringUtils.getStateText(this.isOn(stack)));
        list.add(StringUtils.getHoverModeText(this.isHoverModeOn(stack)));
        list.add(StringUtils.getChargerStateText(this.isChargerOn(stack)));
        if (this.fuelUsage > 0) {
            list.add(StringUtils.getFuelUsageText(this.fuelType, this.fuelUsage));
        }
        list.add(StringUtils.getChargerRateText(this.fuelPerTickOut));
        list.add(StringUtils.getParticlesText(this.getParticleType(stack)));
        StringUtils.addDescriptionLines(list, "jetplate", StringUtils.BRIGHT_GREEN);
        list.add(StringUtils.BRIGHT_BLUE + StringUtils.ITALIC + StringUtils.translate("tooltip.jetplate.controls" + (Config.sneakChangesToggleBehavior ? "" : ".command")));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public String getHUDStatesInfo(ItemStack stack, ItemPack item) {
        Boolean engine = this.isOn(stack);
        Boolean hover = this.isHoverModeOn(stack);
        Boolean charger = this.isChargerOn(stack);
        return StringUtils.getHUDStateText(engine, hover, charger);
    }
    
}
