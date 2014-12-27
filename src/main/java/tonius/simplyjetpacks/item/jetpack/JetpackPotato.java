package tonius.simplyjetpacks.item.jetpack;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.SyncTracker;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.util.FireworkUtils;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;

public class JetpackPotato extends Jetpack {
    
    public JetpackPotato(int tier, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical) {
        super(tier, EnumRarity.common, false, energyCapacity, energyPerTick, speedVertical, accelVertical, 0, 0, 0, 0, 0, false, 0, false);
    }
    
    @Override
    public String getBaseName() {
        return "jetpack.potato";
    }
    
    @Override
    public boolean hasEmptyItem() {
        return false;
    }
    
    @Override
    public void addShiftInformation(ItemStack itemStack, EntityPlayer player, List list) {
        list.add(StringUtils.getEnergyUsageText(this.energyPerTick));
        list.add(StringUtils.getParticlesText(this.getParticleType(itemStack)));
        list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.1"));
        list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.2"));
        list.add(StringUtils.LIGHT_RED + StringUtils.ITALIC + StringUtils.translate("tooltip.jetpackPotato.warning"));
    }
    
    @Override
    public void useJetpack(EntityLivingBase user, ItemStack jetpack, ItemJetpack item, boolean force) {
        if (this.isFired(jetpack)) {
            super.useJetpack(user, jetpack, item, true);
            user.rotationYawHead += 37.5F;
            if (item.getEnergyStored(jetpack) == 0) {
                user.setCurrentItemOrArmor(3, null);
                if (!user.worldObj.isRemote) {
                    Random rand = new Random();
                    user.worldObj.createExplosion(user, user.posX, user.posY, user.posZ, 4.0F, false);
                    for (int i = 0; i <= rand.nextInt(3) + 4; i++) {
                        ItemStack firework = FireworkUtils.getRandomFirework();
                        user.worldObj.spawnEntityInWorld(new EntityFireworkRocket(user.worldObj, user.posX + rand.nextDouble() * 6.0D - 3.0D, user.posY, user.posZ + rand.nextDouble() * 6.0D - 3.0D, firework));
                    }
                    user.attackEntityFrom(new EntityDamageSource("jetpackpotato", user), 100.0F);
                    if (user instanceof EntityPlayer) {
                        user.dropItem(Items.baked_potato, 1);
                    }
                }
            }
        } else {
            if (force || SyncTracker.isFlyKeyDown(user)) {
                if (this.isTimerSet(jetpack)) {
                    this.decrementTimer(jetpack, user);
                } else {
                    this.setTimer(jetpack, 50);
                }
            }
        }
    }
    
    @Override
    public void toggle(ItemStack itemStack, EntityPlayer player, boolean sneakChangesToggleBehavior, boolean showInChat) {
    }
    
    @Override
    public void switchHoverMode(ItemStack itemStack, EntityPlayer player, boolean showInChat) {
    }
    
    @Override
    public boolean switchEmergencyHoverMode(ItemStack itemStack, EntityPlayer player) {
        return false;
    }
    
    @Override
    public JetpackParticleType particleToShow(ItemStack itemStack, ItemJetpack item, EntityLivingBase user) {
        if (!this.isFired(itemStack) && SyncTracker.isFlyKeyDown(user)) {
            return user.getRNG().nextInt(5) == 0 ? JetpackParticleType.SMOKE : null;
        } else if (this.isFired(itemStack)) {
            return this.getParticleType(itemStack);
        }
        return null;
    }
    
    @Override
    public boolean isOn(ItemStack itemStack) {
        return true;
    }
    
    @Override
    public boolean isHoverModeOn(ItemStack itemStack) {
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
            user.worldObj.playSoundAtEntity(user, SimplyJetpacks.RESOURCE_PREFIX + "rocket", 1.0F, 1.0F);
        }
    }
    
}
