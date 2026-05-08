package me.KazXeno.fantasyWorld.combat.indicator;

import me.KazXeno.fantasyWorld.combat.DamageResult;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;
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
            LivingEntity attacker,
            LivingEntity victim,
            DamageResult result) {

        // Get direction toward attacker
        Location victimLocation =
                victim.getLocation();

        Location attackerLocation =
                attacker.getLocation();

        double dx =
                attackerLocation.getX()
                        - victimLocation.getX();

        double dz =
                attackerLocation.getZ()
                        - victimLocation.getZ();

        double length =
                Math.sqrt(dx * dx + dz * dz);

        // Normalize direction
        if (length != 0) {

            dx /= length;

            dz /= length;
        }

        // Random spread
        double randomX =
                ThreadLocalRandom.current()
                        .nextDouble(-0.25, 0.25);

        double randomZ =
                ThreadLocalRandom.current()
                        .nextDouble(-0.25, 0.25);

        double randomY =
                ThreadLocalRandom.current()
                        .nextDouble(1.0, 1.6);

        // Spawn location
        Location location =
                victimLocation.clone()
                        .add(
                                dx * 0.6 + randomX,
                                randomY,
                                dz * 0.6 + randomZ
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

        // Spawn text display
        TextDisplay display =
                (TextDisplay) victim.getWorld()
                        .spawnEntity(
                                location,
                                EntityType.TEXT_DISPLAY
                        );

        // Configure display
        display.setText(damageText);

        display.setBillboard(
                Display.Billboard.CENTER
        );

        display.setSeeThrough(false);

        display.setShadowed(false);

        display.setDefaultBackground(false);

        // Remove later
        new BukkitRunnable() {

            @Override
            public void run() {

                if (!display.isDead()) {
                    display.remove();
                }
            }

        }.runTaskLater(
                plugin,
                20L
        );
    }
}