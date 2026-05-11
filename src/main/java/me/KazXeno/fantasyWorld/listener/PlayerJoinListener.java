package me.KazXeno.fantasyWorld.listener;

import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.profile.ProfileManager;
import me.KazXeno.fantasyWorld.race.RaceManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

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

        // Remove vanilla attack cooldown
        Player player =
                event.getPlayer();

        AttributeInstance attribute =
                player.getAttribute(
                        Attribute.GENERIC_ATTACK_SPEED
                );

        if (attribute != null) {

            attribute.setBaseValue(
                    1024
            );
        }

        // Apply race runtime
        raceManager.applyRace(
                profile
        );
    }
}