package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import tonius.simplyjetpacks.setup.SJCreativeTab;
import tonius.simplyjetpacks.util.StackUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEnergyArmor extends ItemArmor implements ISpecialArmor, IEnergyContainerItem {

    protected String name;
    protected int maxEnergy;
    protected int maxInput;
    protected int maxOutput;
    protected ArmorProperties properties = new ArmorProperties(0, 1, 0);

    public ItemEnergyArmor(ArmorMaterial material, int renderIndex, int armorType, String name, int maxEnergy, int maxInput, int maxOutput) {
        super(material, renderIndex, armorType);
        this.name = name;
        this.maxEnergy = maxEnergy;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
        this.setUnlocalizedName("simplyjetpacks." + name);
        this.setNoRepair();
        this.setCreativeTab(SJCreativeTab.tab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon("simplyjetpacks:" + name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "simplyjetpacks:textures/armor/" + name + "_layer_" + (slot == 2 ? "1" : "0") + ".png";
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(this));
        list.add(this.getChargedItem(this));
    }

    public void toggle(ItemStack itemStack, EntityPlayer player) {
        if (isOn(itemStack)) {
            player.addChatMessage(new ChatComponentText(this.getDeactivateMsg()));
            itemStack.stackTagCompound.setBoolean("On", false);
        } else {
            player.addChatMessage(new ChatComponentText(this.getActivateMsg()));
            itemStack.stackTagCompound.setBoolean("On", true);
        }
    }

    public String getActivateMsg() {
        return "Enabled";
    }

    public String getDeactivateMsg() {
        return "Disabled";
    }

    public boolean isOn(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("On");
    }

    @Override
    public boolean isDamaged(ItemStack itemStack) {
        return itemStack.getItemDamage() < Short.MAX_VALUE;
    }

    @Override
    public int getMaxDamage(ItemStack itemStack) {
        return this.getMaxEnergyStored(itemStack) + 1;
    }

    @Override
    public int getDisplayDamage(ItemStack itemStack) {
        return this.getMaxEnergyStored(itemStack) - this.getEnergyStored(itemStack) + 1;
    }

    public int addEnergy(ItemStack container, int energyToAdd, boolean simulate) {
        StackUtils.getNBT(container);
        int energyCurrent = container.stackTagCompound.getInteger("Energy");
        int energyAdded = Math.min(getMaxEnergyStored(container) - energyCurrent, energyToAdd);

        if (!simulate) {
            energyCurrent += energyAdded;
            container.stackTagCompound.setInteger("Energy", energyCurrent);
        }

        return energyAdded;
    }

    public int subtractEnergy(ItemStack container, int energyToSubtract, boolean simulate) {
        StackUtils.getNBT(container);
        int energyCurrent = container.stackTagCompound.getInteger("Energy");
        int energySubtracted = Math.min(energyCurrent, energyToSubtract);

        if (!simulate) {
            energyCurrent -= energySubtracted;
            container.stackTagCompound.setInteger("Energy", energyCurrent);
        }

        return energySubtracted;
    }

    public ItemStack getChargedItem(ItemEnergyArmor item) {
        ItemStack full = new ItemStack(item);
        item.addEnergy(full, item.getMaxEnergyStored(full), false);
        return full;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return properties;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = getEnergyStored(container);
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
        return maxEnergy;
    }
}
