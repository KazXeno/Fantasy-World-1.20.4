package me.KazXeno.fantasyWorld.combat;

import me.KazXeno.fantasyWorld.combat.lifesteal.LifestealCalculator;
import me.KazXeno.fantasyWorld.combat.lifesteal.LifestealResult;
import me.KazXeno.fantasyWorld.combat.reduction.DamageReductionCalculator;
import me.KazXeno.fantasyWorld.combat.reduction.DamageReductionResult;
import me.KazXeno.fantasyWorld.combat.scaling.DamageScaling;
import me.KazXeno.fantasyWorld.combat.scaling.DamageScalingResult;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.resource.HealthManager;

public class DamageEngine {

    // Damage scaling calculator
    private final DamageScaling damageScaling =
            new DamageScaling();

    // Defense calculator
    private final DefenseCalculator defenseCalculator =
            new DefenseCalculator();

    // Damage reduction calculator
    private final DamageReductionCalculator reductionCalculator =
            new DamageReductionCalculator();

    // Critical calculator
    private final CritCalculator critCalculator =
            new CritCalculator();

    // Lifesteal calculator
    private final LifestealCalculator lifestealCalculator =
            new LifestealCalculator();

    // Health manager
    private final HealthManager healthManager =
            new HealthManager();

    // Process damage calculation
    public DamageResult damage(DamageContext context) {

        CombatEntity attacker = context.getAttacker();

        CombatEntity victim = context.getVictim();

        // Calculate scaled damage
        DamageScalingResult scalingResult =
                damageScaling.scaleDamage(
                        attacker,
                        context.getBaseDamage(),
                        context.getScaling(),
                        context.getDamageType()
                );

        double damage = scalingResult.getDamage();

        // Apply critical hit
        DamageResult critResult =
                critCalculator.applyCrit(attacker, damage);

        damage = critResult.getFinalDamage();

        // Apply defense reduction
        damage = defenseCalculator.applyDefense(
                attacker,
                victim,
                damage,
                context.getDamageType()
        );

        // Apply final damage reduction
        DamageReductionResult reductionResult =
                reductionCalculator.applyReduction(
                        victim,
                        damage
                );

        damage = reductionResult.getDamage();

        // Apply final damage
        healthManager.damage(victim, damage);

        // Apply lifesteal
        LifestealResult lifestealResult =
                lifestealCalculator.applyLifesteal(
                        attacker,
                        damage
                );

        return new DamageResult(
                damage,
                critResult.isCritical(),
                lifestealResult.getHealAmount()
        );
    }
}