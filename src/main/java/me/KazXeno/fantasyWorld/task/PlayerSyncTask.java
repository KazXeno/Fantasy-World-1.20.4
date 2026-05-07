package me.KazXeno.fantasyWorld.task;

import me.KazXeno.fantasyWorld.bridge.BukkitHealthBridge;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.entity.PlayerCombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.ui.ActionBarManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerSyncTask extends BukkitRunnable {

    // Entity manager
    private final EntityManager entityManager =
            EntityManager.getInstance();

    // Health bridge
    private final BukkitHealthBridge healthBridge =
            new BukkitHealthBridge();

    // Action bar manager
    private final ActionBarManager actionBarManager =
            new ActionBarManager();

    // Start sync task
    public void start(JavaPlugin plugin) {

        runTaskTimer(
                plugin,
                0L,
                5L
        );
    }

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            // Register player entity
            PlayerCombatEntity combatEntity =
                    entityManager.registerPlayer(player);

            // Initialize default stats
            initializeStats(combatEntity);

            // Sync vanilla health
            healthBridge.syncHealth(
                    player,
                    combatEntity
            );

            // Update action bar
            actionBarManager.updateActionBar(
                    player,
                    combatEntity
            );
        }
    }

    // Initialize default player stats
    private void initializeStats(PlayerCombatEntity entity) {

        // Prevent re-initialize
        if (entity.getStats()
                .getFinalStat(StatType.MAX_HEALTH) > 0) {
            return;
        }

        // Default health
        entity.getStats().setBaseStat(
                StatType.MAX_HEALTH,
                100
        );

        // Default mana
        entity.getStats().setBaseStat(
                StatType.MANA,
                100
        );

        // Restore resources
        entity.setHealth(100);
        entity.setMana(100);
    }
}