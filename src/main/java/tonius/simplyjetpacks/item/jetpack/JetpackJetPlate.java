package tonius.simplyjetpacks.item.jetpack;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cofh.api.energy.IEnergyContainerItem;

public class JetpackJetPlate extends JetpackArmored {
    
    public final int energyPerTickOut;
    public final boolean allowCharger;
    
    public JetpackJetPlate(int tier, EnumRarity rarity, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, double speedVerticalHover, double speedVerticalHoverSlow, float speedSideways, boolean enchantable, int enchantability, boolean emergencyHoverMode, int armorDisplay, double armorAbsorption, int energyPerHit, int energyPerTickOut) {
        super(tier, rarity, energyCapacity, energyPerTick, speedVertical, accelVertical, speedVerticalHover, speedVerticalHoverSlow, speedSideways, enchantable, enchantability, emergencyHoverMode, armorDisplay, armorAbsorption, energyPerHit);
        this.allowCharger = energyPerTickOut > 0;
        this.energyPerTickOut = energyPerTickOut;
    }
    
    @Override
    public boolean hasArmoredVersion() {
        return false;
    }
    
    public boolean isChargerOn(ItemStack itemStack) {
        return this.allowCharger && StackUtils.getNBT(itemStack).getBoolean("FluxPackOn");
    }
    
    public void toggleCharger(ItemStack itemStack, EntityPlayer player, boolean showInChat) {
        String msg = "";
        if (this.isChargerOn(itemStack)) {
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
    
    @Override
    public String getBaseName() {
        return "jetpack." + this.tier;
    }
    
    @Override
    public void toggle(ItemStack itemStack, EntityPlayer player, boolean showInChat) {
        if (this.allowCharger && player.isSneaking()) {
            this.toggleCharger(itemStack, player, showInChat);
        } else {
            super.toggle(itemStack, player, showInChat);
        }
    }
    
    @Override
    public void useJetpack(EntityLivingBase user, ItemStack armor, ItemJetpack item, boolean force) {
        super.useJetpack(user, armor, item, force);
        if (this.isChargerOn(armor)) {
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
    
    @Override
    public void addShiftInformation(ItemStack itemStack, EntityPlayer player, List list) {
        list.add(StringUtils.getStateText(this.isOn(itemStack)));
        list.add(StringUtils.getHoverModeText(this.isHoverModeOn(itemStack)));
        list.add(StringUtils.getChargerStateText(this.isChargerOn(itemStack)));
        list.add(StringUtils.getEnergyUsageText(this.energyPerTick));
        list.add(StringUtils.getChargerRateText(this.energyPerTickOut));
        list.add(StringUtils.getParticlesText(this.getParticleType(itemStack)));
        list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.1"));
        list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.fluxpack.description.1"));
        list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.2"));
        list.add(StringUtils.BRIGHT_BLUE + StringUtils.ITALIC + StringUtils.translate("tooltip.jetpackFluxPlate.controls"));
    }
    
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemJetpack item, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.damageType.equals("flux")) {
            return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
        }
        return super.getProperties(player, item, armor, source, damage, slot);
    }
    
    @Override
    public void damageArmor(EntityLivingBase entity, ItemJetpack item, ItemStack armor, DamageSource source, int damage, int slot) {
        if (source.damageType.equals("flux")) {
            item.receiveEnergy(armor, damage * this.armorEnergyPerHit, false);
        } else {
            super.damageArmor(entity, item, armor, source, damage, slot);
        }
    }
    
}
