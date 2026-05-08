package me.KazXeno.fantasyWorld.combat.attack;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AttackCooldownManager {

    // Next attack timestamps
    private final Map<UUID, Long> cooldowns =
            new HashMap<>();

    // Check attack cooldown
    public boolean canAttack(Player player,
                             double attackSpeed) {

        long now =
                System.currentTimeMillis();

        long nextAttack =
                cooldowns.getOrDefault(
                        player.getUniqueId(),
                        0L
                );

        // Check cooldown
        if (now < nextAttack) {
            return false;
        }

        // Calculate cooldown
        long cooldown =
                (long) (1000 / attackSpeed);

        // Set next attack time
        cooldowns.put(
                player.getUniqueId(),
                now + cooldown
        );

        return true;
    }

    // Get remaining cooldown
    public long getRemaining(Player player) {

        long now =
                System.currentTimeMillis();

        long nextAttack =
                cooldowns.getOrDefault(
                        player.getUniqueId(),
                        0L
                );

        return Math.max(
                0,
                nextAttack - now
        );
    }
}