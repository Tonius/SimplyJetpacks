package tonius.simplyjetpacks.item.fluxpack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;

public class FluxPack {

    private static Map<Integer, FluxPack> fluxpacks = new HashMap<Integer, FluxPack>();
    private static int highestMeta;

    public int tier;
    public boolean enchantable;
    public EnumRarity rarity;
    public int energyCapacity;
    public int energyPerTick;
    public boolean consumesEnergy;

    public FluxPack(int meta, int tier, boolean enchantable, EnumRarity rarity, int energyCapacity, int energyPerTick, boolean consumesEnergy) {
        this.tier = tier;
        this.enchantable = enchantable;
        this.rarity = rarity;
        this.energyCapacity = energyCapacity;
        this.energyPerTick = energyPerTick;
        this.consumesEnergy = consumesEnergy;

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
        new FluxPack(1, 1, false, EnumRarity.common, 400000, 80, true);
        new FluxPack(2, 2, false, EnumRarity.common, 2000000, 400, true);
        new FluxPack(3, 3, true, EnumRarity.uncommon, 10000000, 2000, true);
        new FluxPack(4, 4, true, EnumRarity.rare, 50000000, 10000, true);
        new FluxPackCreative(9001, true, 20000);
    }

    public String getBaseName() {
        return "fluxpack." + this.tier;
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
        return this.tier + 120;
    }

    public void useFluxPack(EntityLivingBase user, ItemStack armor, ItemFluxPack item) {
        if (this.isOn(armor)) {
        }
    }

    public boolean isOn(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("FluxPackOn");
    }

    public void toggle(ItemStack itemStack, EntityPlayer player) {
        String msg = "";
        if (this.isOn(itemStack)) {
            msg = StringUtils.translate("chat.fluxpack.charger") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled");
            itemStack.stackTagCompound.setBoolean("FluxPackOn", false);
        } else {
            msg = StringUtils.translate("chat.fluxpack.charger") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled");
            itemStack.stackTagCompound.setBoolean("FluxPackOn", true);
        }
        player.addChatMessage(new ChatComponentText(msg));
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, int energyStored) {
        list.add(StringUtils.getChargeText(this.tier == 9001, energyStored, this.energyCapacity));
        if (StringUtils.canShowDetails()) {
            list.add(StringUtils.getStateText(this.isOn(itemStack)));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.fluxpack.description.1"));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.fluxpack.description.2"));
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

}
