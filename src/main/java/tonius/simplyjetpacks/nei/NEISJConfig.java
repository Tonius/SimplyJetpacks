package tonius.simplyjetpacks.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEISJConfig implements IConfigureNEI {
    
    @Override
    public void loadConfig() {
        API.registerRecipeHandler(new RecipeHandlerArmoring());
        API.registerUsageHandler(new RecipeHandlerArmoring());
    }
    
    @Override
    public String getName() {
        return "Simply Jetpacks";
    }
    
    @Override
    public String getVersion() {
        return "1.1";
    }
    
}
