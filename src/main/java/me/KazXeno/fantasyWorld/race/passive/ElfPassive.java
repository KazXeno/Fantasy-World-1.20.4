package me.KazXeno.fantasyWorld.race.passive;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifierType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ElfPassive
        implements RacePassive {

    @Override
    public void apply(
            PlayerProfile profile) {

        // Speed +20%
        profile.getCombatEntity()
                .getStats()
                .addModifier(
                        new StatModifier(
                                "race_elf",

                                StatType.SPEED,

                                20,

                                StatModifierType.FLAT
                        )
                );

        // Max health -15%
        profile.getCombatEntity()
                .getStats()
                .addModifier(
                        new StatModifier(
                                "race_elf",

                                StatType.MAX_HEALTH,

                                -15,

                                StatModifierType.PERCENT
                        )
                );

        // Get player
        Player player =
                Bukkit.getPlayer(
                        profile.getUuid()
                );

        // Validate player
        if (player == null) {
            return;
        }

        // Jump boost II
        player.addPotionEffect(
                new PotionEffect(
                        PotionEffectType.JUMP,

                        Integer.MAX_VALUE,

                        1,

                        false,
                        false,
                        false
                )
        );
    }

    @Override
    public void remove(
            PlayerProfile profile) {

        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "race_elf"
                );

        Player player =
                Bukkit.getPlayer(
                        profile.getUuid()
                );

        if (player != null) {

            player.removePotionEffect(
                    PotionEffectType.JUMP
            );
        }
    }
}