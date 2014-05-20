package tonius.simplyjetpacks.item;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.util.DamageSourceJetpackPotato;
import tonius.simplyjetpacks.util.FireworksGenerator;
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
        if (StringUtils.isShiftKeyDown()) {
            list.add(StringUtils.getChargeText(this.getEnergyStored(itemStack), this.getMaxEnergyStored(itemStack)));
            list.add(StringUtils.getEnergyUsageText(this.tickEnergy));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpackPotato.description.1"));
            list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpackPotato.description.2"));
            list.add(StringUtils.LIGHT_RED + StringUtils.ITALIC + StringUtils.translate("tooltip.jetpackPotato.warning"));
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

    @Override
    public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
        list.add(this.getChargedItem(this));
    }

    @Override
    public void useJetpack(EntityLivingBase user, ItemStack jetpack, boolean force) {
        if (this.isFired(jetpack)) {
            super.useJetpack(user, jetpack, true);
            if (this.getEnergyStored(jetpack) == 0) {
                user.setCurrentItemOrArmor(3, null);
                if (!user.worldObj.isRemote) {
                    Random rand = new Random();
                    user.worldObj.createExplosion(user, user.posX, user.posY, user.posZ, 4.0F, false);
                    for (int i = 0; i <= rand.nextInt(3) + 4; i++) {
                        ItemStack firework = FireworksGenerator.randomFirework();
                        user.worldObj.spawnEntityInWorld(new EntityFireworkRocket(user.worldObj, user.posX + rand.nextDouble() * 6.0D - 3.0D, user.posY, user.posZ + rand.nextDouble() * 6.0D - 3.0D, firework));
                    }
                    user.attackEntityFrom(DamageSourceJetpackPotato.causeJetpackPotatoDamage(user), 20.0F);
                    user.dropItem(Item.poisonousPotato.itemID, 1);
                }
            }
        } else {
            boolean jumpKeyDown = true;
            if (!force && user instanceof EntityPlayer && !KeyboardTracker.isJumpKeyDown((EntityPlayer) user)) {
                jumpKeyDown = false;
            }
            if (jumpKeyDown) {
                if (this.isTimerSet(jetpack)) {
                    if (user.getRNG().nextInt(5) == 0) {
                        this.sendJetpackPacket(user, true);
                    }
                    this.decrementTimer(jetpack, user);
                } else {
                    this.setTimer(jetpack, 50);
                }
            }
        }
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
