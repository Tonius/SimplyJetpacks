package tonius.simplyjetpacks.item;

public class MetaItem {

    public String name;
    public String colorPrefix;
    public String[] tooltipLines;

    public MetaItem(String name, String colorPrefix, String[] tooltipLines) {
        this.name = name;
        this.colorPrefix = colorPrefix != null ? colorPrefix : "";
        this.tooltipLines = tooltipLines != null ? tooltipLines : new String[0];
    }

}
