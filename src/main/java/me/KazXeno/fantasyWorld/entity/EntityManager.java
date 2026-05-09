package me.KazXeno.fantasyWorld.entity;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.profile.ProfileManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EntityManager {

    // Singleton instance
    private static final EntityManager
            instance =
            new EntityManager();

    // Registered combat entities
    private final Map<UUID, CombatEntity>
            entities =
            new HashMap<>();

    // Shared profile manager
    private final ProfileManager
            profileManager =
            ProfileManager.getInstance();

    // Private constructor
    private EntityManager() {
    }

    // Get singleton instance
    public static EntityManager
    getInstance() {

        return instance;
    }

    // Register player combat entity
    public PlayerCombatEntity registerPlayer(
            Player player) {

        // Return existing entity
        if (entities.containsKey(
                player.getUniqueId()
        )) {

            return (PlayerCombatEntity)
                    entities.get(
                            player.getUniqueId()
                    );
        }

        // Get player profile
        PlayerProfile profile =
                profileManager.getProfile(
                        player
                );

        // Get combat entity
        PlayerCombatEntity combatEntity =
                profile.getCombatEntity();

        // Register runtime entity
        entities.put(
                player.getUniqueId(),
                combatEntity
        );

        return combatEntity;
    }

    // Register mob combat entity
    public MobCombatEntity registerMob(
            LivingEntity entity) {

        // Return existing entity
        if (entities.containsKey(
                entity.getUniqueId()
        )) {

            return (MobCombatEntity)
                    entities.get(
                            entity.getUniqueId()
                    );
        }

        // Create combat entity
        MobCombatEntity combatEntity =
                new MobCombatEntity(
                        entity.getUniqueId()
                );

        // Register entity
        entities.put(
                entity.getUniqueId(),
                combatEntity
        );

        return combatEntity;
    }

    // Remove combat entity
    public void removeEntity(
            UUID uuid) {

        entities.remove(uuid);
    }

    // Get combat entity
    public CombatEntity getEntity(
            UUID uuid) {

        return entities.get(uuid);
    }

    // Check entity existence
    public boolean hasEntity(
            UUID uuid) {

        return entities.containsKey(
                uuid
        );
    }

    // Get all entities
    public Map<UUID, CombatEntity>
    getEntities() {

        return entities;
    }
}