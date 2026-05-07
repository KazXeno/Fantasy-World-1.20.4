package me.KazXeno.fantasyWorld.stats.instance;

import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifierType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StatInstance {

    // Stat type
    private final StatType statType;

    // Base value before modifiers
    private double baseValue;

    // Active modifiers
    private final List<StatModifier> modifiers = new ArrayList<>();

    public StatInstance(StatType statType, double baseValue) {
        this.statType = statType;
        this.baseValue = baseValue;
    }

    // Get stat type
    public StatType getStatType() {
        return statType;
    }

    // Get base value
    public double getBaseValue() {
        return baseValue;
    }

    // Set base value
    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    // Add stat modifier
    public void addModifier(StatModifier modifier) {
        modifiers.add(modifier);
    }

    // Remove stat modifier
    public void removeModifier(StatModifier modifier) {
        modifiers.remove(modifier);
    }

    // Remove modifiers from source
    public void removeModifiersBySource(String source) {
        Iterator<StatModifier> iterator = modifiers.iterator();
        while (iterator.hasNext()) {
            StatModifier modifier = iterator.next();
            if (modifier.getSource().equalsIgnoreCase(source)) {
                iterator.remove();
            }
        }
    }

    // Get all modifiers
    public List<StatModifier> getModifiers() {
        return modifiers;
    }

    // Calculate final stat value
    public double getFinalValue() {
        double flatBonus = 0;
        double percentBonus = 0;
        for (StatModifier modifier : modifiers) {
            if (modifier.getModifierType() == StatModifierType.FLAT) {
                flatBonus += modifier.getValue();
            }
            else if (modifier.getModifierType() == StatModifierType.PERCENT) {
                percentBonus += modifier.getValue();
            }
        }
        return (baseValue + flatBonus) * (1 + percentBonus);
    }
}