package me.KazXeno.fantasyWorld.combat.attack;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AttackCooldownManager {

    // Last attack timestamps
    private final Map<UUID, Long> lastAttack = new HashMap<>();

    // Check attack cooldown
    public boolean canAttack(Player player, double attackSpeed) {
        // Prevent invalid speed
        if (attackSpeed <= 0) {
            attackSpeed = 1;
        }
        long now = System.currentTimeMillis();
        long last = lastAttack.getOrDefault(player.getUniqueId(), 0L);

        // Convert attack speed to cooldown
        long cooldown = (long) (1000 / attackSpeed);

        // Still on cooldown
        if (now - last < cooldown) {
            return false;
        }

        // Update timestamp
        lastAttack.put(player.getUniqueId(), now);
        return true;
    }
}