package me.KazXeno.fantasyWorld.profile;

import me.KazXeno.fantasyWorld.profile.data.ProfileStorage;
import me.KazXeno.fantasyWorld.race.RaceManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {

    // Singleton instance
    private static final ProfileManager instance = new ProfileManager();
    // Loaded profiles
    private final Map<UUID, PlayerProfile> profiles = new HashMap<>();
    // Profile storage
    private final ProfileStorage storage = new ProfileStorage();
    // Shared race manager
    private final RaceManager raceManager = RaceManager.getInstance();

    // Get singleton instance
    public static ProfileManager getInstance() {
        return instance;
    }

    // Get/create profile
    public PlayerProfile getProfile(Player player) {
        return profiles.computeIfAbsent(player.getUniqueId(),
                uuid -> {
                    PlayerProfile profile = new PlayerProfile(uuid);
                    // Load saved data
                    storage.loadProfile(profile);
                    // Apply race runtime
                    raceManager.applyRace(profile);
                    return profile;
                }
        );
    }

    // Remove profile
    public void unloadProfile(UUID uuid) {
        profiles.remove(uuid);
    }

    // Save profile
    public void saveProfile(PlayerProfile profile){
        storage.saveProfile(profile);
    }
}