package me.KazXeno.fantasyWorld.entity;

import me.KazXeno.fantasyWorld.bridge.BukkitHealthBridge;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.container.StatContainer;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public abstract class CombatEntity {

    // Shared health bridge
    private static final BukkitHealthBridge
            HEALTH_BRIDGE =
            new BukkitHealthBridge();

    // Entity unique id
    protected final UUID uuid;

    // Entity stat container
    protected final StatContainer stats;

    // Current health value
    protected double health;

    // Current mana value
    protected double mana;

    public CombatEntity(
            UUID uuid) {

        this.uuid = uuid;

        this.stats =
                new StatContainer();

        this.health = 0;

        this.mana = 0;
    }

    // Get entity uuid
    public UUID getUuid() {
        return uuid;
    }

    // Get stat container
    public StatContainer getStats() {
        return stats;
    }

    // Get current health
    public double getHealth() {
        return health;
    }

    // Set current health
    public void setHealth(
            double health) {

        // Get max health
        double maxHealth =
                stats.getFinalStat(
                        StatType.MAX_HEALTH
                );

        // Clamp health
        this.health =
                Math.max(
                        0,
                        Math.min(
                                health,
                                maxHealth
                        )
                );

        // Sync Bukkit entity
        syncHealth();
    }

    // Damage entity
    public void damage(
            double damage) {

        setHealth(
                health - damage
        );
    }

    // Heal entity
    public void heal(
            double amount) {

        setHealth(
                health + amount
        );
    }

    // Sync Bukkit health
    private void syncHealth() {

        // Get Bukkit entity
        org.bukkit.entity.Entity entity =
                Bukkit.getEntity(uuid);

        // Validate living entity
        if (!(entity
                instanceof LivingEntity
                livingEntity)) {

            return;
        }

        // Sync health
        HEALTH_BRIDGE.syncHealth(
                livingEntity,
                this
        );
    }
    // Get current health
    public double getCurrentHealth(){ return health;}

    // Get current mana
    public double getMana() {
        return mana;
    }

    // Set current mana
    public void setMana(double mana) {
        this.mana = mana;
    }


}