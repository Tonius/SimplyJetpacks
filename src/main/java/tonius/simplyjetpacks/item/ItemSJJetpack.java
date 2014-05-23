package tonius.simplyjetpacks.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import tonius.simplyjetpacks.ConfigReader;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.PacketHandler;
import tonius.simplyjetpacks.SJItems;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJJetpack extends ItemSJArmorEnergy {

    protected int jetpackTier;
    protected int tickEnergy;
    protected int tickEnergyHover;
    protected double maxSpeed;
    protected double acceleration;
    protected double forwardThrust;
    protected double hoverModeIdleSpeed;
    protected double hoverModeActiveSpeed;

    public ItemSJJetpack(int id, EnumArmorMaterial material, String name, int maxEnergy, int maxInput, int jetpackTier, int tickEnergy, double maxSpeed, double acceleration, double forwardThrust, double hoverModeIdleSpeed, double hoverModeActiveSpeed) {
        super(id, material, 2, 1, name, maxEnergy, maxInput, 0);
        this.jetpackTier = jetpackTier;
        this.tickEnergy = tickEnergy;
        this.tickEnergyHover = (int) (tickEnergy / 1.5);
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.forwardThrust = forwardThrust;
        this.hoverModeIdleSpeed = hoverModeIdleSpeed;
        this.hoverModeActiveSpeed = hoverModeActiveSpeed;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        switch (this.jetpackTier) {
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
            list.add(StringUtils.getHoverModeText(this.isHoverModeActive(itemStack)));
            int currentTickEnergy = this.isHoverModeActive(itemStack) ? this.tickEnergyHover : this.tickEnergy;
            list.add(StringUtils.getEnergyUsageText(currentTickEnergy));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.1"));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.2"));
            if (ConfigReader.enableCraftingArmorPlating) {
                list.add(StringUtils.getArmorText(this.isArmored()));
                if (!this.isArmored()) {
                    list.add(StringUtils.getRequiredArmorText(this.jetpackTier));
                }
            }
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (this.jetpackTier > 0 && player.isSneaking()) {
            if (this.isArmored()) {
                this.removeArmor(itemStack, player);
                EntityItem item = player.dropPlayerItem(new ItemStack(SJItems.metaItem1, 1, this.jetpackTier + 4));
                item.delayBeforeCanPickup = 0;
            } else {
                InventoryPlayer inv = player.inventory;
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack currentStack = inv.getStackInSlot(i);
                    if (currentStack != null && currentStack.getItem() == SJItems.metaItem1 && currentStack.getItemDamage() == this.jetpackTier + 4) {
                        inv.setInventorySlotContents(i, StackUtils.decrementStack(currentStack));
                        this.applyArmor(itemStack, player);
                        break;
                    }
                }
            }
            return itemStack;
        }
        return super.onItemRightClick(itemStack, world, player);
    }

    @Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
        this.useJetpack(player, itemStack, false);
    }

    public void useJetpack(EntityLivingBase user, ItemStack jetpack, boolean force) {
        if (isOn(jetpack)) {
            boolean hoverMode = this.isHoverModeActive(jetpack);
            double hoverSpeed = this.getHoverSpeed(jetpack, user);
            boolean jumpKeyDown = true;
            if (!force && user instanceof EntityPlayer && !KeyboardTracker.isJumpKeyDown((EntityPlayer) user)) {
                jumpKeyDown = false;
            }

            if (jumpKeyDown || (hoverMode && !user.onGround && user.motionY < 0)) {
                int usedPower = hoverMode ? this.tickEnergyHover : this.tickEnergy;
                this.subtractEnergy(jetpack, usedPower, false);

                if (this.getEnergyStored(jetpack) > 0) {
                    if (jumpKeyDown) {
                        if (!hoverMode) {
                            user.motionY = Math.min(user.motionY + this.acceleration, this.maxSpeed);
                        } else {
                            user.motionY = Math.min(user.motionY + this.acceleration, this.hoverModeActiveSpeed);
                        }
                    } else {
                        user.motionY = Math.max(user.motionY, -hoverSpeed);
                    }

                    if ((!(user instanceof EntityPlayer) && hoverMode || KeyboardTracker.isForwardKeyDown((EntityPlayer) user))) {
                        user.moveFlying(0, (float) this.forwardThrust, (float) this.forwardThrust);
                    }

                    user.fallDistance = 0.0F;
                    if (user instanceof EntityPlayerMP) {
                        ((EntityPlayerMP) user).playerNetServerHandler.ticksForFloatKick = 0;
                    }
                    this.sendJetpackPacket(user, hoverMode);
                }
            }
        }
    }

    public void applyArmor(ItemStack jetpack, EntityPlayer player) {
        jetpack.itemID = SJItems.armoredJetpacks[this.jetpackTier].itemID;
        player.worldObj.playSoundAtEntity(player, "random.anvil_use", 0.8F, 0.9F + player.getRNG().nextFloat() * 0.2F);
    }

    public void removeArmor(ItemStack jetpack, EntityPlayer player) {
        jetpack.itemID = SJItems.jetpacks[this.jetpackTier].itemID;
        player.worldObj.playSoundAtEntity(player, "random.break", 1.0F, 0.9F + player.getRNG().nextFloat() * 0.2F);
    }

    public boolean isHoverModeActive(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("HoverModeActive");
    }

    public void toggleHoverMode(ItemStack itemStack, EntityPlayer player) {
        if (this.isHoverModeActive(itemStack)) {
            player.addChatMessage(StringUtils.translate("chat.jetpack.hoverMode") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled"));
            itemStack.stackTagCompound.setBoolean("HoverModeActive", false);
        } else {
            player.addChatMessage(StringUtils.translate("chat.jetpack.hoverMode") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled"));
            itemStack.stackTagCompound.setBoolean("HoverModeActive", true);
        }
    }

    public double getHoverSpeed(ItemStack jetpack, EntityLivingBase user) {
        if (!ConfigReader.invertHoverSneakingBehavior) {
            return user.isSneaking() ? this.hoverModeActiveSpeed : this.hoverModeIdleSpeed;
        } else {
            return user.isSneaking() ? this.hoverModeIdleSpeed : this.hoverModeActiveSpeed;
        }
    }

    public void sendJetpackPacket(EntityLivingBase user, boolean hoverMode) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        try {
            data.writeInt(PacketHandler.JETPACK_TICK);
            data.writeInt(user.entityId);
            data.writeBoolean(hoverMode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "SmpJet";
        packet.data = bytes.toByteArray();
        packet.length = bytes.size();
        PacketDispatcher.sendPacketToAllAround(user.posX, user.posY, user.posZ, 128, user.worldObj.provider.dimensionId, packet);
    }

    public boolean isArmored() {
        return false;
    }

    @Override
    public String getActivateMsg() {
        return StringUtils.translate("chat.jetpack.engine") + " " + StringUtils.BRIGHT_GREEN + StringUtils.translate("chat.enabled");
    }

    @Override
    public String getDeactivateMsg() {
        return StringUtils.translate("chat.jetpack.engine") + " " + StringUtils.LIGHT_RED + StringUtils.translate("chat.disabled");
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return 0;
    }

}
