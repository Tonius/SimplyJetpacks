package tonius.simplyjetpacks.item;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.util.DamageSourceJetpackPotato;
import tonius.simplyjetpacks.util.FireworksGenerator;
import tonius.simplyjetpacks.util.LangUtils;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSJJetpackPotato extends ItemSJJetpack {

    public ItemSJJetpackPotato(int id, EnumArmorMaterial material) {
        super(id, material, "jetpackTier0", 1200, 0, 45, 0.9D, 0.5D, 0, 0.25, 0.25);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        if (StringUtils.isControlKeyDown() && StringUtils.isShiftKeyDown()) {
            list.add(StringUtils.LIGHT_BLUE + LangUtils.translate("tooltip.jetpackPotato.description.1"));
            list.add(StringUtils.LIGHT_BLUE + LangUtils.translate("tooltip.jetpackPotato.description.2"));
            list.add(StringUtils.LIGHT_RED + StringUtils.ITALIC + LangUtils.translate("tooltip.jetpackPotato.warning"));
        } else if (StringUtils.isShiftKeyDown()) {
            list.add(StringUtils.getChargeText(this.getEnergyStored(itemStack), this.getMaxEnergyStored(itemStack)));
            list.add(StringUtils.getEnergyUsageText(this.tickEnergy));
        } else {
            list.add(StringUtils.getShiftText());
            list.add(StringUtils.getCtrlShiftText());
        }
    }

    @Override
    public void useJetpack(EntityLivingBase user, ItemStack itemStack) {
        if (this.isFired(itemStack)) {
            boolean flown = false;
            this.subtractEnergy(itemStack, this.tickEnergy, false);
            if (getEnergyStored(itemStack) > 0) {
                user.motionY = Math.min(user.motionY + this.acceleration, this.maxSpeed);
                flown = true;
            } else {
                user.setCurrentItemOrArmor(3, null);
                if (!user.worldObj.isRemote) {
                    Random rand = new Random();
                    user.worldObj.createExplosion(user, user.posX, user.posY, user.posZ, 4.0F, false);
                    for (int i = 0; i <= rand.nextInt(3) + 4; i++) {
                        ItemStack firework = FireworksGenerator.randomFirework();
                        user.worldObj.spawnEntityInWorld(new EntityFireworkRocket(user.worldObj, user.posX + rand.nextDouble() * 6 - 3, user.posY, user.posZ + rand.nextDouble() * 6 - 3, firework));
                    }
                    user.attackEntityFrom(DamageSourceJetpackPotato.causeJetpackPotatoDamage(user), 20.0F);
                    user.dropItem(Item.poisonousPotato.itemID, rand.nextInt(4) + 2);
                }
            }
            if (flown) {
                if (!(user instanceof EntityPlayer)) {
                    user.moveFlying(0, (float) this.forwardThrust, (float) this.forwardThrust);
                } else if (KeyboardTracker.isForwardKeyDown((EntityPlayer) user)) {
                    user.moveFlying(0, (float) this.forwardThrust, (float) this.forwardThrust);
                }
                user.fallDistance = 0.0F;
                if (user instanceof EntityPlayerMP) {
                    ((EntityPlayerMP) user).playerNetServerHandler.ticksForFloatKick = 0;
                }
                this.sendJetpackPacket(user, this.isHoverModeActive(itemStack));
            }
        } else {
            if (this.isTimerSet(itemStack)) {
                if (user.getRNG().nextInt(5) == 0) {
                    this.sendJetpackPacket(user, true);
                }
                this.decrementTimer(itemStack, user);
            } else {
                this.setTimer(itemStack, 50);
            }
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

    public boolean isFired(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("Fired");
    }

    public void setFired(ItemStack itemStack) {
        StackUtils.getNBT(itemStack).setBoolean("Fired", true);
    }

    public void setTimer(ItemStack itemStack, int timer) {
        StackUtils.getNBT(itemStack);
        itemStack.stackTagCompound.setInteger("RocketTimer", timer);
        itemStack.stackTagCompound.setBoolean("TimerSet", true);
    }

    public boolean isTimerSet(ItemStack itemStack) {
        return StackUtils.getNBT(itemStack).getBoolean("TimerSet");
    }

    public void decrementTimer(ItemStack itemStack, EntityLivingBase user) {
        StackUtils.getNBT(itemStack);
        int timer = itemStack.stackTagCompound.getInteger("RocketTimer");
        timer = timer > 0 ? timer - 1 : 0;
        itemStack.stackTagCompound.setInteger("RocketTimer", timer);
        if (timer == 0) {
            this.setFired(itemStack);
            user.worldObj.playSoundAtEntity(user, "simplyjetpacks:rocket", 1.0F, 1.0F);
        }
    }

}
