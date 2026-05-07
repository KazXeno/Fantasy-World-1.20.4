package me.KazXeno.fantasyWorld.entity;

import me.KazXeno.fantasyWorld.stats.container.StatContainer;

import java.util.UUID;

public abstract class CombatEntity {

    // Entity unique id
    protected final UUID uuid;
    // Entity stat container
    protected final StatContainer stats;
    // Current health value
    protected double health;
    // Current mana value
    protected double mana;

    public CombatEntity(UUID uuid) {
        this.uuid = uuid;
        this.stats = new StatContainer();
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
    public void setHealth(double health) {
        this.health = health;
    }

    // Get current mana
    public double getMana() {
        return mana;
    }

    // Set current mana
    public void setMana(double mana) {
        this.mana = mana;
    }
}