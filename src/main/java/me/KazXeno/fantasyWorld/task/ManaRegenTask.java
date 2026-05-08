package me.KazXeno.fantasyWorld.task;

import me.KazXeno.fantasyWorld.combat.state.CombatTagManager;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaRegenTask {

    // Shared entity manager
    private final EntityManager entityManager = EntityManager.getInstance();

    // Combat state manager
    private final CombatTagManager combatTagManager = new CombatTagManager();

    // Start mana regeneration task
    public void start(JavaPlugin plugin) {

        new BukkitRunnable() {
            @Override
            public void run() {
                // Loop online players
                for (Player player : Bukkit.getOnlinePlayers()) {

                    CombatEntity entity = entityManager.getEntity(player.getUniqueId());

                    // Validate entity
                    if (entity == null) {
                        continue;
                    }
                    // Get current mana
                    double mana = entity.getMana();

                    // Get max mana
                    double maxMana = entity.getStats().getFinalStat(StatType.MANA);

                    // Get mana regen stat
                    double manaRegen = entity.getStats().getFinalStat(StatType.MANA_REGEN);

                    // Combat regen
                    if (combatTagManager.isInCombat(player)) {
                        mana += 2 + manaRegen;
                    }
                    // Out of combat regen
                    else {
                        mana += 8 + manaRegen;
                    }

                    // Clamp mana
                    mana = Math.min(mana, maxMana);
                    // Update mana
                    entity.setMana(mana);
                }
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }
}