package tonius.simplyjetpacks.item;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemComponents extends ItemSJ {

    public ItemComponents(int id) {
        super(id, "components", 9);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        int dmg = itemStack.getItemDamage();
        if (dmg >= 0 && dmg <= 3 && player.onGround) {
            Random rand = player.getRNG();
            for (int i = 0; i <= 25; i++) {
                world.spawnParticle("smoke", player.posX + rand.nextDouble() * 0.8D - 0.4D, player.posY - 1.2D + rand.nextDouble() * 0.2D - 0.1D, player.posZ + rand.nextDouble() * 0.8D - 0.4D, 0.0D, 0.0D, 0.0D);
            }
            world.playSoundAtEntity(player, "mob.chicken.plop", 1.5F, ((dmg + 1.2F) / 3) + rand.nextFloat() * 0.5F);
            player.motionY = 0.54D + dmg / 10.0D;
        }
        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        switch (itemStack.getItemDamage()) {
        case 2:
            return StringUtils.YELLOW + super.getItemDisplayName(itemStack);
        case 3:
        case 8:
            return StringUtils.BRIGHT_BLUE + super.getItemDisplayName(itemStack);
        }
        return super.getItemDisplayName(itemStack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        if (StringUtils.canShowDetails()) {
            switch (itemStack.getItemDamage()) {
            case 0:
            case 1:
            case 2:
            case 3:
                list.add(StringUtils.translate("tooltip.thruster.description.1"));
                list.add(StringUtils.translate("tooltip.thruster.description.2"));
                break;
            case 4:
                list.add(StringUtils.translate("tooltip.leatherStrap.description.1"));
                list.add(StringUtils.translate("tooltip.leatherStrap.description.2"));
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                list.add(StringUtils.translate("tooltip.armorPlating.description.1"));
                list.add(StringUtils.translate("tooltip.armorPlating.description.2"));
            }
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

}
