package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
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
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.ModelFluxPack;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.setup.SJCreativeTab;
import tonius.simplyjetpacks.setup.SJItems.ModType;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFluxPack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IToggleable, IEnergyHUDInfoProvider {
    
    public final ItemIndex index;
    public final ModType modType;
    private IIcon[] icons = null;
    
    public ItemFluxPack(ItemIndex index, ModType modType) {
        super(ArmorMaterial.IRON, 2, 1);
        
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + "fluxpack" + modType.suffix);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setCreativeTab(SJCreativeTab.tab);
        this.index = index;
        this.modType = modType;
        this.icons = new IIcon[FluxPack.getHighestMeta(index) + 1];
    }
    
    public FluxPack getFluxPack(ItemStack itemStack) {
        return FluxPack.getFluxPack(this.index, itemStack.getItemDamage());
    }
    
    public ItemStack getChargedItem(ItemFluxPack item, int meta) {
        ItemStack full = new ItemStack(item, 1, meta);
        while (item.getEnergyStored(full) < item.getMaxEnergyStored(full)) {
            item.receiveEnergy(full, item.getMaxEnergyStored(full), false);
        }
        return full;
    }
    
    @Override
    public void toggle(ItemStack itemStack, EntityPlayer player, boolean showInChat) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            fluxpack.toggle(itemStack, player, showInChat);
        }
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            return "item.simplyjetpacks." + fluxpack.getBaseName() + this.modType.suffix;
        }
        return super.getUnlocalizedName();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return RenderUtils.getChestplateModel(entityLiving, ModelFluxPack.INSTANCE);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            fluxpack.addInformation(itemStack, player, list, this.getEnergyStored(itemStack));
        }
    }
    
    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            return fluxpack.rarity;
        }
        return super.getRarity(itemStack);
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            fluxpack.useFluxPack(player, itemStack, this);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= FluxPack.getHighestMeta(this.index); i++) {
            FluxPack fluxpack = FluxPack.getFluxPack(this.index, i);
            if (fluxpack != null && fluxpack.isVisible()) {
                if (fluxpack.hasEmptyItem()) {
                    list.add(new ItemStack(this, 1, i));
                }
                list.add(this.getChargedItem(this, i));
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i <= FluxPack.getHighestMeta(this.index); i++) {
            FluxPack fluxpack = FluxPack.getFluxPack(this.index, i);
            if (fluxpack != null) {
                this.icons[i] = register.registerIcon(SimplyJetpacks.RESOURCE_PREFIX + fluxpack.getBaseName() + this.modType.suffix);
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return damage < this.icons.length ? this.icons[damage] : null;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + fluxpack.getBaseName() + this.modType.suffix + ".png";
        }
        return null;
    }
    
    @Override
    public boolean showDurabilityBar(ItemStack itemStack) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            return fluxpack.hasDamageBar();
        }
        return true;
    }
    
    @Override
    public double getDurabilityForDisplay(ItemStack itemStack) {
        double stored = this.getMaxEnergyStored(itemStack) - this.getEnergyStored(itemStack) + 1;
        double max = this.getMaxEnergyStored(itemStack) + 1;
        return stored / max;
    }
    
    @Override
    public boolean isItemTool(ItemStack itemStack) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        return this.getItemEnchantability() > 0 ? fluxpack != null ? fluxpack.enchantable : false : false;
    }
    
    @Override
    public int getItemEnchantability(ItemStack itemStack) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            System.out.println(fluxpack.enchantability);
            return fluxpack.enchantability;
        }
        return super.getItemEnchantability(itemStack);
    }
    
    @Override
    public boolean isBookEnchantable(ItemStack itemStack, ItemStack book) {
        return this.isItemTool(itemStack);
    }
    
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        FluxPack fluxpack = this.getFluxPack(armor);
        if (fluxpack != null) {
            return fluxpack.getProperties(player, this, armor, source, damage, slot);
        }
        return null;
    }
    
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        FluxPack fluxpack = this.getFluxPack(armor);
        if (fluxpack != null) {
            return fluxpack.getArmorDisplay(player, this, armor, slot);
        }
        return 0;
    }
    
    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
        FluxPack fluxpack = this.getFluxPack(armor);
        if (fluxpack != null) {
            fluxpack.damageArmor(entity, this, armor, source, damage, slot);
        }
    }
    
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = this.getEnergyStored(container);
        FluxPack fluxpack = this.getFluxPack(container);
        int maxInput = fluxpack != null ? fluxpack.energyPerTickIn : 0;
        int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, maxInput));
        
        if (!simulate) {
            energy += energyReceived;
            StackUtils.getNBT(container).setInteger("Energy", energy);
        }
        return energyReceived;
    }
    
    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = this.getEnergyStored(container);
        FluxPack fluxpack = this.getFluxPack(container);
        int maxOutput = fluxpack != null ? fluxpack.energyPerTickOut : 0;
        int energyExtracted = Math.min(energy, Math.min(maxExtract, maxOutput));
        
        if (!simulate) {
            energy -= energyExtracted;
            StackUtils.getNBT(container).setInteger("Energy", energy);
        }
        return energyExtracted;
    }
    
    @Override
    public int getEnergyStored(ItemStack container) {
        return StackUtils.getNBT(container).getInteger("Energy");
    }
    
    @Override
    public int getMaxEnergyStored(ItemStack container) {
        FluxPack fluxpack = this.getFluxPack(container);
        return fluxpack != null ? fluxpack.energyCapacity : 0;
    }
    
    @Override
    public String getEnergyInfo(ItemStack stack) {
        FluxPack fluxpack = this.getFluxPack(stack);
        if (fluxpack != null && fluxpack.hasDamageBar()) {
            int energy = this.getEnergyStored(stack);
            int maxEnergy = this.getMaxEnergyStored(stack);
            int percent = (int) Math.ceil((double) energy / (double) maxEnergy * 100D);
            return StringUtils.getHUDEnergyText("fluxpack", percent, energy);
        }
        return null;
    }
    
    @Override
    public String getStatesInfo(ItemStack stack) {
        FluxPack fluxpack = this.getFluxPack(stack);
        if (fluxpack != null) {
            Boolean charger = fluxpack.isOn(stack);
            return StringUtils.getHUDStateText(null, null, charger);
        }
        return null;
    }
    
}
