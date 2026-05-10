package me.KazXeno.fantasyWorld.ui;

import me.KazXeno.fantasyWorld.entity.PlayerCombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ActionBarManager {

    // Update player action bar
    public void updateActionBar(Player player,
                                PlayerCombatEntity combatEntity) {

        double health =
                combatEntity.getHealth();

        double maxHealth =
                combatEntity.getStats()
                        .getFinalStat(StatType.MAX_HEALTH);

        double mana =
                combatEntity.getMana();

        double maxMana =
                combatEntity.getStats()
                        .getFinalStat(StatType.MANA);

        String message =
                "§c❤ " + (int) health +
                        "/" + (int) maxHealth +
                        "   " +
                        "§b✦ " + (int) mana +
                        "/" + (int) maxMana;

        player.sendActionBar(
                Component.text(message)
        );
    }
}