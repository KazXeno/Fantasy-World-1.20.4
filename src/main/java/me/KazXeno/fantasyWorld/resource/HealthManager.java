package me.KazXeno.fantasyWorld.resource;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

public class HealthManager {

    // Get max health value
    public double getMaxHealth(CombatEntity entity) {
        return entity.getStats().getFinalStat(StatType.MAX_HEALTH);
    }

    // Heal entity health
    public void heal(CombatEntity entity, double amount) {
        double maxHealth = getMaxHealth(entity);
        double newHealth = entity.getHealth() + amount;
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }
        entity.setHealth(newHealth);
    }

    // Damage entity health
    public void damage(CombatEntity entity, double amount) {
        double newHealth = entity.getHealth() - amount;
        if (newHealth < 0) {
            newHealth = 0;
        }
        entity.setHealth(newHealth);
    }

    // Set health with clamp
    public void setHealth(CombatEntity entity, double health) {
        double maxHealth = getMaxHealth(entity);
        if (health < 0) {
            health = 0;
        }
        if (health > maxHealth) {
            health = maxHealth;
        }
        entity.setHealth(health);
    }

    // Check if entity is dead
    public boolean isDead(CombatEntity entity) {
        return entity.getHealth() <= 0;
    }

    // Restore full health
    public void restoreFullHealth(CombatEntity entity) {
        entity.setHealth(getMaxHealth(entity));
    }
}