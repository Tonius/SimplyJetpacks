package tonius.simplyjetpacks.item;

import net.minecraft.item.EnumArmorMaterial;

public class ItemSJJetpackArmored extends ItemSJJetpack {

    public ItemSJJetpackArmored(int id, EnumArmorMaterial material, String name, int maxEnergy, int maxInput, int tickEnergy, double maxSpeed, double acceleration, double forwardThrust, double hoverModeIdleSpeed, double hoverModeActiveSpeed) {
        super(id, material, name, maxEnergy, maxInput, tickEnergy, maxSpeed, acceleration, forwardThrust, hoverModeIdleSpeed, hoverModeActiveSpeed);
    }

}
