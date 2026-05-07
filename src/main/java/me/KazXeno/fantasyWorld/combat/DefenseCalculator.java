package me.KazXeno.fantasyWorld.combat;

import me.KazXeno.fantasyWorld.combat.penetration.PenetrationCalculator;
import me.KazXeno.fantasyWorld.combat.penetration.PenetrationResult;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

public class DefenseCalculator {

    // Penetration calculator
    private final PenetrationCalculator penetrationCalculator =
            new PenetrationCalculator();

    // Apply defense reduction
    public double applyDefense(CombatEntity attacker,
                               CombatEntity victim,
                               double damage,
                               DamageType damageType) {

        // Ignore defense for true damage
        if (damageType == DamageType.TRUE) {
            return damage;
        }

        double defense = 0;

        // Get defense stat
        switch (damageType) {

            case MELEE, RANGE ->
                    defense = victim.getStats()
                            .getFinalStat(StatType.MELEE_DEFENSE);

            case MAGIC ->
                    defense = victim.getStats()
                            .getFinalStat(StatType.MAGIC_DEFENSE);
        }

        // Apply penetration
        PenetrationResult penetrationResult =
                penetrationCalculator.applyPenetration(
                        attacker,
                        defense,
                        damageType
                );

        defense = penetrationResult.getDefense();

        // Apply defense formula
        double reducedDamage =
                damage * (100.0 / (100.0 + defense));

        return Math.max(reducedDamage, 0);
    }
}