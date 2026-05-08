package me.KazXeno.fantasyWorld.task;

import me.KazXeno.fantasyWorld.combat.state.CombatTagManager;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthRegenTask {

    // Shared entity manager
    private final EntityManager entityManager =
            EntityManager.getInstance();

    // Combat state manager
    private final CombatTagManager combatTagManager =
            new CombatTagManager();

    // Start health regeneration task
    public void start(JavaPlugin plugin) {

        new BukkitRunnable() {

            @Override
            public void run() {

                // Loop online players
                for (Player player
                        : Bukkit.getOnlinePlayers()) {

                    CombatEntity entity =
                            entityManager.getEntity(
                                    player.getUniqueId()
                            );

                    // Validate combat entity
                    if (entity == null) {
                        continue;
                    }

                    // Get current health
                    double health =
                            entity.getHealth();

                    // Get max health
                    double maxHealth =
                            entity.getStats()
                                    .getFinalStat(
                                            StatType.MAX_HEALTH
                                    );

                    // Skip full health
                    if (health >= maxHealth) {
                        continue;
                    }

                    // Get health regen stat
                    double healthRegen =
                            entity.getStats()
                                    .getFinalStat(
                                            StatType.HEALTH_REGEN
                                    );

                    // Combat regeneration
                    if (combatTagManager
                            .isInCombat(player)) {

                        health += 1 + healthRegen;
                    }

                    // Out of combat regeneration
                    else {

                        health += 4 + healthRegen;
                    }

                    // Clamp health
                    health = Math.min(
                            health,
                            maxHealth
                    );

                    // Update health
                    entity.setHealth(health);
                }
            }

        }.runTaskTimer(
                plugin,
                20L,
                20L
        );
    }
}