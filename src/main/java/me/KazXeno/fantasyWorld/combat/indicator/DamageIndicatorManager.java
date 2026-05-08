package me.KazXeno.fantasyWorld.combat.indicator;

import me.KazXeno.fantasyWorld.combat.DamageResult;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

public class DamageIndicatorManager {

    // Plugin instance
    private final JavaPlugin plugin;

    public DamageIndicatorManager(
            JavaPlugin plugin) {

        this.plugin = plugin;
    }

    // Spawn damage indicator
    public void spawnDamage(
            LivingEntity entity,
            DamageResult result) {

        // Random offset
        double offsetX =
                ThreadLocalRandom.current()
                        .nextDouble(-0.4, 0.4);

        double offsetZ =
                ThreadLocalRandom.current()
                        .nextDouble(-0.4, 0.4);

        double offsetY =
                ThreadLocalRandom.current()
                        .nextDouble(1.0, 1.8);

        // Spawn location
        Location location =
                entity.getLocation()
                        .add(
                                offsetX,
                                offsetY,
                                offsetZ
                        );

        // Format damage
        String damageText =
                String.format(
                        "%.1f",
                        result.getFinalDamage()
                );

        // Critical damage
        if (result.isCritical()) {

            damageText =
                    "§6✧ "
                            + damageText
                            + " ✧";
        }

        // Normal damage
        else {

            damageText =
                    "§f" + damageText;
        }

        // Spawn hologram
        ArmorStand stand =
                (ArmorStand) entity.getWorld()
                        .spawnEntity(
                                location,
                                EntityType.ARMOR_STAND
                        );

        // Configure hologram
        stand.setVisible(false);

        stand.setGravity(false);

        stand.setMarker(true);

        stand.setSmall(true);

        stand.setCustomNameVisible(true);

        stand.setCustomName(damageText);

        // Remove hologram later
        new BukkitRunnable() {

            @Override
            public void run() {

                if (!stand.isDead()) {
                    stand.remove();
                }
            }

        }.runTaskLater(
                plugin,
                20L
        );
    }
}