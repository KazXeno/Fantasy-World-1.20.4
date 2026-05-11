package me.KazXeno.fantasyWorld.combat.modifier.impl;

import me.KazXeno.fantasyWorld.classsystem.ClassType;
import me.KazXeno.fantasyWorld.classsystem.PlayerClassManager;
import me.KazXeno.fantasyWorld.combat.DamageContext;
import me.KazXeno.fantasyWorld.combat.modifier.DamageModifier;
import me.KazXeno.fantasyWorld.combat.state.CombatTagManager;
import me.KazXeno.fantasyWorld.combat.tracker.AttackTracker;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AssassinDamageModifier
        implements DamageModifier {

    // Shared class manager
    private final PlayerClassManager
            classManager =
            PlayerClassManager.getInstance();

    // Combat state manager
    private final CombatTagManager
            combatTagManager =
            CombatTagManager.getInstance();

    // Attack tracker
    private final AttackTracker
            attackTracker =
            AttackTracker.getInstance();

    @Override
    public double modifyDamage(
            DamageContext context,
            double damage) {

        CombatEntity attacker =
                context.getAttacker();

        // Validate assassin
        if (!classManager.hasClass(
                attacker.getUuid(),
                ClassType.ASSASSIN
        )) {

            return damage;
        }

        // Get Bukkit player
        if (!(Bukkit.getEntity(
                attacker.getUuid()
        ) instanceof Player player)) {

            return damage;
        }

        // ====================
        // FIRST STRIKE
        // ====================

        // Out of combat for 3 seconds
        if (!combatTagManager.isInCombat(
                player
        )) {

            // Register attack
            attackTracker.registerAttack(
                    player
            );

            return damage * 4.0;
        }

        // ====================
        // CHARGED ATTACK
        // ====================

        // 1 second without attacking
        if (attackTracker
                .getTimeSinceLastAttack(
                        player
                ) >= 1000) {

            // Register attack
            attackTracker.registerAttack(
                    player
            );

            return damage * 2.2;
        }

        // Register normal attack
        attackTracker.registerAttack(
                player
        );

        return damage;
    }
}