package me.KazXeno.fantasyWorld.stats.container;

import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.instance.StatInstance;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;

import java.util.EnumMap;
import java.util.Map;

public class StatContainer {

    // All stat instances
    private final Map<StatType, StatInstance>
            stats =
            new EnumMap<>(
                    StatType.class
            );

    public StatContainer() {

        for (StatType statType
                : StatType.values()) {

            double baseValue = 0;

            // Final multipliers default to 100%
            if (statType == StatType.FINAL_DAMAGE_MULTIPLIER ||
                statType == StatType.FINAL_HEALING_MULTIPLIER ||
                statType == StatType.SPEED ||
                statType == StatType.ATTACK_SPEED) {

                baseValue = 100;
            }

            stats.put(
                    statType,

                    new StatInstance(
                            statType,
                            baseValue
                    )
            );
        }
    }

    // Get stat instance
    public StatInstance getStat(
            StatType statType) {

        return stats.get(statType);
    }

    // Set base stat value
    public void setBaseStat(
            StatType statType,
            double value) {

        stats.get(statType)
                .setBaseValue(value);
    }

    // Get final stat value
    public double getFinalStat(
            StatType statType) {

        return stats.get(statType)
                .getFinalValue();
    }

    // Add stat modifier
    public void addModifier(
            StatModifier modifier) {

        StatInstance instance =
                stats.get(
                        modifier.getStatType()
                );

        if (instance != null) {

            instance.addModifier(
                    modifier
            );
        }
    }

    // Remove modifiers from source
    public void removeModifiersBySource(
            String source) {

        for (StatInstance instance
                : stats.values()) {

            instance.removeModifiersBySource(
                    source
            );
        }
    }
}