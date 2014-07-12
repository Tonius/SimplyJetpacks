package tonius.simplyjetpacks.item.jetpack;

public class Jetpack {

    public int tier;
    public int energyCapacity;
    public int energyPerTick;
    public double speedVertical;
    public double accelVertical;
    public double speedSideways;
    public int energyPerTickHover;
    public double speedVerticalHover;
    public double speedVerticalHoverSneak;

    public Jetpack(int tier, int energyCapacity, int energyPerTick, double speedVertical, double accelVertical, double speedSideways, int energyPerTickHover, double speedVerticalHover, double speedVerticalHoverSneak) {
        this.tier = tier;
        this.energyCapacity = energyCapacity;
        this.energyPerTick = energyPerTick;
        this.speedVertical = speedVertical;
        this.accelVertical = accelVertical;
        this.speedSideways = speedSideways;
        this.energyPerTickHover = energyPerTickHover;
        this.speedVerticalHover = speedVerticalHover;
        this.speedVerticalHoverSneak = speedVerticalHoverSneak;
    }

    public String getBaseName() {
        return "jetpack." + this.tier;
    }

}
