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
            COMBAT_DURATION = 10000;

    // Combat timestamps
    private final Map<UUID, Long>
            combatTags = new HashMap<>();

    // Private constructor
    private CombatTagManager() {
    }

    // Get shared instance
    public static CombatTagManager
    getInstance() {

        return instance;
    }

    // Enter combat state
    public void tag(LivingEntity entity) {

        combatTags.put(
                entity.getUniqueId(),
                System.currentTimeMillis()
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
                        - (now - lastCombat);

        return Math.max(0, remaining);
    }

    // Remove combat state
    public void remove(
            LivingEntity entity) {

        combatTags.remove(
                entity.getUniqueId()
        );
    }
}