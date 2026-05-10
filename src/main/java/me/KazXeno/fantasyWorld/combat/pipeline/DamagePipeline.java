package me.KazXeno.fantasyWorld.combat.pipeline;

import me.KazXeno.fantasyWorld.combat.*;
import me.KazXeno.fantasyWorld.combat.death.DeathManager;
import me.KazXeno.fantasyWorld.combat.lifesteal.LifestealHandler;
import me.KazXeno.fantasyWorld.combat.modifier.DamageModifierManager;
import me.KazXeno.fantasyWorld.combat.state.CombatTagManager;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

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

    // Combat state manager
    private final CombatTagManager
            combatTagManager =
            CombatTagManager.getInstance();

    // Damage modifier manager
    private final DamageModifierManager
            damageModifierManager =
            new DamageModifierManager();

    // Death manager
    private final DeathManager
            deathManager =
            new DeathManager();

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
        // MODIFIERS
        // ====================

        scaledDamage =
                damageModifierManager
                        .applyModifiers(
                                context,
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
                1 - (
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

        // ====================
        // COMBAT STATE
        // ====================

        // Get Bukkit entities
        LivingEntity attackerEntity =
                (LivingEntity) Bukkit.getEntity(
                        attacker.getUuid()
                );

        LivingEntity victimEntity =
                (LivingEntity) Bukkit.getEntity(
                        victim.getUuid()
                );

        // Register attack state
        if (attackerEntity != null) {

            combatTagManager.registerAttack(
                    attackerEntity
            );
        }

        // Register damaged state
        if (victimEntity != null) {

            combatTagManager.registerDamaged(
                    victimEntity
            );
        }

        // ====================
        // CUSTOM DEATH
        // ====================

        double remainingHealth =
                victim.getHealth()
                        - finalDamage;

        // Prevent real death
        if (remainingHealth <= 0) {

            // Handle player death
            if (victimEntity
                    instanceof Player player) {

                deathManager.handleDeath(
                        player,
                        victim
                );
            }

            // Handle mob death
            else if (victimEntity != null) {

                victimEntity.remove();
            }

            // Stop pipeline
            return new DamageResult(
                    finalDamage,
                    pipeline.isCritical(),
                    0,
                    0
            );
        }

        // ====================
        // DAMAGE
        // ====================

        // Apply custom damage
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