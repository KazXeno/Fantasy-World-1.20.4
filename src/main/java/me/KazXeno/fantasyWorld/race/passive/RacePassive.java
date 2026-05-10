package me.KazXeno.fantasyWorld.race.passive;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;

public interface RacePassive {

    // Apply race passive
    void apply(
            PlayerProfile profile
    );

    // Remove race passive
    void remove(
            PlayerProfile profile
    );
}