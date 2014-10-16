package tonius.simplyjetpacks.config;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;

public class FluxPackConfig {
    
    public final ConfigSection section;
    public final FluxPackDefaults defaults;
    
    public Integer energyCapacity;
    public Integer energyInRate;
    public Integer energyOutRate;
    
    public Integer armorDisplay;
    public Double armorAbsorption;
    public Integer armorEnergyPerHit;
    
    public Boolean enchantable;
    public Integer enchantability;
    
    public FluxPackConfig(ConfigSection section, FluxPackDefaults defaults) {
        this.section = section;
        this.defaults = defaults;
        this.setDefaults();
    }
    
    public void setDefaults() {
        this.energyCapacity = this.defaults.energyCapacity;
        this.energyInRate = this.defaults.energyInRate;
        this.energyOutRate = this.defaults.energyOutRate;
        
        this.armorDisplay = this.defaults.armorDisplay;
        this.armorAbsorption = this.defaults.armorAbsorption;
        this.armorEnergyPerHit = this.defaults.armorEnergyPerHit;
        
        this.enchantable = this.defaults.enchantable;
        this.enchantability = this.defaults.enchantability;
    }
    
    public void processConfig(Configuration config) {
        if (this.energyCapacity != null) {
            this.energyCapacity = config.get(this.section.name, "Energy Capacity", this.defaults.energyCapacity, "The maximum amount of energy that the flux pack can hold.").getInt(this.defaults.energyCapacity);
        }
        if (this.energyInRate != null) {
            this.energyInRate = config.get(this.section.name, "Energy Usage per Tick", this.defaults.energyInRate, "The rate in RF/t at which the flux pack can be charged.").getInt(this.defaults.energyInRate);
        }
        if (this.energyOutRate != null) {
            this.energyOutRate = config.get(this.section.name, "Energy Usage per Tick", this.defaults.energyOutRate, "The rate in RF/t at which the flux pack can charge other items.").getInt(this.defaults.energyOutRate);
        }
        
        if (this.armorDisplay != null) {
            this.armorDisplay = config.get(this.section.name, "Armor Display", this.defaults.armorDisplay, "How powerful the ARMORED version of the flux pack will show up on the ingame GUI. The higher the value, the more armor points show up.").getInt(this.defaults.armorDisplay);
        }
        if (this.armorAbsorption != null) {
            this.armorAbsorption = config.get(this.section.name, "Armor Absorption", this.defaults.armorAbsorption, "The relative amount of damage that the ARMORED version of the flux pack will absorb when getting hit.").getDouble(this.defaults.armorAbsorption);
        }
        if (this.armorEnergyPerHit != null) {
            int armorEnergyPerHit_temp = config.get(this.section.name, "Armor Energy Per Hit", this.defaults.armorEnergyPerHit, "The amount of energy that is consumed from the ARMORED version of the flux pack when getting hit. This value will be multiplied by the amount of damage done.").getInt(this.defaults.armorEnergyPerHit);
            this.armorEnergyPerHit = armorEnergyPerHit_temp > 0 ? armorEnergyPerHit_temp : 1;
        }
        
        if (this.enchantable != null) {
            this.enchantable = config.get(this.section.name, "Enchantable", this.defaults.enchantable, "When enabled, this flux pack will be enchantable using enchantment tables or anvils.").getBoolean(this.defaults.enchantable);
        }
        if (this.enchantability != null) {
            this.enchantability = config.get(this.section.name, "Enchantability", this.defaults.enchantability, "The enchantability of the flux pack. Note that the flux pack may be set not to be enchantable.").setMinValue(0).getInt(this.defaults.enchantability);
        }
    }
    
    public void writeToNBT(NBTTagCompound tag) {
        if (this.energyCapacity != null) {
            tag.setInteger("EnergyCapacity", this.energyCapacity);
        }
        if (this.energyInRate != null) {
            tag.setInteger("EnergyInRate", this.energyInRate);
        }
        if (this.energyOutRate != null) {
            tag.setInteger("EnergyOutRate", this.energyOutRate);
        }
        
        if (this.armorDisplay != null) {
            tag.setInteger("ArmorDisplay", this.armorDisplay);
        }
    }
    
    public void readFromNBT(NBTTagCompound tag) {
        if (this.energyCapacity != null) {
            this.energyCapacity = tag.getInteger("EnergyCapacity");
        }
        if (this.energyInRate != null) {
            this.energyInRate = tag.getInteger("EnergyInRate");
        }
        if (this.energyOutRate != null) {
            this.energyOutRate = tag.getInteger("EnergyOutRate");
        }
        
        if (this.armorDisplay != null) {
            this.armorDisplay = tag.getInteger("ArmorDisplay");
        }
    }
    
}
