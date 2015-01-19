package tonius.simplyjetpacks.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class StackUtils {
    
    public static NBTTagCompound getNBT(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
        }
        return stack.stackTagCompound;
    }
    
    public static boolean getNBTBoolean(ItemStack stack, String tag, boolean fallback) {
        NBTTagCompound tagCompound = getNBT(stack);
        if (!tagCompound.hasKey(tag)) {
            tagCompound.setBoolean(tag, fallback);
        }
        return tagCompound.getBoolean(tag);
    }
    
}
