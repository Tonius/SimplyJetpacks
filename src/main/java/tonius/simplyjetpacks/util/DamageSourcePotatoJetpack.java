package tonius.simplyjetpacks.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.StatCollector;

public class DamageSourcePotatoJetpack extends DamageSource {

    protected DamageSourcePotatoJetpack(String damageType) {
        super(damageType);
    }

    public static DamageSource causeJetpackPotatoDamage(Entity entity) {
        return new EntityDamageSource("jetpackpotato", entity);
    }

    @Override
    public ChatMessageComponent getDeathMessage(EntityLivingBase entityLivingBase) {
        EntityLivingBase entitylivingbase1 = entityLivingBase.func_94060_bK();
        String s = "death." + this.damageType;
        String s1 = s + ".player";
        return entitylivingbase1 != null && StatCollector.func_94522_b(s1) ? ChatMessageComponent.createFromTranslationWithSubstitutions(s1, new Object[] { entityLivingBase.getTranslatedEntityName(), entitylivingbase1.getTranslatedEntityName() }) : ChatMessageComponent.createFromTranslationWithSubstitutions(s, new Object[] { entityLivingBase.getTranslatedEntityName() });
    }
}
