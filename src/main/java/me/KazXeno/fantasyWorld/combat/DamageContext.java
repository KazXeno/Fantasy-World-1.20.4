package me.KazXeno.fantasyWorld.combat;

import me.KazXeno.fantasyWorld.entity.CombatEntity;

public class DamageContext {

    // Damage attacker
    private final CombatEntity attacker;

    // Damage victim
    private final CombatEntity victim;

    // Base skill damage
    private final double baseDamage;

    // Damage scaling multiplier
    private final double scaling;

    // Damage type
    private final DamageType damageType;

    public DamageContext(CombatEntity attacker,
                         CombatEntity victim,
                         double baseDamage,
                         double scaling,
                         DamageType damageType) {

        this.attacker = attacker;
        this.victim = victim;
        this.baseDamage = baseDamage;
        this.scaling = scaling;
        this.damageType = damageType;
    }

    // Get attacker
    public CombatEntity getAttacker() {
        return attacker;
    }

    // Get victim
    public CombatEntity getVictim() {
        return victim;
    }

    // Get base damage
    public double getBaseDamage() {
        return baseDamage;
    }

    // Get scaling multiplier
    public double getScaling() {
        return scaling;
    }

    // Get damage type
    public DamageType getDamageType() {
        return damageType;
    }
}