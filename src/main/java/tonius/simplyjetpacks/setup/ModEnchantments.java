package tonius.simplyjetpacks.setup;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.enchantment.EnchantmentFuelEfficiency;

public abstract class ModEnchantments {
    
    public static final EnumEnchantmentType enchantType = EnumHelper.addEnchantmentType(SimplyJetpacks.MODID);
    public static Enchantment fuelEffeciency = null;
    
    public static void init() {
        int id = Config.enchantFuelEfficiencyID;
        
        if (id > 0) {
            fuelEffeciency = new EnchantmentFuelEfficiency(id);
        }
    }
    
}
