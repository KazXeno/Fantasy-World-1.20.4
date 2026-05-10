package me.KazXeno.fantasyWorld.combat.display;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.entity.LivingEntity;

public class MobHealthDisplay {

    // Update mob health display
    public void updateHealth(LivingEntity entity,
                             CombatEntity combatEntity) {
        double health = combatEntity.getHealth();

        double maxHealth = combatEntity.getStats().getFinalStat(StatType.MAX_HEALTH);

        // Get clean mob name
        String mobName = formatName(entity.getType().name());

        // Format health
        String healthText = String.format("%.0f", health) + "§7/§f"
                          + String.format("%.0f", maxHealth);

        // Set custom name
        entity.setCustomName("§f" + mobName + " §c" + healthText);

        entity.setCustomNameVisible(true);
    }

    // Format entity type name
    private String formatName(String name) {

        String lower = name.toLowerCase().replace("_", " ");

        String[] parts = lower.split(" ");

        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            builder.append(Character.toUpperCase(part.charAt(0)));
            builder.append(part.substring(1));
            builder.append(" ");
        }

        return builder.toString().trim();
    }
}