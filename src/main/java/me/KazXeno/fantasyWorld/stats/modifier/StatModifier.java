package me.KazXeno.fantasyWorld.stats.modifier;

import me.KazXeno.fantasyWorld.stats.StatType;

public class StatModifier {

    // Modifier source name
    private final String source;
    // Target stat type
    private final StatType statType;
    // Modifier value
    private final double value;
    // Modifier calculation type
    private final StatModifierType modifierType;

    public StatModifier(String source,
                        StatType statType,
                        double value,
                        StatModifierType modifierType) {

        this.source = source;
        this.statType = statType;
        this.value = value;
        this.modifierType = modifierType;
    }

    // Get modifier source
    public String getSource() {
        return source;
    }

    // Get target stat type
    public StatType getStatType() {
        return statType;
    }

    // Get modifier value
    public double getValue() {
        return value;
    }

    // Get modifier type
    public StatModifierType getModifierType() {
        return modifierType;
    }
}