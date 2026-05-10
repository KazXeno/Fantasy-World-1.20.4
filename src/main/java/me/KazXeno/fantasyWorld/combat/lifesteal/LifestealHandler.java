package me.KazXeno.fantasyWorld.combat.lifesteal;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

public class LifestealHandler {

    // Apply lifesteal
    public double applyLifesteal(
            CombatEntity attacker,
            double damage) {

        // Get lifesteal %
        double lifesteal =
                attacker.getStats()
                        .getFinalStat(
                                StatType.LIFESTEAL
                        );

        // Validate lifesteal
        if (lifesteal <= 0) {

            return 0;
        }

        // Calculate heal
        double heal =
                damage * (
                        lifesteal / 100.0
                );

        // Heal attacker
        attacker.heal(
                heal
        );

        return heal;
    }
}