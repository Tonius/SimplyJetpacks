package tonius.simplyjetpacks.item.meta;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.lib.util.helpers.FireworksHelper;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class JetpackPotato extends Jetpack {
    
    protected static final String TAG_FIRED = "JetpackPotatoFired";
    protected static final String TAG_ROCKET_TIMER = "JetpackPotatoRocketTimer";
    protected static final String TAG_ROCKET_TIMER_SET = "JetpackPotatoRocketTimerSet";
    
    public JetpackPotato(int tier, EnumRarity rarity, String defaultConfigKey) {
        super(tier, rarity, defaultConfigKey);
        this.setHasStateIndicators(false);
        this.setShowEmptyInCreativeTab(false);
        this.setArmorModel(PackModelType.FLAT);
    }
    
    @Override
    public void flyUser(EntityLivingBase user, ItemStack stack, ItemPack item, boolean force) {
        if (this.isFired(stack)) {
            super.flyUser(user, stack, item, true);
            user.rotationYawHead += 37.5F;
            if (item.getFuelStored(stack) <= 0) {
                user.setCurrentItemOrArmor(3, null);
                if (!user.worldObj.isRemote) {
                    user.worldObj.createExplosion(user, user.posX, user.posY, user.posZ, 4.0F, false);
                    for (int i = 0; i <= MathHelper.RANDOM.nextInt(3) + 4; i++) {
                        ItemStack firework = FireworksHelper.getRandomFireworks(0, 1, MathHelper.RANDOM.nextInt(6) + 1, 1);
                        user.worldObj.spawnEntityInWorld(new EntityFireworkRocket(user.worldObj, user.posX + MathHelper.RANDOM.nextDouble() * 6.0D - 3.0D, user.posY, user.posZ + MathHelper.RANDOM.nextDouble() * 6.0D - 3.0D, firework));
                    }
                    user.attackEntityFrom(new EntityDamageSource("jetpackpotato", user), 100.0F);
                    if (user instanceof EntityPlayer) {
                        user.dropItem(Items.baked_potato, 1);
                    }
                }
            }
        } else {
            if (force || SyncHandler.isFlyKeyDown(user)) {
                if (this.isTimerSet(stack)) {
                    this.decrementTimer(stack, user);
                } else {
                    this.setTimer(stack, 50);
                }
            }
        }
    }
    
    @Override
    public boolean isOn(ItemStack stack) {
        return true;
    }
    
    @Override
    public boolean isHoverModeOn(ItemStack stack) {
        return false;
    }
    
    @Override
    public void togglePrimary(ItemStack stack, EntityPlayer player, boolean showInChat) {
    }
    
    @Override
    public void switchModePrimary(ItemStack stack, EntityPlayer player, boolean showInChat) {
    }
    
    @Override
    public ParticleType getDisplayParticleType(ItemStack itemStack, ItemPack item, EntityLivingBase user) {
        if (!this.isFired(itemStack) && SyncHandler.isFlyKeyDown(user)) {
            return user.getRNG().nextInt(5) == 0 ? ParticleType.SMOKE : null;
        } else if (this.isFired(itemStack)) {
            return this.getParticleType(itemStack);
        }
        return null;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addShiftInformation(ItemStack stack, ItemPack item, EntityPlayer player, List list) {
        super.addShiftInformation(stack, item, player, list);
        list.add(StringHelper.LIGHT_RED + StringHelper.ITALIC + SJStringHelper.localize("tooltip.jetpackPotato.warning"));
    }
    
    protected boolean isFired(ItemStack itemStack) {
        return NBTHelper.getNBT(itemStack).getBoolean(TAG_FIRED);
    }
    
    protected void setFired(ItemStack itemStack) {
        NBTHelper.getNBT(itemStack).setBoolean(TAG_FIRED, true);
    }
    
    protected void setTimer(ItemStack itemStack, int timer) {
        NBTHelper.getNBT(itemStack).setInteger(TAG_ROCKET_TIMER, timer);
        NBTHelper.getNBT(itemStack).setBoolean(TAG_ROCKET_TIMER_SET, true);
    }
    
    protected boolean isTimerSet(ItemStack itemStack) {
        return NBTHelper.getNBT(itemStack).getBoolean(TAG_ROCKET_TIMER_SET);
    }
    
    protected void decrementTimer(ItemStack itemStack, EntityLivingBase user) {
        int timer = NBTHelper.getNBT(itemStack).getInteger(TAG_ROCKET_TIMER);
        timer = timer > 0 ? timer - 1 : 0;
        NBTHelper.getNBT(itemStack).setInteger(TAG_ROCKET_TIMER, timer);
        if (timer == 0) {
            this.setFired(itemStack);
            user.worldObj.playSoundAtEntity(user, SimplyJetpacks.RESOURCE_PREFIX + "rocket", 1.0F, 1.0F);
        }
    }
    
}
