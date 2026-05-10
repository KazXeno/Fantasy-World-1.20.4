package me.KazXeno.fantasyWorld.bridge;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.entity.LivingEntity;

public class BukkitHealthBridge {

    // Sync RPG health to Minecraft health
    public void syncHealth(
            LivingEntity entity,
            CombatEntity combatEntity) {

        // Get RPG max health
        double maxHealth =
                combatEntity.getStats()
                        .getFinalStat(
                                StatType.MAX_HEALTH
                        );

        // Prevent invalid max health
        if (maxHealth <= 0) {
            maxHealth = 1;
        }

        // Clamp current health
        double currentHealth =
                Math.max(
                        0,
                        Math.min(
                                combatEntity.getHealth(),
                                maxHealth
                        )
                );

        // Prevent zero health
        // Death handled elsewhere
        currentHealth =
                Math.max(
                        0.1,
                        currentHealth
                );

        // Get health percentage
        double percent =
                currentHealth / maxHealth;

        // Convert to vanilla health
        double vanillaHealth =
                entity.getMaxHealth()
                        * percent;

        // Prevent invalid vanilla health
        vanillaHealth =
                Math.max(
                        0.1,
                        Math.min(
                                vanillaHealth,
                                entity.getMaxHealth()
                        )
                );

        // Sync vanilla health
        entity.setHealth(
                vanillaHealth
        );
    }
}