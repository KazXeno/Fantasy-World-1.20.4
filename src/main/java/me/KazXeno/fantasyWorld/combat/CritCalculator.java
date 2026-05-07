package me.KazXeno.fantasyWorld.combat;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

import java.util.concurrent.ThreadLocalRandom;

public class CritCalculator {

    // Apply critical hit calculation
    public DamageResult applyCrit(CombatEntity attacker,
                                  double damage) {

        double critChance = attacker.getStats()
                .getFinalStat(StatType.CRIT_CHANCE);

        double critDamage = attacker.getStats()
                .getFinalStat(StatType.CRIT_DAMAGE);

        boolean critical =
                ThreadLocalRandom.current().nextDouble() < critChance;

        if (critical) {
            damage *= (1 + critDamage);
        }

        return new DamageResult(
                damage,
                critical,
                0
        );
    }
}