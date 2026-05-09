package me.KazXeno.fantasyWorld.skill.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    // Shared instance
    private static final CooldownManager
            instance =
            new CooldownManager();

    // Player cooldown map
    private final Map<UUID,
            Map<String, Long>>
            cooldowns =
            new HashMap<>();

    // Private constructor
    private CooldownManager() {
    }

    // Get shared instance
    public static CooldownManager
    getInstance() {

        return instance;
    }

    // Check cooldown state
    public boolean isOnCooldown(
            Player player,
            String skillId) {

        Map<String, Long> playerCooldowns =
                cooldowns.get(
                        player.getUniqueId()
                );

        // No cooldowns
        if (playerCooldowns == null) {
            return false;
        }

        Long endTime =
                playerCooldowns.get(skillId);

        // Skill not found
        if (endTime == null) {
            return false;
        }

        // Check timestamp
        return System.currentTimeMillis()
                < endTime;
    }

    // Set skill cooldown
    public void setCooldown(
            Player player,
            String skillId,
            long durationMillis) {

        Map<String, Long> playerCooldowns =
                cooldowns.computeIfAbsent(
                        player.getUniqueId(),
                        k -> new HashMap<>()
                );

        long endTime =
                System.currentTimeMillis()
                        + durationMillis;

        playerCooldowns.put(
                skillId,
                endTime
        );
    }

    // Get remaining cooldown
    public long getRemaining(
            Player player,
            String skillId) {

        Map<String, Long> playerCooldowns =
                cooldowns.get(
                        player.getUniqueId()
                );

        // No cooldowns
        if (playerCooldowns == null) {
            return 0;
        }

        Long endTime =
                playerCooldowns.get(skillId);

        // Skill not found
        if (endTime == null) {
            return 0;
        }

        long remaining =
                endTime
                        - System.currentTimeMillis();

        return Math.max(0, remaining);
    }

    // Remove cooldown
    public void removeCooldown(
            Player player,
            String skillId) {

        Map<String, Long> playerCooldowns =
                cooldowns.get(
                        player.getUniqueId()
                );

        // Validate cooldown map
        if (playerCooldowns == null) {
            return;
        }

        playerCooldowns.remove(skillId);
    }
}