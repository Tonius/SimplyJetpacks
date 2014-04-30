package tonius.simplyjetpacks.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.PacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJJetpack extends ItemSJArmorEnergy {

    protected int tickEnergy;
    protected int tickEnergyHover;
    protected double maxSpeed;
    protected double acceleration;
    protected double forwardThrust;
    protected double hoverModeIdleSpeed;
    protected double hoverModeActiveSpeed;

    public ItemSJJetpack(int id, EnumArmorMaterial material, String name, int maxEnergy, int maxInput, int tickEnergy, double maxSpeed, double acceleration, double forwardThrust, double hoverModeIdleSpeed, double hoverModeActiveSpeed) {
        super(id, material, 2, 1, name, maxEnergy, maxInput, 0);
        this.tickEnergy = tickEnergy;
        this.tickEnergyHover = (int) (tickEnergy / 1.5);
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.forwardThrust = forwardThrust;
        this.hoverModeIdleSpeed = hoverModeIdleSpeed;
        this.hoverModeActiveSpeed = hoverModeActiveSpeed;
        this.msgActivate = "Jetpack engine " + EnumChatFormatting.GREEN + "enabled";
        this.msgDeactivate = "Jetpack engine " + EnumChatFormatting.RED + "disabled";
    }

    @Override
    public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(id, 1, 31));

        ItemStack fullJetpack = new ItemStack(id, 1, 1);
        fullJetpack.stackTagCompound = new NBTTagCompound();
        fullJetpack.stackTagCompound.setInteger("Energy", this.getMaxEnergyStored(fullJetpack));
        list.add(fullJetpack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.GOLD + "Charge: " + EnumChatFormatting.GRAY + getEnergyStored(itemStack) + "/" + this.getMaxEnergyStored(itemStack) + " RF");
        String onOrOff = this.isOn(itemStack) ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off";
        list.add(EnumChatFormatting.GOLD + "State: " + onOrOff);
        String enabledOrDisabled = this.isHoverModeActive(itemStack) ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled";
        list.add(EnumChatFormatting.GOLD + "Hover Mode: " + enabledOrDisabled);
        int currentTickEnergy = this.isHoverModeActive(itemStack) ? this.tickEnergyHover : this.tickEnergy;
        list.add(EnumChatFormatting.BLUE + "Energy usage: " + EnumChatFormatting.GRAY + currentTickEnergy + " RF/t");
        list.add(EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + "Allows flight when active.");
        list.add(EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + "Must be worn in the chestplate slot.");
    }

    @Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
        if (this.isOn(itemStack)) {
            double hoverSpeed = player.isSneaking() ? this.hoverModeActiveSpeed : this.hoverModeIdleSpeed;
            if (KeyboardTracker.isJumpKeyDown(player)) {
                this.useJetpack(player, itemStack);
            } else if (this.isHoverModeActive(itemStack) && !player.onGround && player.motionY < -hoverSpeed) {
                this.subtractEnergy(itemStack, this.tickEnergyHover, false);
                if (this.getEnergyStored(itemStack) > 0) {
                    player.motionY = -hoverSpeed;
                    if (KeyboardTracker.isForwardKeyDown(player)) {
                        player.moveFlying(0, (float) this.forwardThrust, (float) this.forwardThrust);
                    }
                    player.fallDistance = 0.0F;
                    if (player instanceof EntityPlayerMP) {
                        ((EntityPlayerMP) player).playerNetServerHandler.ticksForFloatKick = 0;
                    }
                    updateEnergyDisplay(itemStack);
                    sendJetpackPacket(player, true);
                }
            }
        }
    }

    public void useJetpack(EntityPlayer player, ItemStack itemStack) {
        boolean flown = false;
        if (!(this.isHoverModeActive(itemStack))) {
            this.subtractEnergy(itemStack, this.tickEnergy, false);
            if (this.getEnergyStored(itemStack) > 0) {
                player.motionY = Math.min(player.motionY + this.acceleration, this.maxSpeed);
                flown = true;
            }
        } else {
            this.subtractEnergy(itemStack, this.tickEnergyHover, false);
            if (this.getEnergyStored(itemStack) > 0) {
                player.motionY = Math.min(player.motionY + this.acceleration, this.hoverModeActiveSpeed);
                flown = true;
            }
        }
        if (flown) {
            if (KeyboardTracker.isForwardKeyDown(player)) {
                player.moveFlying(0, (float) this.forwardThrust, (float) this.forwardThrust);
            }
            player.fallDistance = 0.0F;
            if (player instanceof EntityPlayerMP) {
                ((EntityPlayerMP) player).playerNetServerHandler.ticksForFloatKick = 0;
            }
            sendJetpackPacket(player, this.isHoverModeActive(itemStack));
        }
        this.updateEnergyDisplay(itemStack);
    }

    public void sendJetpackPacket(EntityPlayer player, boolean hoverMode) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        try {
            data.writeInt(PacketHandler.JETPACK_TICK);
            data.writeInt(player.entityId);
            data.writeBoolean(hoverMode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "SmpJet";
        packet.data = bytes.toByteArray();
        packet.length = bytes.size();
        PacketDispatcher.sendPacketToAllAround(player.posX, player.posY, player.posZ, 128, player.worldObj.provider.dimensionId, packet);
    }

    public void toggleHoverMode(ItemStack itemStack, EntityPlayer player) {
        if (this.isHoverModeActive(itemStack)) {
            player.addChatMessage("Hover Mode " + EnumChatFormatting.RED + "disabled");
            itemStack.stackTagCompound.setBoolean("HoverModeActive", false);
        } else {
            player.addChatMessage("Hover Mode " + EnumChatFormatting.GREEN + "enabled");
            itemStack.stackTagCompound.setBoolean("HoverModeActive", true);
        }
    }

    public boolean isHoverModeActive(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        return itemStack.stackTagCompound.getBoolean("HoverModeActive");
    }

    /* IEnergyContainerItem */
    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return 0;
    }

}
