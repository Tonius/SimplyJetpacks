package tonius.simplyjetpacks.item.meta;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.config.PackDefaults;
import tonius.simplyjetpacks.integration.ModType;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModKey;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PackBase {
    
    protected static final Set<PackBase> ALL_PACKS = new LinkedHashSet<PackBase>();
    protected static final String TAG_ON = "PackOn";
    
    public final String name;
    public final int tier;
    public final EnumRarity rarity;
    protected final PackDefaults defaults;
    
    public int fuelCapacity = 1;
    public int fuelUsage = 0;
    public int fuelPerTickIn = 0;
    public int fuelPerTickOut = 0;
    public int armorReduction = 0;
    public int armorFuelPerHit = 0;
    public int enchantability = 0;
    
    public IIcon icon;
    public FuelType fuelType = FuelType.ENERGY;
    public String fuelFluid = null;
    public PackModelType armorModel = PackModelType.FLAT;
    public boolean showInCreativeTab = true;
    public boolean showEmptyInCreativeTab = true;
    public boolean usesFuel = true;
    public boolean hasFuelIndicator = true;
    public boolean hasStateIndicators = true;
    public boolean isArmored = false;
    public boolean showArmored = true;
    public boolean showTier = true;
    public Integer platingMeta = null;
    public boolean isFluxBased = false;
    
    public PackBase(String name, int tier, EnumRarity rarity, String defaultConfigKey) {
        this.name = name;
        this.tier = tier;
        this.rarity = rarity;
        this.defaults = PackDefaults.get(defaultConfigKey);
        if (this.defaults == null) {
            throw new IllegalArgumentException("No PackDefaults instance found for key " + defaultConfigKey);
        }
        
        ALL_PACKS.add(this);
    }
    
    public PackBase(int tier, EnumRarity rarity, String defaultConfigKey) {
        this("pack", tier, rarity, defaultConfigKey);
    }
    
    public static void loadAllConfigs(Configuration config) {
        for (PackBase pack : ALL_PACKS) {
            pack.loadConfig(config);
        }
    }
    
    public static void writeAllConfigsToNBT(NBTTagCompound tag) {
        for (PackBase pack : ALL_PACKS) {
            NBTTagCompound packTag = new NBTTagCompound();
            pack.writeConfigToNBT(packTag);
            tag.setTag(pack.defaults.section.id, packTag);
        }
    }
    
    public static void readAllConfigsFromNBT(NBTTagCompound tag) {
        for (PackBase pack : ALL_PACKS) {
            NBTTagCompound packTag = tag.getCompoundTag(pack.defaults.section.id);
            pack.readConfigFromNBT(packTag);
        }
    }
    
    public PackBase setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
        return this;
    }
    
    public PackBase setFuelFluid(String fuelFluid) {
        this.setFuelType(FuelType.FLUID);
        this.fuelFluid = fuelFluid;
        return this;
    }
    
    public PackBase setArmorModel(PackModelType armorModel) {
        this.armorModel = armorModel;
        return this;
    }
    
    public PackBase setShowInCreativeTab(boolean showInCreativeTab) {
        this.showInCreativeTab = showInCreativeTab;
        return this;
    }
    
    public PackBase setShowEmptyInCreativeTab(boolean showEmptyInCreativeTab) {
        this.showEmptyInCreativeTab = showEmptyInCreativeTab;
        return this;
    }
    
    public PackBase setUsesFuel(boolean usesFuel) {
        this.usesFuel = usesFuel;
        return this;
    }
    
    public PackBase setHasFuelIndicator(boolean hasFuelIndicator) {
        this.hasFuelIndicator = hasFuelIndicator;
        return this;
    }
    
    public PackBase setHasStateIndicators(boolean hasStateIndicators) {
        this.hasStateIndicators = hasStateIndicators;
        return this;
    }
    
    public PackBase setIsArmored(boolean isArmored) {
        this.isArmored = isArmored;
        return this;
    }
    
    public PackBase setShowArmored(boolean showArmored) {
        this.showArmored = showArmored;
        return this;
    }
    
    public PackBase setShowTier(boolean showTier) {
        this.showTier = showTier;
        return this;
    }
    
    public PackBase setPlatingMeta(int platingMeta) {
        this.platingMeta = platingMeta;
        return this;
    }
    
    public PackBase setFluxBased(boolean fluxBased) {
        this.isFluxBased = fluxBased;
        return this;
    }
    
    public void tickInventory(World world, EntityPlayer player, ItemStack stack, ItemPack item) {
    }
    
    public void tickArmor(World world, EntityPlayer player, ItemStack stack, ItemPack item) {
    }
    
    protected void chargeInventory(EntityLivingBase user, ItemStack stack, ItemPack item) {
        if (this.fuelType == FuelType.ENERGY) {
            for (int i = 0; i <= 4; i++) {
                ItemStack currentStack = user.getEquipmentInSlot(i);
                if (currentStack != null && currentStack != stack && currentStack.getItem() instanceof IEnergyContainerItem) {
                    IEnergyContainerItem heldEnergyItem = (IEnergyContainerItem) currentStack.getItem();
                    if (this.usesFuel) {
                        int energyToAdd = Math.min(item.useFuel(stack, this.fuelPerTickOut, true), heldEnergyItem.receiveEnergy(currentStack, this.fuelPerTickOut, true));
                        item.useFuel(stack, energyToAdd, false);
                        heldEnergyItem.receiveEnergy(currentStack, energyToAdd, false);
                    } else {
                        heldEnergyItem.receiveEnergy(currentStack, this.fuelPerTickOut, false);
                    }
                }
            }
        }
    }
    
    public String getBaseName(boolean armoredInfo) {
        return this.name + "." + this.tier + (armoredInfo && this.isArmored && this.showArmored ? ".armored" : "");
    }
    
    protected void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showInChat) {
        stack.stackTagCompound.setBoolean(tag, !on);
        
        if (player != null && showInChat) {
            String color = on ? StringHelper.LIGHT_RED : StringHelper.BRIGHT_GREEN;
            type = type != null && !type.equals("") ? "chat." + this.name + "." + type + ".on" : "chat." + this.name + ".on";
            String msg = SJStringHelper.localize(type) + " " + color + SJStringHelper.localize("chat." + (on ? "disabled" : "enabled"));
            player.addChatMessage(new ChatComponentText(msg));
        }
    }
    
    public boolean isOn(ItemStack stack) {
        return NBTHelper.getNBTBoolean(stack, TAG_ON, true);
    }
    
    public void togglePrimary(ItemStack stack, EntityPlayer player, boolean showInChat) {
        this.toggleState(this.isOn(stack), stack, null, TAG_ON, player, showInChat);
    }
    
    public void toggleSecondary(ItemStack stack, EntityPlayer player, boolean showInChat) {
    }
    
    public void switchModePrimary(ItemStack stack, EntityPlayer player, boolean showInChat) {
    }
    
    public void switchModeSecondary(ItemStack stack, EntityPlayer player, boolean showInChat) {
    }
    
    public String getGuiTitlePrefix() {
        return "gui.pack";
    }
    
    public ModKey[] getGuiControls() {
        return new ModKey[] { ModKey.TOGGLE_PRIMARY };
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register, ModType modType) {
        this.icon = register.registerIcon(SimplyJetpacks.RESOURCE_PREFIX + this.getBaseName(true) + modType.suffix);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack) {
        if (this.icon != null) {
            return this.icon;
        }
        return null;
    }
    
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, ModType modType) {
        String flat = Config.enableArmor3DModels || this.armorModel == PackModelType.FLAT ? "" : ".flat";
        return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + this.getBaseName(true) + modType.suffix + flat + ".png";
    }
    
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, ItemPack item, EntityPlayer player, List list) {
        if (this.showTier) {
            list.add(SJStringHelper.getTierText(this.tier));
        }
        list.add(SJStringHelper.getFuelText(this.fuelType, item.getFuelStored(stack), this.fuelCapacity, !this.usesFuel));
    }
    
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addShiftInformation(ItemStack stack, ItemPack item, EntityPlayer player, List list) {
        list.add(SJStringHelper.getStateText(this.isOn(stack)));
    }
    
    @SideOnly(Side.CLIENT)
    public String getHUDFuelInfo(ItemStack stack, ItemPack item) {
        int fuel = item.getFuelStored(stack);
        int maxFuel = item.getMaxFuelStored(stack);
        int percent = (int) Math.ceil((double) fuel / (double) maxFuel * 100D);
        return SJStringHelper.getHUDFuelText(this.name, percent, this.fuelType, fuel);
    }
    
    @SideOnly(Side.CLIENT)
    public String getHUDStatesInfo(ItemStack stack, ItemPack item) {
        return null;
    }
    
    protected void loadConfig(Configuration config) {
        if (this.defaults.fuelCapacity != null) {
            this.fuelCapacity = config.get(this.defaults.section.name, "Fuel Capacity", this.defaults.fuelCapacity, "The maximum amount of fuel that this pack can hold.").setMinValue(1).getInt(this.defaults.fuelCapacity);
        }
        if (this.defaults.fuelUsage != null) {
            this.fuelUsage = config.get(this.defaults.section.name, "Fuel Usage", this.defaults.fuelUsage, "The amount of fuel that this pack uses every tick when used.").setMinValue(0).getInt(this.defaults.fuelUsage);
        }
        if (this.defaults.fuelPerTickIn != null) {
            this.fuelPerTickIn = config.get(this.defaults.section.name, "Fuel Per Tick In", this.defaults.fuelPerTickIn, "The amount of fuel that can be inserted into this pack per tick from external sources.").setMinValue(0).getInt(this.defaults.fuelPerTickIn);
        }
        if (this.defaults.fuelPerTickOut != null) {
            this.fuelPerTickOut = config.get(this.defaults.section.name, "Fuel Per Tick Out", this.defaults.fuelPerTickOut, "The amount of fuel that can be extracted from this pack per tick by external sources. Also determines how quickly Flux Packs can charge other items.").setMinValue(0).getInt(this.defaults.fuelPerTickOut);
        }
        if (this.defaults.armorReduction != null) {
            this.armorReduction = config.get(this.defaults.section.name, "Armor Reduction", this.defaults.armorReduction, "How well this pack can protect the user from damage, if armored. The higher the value, the stronger the armor will be.").setMinValue(0).getInt(this.defaults.armorReduction);
        }
        if (this.defaults.armorFuelPerHit != null) {
            this.armorFuelPerHit = config.get(this.defaults.section.name, "Armor Fuel Per Hit", this.defaults.armorFuelPerHit, "How much fuel is lost from this pack when the user is hit, if armored.").setMinValue(0).getInt(this.defaults.armorFuelPerHit);
        }
        if (this.defaults.enchantability != null) {
            this.enchantability = config.get(this.defaults.section.name, "Enchantability", this.defaults.enchantability, "The enchantability of this pack. If set to 0, no enchantments can be applied.").setMinValue(0).getInt(this.defaults.enchantability);
        }
    }
    
    protected void writeConfigToNBT(NBTTagCompound tag) {
        if (this.defaults.fuelCapacity != null) {
            tag.setInteger("FuelCapacity", this.fuelCapacity);
        }
        if (this.defaults.fuelUsage != null) {
            tag.setInteger("FuelUsage", this.fuelUsage);
        }
        if (this.defaults.fuelPerTickIn != null) {
            tag.setInteger("FuelPerTickIn", this.fuelPerTickIn);
        }
        if (this.defaults.fuelPerTickOut != null) {
            tag.setInteger("FuelPerTickOut", this.fuelPerTickOut);
        }
        if (this.defaults.armorReduction != null) {
            tag.setInteger("ArmorReduction", this.armorReduction);
        }
    }
    
    protected void readConfigFromNBT(NBTTagCompound tag) {
        if (this.defaults.fuelCapacity != null) {
            this.fuelCapacity = tag.getInteger("FuelCapacity");
        }
        if (this.defaults.fuelUsage != null) {
            this.fuelUsage = tag.getInteger("FuelUsage");
        }
        if (this.defaults.fuelPerTickIn != null) {
            this.fuelPerTickIn = tag.getInteger("FuelPerTickIn");
        }
        if (this.defaults.fuelPerTickOut != null) {
            this.fuelPerTickOut = tag.getInteger("FuelPerTickOut");
        }
        if (this.defaults.armorReduction != null) {
            this.armorReduction = tag.getInteger("ArmorReduction");
        }
    }
    
}
