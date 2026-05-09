package me.KazXeno.fantasyWorld.profile;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.KazXeno.fantasyWorld.profile.data.ProfileStorage;

public class ProfileManager {

    // Singleton instance
    private static final ProfileManager instance = new ProfileManager();
    // Loaded profiles
    private final Map<UUID, PlayerProfile> profiles = new HashMap<>();
    // Profile storage
    private final ProfileStorage storage = new ProfileStorage();

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