package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tonius.simplyjetpacks.util.StringUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJCapacitorPack extends ItemSJArmorEnergy {

    protected int capacitorPackTier;

    public ItemSJCapacitorPack(int id, EnumArmorMaterial material, String name, int maxEnergy, int maxInput, int maxOutput, int capacitorPackTier) {
        super(id, material, 2, 1, name, maxEnergy, maxInput, maxOutput);
        this.capacitorPackTier = capacitorPackTier;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        switch (this.capacitorPackTier) {
        case 3:
            return StringUtils.YELLOW + super.getItemDisplayName(itemStack);
        case 4:
            return StringUtils.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
        }
        return super.getItemDisplayName(itemStack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        if (StringUtils.isShiftKeyDown()) {
            list.add(StringUtils.getChargeText(this.getEnergyStored(itemStack), this.getMaxEnergyStored(itemStack)));
            list.add(StringUtils.getStateText(this.isOn(itemStack)));
            list.add(StringUtils.getChargingRateText(this.maxOutput));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.capacitorPack.description.1"));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.capacitorPack.description.2"));
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

    @Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
        if (isOn(itemStack)) {
            ItemStack heldItem = player.getHeldItem();
            if (heldItem != null && heldItem.getItem() instanceof IEnergyContainerItem) {
                IEnergyContainerItem heldEnergyItem = (IEnergyContainerItem) (heldItem.getItem());
                int energyToAdd = Math.min(subtractEnergy(itemStack, maxOutput, true), heldEnergyItem.receiveEnergy(heldItem, maxOutput, true));
                subtractEnergy(itemStack, energyToAdd, false);
                heldEnergyItem.receiveEnergy(heldItem, energyToAdd, false);
            }
        }
    }

    @Override
    public String getActivateMsg() {
        return StringUtils.translate("chat.capacitorPack.charger") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled");
    }

    @Override
    public String getDeactivateMsg() {
        return StringUtils.translate("chat.capacitorPack.charger") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled");
    }

}
