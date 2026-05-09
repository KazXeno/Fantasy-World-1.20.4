package me.KazXeno.fantasyWorld.profile.data;

import me.KazXeno.fantasyWorld.FantasyWorld;
import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ProfileStorage {

    // Plugin instance
    private final FantasyWorld
            plugin =
            FantasyWorld.getInstance();

    // Save player profile
    public void saveProfile(
            PlayerProfile profile) {

        // Players folder
        File folder =
                new File(
                        plugin.getDataFolder(),
                        "players"
                );

        // Create folder
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Player file
        File file =
                new File(
                        folder,
                        profile.getUuid()
                                + ".yml"
                );

        // Config
        FileConfiguration config =
                YamlConfiguration
                        .loadConfiguration(
                                file
                        );

        // Save progression
        config.set(
                "level",
                profile.getLevel()
        );

        config.set(
                "experience",
                profile.getExperience()
        );

        // Save identity
        config.set(
                "class",
                profile.getPlayerClass()
        );

        config.set(
                "race",
                profile.getPlayerRace()
        );

        // Save file
        try {

            config.save(file);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // Load player profile
    public void loadProfile(
            PlayerProfile profile) {

        // Players folder
        File folder =
                new File(
                        plugin.getDataFolder(),
                        "players"
                );

        // Player file
        File file =
                new File(
                        folder,
                        profile.getUuid()
                                + ".yml"
                );

        // First join
        if (!file.exists()) {
            return;
        }

        // Config
        FileConfiguration config =
                YamlConfiguration
                        .loadConfiguration(
                                file
                        );

        // Load progression
        profile.setLevel(
                config.getInt(
                        "level",
                        1
                )
        );

        profile.setExperience(
                config.getDouble(
                        "experience",
                        0
                )
        );

        // Load identity
        profile.setPlayerClass(
                config.getString(
                        "class",
                        "none"
                )
        );

        profile.setPlayerRace(
                config.getString(
                        "race",
                        "human"
                )
        );
    }
}