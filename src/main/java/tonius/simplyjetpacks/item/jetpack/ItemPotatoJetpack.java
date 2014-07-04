package tonius.simplyjetpacks.item.jetpack;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.KeyboardTracker;
import tonius.simplyjetpacks.config.MainConfig;
import tonius.simplyjetpacks.util.DamageSourcePotatoJetpack;
import tonius.simplyjetpacks.util.FireworksGenerator;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPotatoJetpack extends ItemJetpack {

    public ItemPotatoJetpack(ArmorMaterial material, String name, int maxEnergy, int maxInput, int jetpackTier, int tickEnergy, double maxSpeed, double acceleration, double forwardThrust, double hoverModeIdleSpeed, double hoverModeActiveSpeed) {
        super(material, name, maxEnergy, maxInput, jetpackTier, tickEnergy, maxSpeed, acceleration, forwardThrust, hoverModeIdleSpeed, hoverModeActiveSpeed);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        if (StringUtils.canShowDetails()) {
            list.add(StringUtils.getChargeText(false, this.getEnergyStored(itemStack), this.getMaxEnergyStored(itemStack)));
            list.add(StringUtils.getEnergyUsageText(this.tickEnergy));
            if (!MainConfig.hideJetpackTier0Warning) {
                list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpackPotato.description.1"));
                list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpackPotato.description.2"));
                list.add(StringUtils.LIGHT_RED + StringUtils.ITALIC + StringUtils.translate("tooltip.jetpackPotato.warning"));
            } else {
                list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.1"));
                list.add(StringUtils.BRIGHT_GREEN + StringUtils.translate("tooltip.jetpack.description.2"));
            }
        } else {
            list.add(StringUtils.getShiftText());
        }
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
                    user.attackEntityFrom(DamageSourcePotatoJetpack.causeJetpackPotatoDamage(user), 20.0F);
                    user.dropItem(Items.baked_potato, 1);
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
                        this.sendParticlePacket(user, JetpackParticleType.SMOKE);
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
