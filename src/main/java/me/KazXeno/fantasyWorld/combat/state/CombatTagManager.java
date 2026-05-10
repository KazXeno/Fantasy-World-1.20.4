package me.KazXeno.fantasyWorld.combat.state;

import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatTagManager {

    // Shared instance
    private static final CombatTagManager
            instance =
            new CombatTagManager();

    // Combat duration in milliseconds
    private static final long
            COMBAT_DURATION =
            10000;

    // Combat timestamps
    private final Map<UUID, Long>
            combatTags =
            new HashMap<>();

    // Last attack timestamps
    private final Map<UUID, Long>
            lastAttackTimes =
            new HashMap<>();

    // Last damaged timestamps
    private final Map<UUID, Long>
            lastDamagedTimes =
            new HashMap<>();

    // Private constructor
    private CombatTagManager() {
    }

    // Get shared instance
    public static CombatTagManager
    getInstance() {

        return instance;
    }

    // Enter combat state
    public void tag(
            LivingEntity entity) {

        combatTags.put(
                entity.getUniqueId(),
                System.currentTimeMillis()
        );
    }

    // Register attack
    public void registerAttack(
            LivingEntity entity) {

        long now =
                System.currentTimeMillis();

        // Update attack time
        lastAttackTimes.put(
                entity.getUniqueId(),
                now
        );

        // Enter combat
        combatTags.put(
                entity.getUniqueId(),
                now
        );
    }

    // Register damaged
    public void registerDamaged(
            LivingEntity entity) {

        long now =
                System.currentTimeMillis();

        // Update damaged time
        lastDamagedTimes.put(
                entity.getUniqueId(),
                now
        );

        // Enter combat
        combatTags.put(
                entity.getUniqueId(),
                now
        );
    }

    // Check combat state
    public boolean isInCombat(
            LivingEntity entity) {

        long lastCombat =
                combatTags.getOrDefault(
                        entity.getUniqueId(),
                        0L
                );

        long now =
                System.currentTimeMillis();

        return now - lastCombat
                < COMBAT_DURATION;
    }

    // Get remaining combat time
    public long getRemainingTime(
            LivingEntity entity) {

        long lastCombat =
                combatTags.getOrDefault(
                        entity.getUniqueId(),
                        0L
                );

        long now =
                System.currentTimeMillis();

        long remaining =
                COMBAT_DURATION
                        - (
                        now
                                - lastCombat
                );

        return Math.max(
                0,
                remaining
        );
    }

    // Get time since attack
    public long getTimeSinceAttack(
            LivingEntity entity) {

        long lastAttack =
                lastAttackTimes
                        .getOrDefault(
                                entity.getUniqueId(),
                                0L
                        );

        return System.currentTimeMillis()
                - lastAttack;
    }

    // Get time since damaged
    public long getTimeSinceDamaged(
            LivingEntity entity) {

        long lastDamaged =
                lastDamagedTimes
                        .getOrDefault(
                                entity.getUniqueId(),
                                0L
                        );

        return System.currentTimeMillis()
                - lastDamaged;
    }

    // Remove combat state
    public void remove(
            LivingEntity entity) {

        UUID uuid =
                entity.getUniqueId();

        combatTags.remove(
                uuid
        );

        lastAttackTimes.remove(
                uuid
        );

        lastDamagedTimes.remove(
                uuid
        );
    }
}