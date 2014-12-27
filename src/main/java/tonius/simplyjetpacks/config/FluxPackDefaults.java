package tonius.simplyjetpacks.config;

public class FluxPackDefaults {
    
    public final Integer energyCapacity;
    public final Integer energyInRate;
    public final Integer energyOutRate;
    
    public final Integer armorReduction;
    public final Integer armorEnergyPerHit;
    
    public final Boolean enchantable;
    public final Integer enchantability;
    
    public FluxPackDefaults(Integer energyCapacity, Integer energyInRate, Integer energyOutRate, Integer armorReduction, Integer armorEnergyPerHit, Boolean enchantable, Integer enchantability) {
        this.energyCapacity = energyCapacity;
        this.energyInRate = energyInRate;
        this.energyOutRate = energyOutRate;
        
        this.armorReduction = armorReduction;
        this.armorEnergyPerHit = armorEnergyPerHit;
        
        this.enchantable = enchantable;
        this.enchantability = enchantability;
    }
    
}
