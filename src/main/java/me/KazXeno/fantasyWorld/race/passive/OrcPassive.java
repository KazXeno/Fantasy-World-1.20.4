package me.KazXeno.fantasyWorld.race.passive;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifierType;

public class OrcPassive
        implements RacePassive {

    @Override
    public void apply(
            PlayerProfile profile) {

        profile.getCombatEntity()
                .getStats()
                .addModifier(
                        new StatModifier(
                                "race_orc",

                                StatType
                                        .FINAL_DAMAGE_MULTIPLIER,

                                15,

                                StatModifierType.FLAT
                        )
                );

        profile.getCombatEntity()
                .getStats()
                .addModifier(
                        new StatModifier(
                                "race_orc",

                                StatType.SPEED,

                                -15,

                                StatModifierType.FLAT
                        )
                );

        profile.getCombatEntity()
                .getStats()
                .addModifier(
                        new StatModifier(
                                "race_orc",

                                StatType
                                        .KNOCKBACK_RESISTANCE,

                                20,

                                StatModifierType.FLAT
                        )
                );

        profile.getCombatEntity()
                .getStats()
                .addModifier(
                        new StatModifier(
                                "race_orc",

                                StatType.MAX_HEALTH,

                                15,

                                StatModifierType.PERCENT
                        )
                );
    }

    @Override
    public void remove(
            PlayerProfile profile) {

        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "race_orc"
                );
    }
}