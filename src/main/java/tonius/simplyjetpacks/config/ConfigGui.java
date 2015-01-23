package tonius.simplyjetpacks.config;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.util.StringUtils;

public class ConfigGui extends GuiConfig {
    
    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), SimplyJetpacks.MODID, false, false, StringUtils.translate("config.title"));
    }
    
    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        String prefix = SimplyJetpacks.PREFIX + "config.";
        
        for (Section configSection : Config.configSections) {
            if (configSection.client) {
                list.add(new ConfigElement<ConfigCategory>(Config.configClient.getCategory(configSection.toLowerCase()).setLanguageKey(prefix + configSection.id)));
            } else {
                list.add(new ConfigElement<ConfigCategory>(Config.config.getCategory(configSection.toLowerCase()).setLanguageKey(prefix + configSection.id)));
            }
        }
        
        return list;
    }
    
}
