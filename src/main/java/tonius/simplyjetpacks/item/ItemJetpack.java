package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.setup.SJCreativeTab;
import tonius.simplyjetpacks.setup.SJItems;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJetpack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem {

    protected IIcon[] icons = null;

    public ItemJetpack() {
        super(ArmorMaterial.IRON, 2, 1);

        this.setUnlocalizedName("simplyjetpacks.jetpack");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setCreativeTab(SJCreativeTab.tab);
        this.icons = new IIcon[Jetpack.getHighestMeta() + 1];
    }

    public Jetpack getJetpack(ItemStack itemStack) {
        return Jetpack.getJetpack(itemStack.getItemDamage());
    }

    public ItemStack getChargedItem(ItemJetpack item, int meta) {
        ItemStack full = new ItemStack(item, 1, meta);
        while (item.getEnergyStored(full) < item.getMaxEnergyStored(full)) {
            item.receiveEnergy(full, item.getMaxEnergyStored(full), false);
        }
        return full;
    }

    public void toggle(ItemStack itemStack, EntityPlayer player) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            jetpack.toggle(itemStack, player);
        }
    }

    public void toggleHoverMode(ItemStack itemStack, EntityPlayer player) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            jetpack.toggleHoverMode(itemStack, player);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return "item.simplyjetpacks." + jetpack.getBaseName();
        }
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            switch (jetpack.tier) {
            case 3:
                return StringUtils.YELLOW + super.getItemStackDisplayName(itemStack);
            case 4:
                return StringUtils.BRIGHT_BLUE + super.getItemStackDisplayName(itemStack);
            case 9001:
                return StringUtils.PINK + super.getItemStackDisplayName(itemStack);
            }
        }
        return super.getItemStackDisplayName(itemStack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            jetpack.addInformation(itemStack, player, list, this.getEnergyStored(itemStack));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            if (jetpack.hasArmoredVersion() && player.isSneaking()) {
                if (jetpack.isArmored()) {
                    jetpack.removeArmor(itemStack, player);
                    if (!world.isRemote) {
                        EntityItem item = player.entityDropItem(new ItemStack(SJItems.components, 1, jetpack.getPlatingMeta()), 0.0F);
                        item.delayBeforeCanPickup = 0;
                    }
                } else {
                    InventoryPlayer inv = player.inventory;
                    for (int i = 0; i < inv.getSizeInventory(); i++) {
                        ItemStack currentStack = inv.getStackInSlot(i);
                        if (currentStack != null && currentStack.getItem() == SJItems.components && currentStack.getItemDamage() == jetpack.getPlatingMeta()) {
                            inv.setInventorySlotContents(i, StackUtils.decrementStack(currentStack));
                            jetpack.applyArmor(itemStack, player);
                            break;
                        }
                    }
                }
                return itemStack;
            }
        }
        return super.onItemRightClick(itemStack, world, player);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            jetpack.useJetpack(player, itemStack, this, false);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= Jetpack.getHighestMeta(); i++) {
            Jetpack jetpack = Jetpack.getJetpack(i);
            if (jetpack != null && jetpack.isVisible()) {
                if (jetpack.hasEmptyItem()) {
                    list.add(new ItemStack(this, 1, i));
                }
                list.add(this.getChargedItem(this, i));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i <= Jetpack.getHighestMeta(); i++) {
            Jetpack jetpack = Jetpack.getJetpack(i);
            if (jetpack != null) {
                this.icons[i] = register.registerIcon("simplyjetpacks:" + jetpack.getBaseName());
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
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return "simplyjetpacks:textures/armor/" + jetpack.getBaseName() + ".png";
        }
        return null;
    }

    @Override
    public boolean showDurabilityBar(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return jetpack.hasDamageBar();
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
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        Jetpack jetpack = this.getJetpack(armor);
        if (jetpack != null) {
            return jetpack.getProperties(player, this, armor, source, damage, slot);
        }
        return null;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        Jetpack jetpack = this.getJetpack(armor);
        if (jetpack != null) {
            return jetpack.getArmorDisplay(player, this, armor, slot);
        }
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
        Jetpack jetpack = this.getJetpack(armor);
        if (jetpack != null) {
            jetpack.damageArmor(entity, this, armor, source, damage, slot);
        }
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = getEnergyStored(container);
        Jetpack jetpack = this.getJetpack(container);
        int maxInput = jetpack != null ? jetpack.energyCapacity : 0;
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
        Jetpack jetpack = this.getJetpack(container);
        int maxOutput = jetpack != null ? jetpack.energyCapacity : 0;
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
        Jetpack jetpack = this.getJetpack(container);
        return jetpack != null ? jetpack.energyCapacity : 0;
    }

}
