package tonius.simplyjetpacks.config;

public class ConfigSection {
    
    public boolean client;
    public String name;
    public String id;
    
    public ConfigSection(boolean client, String name, String id) {
        this.client = client;
        this.name = name;
        this.id = id;
        SJConfig.configSections.add(this);
    }
    
    public String toLowerCase() {
        return this.name.toLowerCase();
    }
    
}
