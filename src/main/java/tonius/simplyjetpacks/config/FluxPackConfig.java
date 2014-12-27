package tonius.simplyjetpacks.config;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;

public class FluxPackConfig {
    
    public final Section section;
    public final FluxPackDefaults defaults;
    
    public Integer energyCapacity;
    public Integer energyInRate;
    public Integer energyOutRate;
    
    public Integer armorReduction;
    public Integer armorEnergyPerHit;
    
    public Boolean enchantable;
    public Integer enchantability;
    
    public FluxPackConfig(Section section, FluxPackDefaults defaults) {
        this.section = section;
        this.defaults = defaults;
        this.setDefaults();
    }
    
    public void setDefaults() {
        this.energyCapacity = this.defaults.energyCapacity;
        this.energyInRate = this.defaults.energyInRate;
        this.energyOutRate = this.defaults.energyOutRate;
        
        this.armorReduction = this.defaults.armorReduction;
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
        
        if (this.armorReduction != null) {
            this.armorReduction = config.get(this.section.name, "Armor Reduction", this.defaults.armorReduction, "How well the ARMORED version of the flux pack can protect the user from damage. The higher the value, the stronger the flux pack's armor will be.").setMinValue(0).setMaxValue(20).getInt(this.defaults.armorReduction);
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
        
        if (this.armorReduction != null) {
            tag.setInteger("ArmorReduction", this.armorReduction);
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
        
        if (this.armorReduction != null) {
            this.armorReduction = tag.getInteger("ArmorReduction");
        }
    }
    
}
