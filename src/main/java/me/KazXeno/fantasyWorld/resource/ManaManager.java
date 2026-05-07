package me.KazXeno.fantasyWorld.resource;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;

public class ManaManager {

    // Get max mana value
    public double getMaxMana(CombatEntity entity) {
        return entity.getStats().getFinalStat(StatType.MANA);
    }

    // Add mana to entity
    public void addMana(CombatEntity entity, double amount) {
        double maxMana = getMaxMana(entity);
        double newMana = entity.getMana() + amount;
        if (newMana > maxMana) {
            newMana = maxMana;
        }
        entity.setMana(newMana);
    }

    // Remove mana from entity
    public void removeMana(CombatEntity entity, double amount) {
        double newMana = entity.getMana() - amount;
        if (newMana < 0) {
            newMana = 0;
        }
        entity.setMana(newMana);
    }

    // Set mana with clamp
    public void setMana(CombatEntity entity, double mana) {
        double maxMana = getMaxMana(entity);
        if (mana < 0) {
            mana = 0;
        }
        if (mana > maxMana) {
            mana = maxMana;
        }
        entity.setMana(mana);
    }

    // Check mana requirement
    public boolean hasEnoughMana(CombatEntity entity, double amount) {
        return entity.getMana() >= amount;
    }

    // Restore full mana
    public void restoreFullMana(CombatEntity entity) {
        entity.setMana(getMaxMana(entity));
    }
}