package me.KazXeno.fantasyWorld.race.listener;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.profile.ProfileManager;
import me.KazXeno.fantasyWorld.race.RaceType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffectType;

public class RaceListener
        implements Listener {

    // Shared profile manager
    private final ProfileManager
            profileManager =
            ProfileManager.getInstance();

    // Angel immunity runtime
    @EventHandler
    public void onPotionEffect(
            EntityPotionEffectEvent event) {

        // Validate player
        if (!(event.getEntity()
                instanceof Player player)) {

            return;
        }

        // Validate new effect
        if (event.getNewEffect()
                == null) {

            return;
        }

        // Get profile
        PlayerProfile profile =
                profileManager.getProfile(
                        player
                );

        // Get race
        RaceType raceType =
                RaceType.valueOf(
                        profile.getPlayerRace()
                                .toUpperCase()
                );

        // Validate angel
        if (raceType
                != RaceType.ANGEL) {

            return;
        }

        // Get effect type
        PotionEffectType effectType =
                event.getNewEffect()
                        .getType();

        // Poison immunity
        if (effectType
                == PotionEffectType.POISON) {

            event.setCancelled(true);
        }

        // Wither immunity
        if (effectType
                == PotionEffectType.WITHER) {

            event.setCancelled(true);
        }
    }
}