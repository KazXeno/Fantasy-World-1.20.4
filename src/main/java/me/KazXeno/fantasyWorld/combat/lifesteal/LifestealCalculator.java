package me.KazXeno.fantasyWorld.combat.lifesteal;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.resource.HealthManager;
import me.KazXeno.fantasyWorld.stats.StatType;

public class LifestealCalculator {

    // Health manager
    private final HealthManager healthManager =
            new HealthManager();

    // Apply lifesteal healing
    public LifestealResult applyLifesteal(CombatEntity attacker,
                                          double finalDamage) {

        double lifesteal = attacker.getStats()
                .getFinalStat(StatType.LIFESTEAL);

        // Prevent invalid lifesteal
        if (lifesteal < 0) {
            lifesteal = 0;
        }

        double healAmount =
                finalDamage * lifesteal;

        // Heal attacker
        healthManager.heal(attacker, healAmount);

        return new LifestealResult(healAmount);
    }
}