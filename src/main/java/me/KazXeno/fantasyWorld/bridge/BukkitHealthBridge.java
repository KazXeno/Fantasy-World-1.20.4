package me.KazXeno.fantasyWorld.bridge;

import me.KazXeno.fantasyWorld.entity.PlayerCombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class BukkitHealthBridge {

    // Sync RPG health to Minecraft health
    public void syncHealth(Player player,
                           PlayerCombatEntity combatEntity) {

        double maxHealth = combatEntity.getStats()
                .getFinalStat(StatType.MAX_HEALTH);

        double currentHealth =
                combatEntity.getHealth();

        // Prevent invalid max health
        if (maxHealth <= 0) {
            maxHealth = 1;
        }

        // Get health percentage
        double percent =
                currentHealth / maxHealth;

        // Clamp percentage
        percent = Math.max(0, percent);
        percent = Math.min(1, percent);

        // Get vanilla max health attribute
        AttributeInstance attribute =
                player.getAttribute(
                        Attribute.GENERIC_MAX_HEALTH
                );

        // Set vanilla max health
        if (attribute != null) {
            attribute.setBaseValue(20);
        }

        // Convert RPG health to vanilla hearts
        double vanillaHealth =
                20 * percent;

        // Prevent invalid vanilla health
        vanillaHealth = Math.max(0.1, vanillaHealth);

        player.setHealth(vanillaHealth);
    }
}