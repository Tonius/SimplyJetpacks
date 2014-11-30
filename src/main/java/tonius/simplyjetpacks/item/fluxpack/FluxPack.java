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
import tonius.simplyjetpacks.config.FluxPackConfig;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemIndex;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cofh.api.energy.IEnergyContainerItem;

public class FluxPack {
    
    public static final int ARMORED_META_OFFSET = 100;
    
    private static Map<Integer, FluxPack> fluxPacksCommon = new HashMap<Integer, FluxPack>();
    private static int highestMetaCommon;
    private static Map<Integer, FluxPack> fluxPacksPerMod = new HashMap<Integer, FluxPack>();
    private static int highestMetaPerMod;
    
    public int tier;
    public EnumRarity rarity;
    
    public int energyCapacity;
    public int energyPerTickIn;
    public int energyPerTickOut;
    
    public boolean enchantable;
    public int enchantability;
    
    public FluxPack(int tier, EnumRarity rarity, int energyCapacity, int energyPerTickIn, int energyPerTickOut, boolean enchantable, int enchantability) {
        this.tier = tier;
        this.rarity = rarity;
        
        this.energyCapacity = energyCapacity;
        this.energyPerTickIn = energyPerTickIn;
        this.energyPerTickOut = energyPerTickOut;
        
        this.enchantable = enchantable;
        this.enchantability = enchantability;
    }
    
    public static FluxPack getFluxPack(ItemIndex index, int meta) {
        switch (index) {
        case COMMON:
            return fluxPacksCommon.get(meta);
        case PER_MOD:
            return fluxPacksPerMod.get(meta);
        }
        return null;
    }
    
    public static int getHighestMeta(ItemIndex index) {
        switch (index) {
        case COMMON:
            return highestMetaCommon;
        case PER_MOD:
            return highestMetaPerMod;
        }
        return 0;
    }
    
    public static void addFluxPack(ItemIndex index, int meta, FluxPack fluxPack) {
        switch (index) {
        case COMMON:
            fluxPacksCommon.put(meta, fluxPack);
            if (highestMetaCommon < meta) {
                highestMetaCommon = meta;
            }
            break;
        case PER_MOD:
            fluxPacksPerMod.put(meta, fluxPack);
            if (highestMetaPerMod < meta) {
                highestMetaPerMod = meta;
            }
        }
    }
    
    public static void reconstructFluxPacks() {
        newFluxPack(ItemIndex.COMMON, 9001, null, false);
        
        newFluxPack(ItemIndex.PER_MOD, 1, EnumRarity.common, false);
        newFluxPack(ItemIndex.PER_MOD, 2, EnumRarity.common, true);
        newFluxPack(ItemIndex.PER_MOD, 3, EnumRarity.uncommon, true);
        newFluxPack(ItemIndex.PER_MOD, 4, EnumRarity.rare, true);
    }
    
    public static void newFluxPack(ItemIndex index, int tier, EnumRarity rarity, boolean canBeArmored) {
        FluxPackConfig config = SJConfig.fluxPackConfigs.get(tier);
        if (config != null) {
            FluxPack fluxPack;
            switch (tier) {
            case 9001:
                fluxPack = new FluxPackCreative(config.energyOutRate, config.enchantable, config.enchantability, config.armorDisplay, config.armorAbsorption);
                break;
            default:
                fluxPack = new FluxPack(tier, rarity, config.energyCapacity, config.energyInRate, config.energyOutRate, config.enchantable, config.enchantability);
                if (canBeArmored) {
                    FluxPack fluxPackArmored = new FluxPackArmored(tier, rarity, config.energyCapacity, config.energyInRate, config.energyOutRate, config.enchantable, config.enchantability, config.armorDisplay, config.armorAbsorption, config.armorEnergyPerHit);
                    FluxPack.addFluxPack(index, tier + Jetpack.ARMORED_META_OFFSET, fluxPackArmored);
                }
            }
            FluxPack.addFluxPack(index, tier, fluxPack);
        }
    }
    
    public String getBaseName() {
        return "fluxpack." + this.tier;
    }
    
    public boolean isArmored() {
        return false;
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
    
    public boolean consumesEnergy() {
        return true;
    }
    
    public void useFluxPack(EntityLivingBase user, ItemStack armor, ItemFluxPack item) {
        if (this.isOn(armor)) {
            for (int i = 0; i <= 4; i++) {
                ItemStack currentStack = user.getEquipmentInSlot(i);
                if (currentStack != null && currentStack != armor && currentStack.getItem() instanceof IEnergyContainerItem) {
                    IEnergyContainerItem heldEnergyItem = (IEnergyContainerItem) currentStack.getItem();
                    if (this.consumesEnergy()) {
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
        if (player != null && player.worldObj.isRemote && showInChat) {
            player.addChatMessage(new ChatComponentText(msg));
        }
    }
    
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, int energyStored) {
        list.add(StringUtils.getTierText(this.tier));
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
    
    public ArmorProperties getProperties(EntityLivingBase player, ItemFluxPack item, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, 1, 0);
    }
    
    public int getArmorDisplay(EntityPlayer player, ItemFluxPack item, ItemStack armor, int slot) {
        return 0;
    }
    
    public void damageArmor(EntityLivingBase entity, ItemFluxPack item, ItemStack armor, DamageSource source, int damage, int slot) {
    }
    
}
