package me.KazXeno.fantasyWorld.profile;

import me.KazXeno.fantasyWorld.entity.PlayerCombatEntity;

import java.util.UUID;

public class PlayerProfile {

    // Player uuid
    private final UUID uuid;

    // Combat entity
    private final PlayerCombatEntity
            combatEntity;

    // Player level
    private int level;

    // Player experience
    private double experience;

    // Player class id
    private String playerClass;

    // Player race id
    private String playerRace;

    public PlayerProfile(
            UUID uuid) {

        this.uuid = uuid;

        this.combatEntity =
                new PlayerCombatEntity(
                        uuid
                );

        // Default progression
        this.level = 1;

        this.experience = 0;

        // Default identity
        this.playerClass = "none";

        this.playerRace = "human";
    }

    // Get uuid
    public UUID getUuid() {
        return uuid;
    }

    // Get combat entity
    public PlayerCombatEntity
    getCombatEntity() {

        return combatEntity;
    }

    // Get level
    public int getLevel() {
        return level;
    }

    // Set level
    public void setLevel(
            int level) {

        this.level = level;
    }

    // Get experience
    public double getExperience() {
        return experience;
    }

    // Set experience
    public void setExperience(
            double experience) {

        this.experience = experience;
    }

    // Get player class
    public String getPlayerClass() {
        return playerClass;
    }

    // Set player class
    public void setPlayerClass(
            String playerClass) {

        this.playerClass = playerClass;
    }

    // Get player race
    public String getPlayerRace() {
        return playerRace;
    }

    // Set player race
    public void setPlayerRace(
            String playerRace) {

        this.playerRace = playerRace;
    }
}