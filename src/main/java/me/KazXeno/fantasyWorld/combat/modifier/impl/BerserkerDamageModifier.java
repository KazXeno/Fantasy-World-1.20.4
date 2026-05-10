package me.KazXeno.fantasyWorld.combat.modifier.impl;

import me.KazXeno.fantasyWorld.classsystem.ClassType;
import me.KazXeno.fantasyWorld.classsystem.PlayerClassManager;
import me.KazXeno.fantasyWorld.combat.DamageContext;
import me.KazXeno.fantasyWorld.combat.modifier.DamageModifier;
import me.KazXeno.fantasyWorld.entity.CombatEntity;

public class BerserkerDamageModifier
        implements DamageModifier {

    // Shared class manager
    private final PlayerClassManager
            classManager =
            PlayerClassManager.getInstance();

    @Override
    public double modifyDamage(
            DamageContext context,
            double damage) {

        CombatEntity attacker =
                context.getAttacker();

        // Validate berserker
        if (!classManager.hasClass(
                attacker.getUuid(),
                ClassType.BERSERKER
        )) {

            return damage;
        }

        // Get health %
        double currentHealth = attacker.getCurrentHealth();

        double maxHealth =
                attacker.getStats()
                        .getFinalStat(
                                me.KazXeno.fantasyWorld.stats.StatType.MAX_HEALTH
                        );

        double healthPercent =
                currentHealth
                        / maxHealth;

        // Below 50%
        if (healthPercent <= 0.5) {

            return damage * 1.4;
        }

        // 50% - 80%
        if (healthPercent <= 0.8) {

            return damage * 1.2;
        }

        // Above 80%
        return damage;
    }
}