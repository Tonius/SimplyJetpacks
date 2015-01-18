package tonius.simplyjetpacks.setup;

public enum FuelType {
    
    ENERGY(" RF"),
    FLUID(" mB");
    
    public final String suffix;
    
    private FuelType(String suffix) {
        this.suffix = suffix;
    }
    
}
