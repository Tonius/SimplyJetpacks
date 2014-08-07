package tonius.simplyjetpacks.item.fluxpack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cofh.api.energy.IEnergyContainerItem;

public class FluxPack {

    private static Map<Integer, FluxPack> fluxpacks = new HashMap<Integer, FluxPack>();
    private static int highestMeta;

    public int tier;
    public boolean enchantable;
    public EnumRarity rarity;
    public int energyCapacity;
    public int energyPerTickIn;
    public int energyPerTickOut;

    public FluxPack(int meta, int tier, boolean enchantable, EnumRarity rarity, int energyCapacity, int energyPerTickIn, int energyPerTickOut) {
        this.tier = tier;
        this.enchantable = enchantable;
        this.rarity = rarity;
        this.energyCapacity = energyCapacity;
        this.energyPerTickIn = energyPerTickIn;
        this.energyPerTickOut = energyPerTickOut;

        addFluxPack(meta, this);
    }

    public static FluxPack getFluxPack(int meta) {
        return fluxpacks.get(meta);
    }

    public static int getHighestMeta() {
        return highestMeta;
    }

    public static void addFluxPack(int meta, FluxPack fluxpack) {
        fluxpacks.put(meta, fluxpack);
        if (highestMeta < meta) {
            highestMeta = meta;
        }
    }

    public static void reconstructFluxPacks() {
        new FluxPack(1, 1, SJConfig.fluxpackLeadstoneEnchantable, EnumRarity.common, SJConfig.fluxpackLeadstoneEnergyCapacity, SJConfig.fluxpackLeadstoneEnergyInRate, SJConfig.fluxpackLeadstoneEnergyOutRate);
        new FluxPack(2, 2, SJConfig.fluxpackHardenedEnchantable, EnumRarity.common, SJConfig.fluxpackHardenedEnergyCapacity, SJConfig.fluxpackHardenedEnergyInRate, SJConfig.fluxpackHardenedEnergyOutRate);
        new FluxPack(3, 3, SJConfig.fluxpackRedstoneEnchantable, EnumRarity.uncommon, SJConfig.fluxpackRedstoneEnergyCapacity, SJConfig.fluxpackRedstoneEnergyInRate, SJConfig.fluxpackRedstoneEnergyOutRate);
        new FluxPack(4, 4, SJConfig.fluxpackResonantEnchantable, EnumRarity.rare, SJConfig.fluxpackResonantEnergyCapacity, SJConfig.fluxpackResonantEnergyInRate, SJConfig.fluxpackResonantEnergyOutRate);
        new FluxPackArmored(102, 2, SJConfig.fluxpackHardenedEnchantable, EnumRarity.common, SJConfig.fluxpackHardenedEnergyCapacity, SJConfig.fluxpackHardenedEnergyInRate, SJConfig.fluxpackHardenedEnergyOutRate, SJConfig.fluxpackHardenedArmorDisplay, SJConfig.fluxpackHardenedArmorAbsorption, SJConfig.fluxpackHardenedArmorEnergyPerHit);
        new FluxPackArmored(103, 3, SJConfig.fluxpackRedstoneEnchantable, EnumRarity.uncommon, SJConfig.fluxpackRedstoneEnergyCapacity, SJConfig.fluxpackRedstoneEnergyInRate, SJConfig.fluxpackRedstoneEnergyOutRate, SJConfig.fluxpackRedstoneArmorDisplay, SJConfig.fluxpackRedstoneArmorAbsorption, SJConfig.fluxpackRedstoneArmorEnergyPerHit);
        new FluxPackArmored(104, 4, SJConfig.fluxpackResonantEnchantable, EnumRarity.rare, SJConfig.fluxpackResonantEnergyCapacity, SJConfig.fluxpackResonantEnergyInRate, SJConfig.fluxpackResonantEnergyOutRate, SJConfig.fluxpackResonantArmorDisplay, SJConfig.fluxpackResonantArmorAbsorption, SJConfig.fluxpackResonantArmorEnergyPerHit);
        new FluxPackCreative(9001, SJConfig.fluxpackCreativeEnchantable, SJConfig.fluxpackCreativeEnergyOutRate, SJConfig.fluxpackCreativeArmorDisplay, SJConfig.fluxpackCreativeArmorAbsorption);
    }

