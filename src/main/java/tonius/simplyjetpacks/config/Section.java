package tonius.simplyjetpacks.config;

public class Section {
    
    public final boolean client;
    public final String name;
    public final String id;
    
    public Section(boolean client, String name, String id) {
        this.client = client;
        this.name = name;
        this.id = id;
        Config.configSections.add(this);
    }
    
    public String toLowerCase() {
        return this.name.toLowerCase();
    }
    
}
