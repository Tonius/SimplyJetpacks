package tonius.simplyjetpacks.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class DamageSourcePotatoJetpack extends DamageSource {

    protected DamageSourcePotatoJetpack(String damageType) {
        super(damageType);
    }

    public static DamageSource causeJetpackPotatoDamage(Entity entity) {
        return new EntityDamageSource("jetpackpotato", entity);
    }
}