    public String getBaseName() {
        return "fluxpack." + this.tier;
    }

    public boolean isArmored() {
        return false;
    }

    public boolean hasArmoredVersion() {
        return this.tier != 1;
    }

    public boolean isVisible() {
        return true;
    }

    public boolean hasEmptyItem() {
        return true;
    }

    public boolean hasDamageBar() {
        return true;
    }

    public int getPlatingMeta() {
        return this.tier + 119;
    }

    public void useFluxPack(EntityLivingBase user, ItemStack armor, ItemFluxPack item) {
        if (!user.worldObj.isRemote && this.isOn(armor)) {
            for (int i = 0; i <= 4; i++) {
                ItemStack currentStack = user.getEquipmentInSlot(i);
                if (currentStack != null && currentStack != armor && currentStack.getItem() instanceof IEnergyContainerItem) {
                    IEnergyContainerItem heldEnergyItem = (IEnergyContainerItem) (currentStack.getItem());
                    if (!(this instanceof FluxPackCreative)) {
                        int energyToAdd = Math.min(item.extractEnergy(armor, this.energyPerTickOut, true), heldEnergyItem.receiveEnergy(currentStack, this.energyPerTickOut, true));
                        item.extractEnergy(armor, energyToAdd, false);
                        heldEnergyItem.receiveEnergy(currentStack, energyToAdd, false);
                    } else {
                        heldEnergyItem.receiveEnergy(currentStack, this.energyPerTickOut, false);
                    }
                }
            }
        }
    }

    public boolean isOn(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("FluxPackOn");
    }

    public void toggle(ItemStack itemStack, EntityPlayer player, boolean showInChat) {
        String msg = "";
        if (this.isOn(itemStack)) {
            msg = StringUtils.translate("chat.fluxpack.charger") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled");
            itemStack.stackTagCompound.setBoolean("FluxPackOn", false);
        } else {
            msg = StringUtils.translate("chat.fluxpack.charger") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled");
            itemStack.stackTagCompound.setBoolean("FluxPackOn", true);
        }
        if (showInChat)
            player.addChatMessage(new ChatComponentText(msg));
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, int energyStored) {
        list.add(StringUtils.getChargeText(this.tier == 9001, energyStored, this.energyCapacity));
        if (StringUtils.canShowDetails()) {
            list.add(StringUtils.getStateText(this.isOn(itemStack)));
            list.add(StringUtils.getEnergySendText(this.energyPerTickOut));
            list.add(StringUtils.getEnergyReceiveText(this.energyPerTickIn));
            list.add(StringUtils.getArmoredText(this.isArmored()));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.fluxpack.description.1"));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.fluxpack.description.2"));
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

    public void applyArmor(ItemStack itemStack, EntityPlayer player) {
        itemStack.setItemDamage(itemStack.getItemDamage() + 100);
        player.worldObj.playSoundAtEntity(player, "random.anvil_use", 0.8F, 0.9F + player.getRNG().nextFloat() * 0.2F);
    }

    public void removeArmor(ItemStack itemStack, EntityPlayer player) {
        itemStack.setItemDamage(itemStack.getItemDamage() - 100);
        player.worldObj.playSoundAtEntity(player, "random.break", 1.0F, 0.9F + player.getRNG().nextFloat() * 0.2F);
    }

    public ArmorProperties getProperties(EntityLivingBase player, ItemFluxPack item, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, 1, 0);
    }

    public int getArmorDisplay(EntityPlayer player, ItemFluxPack item, ItemStack armor, int slot) {
        return 0;
    }

    public void damageArmor(EntityLivingBase entity, ItemFluxPack item, ItemStack armor, DamageSource source, int damage, int slot) {
    }

}
