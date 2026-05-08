package me.KazXeno.fantasyWorld.combat.death;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DeathManager {

    // Shared entity manager
    private final EntityManager entityManager =
            EntityManager.getInstance();

    // Handle entity death
    public void handleDeath(LivingEntity entity,
                            CombatEntity combatEntity) {

        // Handle player death
        if (entity instanceof Player player) {

            handlePlayerDeath(
                    player,
                    combatEntity
            );

            return;
        }

        // Handle mob death
        handleMobDeath(
                entity,
                combatEntity
        );
    }

    // Handle player death
    private void handlePlayerDeath(
            Player player,
            CombatEntity combatEntity) {

        // Remove combat entity
        entityManager.removeEntity(
                player.getUniqueId()
        );

        // Use vanilla death
        player.setHealth(0);
    }

    // Handle mob death
    private void handleMobDeath(
            LivingEntity entity,
            CombatEntity combatEntity) {

        // Remove combat entity
        entityManager.removeEntity(
                entity.getUniqueId()
        );

        // Remove entity
        entity.setHealth(0);
    }
}