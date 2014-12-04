package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.ModelJetpack;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.item.jetpack.JetpackJetPlate;
import tonius.simplyjetpacks.item.jetpack.JetpackPotato;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.setup.ModItems.ModType;
import tonius.simplyjetpacks.util.StackUtils;
import tonius.simplyjetpacks.util.StringUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJetpack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IToggleable, IModeSwitchable, IEnergyHUDInfoProvider {
    
    public final ItemIndex index;
    public final ModType modType;
    private IIcon[] icons = null;
    
    public ItemJetpack(ItemIndex index, ModType modType) {
        super(ArmorMaterial.IRON, 2, 1);
        
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + "jetpack" + modType.suffix);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setCreativeTab(ModCreativeTab.tab);
        this.index = index;
        this.modType = modType;
        this.icons = new IIcon[Jetpack.getHighestMeta(index) + 1];
    }
    
    public Jetpack getJetpack(ItemStack itemStack) {
        return Jetpack.getJetpack(this.index, itemStack.getItemDamage());
    }
    
    public ItemStack getChargedItem(ItemJetpack item, int meta) {
        ItemStack full = new ItemStack(item, 1, meta);
        while (item.getEnergyStored(full) < item.getMaxEnergyStored(full)) {
            item.receiveEnergy(full, item.getMaxEnergyStored(full), false);
        }
        return full;
    }
    
    @Override
    public void toggle(ItemStack itemStack, EntityPlayer player, boolean sneakChangesToggleBehavior, boolean showInChat) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            jetpack.toggle(itemStack, player, sneakChangesToggleBehavior, showInChat);
        }
    }
    
    @Override
    public void switchMode(ItemStack itemStack, EntityPlayer player, boolean sneakChangesToggleBehavior, boolean showInChat) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            if (!player.isSneaking() || !jetpack.emergencyHoverMode || !sneakChangesToggleBehavior) {
                jetpack.switchHoverMode(itemStack, player, showInChat);
            } else {
                jetpack.switchEmergencyHoverMode(itemStack, player);
            }
        }
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return "item.simplyjetpacks." + jetpack.getBaseName() + this.modType.suffix;
        }
        return super.getUnlocalizedName();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (Config.enableJetpackModel) {
            Jetpack jetpack = this.getJetpack(itemStack);
            if (jetpack != null && jetpack.useModel) {
                return RenderUtils.getChestplateModel(entityLiving, ModelJetpack.INSTANCE);
            }
        }
        return super.getArmorModel(entityLiving, itemStack, armorSlot);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            jetpack.addInformation(itemStack, player, list, this.getEnergyStored(itemStack));
            if (StringUtils.canShowDetails()) {
                jetpack.addShiftInformation(itemStack, player, list);
            } else {
                list.add(StringUtils.getShiftText());
            }
        }
    }
    
    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return jetpack.rarity;
        }
        return super.getRarity(itemStack);
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            jetpack.useJetpack(player, itemStack, this, false);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= Jetpack.getHighestMeta(this.index); i++) {
            Jetpack jetpack = Jetpack.getJetpack(this.index, i);
            if (jetpack != null && jetpack.isVisible()) {
                if (jetpack.hasEmptyItem()) {
                    list.add(new ItemStack(this, 1, i));
                }
                list.add(this.getChargedItem(this, i));
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i <= Jetpack.getHighestMeta(this.index); i++) {
            Jetpack jetpack = Jetpack.getJetpack(this.index, i);
            if (jetpack != null) {
                this.icons[i] = register.registerIcon(SimplyJetpacks.RESOURCE_PREFIX + jetpack.getBaseName() + this.modType.suffix);
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return damage < this.icons.length ? this.icons[damage] : null;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + jetpack.getBaseName() + this.modType.suffix + (Config.enableJetpackModel && jetpack.useModel ? ".3d" : "") + ".png";
        }
        return null;
    }
    
    @Override
    public boolean showDurabilityBar(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return jetpack.hasDamageBar();
        }
        return true;
    }
    
    @Override
    public double getDurabilityForDisplay(ItemStack itemStack) {
        double stored = this.getMaxEnergyStored(itemStack) - this.getEnergyStored(itemStack) + 1;
        double max = this.getMaxEnergyStored(itemStack) + 1;
        return stored / max;
    }
    
    @Override
    public boolean isItemTool(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        return this.getItemEnchantability() > 0 ? jetpack != null ? jetpack.enchantable : false : false;
    }
    
    @Override
    public int getItemEnchantability(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return jetpack.enchantability;
        }
        return super.getItemEnchantability(itemStack);
    }
    
    @Override
    public boolean isBookEnchantable(ItemStack itemStack, ItemStack book) {
        return this.isItemTool(itemStack);
    }
    
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        Jetpack jetpack = this.getJetpack(armor);
        if (jetpack != null) {
            return jetpack.getProperties(player, this, armor, source, damage, slot);
        }
        return null;
    }
    
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        Jetpack jetpack = this.getJetpack(armor);
        if (jetpack != null) {
            return jetpack.getArmorDisplay(player, this, armor, slot);
        }
        return 0;
    }
    
    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
        Jetpack jetpack = this.getJetpack(armor);
        if (jetpack != null) {
            jetpack.damageArmor(entity, this, armor, source, damage, slot);
        }
    }
    
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = this.getEnergyStored(container);
        Jetpack jetpack = this.getJetpack(container);
        int maxInput = jetpack != null ? jetpack.energyCapacity : 0;
        int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, maxInput));
        
        if (!simulate) {
            energy += energyReceived;
            StackUtils.getNBT(container).setInteger("Energy", energy);
        }
        return energyReceived;
    }
    
    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = this.getEnergyStored(container);
        Jetpack jetpack = this.getJetpack(container);
        int maxOutput = jetpack != null ? jetpack.energyCapacity : 0;
        int energyExtracted = Math.min(energy, Math.min(maxExtract, maxOutput));
        
        if (!simulate) {
            energy -= energyExtracted;
            StackUtils.getNBT(container).setInteger("Energy", energy);
        }
        return energyExtracted;
    }
    
    @Override
    public int getEnergyStored(ItemStack container) {
        return StackUtils.getNBT(container).getInteger("Energy");
    }
    
    @Override
    public int getMaxEnergyStored(ItemStack container) {
        Jetpack jetpack = this.getJetpack(container);
        return jetpack != null ? jetpack.energyCapacity : 0;
    }
    
    @Override
    public String getEnergyInfo(ItemStack stack) {
        Jetpack jetpack = this.getJetpack(stack);
        if (jetpack != null && jetpack.hasDamageBar() && !(jetpack instanceof JetpackPotato)) {
            int energy = this.getEnergyStored(stack);
            int maxEnergy = this.getMaxEnergyStored(stack);
            int percent = (int) Math.ceil((double) energy / (double) maxEnergy * 100D);
            return StringUtils.getHUDEnergyText("jetpack", percent, energy);
        }
        return null;
    }
    
    @Override
    public String getStatesInfo(ItemStack stack) {
        Jetpack jetpack = this.getJetpack(stack);
        if (jetpack != null && !(jetpack instanceof JetpackPotato)) {
            Boolean engine = jetpack.isOn(stack);
            Boolean hover = jetpack.isHoverModeOn(stack);
            Boolean charger = null;
            if (jetpack instanceof JetpackJetPlate && ((JetpackJetPlate) jetpack).allowCharger) {
                charger = ((JetpackJetPlate) jetpack).isChargerOn(stack);
            }
            return StringUtils.getHUDStateText(engine, hover, charger);
        }
        return null;
    }
    
}
