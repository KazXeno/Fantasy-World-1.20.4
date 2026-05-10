package me.KazXeno.fantasyWorld.listener;

import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.profile.ProfileManager;
import me.KazXeno.fantasyWorld.race.RaceManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener
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

    @EventHandler
    public void onJoin(
            PlayerJoinEvent event) {

        // Register combat runtime
        entityManager.registerPlayer(
                event.getPlayer()
        );

        // Get profile
        PlayerProfile profile =
                profileManager.getProfile(
                        event.getPlayer()
                );

        // Apply race runtime
        raceManager.applyRace(
                profile
        );
    }
}