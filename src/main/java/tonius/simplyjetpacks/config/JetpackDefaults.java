package tonius.simplyjetpacks.config;

public class JetpackDefaults {
    
    public final Integer energyCapacity;
    public final Integer energyPerTick;
    
    public final Double speedVertical;
    public final Double accelVertical;
    public final Double speedVerticalHover;
    public final Double speedVerticalHoverSlow;
    public final Double speedSideways;
    public final Double sprintSpeedModifier;
    public final Double sprintEnergyModifier;
    
    public final Integer armorReduction;
    public final Integer armorEnergyPerHit;
    
    public final Boolean enchantable;
    public final Integer enchantability;
    public final Boolean emergencyHoverMode;
    
    public final Integer chargerRate;
    
    public JetpackDefaults(Integer energyCapacity, Integer energyPerTick, Double speedVertical, Double accelVertical, Double speedVerticalHover, Double speedVerticalHoverSlow, Double speedSideways, Double sprintSpeedModifier, Double sprintEnergyModifier, Integer armorReduction, Integer armorEnergyPerHit, Boolean enchantable, Integer enchantability, Boolean emergencyHoverMode, Integer chargerRate) {
        this.energyCapacity = energyCapacity;
        this.energyPerTick = energyPerTick;
        
        this.speedVertical = speedVertical;
        this.accelVertical = accelVertical;
        this.speedVerticalHover = speedVerticalHover;
        this.speedVerticalHoverSlow = speedVerticalHoverSlow;
        this.speedSideways = speedSideways;
        this.sprintSpeedModifier = sprintSpeedModifier != null ? sprintSpeedModifier : 1.0D;
        this.sprintEnergyModifier = sprintEnergyModifier != null ? sprintEnergyModifier : 1.0D;
        
        this.armorReduction = armorReduction;
        this.armorEnergyPerHit = armorEnergyPerHit;
        
        this.enchantable = enchantable;
        this.enchantability = enchantability;
        this.emergencyHoverMode = emergencyHoverMode;
        
        this.chargerRate = chargerRate;
    }
    
}
