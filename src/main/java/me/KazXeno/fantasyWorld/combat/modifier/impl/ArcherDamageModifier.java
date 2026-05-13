package me.KazXeno.fantasyWorld.combat.modifier.impl;

import me.KazXeno.fantasyWorld.classsystem.ClassType;
import me.KazXeno.fantasyWorld.classsystem.PlayerClassManager;
import me.KazXeno.fantasyWorld.combat.DamageContext;
import me.KazXeno.fantasyWorld.combat.modifier.DamageModifier;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class ArcherDamageModifier
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

        CombatEntity victim =
                context.getVictim();

        // Validate archer
        if (!classManager.hasClass(
                attacker.getUuid(),
                ClassType.ARCHER
        )) {

            return damage;
        }

        // Get Bukkit entities
        Entity attackerEntity =
                Bukkit.getEntity(
                        attacker.getUuid()
                );

        Entity victimEntity =
                Bukkit.getEntity(
                        victim.getUuid()
                );

        // Validate entities
        if (attackerEntity == null
                || victimEntity == null) {

            return damage;
        }

        // Calculate distance
        Location attackerLocation =
                attackerEntity.getLocation();

        Location victimLocation =
                victimEntity.getLocation();

        double distance =
                attackerLocation.distance(
                        victimLocation
                );

        // ====================
        // DISTANCE SCALING
        // ====================

        // 1 - 2 blocks
        if (distance <= 2) {

            return damage * 0.85;
        }

        // 2 - 5 blocks
        if (distance <= 5) {

            return damage;
        }

        // 5 - 10 blocks
        if (distance <= 10) {

            return damage * 1.1;
        }

        // 10+ blocks
        return damage * 1.2;
    }
}