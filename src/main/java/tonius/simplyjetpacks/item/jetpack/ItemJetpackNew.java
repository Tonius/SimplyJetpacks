package tonius.simplyjetpacks.item.jetpack;

import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.ISpecialArmor;
import tonius.simplyjetpacks.setup.SJCreativeTab;
import tonius.simplyjetpacks.util.StackUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJetpackNew extends ItemArmor implements ISpecialArmor, IEnergyContainerItem {

    private static final ArmorMaterial material = ArmorMaterial.IRON;

    protected final Map<Integer, Jetpack> subJetpacks;
    protected final int highestMeta;

    protected IIcon[] icons = null;

    public ItemJetpackNew(Map<Integer, Jetpack> subJetpacks) {
        super(material, 2, 1);

        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setCreativeTab(SJCreativeTab.tab);

        this.subJetpacks = subJetpacks;
        int highestMeta = 0;
        for (int meta : subJetpacks.keySet()) {
            if (meta > highestMeta) {
                highestMeta = meta;
            }
        }
        this.highestMeta = highestMeta;
        this.icons = new IIcon[highestMeta + 1];
    }

    public Jetpack getJetpack(ItemStack itemStack) {
        return this.subJetpacks.get(itemStack.getItemDamage());
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        Jetpack jetpack = this.getJetpack(itemStack);
        if (jetpack != null) {
            return "item.simplyjetpacks." + jetpack.getBaseName();
        }
        return "item.tonisutilties.jetpack";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= this.highestMeta; i++) {
            if (this.subJetpacks.get(i) != null) {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i <= this.highestMeta; i++) {
            Jetpack jetpack = this.subJetpacks.get(i);
            if (jetpack != null) {
                this.icons[i] = register.registerIcon("tonisutilities:" + jetpack.getBaseName());
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
            return "simplyjetpacks:textures/armor/" + jetpack.getBaseName() + ".png";
        }
        return null;
    }

    @Override
    public boolean isDamaged(ItemStack itemStack) {
        return itemStack.getItemDamageForDisplay() < Short.MAX_VALUE;
    }

    @Override
    public int getMaxDamage(ItemStack itemStack) {
        return this.getMaxEnergyStored(itemStack) + 1;
    }

    @Override
    public int getDisplayDamage(ItemStack itemStack) {
        return this.getMaxEnergyStored(itemStack) - this.getEnergyStored(itemStack) + 1;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, 1, 0);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = getEnergyStored(container);
        Jetpack jetpack = this.getJetpack(container);
        int maxInput = jetpack != null ? jetpack.energyCapacity : 0;
        int energyReceived = Math.min(getMaxEnergyStored(container) - energy, Math.min(maxReceive, maxInput));

        if (!simulate) {
            energy += energyReceived;
            StackUtils.getNBT(container).setInteger("Energy", energy);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = getEnergyStored(container);
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
}
