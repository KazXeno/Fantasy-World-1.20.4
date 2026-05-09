package me.KazXeno.fantasyWorld.command;

import me.KazXeno.fantasyWorld.combat.state.CombatTagManager;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StatsCommand implements CommandExecutor {

    // Shared entity manager
    private final EntityManager entityManager = EntityManager.getInstance();

    // Combat state manager
    private final CombatTagManager
            combatTagManager =
            CombatTagManager.getInstance();

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            String[] args) {

        // Validate player
        if (!(sender instanceof Player player)) {
            return true;
        }

        CombatEntity entity =
                entityManager.getEntity(
                        player.getUniqueId()
                );

        // Validate combat entity
        if (entity == null) {

            player.sendMessage(
                    "§cCombat entity not found."
            );

            return true;
        }

        player.sendMessage(
                "§6===== Player Stats ====="
        );

        // Health
        player.sendMessage(
                "§cHealth: §f"
                        + String.format(
                        "%.1f",
                        entity.getHealth()
                )
                        + " / "
                        + String.format(
                        "%.1f",
                        entity.getStats()
                                .getFinalStat(
                                        StatType.MAX_HEALTH
                                )
                )
        );

        // Mana
        player.sendMessage(
                "§9Mana: §f"
                        + String.format(
                        "%.1f",
                        entity.getMana()
                )
                        + " / "
                        + String.format(
                        "%.1f",
                        entity.getStats()
                                .getFinalStat(
                                        StatType.MANA
                                )
                )
        );

        // Combat state
        player.sendMessage(
                "§eIn Combat: §f"
                        + combatTagManager
                        .isInCombat(player)
        );

        player.sendMessage(" ");

        // All stats
        for (StatType stat
                : StatType.values()) {

            double value =
                    entity.getStats()
                            .getFinalStat(stat);

            player.sendMessage(
                    "§7"
                            + stat.getDisplayName()
                            + ": §f"
                            + String.format(
                            "%.2f",
                            value
                    )
            );
        }

        return true;
    }
}