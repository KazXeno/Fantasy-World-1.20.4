package me.KazXeno.fantasyWorld.combat.tracker;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AttackTracker {

    // Shared instance
    private static final AttackTracker
            instance =
            new AttackTracker();

    // Last attack timestamps
    private final Map<UUID, Long>
            lastAttackTime =
            new HashMap<>();

    // Private constructor
    private AttackTracker() {
    }

    // Get shared instance
    public static AttackTracker
    getInstance() {

        return instance;
    }

    // Register attack
    public void registerAttack(
            Player player) {

        lastAttackTime.put(
                player.getUniqueId(),
                System.currentTimeMillis()
        );
    }

    // Get time since last attack
    public long getTimeSinceLastAttack(
            Player player) {

        long lastAttack =
                lastAttackTime.getOrDefault(
                        player.getUniqueId(),
                        0L
                );

        return System.currentTimeMillis()
                - lastAttack;
    }

    // Has charged attack
    public boolean hasChargedAttack(
            Player player) {

        return getTimeSinceLastAttack(
                player
        ) >= 3000;
    }
}