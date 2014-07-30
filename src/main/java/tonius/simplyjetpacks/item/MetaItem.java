package tonius.simplyjetpacks.item;

import net.minecraft.item.EnumRarity;

public class MetaItem {

    public String name;
    public String[] tooltipLines;
    public EnumRarity rarity;

    public MetaItem(String name, String[] tooltipLines, EnumRarity rarity) {
        this.name = name;
        this.tooltipLines = tooltipLines != null ? tooltipLines : new String[0];
        this.rarity = rarity;
    }

}
