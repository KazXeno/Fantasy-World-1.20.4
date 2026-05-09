package me.KazXeno.fantasyWorld.profile;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener
        implements Listener {

    // Shared profile manager
    private final ProfileManager
            profileManager =
            ProfileManager.getInstance();

    // Save on quit
    @EventHandler
    public void onQuit(
            PlayerQuitEvent event) {

        PlayerProfile profile =
                profileManager.getProfile(
                        event.getPlayer()
                );

        // Save profile
        profileManager.saveProfile(
                profile
        );

        // Unload profile
        profileManager.unloadProfile(
                profile.getUuid()
        );
    }
}