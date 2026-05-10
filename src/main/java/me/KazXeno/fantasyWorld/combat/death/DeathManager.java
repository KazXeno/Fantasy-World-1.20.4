package me.KazXeno.fantasyWorld.combat.death;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class DeathManager {

    // Handle custom death
    public void handleDeath(
            Player player,
            CombatEntity combatEntity) {

        // Get first world
        World world =
                Bukkit.getWorlds()
                        .get(0);

        // Respawn location
        Location respawn =
                new Location(
                        world,
                        0,
                        -60,
                        0
                );

        // Teleport player
        player.teleport(
                respawn
        );

        // Restore RPG health
        combatEntity.setHealth(
                combatEntity.getStats()
                        .getFinalStat(
                                StatType.MAX_HEALTH
                        )
        );

        // Restore vanilla health
        player.setHealth(
                player.getMaxHealth()
        );

        // Feed player
        player.setFoodLevel(20);

        // Extinguish fire
        player.setFireTicks(0);

        // Remove potion effects
        for (PotionEffect effect
                : player.getActivePotionEffects()) {

            player.removePotionEffect(
                    effect.getType()
            );
        }

        // Reset velocity
        player.setVelocity(
                player.getVelocity()
                        .zero()
        );

        // Survival safety
        player.setGameMode(
                GameMode.SURVIVAL
        );

        // Death message
        Bukkit.broadcastMessage(
                "§c"
                        + player.getName()
                        + " died."
        );
    }
}