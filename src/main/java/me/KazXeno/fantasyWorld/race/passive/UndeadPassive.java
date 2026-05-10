package me.KazXeno.fantasyWorld.race.passive;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifierType;

public class UndeadPassive
        implements RacePassive {

    @Override
    public void apply(
            PlayerProfile profile) {

        // Base lifesteal
        profile.getCombatEntity()
                .getStats()
                .addModifier(
                        new StatModifier(
                                "race_undead",

                                StatType.LIFESTEAL,

                                15,

                                StatModifierType.FLAT
                        )
                );
    }

    @Override
    public void remove(
            PlayerProfile profile) {

        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "race_undead"
                );

        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "undead_day"
                );

        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "undead_night"
                );
    }
}