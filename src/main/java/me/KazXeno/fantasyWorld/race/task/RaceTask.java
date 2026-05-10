package me.KazXeno.fantasyWorld.race.task;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.profile.ProfileManager;
import me.KazXeno.fantasyWorld.race.RaceType;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifierType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RaceTask
        extends BukkitRunnable {

    // Shared profile manager
    private final ProfileManager
            profileManager =
            ProfileManager.getInstance();

    // Start task
    public void start(
            org.bukkit.plugin.Plugin plugin) {

        runTaskTimer(
                plugin,
                20L,
                20L
        );
    }

    @Override
    public void run() {

        // Loop online players
        for (Player player
                : Bukkit.getOnlinePlayers()) {

            PlayerProfile profile =
                    profileManager.getProfile(
                            player
                    );

            // Validate race
            RaceType raceType =
                    RaceType.valueOf(
                            profile.getPlayerRace()
                                    .toUpperCase()
                    );

            // Dragonborn runtime
            handleDragonborn(
                    player,
                    profile,
                    raceType
            );

            // Undead runtime
            handleUndead(
                    player,
                    profile,
                    raceType
            );

            // Angel runtime
            handleAngel(
                    player,
                    profile,
                    raceType
            );
        }
    }

    // Dragonborn runtime
    private void handleDragonborn(
            Player player,
            PlayerProfile profile,
            RaceType raceType) {

        // Validate race
        if (raceType
                != RaceType.DRAGONBORN) {

            return;
        }

        // Remove old modifier
        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "dragonborn_water"
                );

        // Water weakness
        if (player.isInWater()) {

            profile.getCombatEntity()
                    .getStats()
                    .addModifier(
                            new StatModifier(
                                    "dragonborn_water",

                                    StatType
                                            .FINAL_DAMAGE_MULTIPLIER,

                                    -20,

                                    StatModifierType
                                            .PERCENT
                            )
                    );
        }
    }

    // Undead runtime
    private void handleUndead(
            Player player,
            PlayerProfile profile,
            RaceType raceType) {

        // Validate race
        if (raceType
                != RaceType.UNDEAD) {

            return;
        }

        // Remove old modifiers
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

        // Get world time
        long time =
                player.getWorld()
                        .getTime();

        // Night runtime
        boolean night =
                time >= 13000
                        && time <= 23000;

        // Night buff
        if (night) {

            profile.getCombatEntity()
                    .getStats()
                    .addModifier(
                            new StatModifier(
                                    "undead_night",

                                    StatType
                                            .FINAL_DAMAGE_MULTIPLIER,

                                    20,

                                    StatModifierType
                                            .FLAT
                            )
                    );

            profile.getCombatEntity()
                    .getStats()
                    .addModifier(
                            new StatModifier(
                                    "undead_night",

                                    StatType
                                            .LIFESTEAL,

                                    30,

                                    StatModifierType
                                            .FLAT
                            )
                    );
        }

        // Day debuff
        else {

            profile.getCombatEntity()
                    .getStats()
                    .addModifier(
                            new StatModifier(
                                    "undead_day",

                                    StatType
                                            .FINAL_DAMAGE_MULTIPLIER,

                                    -15,

                                    StatModifierType
                                            .FLAT
                            )
                    );

            profile.getCombatEntity()
                    .getStats()
                    .addModifier(
                            new StatModifier(
                                    "undead_day",

                                    StatType
                                            .LIFESTEAL,

                                    15,

                                    StatModifierType
                                            .FLAT
                            )
                    );
        }
    }

    // Angel runtime
    private void handleAngel(
            Player player,
            PlayerProfile profile,
            RaceType raceType) {

        // Validate race
        if (raceType
                != RaceType.ANGEL) {

            return;
        }

        // Remove old modifiers
        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "angel_day"
                );

        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "angel_night"
                );

        // Get world time
        long time =
                player.getWorld()
                        .getTime();

        // Day runtime
        boolean day =
                time >= 0
                        && time <= 12000;

        // Day buff
        if (day) {

            profile.getCombatEntity()
                    .getStats()
                    .addModifier(
                            new StatModifier(
                                    "angel_day",

                                    StatType
                                            .FINAL_HEALING_MULTIPLIER,

                                    5,

                                    StatModifierType
                                            .PERCENT
                            )
                    );

            profile.getCombatEntity()
                    .getStats()
                    .addModifier(
                            new StatModifier(
                                    "angel_day",

                                    StatType.SPEED,

                                    10,

                                    StatModifierType
                                            .PERCENT
                            )
                    );
        }

        // Night debuff
        else {

            profile.getCombatEntity()
                    .getStats()
                    .addModifier(
                            new StatModifier(
                                    "angel_night",

                                    StatType
                                            .FINAL_DAMAGE_MULTIPLIER,

                                    -15,

                                    StatModifierType
                                            .FLAT
                            )
                    );
        }
    }
}