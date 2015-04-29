package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import buildcraft.api.recipes.BuildcraftRecipeRegistry;

public abstract class BCRecipes {
    
    public static void addAssemblyRecipe(String recipeId, int energy, ItemStack[] inputs, ItemStack output) {
        SimplyJetpacks.logger.info("Registering BC Assembly Table recipe");
        
        BuildcraftRecipeRegistry.assemblyTable.addRecipe("simplyjetpacks:" + recipeId, energy, output, (Object[]) inputs);
    }
}
