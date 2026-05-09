package me.KazXeno.fantasyWorld.combat.pipeline;

import me.KazXeno.fantasyWorld.combat.*;
import me.KazXeno.fantasyWorld.entity.CombatEntity;

public class DamagePipeline {

    // Damage calculators
    private final DamageCalculator
            damageCalculator =
            new DamageCalculator();

    private final CritCalculator
            critCalculator =
            new CritCalculator();

    private final DefenseCalculator
            defenseCalculator =
            new DefenseCalculator();

    // Process damage pipeline
    public DamageResult process(
            DamageContext context) {

        CombatEntity attacker =
                context.getAttacker();

        CombatEntity victim =
                context.getVictim();

        // Pipeline runtime
        DamagePipelineResult pipeline =
                new DamagePipelineResult();

        // ====================
        // BASE
        // ====================

        double baseDamage =
                context.getBaseDamage();

        pipeline.setBaseDamage(
                baseDamage
        );

        // ====================
        // SCALING
        // ====================

        double scaledDamage =
                damageCalculator.calculate(
                        context
                );

        pipeline.setScaledDamage(
                scaledDamage
        );

        // ====================
        // CRIT
        // ====================

        DamageResult critResult =
                critCalculator.applyCrit(
                        attacker,
                        scaledDamage
                );

        double criticalDamage =
                critResult.getFinalDamage();

        pipeline.setCriticalDamage(
                criticalDamage
        );

        pipeline.setCritical(
                critResult.isCritical()
        );

        // ====================
        // DEFENSE
        // ====================

        double finalDamage =
                defenseCalculator.applyDefense(
                        attacker,
                        victim,
                        criticalDamage,
                        context.getDamageType()
                );

        pipeline.setFinalDamage(finalDamage);

        // Apply health damage
        victim.damage(finalDamage);
        // Return result
        return new DamageResult(
                finalDamage,
                pipeline.isCritical(),
                0
        );
    }
}