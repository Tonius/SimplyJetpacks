package tonius.simplyjetpacks.item;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.util.DamageSourceJetpackPotato;
import tonius.simplyjetpacks.util.FireworksGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJJetpackPotato extends ItemSJJetpack {

    public ItemSJJetpackPotato(int id, EnumArmorMaterial material) {
        super(id, material, "jetpackTier0", 1200, 0, 30, 1.0D, 0.32D, 0, 0.25, 0.25);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.GOLD + "Charge: " + EnumChatFormatting.GRAY + getEnergyStored(itemStack) + "/" + this.getMaxEnergyStored(itemStack) + " RF");
        list.add(EnumChatFormatting.BLUE + "Energy usage: " + EnumChatFormatting.GRAY + this.tickEnergy + " RF/t");
        list.add(EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + "Allows a short and deadly flight.");
        list.add(EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + "Must be worn in the chestplate slot.");
        list.add(EnumChatFormatting.RED + "" + EnumChatFormatting.ITALIC + "Note: Do not use if you do not wish to die.");
    }

    @Override
    public void useJetpack(EntityPlayer player, ItemStack itemStack) {
        boolean flown = false;
        this.subtractEnergy(itemStack, this.tickEnergy, false);
        if (getEnergyStored(itemStack) > 0) {
            player.motionY = Math.min(player.motionY + this.acceleration, this.maxSpeed);
            flown = true;
        } else {
            player.inventory.armorInventory[2] = null;
            if (!player.worldObj.isRemote) {
                Random rand = new Random();
                player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 4.0F, false);
                for (int i = 0; i <= rand.nextInt(3) + 4; i++) {
                    ItemStack firework = FireworksGenerator.randomFirework();
                    player.worldObj.spawnEntityInWorld(new EntityFireworkRocket(player.worldObj, player.posX + rand.nextDouble() * 6 - 3, player.posY, player.posZ + rand.nextDouble() * 6 - 3, firework));
                }
                player.attackEntityFrom(DamageSourceJetpackPotato.causeJetpackPotatoDamage(player), 20.0F);
                player.dropItem(Item.poisonousPotato.itemID, rand.nextInt(4) + 2);
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
        updateEnergyDisplay(itemStack);
    }

    @Override
    public void toggle(ItemStack itemStack, EntityPlayer player) {
    }

    @Override
    public void toggleHoverMode(ItemStack itemStack, EntityPlayer player) {
    }

    @Override
    public boolean isOn(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean isHoverModeActive(ItemStack itemStack) {
        return false;
    }

}
