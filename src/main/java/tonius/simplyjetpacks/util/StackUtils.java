package tonius.simplyjetpacks.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class StackUtils {
    
    public static NBTTagCompound getNBT(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        return itemStack.stackTagCompound;
    }
    
    public static boolean getNBTBoolean(ItemStack itemStack, String tag, boolean fallback) {
        NBTTagCompound tagCompound = getNBT(itemStack);
        if (!tagCompound.hasKey(tag)) {
            tagCompound.setBoolean(tag, fallback);
        }
        return tagCompound.getBoolean(tag);
    }
    
}
