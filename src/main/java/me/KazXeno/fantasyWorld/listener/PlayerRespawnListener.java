package me.KazXeno.fantasyWorld.listener;

import me.KazXeno.fantasyWorld.FantasyWorld;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.item.equipment.EquipmentManager;
import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.profile.ProfileManager;
import me.KazXeno.fantasyWorld.race.RaceManager;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener
        implements Listener {

    // Shared managers
    private final EntityManager
            entityManager =
            EntityManager.getInstance();

    private final ProfileManager
            profileManager =
            ProfileManager.getInstance();

    private final RaceManager
            raceManager =
            RaceManager.getInstance();

    private final EquipmentManager
            equipmentManager =
            new EquipmentManager();

    @EventHandler
    public void onRespawn(
            PlayerRespawnEvent event) {

        Player player =
                event.getPlayer();

        // Sync next tick
        player.getServer()
                .getScheduler()
                .runTaskLater(
                        FantasyWorld.getInstance(),

                        () -> {

                            // Re-register combat entity
                            entityManager
                                    .registerPlayer(
                                            player
                                    );

                            // Reload profile
                            PlayerProfile profile =
                                    profileManager
                                            .getProfile(
                                                    player
                                            );

                            // Restore combat health
                            profile.getCombatEntity()
                                    .setHealth(

                                            profile.getCombatEntity()
                                                    .getStats()
                                                    .getFinalStat(
                                                            StatType.MAX_HEALTH
                                                    )
                                    );

                            // Re-apply race
                            raceManager
                                    .applyRace(
                                            profile
                                    );

                            // Re-sync equipment
                            equipmentManager
                                    .updateEquipment(
                                            player
                                    );

                        },

                        1L
                );
    }
}