package me.KazXeno.fantasyWorld.combat.reduction;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

public class DamageReductionCalculator {

    // Apply final damage reduction
    public DamageReductionResult applyReduction(CombatEntity victim,
                                                double damage) {

        double reduction = victim.getStats()
                .getFinalStat(StatType.FINAL_DAMAGE_REDUCTION);

        // Prevent invalid reduction
        if (reduction < 0) {
            reduction = 0;
        }

        if (reduction > 0.9) {
            reduction = 0.9;
        }

        double reducedDamage =
                damage * (1 - reduction);

        return new DamageReductionResult(reducedDamage);
    }
}