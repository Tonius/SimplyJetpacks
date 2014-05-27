package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemParticleCustomizers extends ItemSJ {

    public ItemParticleCustomizers(int id) {
        super(id, "particleCustomizers", 8);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        if (StringUtils.canShowDetails()) {
            list.add(StringUtils.translate("tooltip.particleCustomizers.description.1"));
            list.add(StringUtils.translate("tooltip.particleCustomizers.description.2"));
        } else {
            list.add(StringUtils.getShiftText());
        }
    }

}
