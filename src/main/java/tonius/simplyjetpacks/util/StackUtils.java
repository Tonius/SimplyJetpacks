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

    public static ItemStack decrementStack(ItemStack itemStack) {
        if (--itemStack.stackSize <= 0) {
            itemStack = null;
        }
        return itemStack;
    }

}
