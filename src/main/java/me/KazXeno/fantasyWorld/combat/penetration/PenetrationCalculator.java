package me.KazXeno.fantasyWorld.combat.penetration;

import me.KazXeno.fantasyWorld.combat.DamageType;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

public class PenetrationCalculator {

    // Apply penetration reduction
    public PenetrationResult applyPenetration(CombatEntity attacker,
                                              double defense,
                                              DamageType damageType) {

        double penetration = 0;

        // Get penetration stat
        switch (damageType) {

            case MELEE, RANGE ->
                    penetration = attacker.getStats()
                            .getFinalStat(StatType.ARMOR_PEN);

            case MAGIC ->
                    penetration = attacker.getStats()
                            .getFinalStat(StatType.MAGIC_PEN);

            case TRUE ->
                    penetration = 0;
        }

        double finalDefense = defense - penetration;

        // Prevent negative defense
        if (finalDefense < 0) {
            finalDefense = 0;
        }

        return new PenetrationResult(finalDefense);
    }
}