package tonius.simplyjetpacks.item;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.GuiHandler;
import tonius.simplyjetpacks.integration.ModType;
import tonius.simplyjetpacks.item.meta.FluxPack;
import tonius.simplyjetpacks.item.meta.Jetpack;
import tonius.simplyjetpacks.item.meta.PackBase;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModKey;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPack<T extends PackBase> extends ItemArmor implements IControllableArmor, ISpecialArmor, IEnergyContainerItem, IFluidContainerItem, IHUDInfoProvider {
    
    private static final String TAG_ENERGY = "Energy";
    private static final String TAG_FLUID = "Fluid";
    public final ModType modType;
    private final Map<Integer, T> packs = new LinkedHashMap<Integer, T>();
    
    public ItemPack(ModType modType) {
        super(ArmorMaterial.IRON, 2, 1);
        this.modType = modType;
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + "pack" + modType.suffix);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(ModCreativeTab.instance);
    }
    
    public ItemStack putPack(int meta, T pack, boolean returnFull) {
        this.packs.put(meta, pack);
        ItemStack stack = new ItemStack(this, 1, meta);
        if (returnFull) {
            this.addFuel(stack, this.getMaxFuelStored(stack), false);
        }
        return stack;
    }
    
    public ItemStack putPack(int meta, T pack) {
        return this.putPack(meta, pack, false);
    }
    
    public T getPack(ItemStack stack) {
        return this.packs.get(stack.getItemDamage());
    }
    
    public Collection<T> getPacks() {
        return this.packs.values();
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        T pack = this.getPack(stack);
        if (pack != null && entity instanceof EntityPlayer) {
            pack.tickInventory(world, (EntityPlayer) entity, stack, this);
        }
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        T pack = this.getPack(stack);
        if (pack != null) {
            pack.tickArmor(world, player, stack, this);
        }
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        T pack = this.getPack(stack);
        if (pack != null) {
            return "item." + SimplyJetpacks.PREFIX + pack.getBaseName(true) + this.modType.suffix;
        }
        return super.getUnlocalizedName();
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        T pack = this.getPack(stack);
        if (pack != null) {
            return pack.rarity;
        }
        return super.getRarity(stack);
    }
    
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        T pack = this.getPack(stack);
        if (pack != null) {
            return pack.hasFuelIndicator;
        }
        return super.showDurabilityBar(stack);
    }
    
    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        double stored = this.getMaxFuelStored(stack) - this.getFuelStored(stack) + 1;
        double max = this.getMaxFuelStored(stack) + 1;
        return stored / max;
    }
    
    @Override
    public int getItemEnchantability(ItemStack stack) {
        T pack = this.getPack(stack);
        if (pack != null) {
            return pack.enchantability;
        }
        return super.getItemEnchantability(stack);
    }
    
    @Override
    public boolean isItemTool(ItemStack stack) {
        return this.getItemEnchantability() > 0;
    }
    
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return this.getItemEnchantability() > 0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        T pack = this.getPack(stack);
        if (pack != null) {
            pack.addInformation(stack, this, player, list);
            if (SJStringHelper.canShowDetails()) {
                pack.addShiftInformation(stack, this, player, list);
            } else {
                list.add(SJStringHelper.getShiftText());
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (Entry<Integer, T> e : this.packs.entrySet()) {
            e.getValue().addSubItems(this, e.getKey(), list);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        for (T pack : this.packs.values()) {
            pack.registerIcons(register, this.modType);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        T pack = this.packs.get(stack.getItemDamage());
        if (pack != null) {
            IIcon icon = pack.getIcon(stack);
            if (icon != null) {
                return icon;
            }
        }
        return super.getIcon(stack, pass);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        T pack = this.getPack(stack);
        if (pack != null) {
            return pack.getArmorTexture(stack, entity, slot, this.modType);
        }
        return super.getArmorTexture(stack, entity, slot, type);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
        T pack = this.getPack(stack);
        if (pack != null && pack.armorModel != null && Config.enableArmor3DModels) {
            ModelBiped model = RenderUtils.getArmorModel(pack, entityLiving);
            if (model != null) {
                return model;
            }
        }
        return super.getArmorModel(entityLiving, stack, armorSlot);
    }
    
    // control
    @Override
    public void onKeyPressed(ItemStack stack, EntityPlayer player, ModKey key, boolean showInChat) {
        T pack = this.getPack(stack);
        if (pack != null) {
            switch (key) {
            case TOGGLE_PRIMARY:
                pack.togglePrimary(stack, player, showInChat);
                break;
            case TOGGLE_SECONDARY:
                pack.toggleSecondary(stack, player, showInChat);
                break;
            case MODE_PRIMARY:
                pack.switchModePrimary(stack, player, showInChat);
                break;
            case MODE_SECONDARY:
                pack.switchModeSecondary(stack, player, showInChat);
                break;
            case OPEN_PACK_GUI:
                player.openGui(SimplyJetpacks.instance, GuiHandler.PACK, player.worldObj, 0, 0, 0);
                break;
            default:
            }
        }
    }
    
    // armor
    protected int getFuelPerDamage(ItemStack stack, T pack) {
        if (ModEnchantments.fuelEffeciency == null) {
            return pack.armorFuelPerHit;
        }
        
        int fuelEfficiencyLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency.effectId, stack), 0, 4);
        return (int) Math.round(pack.armorFuelPerHit * (5 - fuelEfficiencyLevel) / 5.0D);
    }
    
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        T pack = this.getPack(armor);
        if (pack != null && pack.isArmored && !source.isUnblockable()) {
            if (pack.isFluxBased && source.damageType.equals("flux")) {
                return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
            }
            int energyPerDamage = this.getFuelPerDamage(armor, pack);
            int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getFuelStored(armor) / energyPerDamage) : 0;
            return new ArmorProperties(0, 0.85D * (pack.armorReduction / 20.0D), maxAbsorbed);
        }
        return new ArmorProperties(0, 1, 0);
    }
    
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        T pack = this.getPack(armor);
        if (pack != null && pack.isArmored) {
            if (this.getFuelStored(armor) >= pack.armorFuelPerHit) {
                return pack.armorReduction;
            }
        }
        return 0;
    }
    
    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
        T pack = this.getPack(armor);
        if (pack != null && pack.isArmored && pack.usesFuel) {
            if (pack.fuelType == FuelType.ENERGY && pack.isFluxBased && source.damageType.equals("flux")) {
                this.addFuel(armor, damage * (source.getEntity() == null ? pack.armorFuelPerHit / 2 : this.getFuelPerDamage(armor, pack)), false);
            } else {
                this.useFuel(armor, damage * this.getFuelPerDamage(armor, pack), false);
            }
        }
    }
    
    // fuel
    public int getFuelStored(ItemStack stack) {
        T pack = this.getPack(stack);
        switch (pack.fuelType) {
        case ENERGY:
        default:
            return this.getEnergyStored(stack);
        case FLUID:
            FluidStack stored = this.getFluid(stack);
            return stored != null ? stored.amount : 0;
        }
    }
    
    public int getMaxFuelStored(ItemStack stack) {
        T pack = this.getPack(stack);
        switch (pack.fuelType) {
        case ENERGY:
        default:
            return this.getMaxEnergyStored(stack);
        case FLUID:
            return this.getCapacity(stack);
        }
    }
    
    public int addFuel(ItemStack stack, int maxAdd, boolean simulate) {
        T pack = this.getPack(stack);
        if (pack == null) {
            return 0;
        }
        switch (pack.fuelType) {
        case ENERGY:
        default:
            int energy = this.getEnergyStored(stack);
            int energyReceived = Math.min(this.getMaxEnergyStored(stack) - energy, maxAdd);
            if (!simulate) {
                energy += energyReceived;
                NBTHelper.getNBT(stack).setInteger(TAG_ENERGY, energy);
            }
            return energyReceived;
        case FLUID:
            if (pack.fuelFluid == null) {
                return 0;
            }
            FluidStack fluid = this.getFluid(stack);
            int amount = fluid != null ? fluid.amount : 0;
            int fluidReceived = Math.min(this.getCapacity(stack) - amount, maxAdd);
            if (!simulate) {
                amount += fluidReceived;
                NBTHelper.getNBT(stack).setInteger(TAG_FLUID, amount);
            }
            return fluidReceived;
        }
    }
    
    public int useFuel(ItemStack stack, int maxUse, boolean simulate) {
        T pack = this.getPack(stack);
        switch (pack.fuelType) {
        case ENERGY:
        default:
            int energy = this.getEnergyStored(stack);
            int energyExtracted = Math.min(energy, maxUse);
            if (!simulate) {
                energy -= energyExtracted;
                NBTHelper.getNBT(stack).setInteger(TAG_ENERGY, energy);
            }
            return energyExtracted;
        case FLUID:
            if (pack.fuelFluid == null) {
                return 0;
            }
            FluidStack fluid = this.getFluid(stack);
            int amount = fluid != null ? fluid.amount : 0;
            int fluidExtracted = Math.min(amount, maxUse);
            if (!simulate) {
                amount -= fluidExtracted;
                NBTHelper.getNBT(stack).setInteger(TAG_FLUID, amount);
            }
            return fluidExtracted;
        }
    }
    
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        T pack = this.getPack(container);
        if (pack == null || pack.fuelType != FuelType.ENERGY) {
            return 0;
        }
        int energy = this.getEnergyStored(container);
        int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, pack.fuelPerTickIn));
        if (!simulate) {
            energy += energyReceived;
            NBTHelper.getNBT(container).setInteger(TAG_ENERGY, energy);
        }
        return energyReceived;
    }
    
    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        T pack = this.getPack(container);
        if (pack == null || pack.fuelType != FuelType.ENERGY) {
            return 0;
        }
        int energy = this.getEnergyStored(container);
        int energyExtracted = Math.min(energy, Math.min(maxExtract, pack.fuelPerTickOut));
        if (!simulate) {
            energy -= energyExtracted;
            NBTHelper.getNBT(container).setInteger(TAG_ENERGY, energy);
        }
        return energyExtracted;
    }
    
    @Override
    public int getEnergyStored(ItemStack container) {
        T pack = this.getPack(container);
        if (pack == null || pack.fuelType != FuelType.ENERGY) {
            return 0;
        }
        return NBTHelper.getNBT(container).getInteger(TAG_ENERGY);
    }
    
    @Override
    public int getMaxEnergyStored(ItemStack container) {
        T pack = this.getPack(container);
        if (pack == null || pack.fuelType != FuelType.ENERGY) {
            return 0;
        }
        return pack.fuelCapacity;
    }
    
    @Override
    public FluidStack getFluid(ItemStack container) {
        T pack = this.getPack(container);
        if (pack == null || pack.fuelType != FuelType.FLUID || pack.fuelFluid == null) {
            return null;
        }
        int amount = NBTHelper.getNBT(container).getInteger(TAG_FLUID);
        return amount > 0 ? new FluidStack(FluidRegistry.getFluid(pack.fuelFluid), amount) : null;
    }
    
    @Override
    public int getCapacity(ItemStack container) {
        T pack = this.getPack(container);
        if (pack == null || pack.fuelType != FuelType.FLUID || pack.fuelFluid == null) {
            return 0;
        }
        return pack.fuelCapacity;
    }
    
    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill) {
        if (resource == null) {
            return 0;
        }
        T pack = this.getPack(container);
        if (pack == null || pack.fuelType != FuelType.FLUID || pack.fuelFluid == null || resource.getFluid() != FluidRegistry.getFluid(pack.fuelFluid)) {
            return 0;
        }
        FluidStack fluid = this.getFluid(container);
        int amount = fluid != null ? fluid.amount : 0;
        int fluidReceived = Math.min(this.getCapacity(container) - amount, Math.min(resource.amount, pack.fuelPerTickIn));
        if (doFill) {
            amount += fluidReceived;
            NBTHelper.getNBT(container).setInteger(TAG_FLUID, amount);
        }
        return fluidReceived;
    }
    
    @Override
    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
        T pack = this.getPack(container);
        if (pack == null || pack.fuelType != FuelType.FLUID || pack.fuelFluid == null) {
            return null;
        }
        FluidStack fluid = this.getFluid(container);
        int amount = fluid != null ? fluid.amount : 0;
        int fluidExtracted = Math.min(amount, Math.min(maxDrain, pack.fuelPerTickOut));
        if (doDrain) {
            amount -= fluidExtracted;
            NBTHelper.getNBT(container).setInteger(TAG_FLUID, amount);
        }
        return fluidExtracted > 0 ? new FluidStack(FluidRegistry.getFluid(pack.fuelFluid), fluidExtracted) : null;
    }
    
    // HUD info
    @Override
    @SideOnly(Side.CLIENT)
    public void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState) {
        T pack = this.getPack(stack);
        if (pack != null) {
            if (showFuel && pack.hasFuelIndicator) {
                list.add(pack.getHUDFuelInfo(stack, this));
            }
            if (showState && pack.hasStateIndicators) {
                list.add(pack.getHUDStatesInfo(stack, this));
            }
        }
    }
    
    public static class ItemJetpack extends ItemPack<Jetpack> {
        
        public ItemJetpack(ModType modType) {
            super(modType);
        }
        
    }
    
    public static class ItemFluxPack extends ItemPack<FluxPack> {
        
        public ItemFluxPack(ModType modType) {
            super(modType);
        }
        
    }
    
}
