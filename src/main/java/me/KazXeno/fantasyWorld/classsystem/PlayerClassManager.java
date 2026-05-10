package me.KazXeno.fantasyWorld.classsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerClassManager {

    // Singleton instance
    private static final PlayerClassManager
            instance =
            new PlayerClassManager();

    // Player classes
    private final Map<UUID, ClassType>
            playerClasses =
            new HashMap<>();

    private PlayerClassManager() {
    }

    // Get singleton instance
    public static PlayerClassManager
    getInstance() {

        return instance;
    }

    // Set player class
    public void setClass(
            UUID uuid,
            ClassType classType) {

        playerClasses.put(
                uuid,
                classType
        );
    }

    // Get player class
    public ClassType getClass(
            UUID uuid) {

        return playerClasses.getOrDefault(
                uuid,
                ClassType.WARRIOR
        );
    }

    // Has class
    public boolean hasClass(
            UUID uuid,
            ClassType classType) {

        return getClass(uuid)
                == classType;
    }
}