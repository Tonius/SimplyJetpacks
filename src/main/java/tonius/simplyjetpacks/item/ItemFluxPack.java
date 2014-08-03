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
import tonius.simplyjetpacks.client.model.ModelFluxPack;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.setup.SJCreativeTab;
import tonius.simplyjetpacks.util.StackUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFluxPack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IToggleable {

    protected IIcon[] icons = null;

    public ItemFluxPack() {
        super(ArmorMaterial.IRON, 2, 1);

        this.setUnlocalizedName("simplyjetpacks.fluxpack");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setCreativeTab(SJCreativeTab.tab);
        this.icons = new IIcon[FluxPack.getHighestMeta() + 1];
    }

    public FluxPack getFluxPack(ItemStack itemStack) {
        return FluxPack.getFluxPack(itemStack.getItemDamage());
    }

    public ItemStack getChargedItem(ItemFluxPack item, int meta) {
        ItemStack full = new ItemStack(item, 1, meta);
        while (item.getEnergyStored(full) < item.getMaxEnergyStored(full)) {
            item.receiveEnergy(full, item.getMaxEnergyStored(full), false);
        }
        return full;
    }

    @Override
    public void toggle(ItemStack itemStack, EntityPlayer player) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            fluxpack.toggle(itemStack, player);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        FluxPack fluxpack = this.getFluxPack(itemStack);
        if (fluxpack != null) {
            return "item.simplyjetpacks." + fluxpack.getBaseName();
        }
        return super.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        ModelBiped armorModel = new ModelFluxPack(1.0F);
        armorModel.bipedHead.showModel = armorSlot == 0;
        armorModel.bipedHeadwear.showModel = armorSlot == 0;
        armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
        armorModel.bipedRightArm.showModel = armorSlot == 1;
        armorModel.bipedLeftArm.showModel = armorSlot == 1;
        armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
        armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
        armorModel.isSneak = entityLiving.isSneaking();
        armorModel.isRiding = entityLiving.isRiding();
        armorModel.isChild = entityLiving.isChild();
        armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
        if (entityLiving instanceof EntityPlayer) {
            armorModel.aimedBow = ((EntityPlayer) entityLiving).getItemInUseDuration() > 2;
        }
        return armorModel;
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
        for (int i = 0; i <= FluxPack.getHighestMeta(); i++) {
            FluxPack fluxpack = FluxPack.getFluxPack(i);
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
        for (int i = 0; i <= FluxPack.getHighestMeta(); i++) {
            FluxPack fluxpack = FluxPack.getFluxPack(i);
            if (fluxpack != null) {
                this.icons[i] = register.registerIcon("simplyjetpacks:" + fluxpack.getBaseName());
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
            return "simplyjetpacks:textures/armor/" + fluxpack.getBaseName() + ".png";
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
    public int getItemEnchantability() {
        return SJConfig.fluxpackEnchantability;
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemStack, ItemStack book) {
        return this.isItemTool(itemStack);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, 1, 0);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = getEnergyStored(container);
        FluxPack fluxpack = this.getFluxPack(container);
        int maxInput = fluxpack != null ? fluxpack.energyCapacity : 0;
        int energyReceived = Math.min(getMaxEnergyStored(container) - energy, Math.min(maxReceive, maxInput));

        if (!simulate) {
            energy += energyReceived;
            StackUtils.getNBT(container).setInteger("Energy", energy);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = getEnergyStored(container);
        FluxPack fluxpack = this.getFluxPack(container);
        int maxOutput = fluxpack != null ? fluxpack.energyCapacity : 0;
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

}
