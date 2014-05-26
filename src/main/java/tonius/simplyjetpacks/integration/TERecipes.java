package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.event.FMLInterModComms;

public class TERecipes {

    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setCompoundTag("primaryInput", new NBTTagCompound());
        toSend.setCompoundTag("secondaryInput", new NBTTagCompound());
        toSend.setCompoundTag("primaryOutput", new NBTTagCompound());
        toSend.setCompoundTag("secondaryOutput", new NBTTagCompound());

        primaryInput.writeToNBT(toSend.getCompoundTag("primaryInput"));
        secondaryInput.writeToNBT(toSend.getCompoundTag("secondaryInput"));
        primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
        if (secondaryOutput != null) {
            secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
            toSend.setInteger("secondaryChance", secondaryChance);
        }

        FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", toSend);
    }

    public static void addTransposerFill(int energy, ItemStack input, ItemStack output, FluidStack fluid, boolean reversible) {
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setCompoundTag("input", new NBTTagCompound());
        toSend.setCompoundTag("output", new NBTTagCompound());
        toSend.setCompoundTag("fluid", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("output"));
        toSend.setBoolean("reversible", reversible);
        fluid.writeToNBT(toSend.getCompoundTag("fluid"));

        FMLInterModComms.sendMessage("ThermalExpansion", "TransposerFillRecipe", toSend);
    }

}
