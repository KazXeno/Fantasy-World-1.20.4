package me.KazXeno.fantasyWorld.race;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.race.passive.*;

import java.util.EnumMap;
import java.util.Map;

public class RaceManager {

    // Singleton instance
    private static final RaceManager
            instance =
            new RaceManager();

    // Registered passives
    private final Map<RaceType, RacePassive>
            passives =
            new EnumMap<>(
                    RaceType.class
            );

    private RaceManager() {

        registerPassives();
    }

    // Get singleton instance
    public static RaceManager
    getInstance() {

        return instance;
    }

    // Register passives
    private void registerPassives() {

        passives.put(RaceType.HUMAN, new HumanPassive());
        passives.put(RaceType.DRAGONBORN, new DragonbornPassive());
        passives.put(RaceType.ELF, new ElfPassive());
        passives.put(RaceType.ORC, new OrcPassive());
        passives.put(RaceType.UNDEAD, new UndeadPassive());
        passives.put(RaceType.ANGEL, new AngelPassive());
    }

    // Apply race passive
    public void applyRace(
            PlayerProfile profile) {
        System.out.println(
                "Applying race: "
                        + profile.getPlayerRace()
        );
        // Remove all old passives
        removeRace(profile);

        // Get race type
        RaceType raceType =
                RaceType.valueOf(
                        profile.getPlayerRace()
                                .toUpperCase()
                );

        // Get passive
        RacePassive passive =
                passives.get(raceType);
        System.out.println(
                "Passive found: "
                        + passive
        );
        // Apply passive
        if (passive != null) {

            passive.apply(profile);
        }
    }

    // Remove race passive
    public void removeRace(
            PlayerProfile profile) {

        for (RacePassive passive
                : passives.values()) {

            passive.remove(profile);
        }
    }
}