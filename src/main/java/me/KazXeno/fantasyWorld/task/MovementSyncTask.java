package me.KazXeno.fantasyWorld.task;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MovementSyncTask
        extends BukkitRunnable {

    // Shared entity manager
    private final EntityManager
            entityManager =
            EntityManager.getInstance();

    // Start task
    public void start(
            org.bukkit.plugin.Plugin plugin) {

        runTaskTimer(
                plugin,
                1L,
                1L
        );
    }

    @Override
    public void run() {

        // Loop online players
        for (Player player
                : Bukkit.getOnlinePlayers()) {

            CombatEntity entity =
                    entityManager.getEntity(
                            player.getUniqueId()
                    );

            // Validate entity
            if (entity == null) {
                continue;
            }

            // Get speed stat
            double speed =
                    entity.getStats()
                            .getFinalStat(
                                    StatType.SPEED
                            );

            // Default vanilla speed
            if (speed <= 0) {
                speed = 100;
            }

            // Convert to Bukkit walk speed
            float walkSpeed =
                    (float) (
                            0.2f
                                    * (
                                    speed / 100.0
                            )
                    );

            // Clamp speed
            walkSpeed =
                    Math.max(
                            -1f,
                            Math.min(
                                    1f,
                                    walkSpeed
                            )
                    );

            // Apply movement speed
            player.setWalkSpeed(
                    walkSpeed
            );
        }
    }
}