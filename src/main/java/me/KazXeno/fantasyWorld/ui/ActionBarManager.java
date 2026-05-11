package me.KazXeno.fantasyWorld.ui;

import me.KazXeno.fantasyWorld.classsystem.ClassType;
import me.KazXeno.fantasyWorld.classsystem.PlayerClassManager;
import me.KazXeno.fantasyWorld.combat.state.CombatTagManager;
import me.KazXeno.fantasyWorld.combat.tracker.AttackTracker;
import me.KazXeno.fantasyWorld.entity.PlayerCombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ActionBarManager {

    // Shared managers
    private final PlayerClassManager
            classManager =
            PlayerClassManager.getInstance();

    private final AttackTracker
            attackTracker =
            AttackTracker.getInstance();

    private final CombatTagManager
            combatTagManager =
            CombatTagManager.getInstance();

    // Update player action bar
    public void updateActionBar(
            Player player,
            PlayerCombatEntity combatEntity) {

        double health =
                combatEntity.getHealth();

        double maxHealth =
                combatEntity.getStats()
                        .getFinalStat(
                                StatType.MAX_HEALTH
                        );

        double mana =
                combatEntity.getMana();

        double maxMana =
                combatEntity.getStats()
                        .getFinalStat(
                                StatType.MANA
                        );

        // Base actionbar
        String message =
                "§c❤ "
                        + (int) health
                        + "/"
                        + (int) maxHealth
                        + "   "
                        + "§b✦ "
                        + (int) mana
                        + "/"
                        + (int) maxMana;

        // ====================
        // ASSASSIN STATUS
        // ====================

        if (classManager.hasClass(
                player.getUniqueId(),
                ClassType.ASSASSIN
        )) {

            // Time since attack
            long attackTime =
                    attackTracker
                            .getTimeSinceLastAttack(
                                    player
                            );

            // Charge remaining
            double chargeRemaining =
                    Math.max(
                            0,
                            1.0 - (
                                    attackTime / 1000.0
                            )
                    );

            // First strike remaining
            double firstStrikeRemaining =
                    Math.max(
                            0,
                            combatTagManager
                                    .getRemainingTime(
                                            player
                                    ) / 1000.0
                    );

            String assassinText;

            // First strike ready
            if (!combatTagManager.isInCombat(
                    player
            )) {

                assassinText =
                        "   §e⚔ FIRST STRIKE";
            }

            // Charged ready
            else if (chargeRemaining <= 0) {

                assassinText =
                        "   §e⚔ READY";

                // Show first strike timer
                if (firstStrikeRemaining > 0) {

                    assassinText +=
                            " §7| "
                                    + String.format(
                                    "%.1fs",
                                    firstStrikeRemaining
                            );
                }
            }

            // Charging
            else {

                assassinText =
                        " §e⚔ "
                                + String.format(
                                "%.1fs",
                                chargeRemaining
                        );
            }

            message += assassinText;
        }

        // Send actionbar
        player.sendActionBar(
                Component.text(message)
        );
    }
}