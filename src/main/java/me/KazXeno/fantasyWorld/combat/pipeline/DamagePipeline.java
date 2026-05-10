package me.KazXeno.fantasyWorld.combat.pipeline;

import me.KazXeno.fantasyWorld.combat.*;
import me.KazXeno.fantasyWorld.combat.lifesteal.LifestealHandler;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

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

    // Lifesteal runtime
    private final LifestealHandler
            lifestealHandler =
            new LifestealHandler();

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

        double defendedDamage =
                defenseCalculator.applyDefense(
                        attacker,
                        victim,
                        criticalDamage,
                        context.getDamageType()
                );

        // ====================
        // FINAL MULTIPLIER
        // ====================

        double finalMultiplier =
                attacker.getStats()
                        .getFinalStat(
                                StatType
                                        .FINAL_DAMAGE_MULTIPLIER
                        );

        defendedDamage *= (
                finalMultiplier / 100.0
        );

        // ====================
        // FINAL REDUCTION
        // ====================

        double finalReduction =
                victim.getStats()
                        .getFinalStat(
                                StatType
                                        .FINAL_DAMAGE_REDUCTION
                        );

        defendedDamage *= (
                1
                        - (
                        finalReduction / 100.0
                )
        );

        // Prevent negative damage
        double finalDamage =
                Math.max(
                        defendedDamage,
                        0
                );

        pipeline.setFinalDamage(
                finalDamage
        );

        // Apply health damage
        victim.damage(
                finalDamage
        );

        // ====================
        // LIFESTEAL
        // ====================

        double lifestealHeal =
                lifestealHandler
                        .applyLifesteal(
                                attacker,
                                finalDamage
                        );

        // ====================
        // RESULT
        // ====================

        return new DamageResult(
                finalDamage,
                pipeline.isCritical(),
                0,
                lifestealHeal
        );
    }
}