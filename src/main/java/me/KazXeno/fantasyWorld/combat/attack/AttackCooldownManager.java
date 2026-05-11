package me.KazXeno.fantasyWorld.combat.attack;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AttackCooldownManager {

    // Last attack timestamps
    private final Map<UUID, Long> lastAttack = new HashMap<>();

    // Check attack cooldown
    public boolean canAttack(Player player,
                             double attackSpeed) {

        // Clamp attack speed
        attackSpeed = Math.max(0, Math.min(attackSpeed, 200));

        // Current time
        long now = System.currentTimeMillis();
        // Last attack
        long last = lastAttack.getOrDefault(player.getUniqueId(), 0L);
        // Calculate cooldown
        double cooldownSeconds = 1.0 / Math.pow(2, attackSpeed / 100.0);
        // Convert to milliseconds
        long cooldown = (long) (cooldownSeconds * 1000);
        // Still on cooldown
        if (now - last < cooldown) {
            return false;
        }
        // Update timestamp
        lastAttack.put(player.getUniqueId(), now);
        return true;
    }
}