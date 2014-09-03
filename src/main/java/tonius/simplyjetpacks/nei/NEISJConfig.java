package tonius.simplyjetpacks.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEISJConfig implements IConfigureNEI {
    
    @Override
    public void loadConfig() {
        API.registerRecipeHandler(new ArmoringRecipeHandler());
        API.registerUsageHandler(new ArmoringRecipeHandler());
        API.registerRecipeHandler(new DeArmoringRecipeHandler());
        API.registerUsageHandler(new DeArmoringRecipeHandler());
    }
    
    @Override
    public String getName() {
        return "Simply Jetpacks";
    }
    
    @Override
    public String getVersion() {
        return "1.0";
    }
    
}
