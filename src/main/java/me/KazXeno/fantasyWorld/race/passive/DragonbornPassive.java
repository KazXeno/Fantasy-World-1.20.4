package me.KazXeno.fantasyWorld.race.passive;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifierType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DragonbornPassive
        implements RacePassive {

    @Override
    public void apply(
            PlayerProfile profile) {

        Player player =
                Bukkit.getPlayer(
                        profile.getUuid()
                );

        if (player == null) {
            return;
        }

        // Permanent fire resistance
        player.addPotionEffect(
                new PotionEffect(
                        PotionEffectType
                                .FIRE_RESISTANCE,

                        Integer.MAX_VALUE,

                        0,

                        false,
                        false,
                        false
                )
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
                                            .FLAT
                            )
                    );
        }

        // Remove if not in water
        else {

            profile.getCombatEntity()
                    .getStats()
                    .removeModifiersBySource(
                            "dragonborn_water"
                    );
        }
    }

    @Override
    public void remove(
            PlayerProfile profile) {

        Player player =
                Bukkit.getPlayer(
                        profile.getUuid()
                );

        if (player != null) {

            player.removePotionEffect(
                    PotionEffectType
                            .FIRE_RESISTANCE
            );
        }

        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "dragonborn_water"
                );
    }
}