package tonius.simplyjetpacks.config;

public class FluxPackDefaults {
    
    public final Integer energyCapacity;
    public final Integer energyInRate;
    public final Integer energyOutRate;
    
    public final Integer armorDisplay;
    public final Double armorAbsorption;
    public final Integer armorEnergyPerHit;
    
    public final Boolean enchantable;
    public final Integer enchantability;
    
    public FluxPackDefaults(Integer energyCapacity, Integer energyInRate, Integer energyOutRate, Integer armorDisplay, Double armorAbsorption, Integer armorEnergyPerHit, Boolean enchantable, Integer enchantability) {
        this.energyCapacity = energyCapacity;
        this.energyInRate = energyInRate;
        this.energyOutRate = energyOutRate;
        
        this.armorDisplay = armorDisplay;
        this.armorAbsorption = armorAbsorption;
        this.armorEnergyPerHit = armorEnergyPerHit;
        
        this.enchantable = enchantable;
        this.enchantability = enchantability;
    }
    
}
