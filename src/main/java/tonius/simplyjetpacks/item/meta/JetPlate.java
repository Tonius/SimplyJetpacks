package tonius.simplyjetpacks.item.meta;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.integration.ModType;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.setup.ModKey;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class JetPlate extends Jetpack {
    
    protected static final String TAG_CHARGER_ON = "JetPlateChargerOn";
    protected static final String TAG_ENDERIUM_UPGRADE = "JetPlateEnderiumUpgrade";
    
    public IIcon iconEnderium;
    
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
    
    @Override
    public void toggleSecondary(ItemStack stack, EntityPlayer player, boolean showInChat) {
        this.toggleCharger(stack, player, showInChat);
    }
    
    public boolean isChargerOn(ItemStack stack) {
        return NBTHelper.getNBTBoolean(stack, TAG_CHARGER_ON, true);
    }
    
    public void toggleCharger(ItemStack stack, EntityPlayer player, boolean showInChat) {
        this.toggleState(this.isChargerOn(stack), stack, "jetplate.charger", TAG_CHARGER_ON, player, showInChat);
    }
    
    public boolean hasEnderiumUpgrade(ItemStack stack) {
        return NBTHelper.getNBTBoolean(stack, TAG_ENDERIUM_UPGRADE, false);
    }
    
    public void setEnderiumUpgrade(ItemStack stack, boolean enderUpgrade) {
        NBTHelper.getNBT(stack).setBoolean(TAG_ENDERIUM_UPGRADE, enderUpgrade);
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
    public void registerIcons(IIconRegister register, ModType modType) {
        super.registerIcons(register, modType);
        if (modType != ModType.THERMAL_EXPANSION || !ModType.REDSTONE_ARMORY.loaded) {
            return;
        }
        this.iconEnderium = register.registerIcon(SimplyJetpacks.RESOURCE_PREFIX + this.getBaseName(true) + modType.suffix + ".enderium");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack) {
        if (this.iconEnderium != null && this.hasEnderiumUpgrade(stack)) {
            return this.iconEnderium;
        }
        return super.getIcon(stack);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, ModType modType) {
        if (modType != ModType.THERMAL_EXPANSION || !ModType.REDSTONE_ARMORY.loaded) {
            return super.getArmorTexture(stack, entity, slot, modType);
        }
        String flat = Config.enableArmor3DModels || this.armorModel == PackModelType.FLAT ? "" : ".flat";
        String enderium = this.hasEnderiumUpgrade(stack) ? ".enderium" : "";
        return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + this.getBaseName(true) + modType.suffix + enderium + flat + ".png";
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, ItemPack item, EntityPlayer player, List list) {
        super.addInformation(stack, item, player, list);
        if (StringHelper.isControlKeyDown()) {
            list.add(StringHelper.GRAY + SJStringHelper.localize("tooltip.jetplate.dank"));
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
    @SuppressWarnings("unchecked")
    public void addSubItems(ItemPack item, int meta, List list) {
        super.addSubItems(item, meta, list);
        if (!this.showInCreativeTab || item.modType != ModType.THERMAL_EXPANSION || !ModType.REDSTONE_ARMORY.loaded) {
            return;
        }
        ItemStack stack;
        if (this.showEmptyInCreativeTab) {
            stack = new ItemStack(item, 1, meta);
            this.setEnderiumUpgrade(stack, true);
            list.add(stack);
        }
        stack = new ItemStack(item, 1, meta);
        this.setEnderiumUpgrade(stack, true);
        item.addFuel(stack, item.getMaxFuelStored(stack), false);
        list.add(stack);
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
