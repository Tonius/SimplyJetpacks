package tonius.simplyjetpacks.integration;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class EIORecipes {
    
    public static void addAlloySmelterRecipe(String name, int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack tertiaryInput, ItemStack output) {
        SimplyJetpacks.logger.info("Registering EIO Alloy Smelter recipe");
        
        StringBuilder toSend = new StringBuilder();
        
        toSend.append("<recipeGroup name=\"" + SimplyJetpacks.MODID + "\">");
        {
            toSend.append("<recipe name=\"" + name + "\" energyCost=\"" + energy + "\">");
            {
                toSend.append("<input>");
                {
                    appendItemStack(toSend, primaryInput);
                    appendItemStack(toSend, secondaryInput);
                    appendItemStack(toSend, tertiaryInput);
                }
                toSend.append("</input>");
                
                toSend.append("<output>");
                {
                    appendItemStack(toSend, output);
                }
                toSend.append("</output>");
            }
            toSend.append("</recipe>");
        }
        toSend.append("</recipeGroup>");
        
        FMLInterModComms.sendMessage("EnderIO", "recipe:alloysmelter", toSend.toString());
    }
    
    private static void appendItemStack(StringBuilder sb, ItemStack stack) {
        if (stack != null) {
            String[] itemName = Item.itemRegistry.getNameForObject(stack.getItem()).split(":");
            sb.append("<itemStack modID=\"" + itemName[0] + "\" itemName=\"" + itemName[1] + "\" itemMeta=\"" + stack.getItemDamage() + "\" number=\"" + stack.stackSize + "\" />");
        }
    }
    
    public static void addSoulBinderRecipe(String recipeID, int energy, int xp, String soulTypes, ItemStack input, ItemStack output) {
        SimplyJetpacks.logger.info("Registering EIO Soul Binder recipe");
        
        NBTTagCompound toSend = new NBTTagCompound();
        
        toSend.setString("recipeUID", recipeID);
        toSend.setInteger("requiredEnergyRF", energy);
        toSend.setInteger("requiredXP", xp);
        toSend.setString("entityTypes", soulTypes);
        writeItemStack(toSend, "inputStack", input);
        writeItemStack(toSend, "outputStack", output);
        
        FMLInterModComms.sendMessage("EnderIO", "recipe:soulbinder", toSend);
    }
    
    private static void writeItemStack(NBTTagCompound nbt, String tagName, ItemStack stack) {
        if (stack != null) {
            NBTTagCompound stackTag = new NBTTagCompound();
            stack.writeToNBT(stackTag);
            nbt.setTag(tagName, stackTag);
        }
    }
    
}
