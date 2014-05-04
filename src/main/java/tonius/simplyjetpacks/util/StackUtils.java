package tonius.simplyjetpacks.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackUtils {

    public static NBTTagCompound getNBT(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        return itemStack.stackTagCompound;
    }

}
