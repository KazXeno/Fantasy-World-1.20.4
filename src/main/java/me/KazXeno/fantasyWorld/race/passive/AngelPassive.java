package me.KazXeno.fantasyWorld.race.passive;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;

public class AngelPassive
        implements RacePassive {

    @Override
    public void apply(
            PlayerProfile profile) {

        // Angel dynamic runtime
        // handled by RaceTask
        //
        // - Day bonuses
        // - Night penalties
        // - Potion immunity
    }

    @Override
    public void remove(
            PlayerProfile profile) {

        // Remove day modifiers
        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "angel_day"
                );

        // Remove night modifiers
        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "angel_night"
                );
    }
}