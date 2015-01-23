package tonius.simplyjetpacks.integration;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class TERecipes {
    
    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
        SimplyJetpacks.logger.info("Registering TE Induction Smelter recipe");
        
        NBTTagCompound toSend = new NBTTagCompound();
        
        toSend.setInteger("energy", energy);
        toSend.setTag("primaryInput", new NBTTagCompound());
        toSend.setTag("secondaryInput", new NBTTagCompound());
        toSend.setTag("primaryOutput", new NBTTagCompound());
        toSend.setTag("secondaryOutput", new NBTTagCompound());
        
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
        SimplyJetpacks.logger.info("Registering TE Fluid Transposer fill recipe");
        
        NBTTagCompound toSend = new NBTTagCompound();
        
        toSend.setInteger("energy", energy);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("output", new NBTTagCompound());
        toSend.setTag("fluid", new NBTTagCompound());
        
        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("output"));
        toSend.setBoolean("reversible", reversible);
        fluid.writeToNBT(toSend.getCompoundTag("fluid"));
        
        FMLInterModComms.sendMessage("ThermalExpansion", "TransposerFillRecipe", toSend);
    }
    
}
