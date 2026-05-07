package me.KazXeno.fantasyWorld.combat.scaling;

import me.KazXeno.fantasyWorld.combat.DamageType;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

public class DamageScaling {

    // Calculate scaled damage
    public DamageScalingResult scaleDamage(CombatEntity attacker,
                                           double baseDamage,
                                           double scaling,
                                           DamageType damageType) {

        double statDamage = 0;

        // Get damage stat based on damage type
        switch (damageType) {

            case MELEE ->
                    statDamage = attacker.getStats()
                            .getFinalStat(StatType.MELEE_DAMAGE);

            case RANGE ->
                    statDamage = attacker.getStats()
                            .getFinalStat(StatType.RANGE_DAMAGE);

            case MAGIC ->
                    statDamage = attacker.getStats()
                            .getFinalStat(StatType.MAGIC_DAMAGE);

            case TRUE ->
                    statDamage = attacker.getStats()
                            .getFinalStat(StatType.TRUE_DAMAGE);
        }

        double finalDamage =
                baseDamage + (statDamage * scaling);

        return new DamageScalingResult(finalDamage);
    }
}