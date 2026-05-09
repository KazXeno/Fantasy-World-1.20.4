package me.KazXeno.fantasyWorld.item.custom.lore;

import me.KazXeno.fantasyWorld.item.custom.data.CustomWeaponItem;
import me.KazXeno.fantasyWorld.stats.StatType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoreBuilder {

    // Build weapon lore
    public List<String> buildWeaponLore(
            CustomWeaponItem item) {

        List<String> lore =
                new ArrayList<>();

        // Requirements
        lore.add(
                "§7职业要求: §f"
                        + item.getRequiredClass()
        );

        lore.add(
                "§7等级要求: §f"
                        + item.getRequiredLevel()
        );

        lore.add("");

        lore.add("§8────────────");

        // Stats
        for (Map.Entry<StatType, Double>
                entry
                : item.getStats()
                .entrySet()) {

            lore.add(
                    formatStat(
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        lore.add("");

        lore.add("§8────────────");

        // Ability title
        lore.add(
                "§a"
                        + item.getAbilityName()
                        + " §7["
                        + item.getAbilityTrigger()
                        + "]"
        );

        // Mana/cooldown
        lore.add(
                "§b"
                        + String.format(
                        "%.0f",
                        item.getManaCost()
                )
                        + " 魔力 §8| §e"
                        + String.format(
                        "%.0f",
                        item.getCooldown()
                )
                        + " 秒冷却"
        );

        lore.add("");

        // Description
        for (String line
                : item.getDescription()) {

            lore.add(
                    "§7" + line
            );
        }

        return lore;
    }

    // Format stat line
    private String formatStat(
            StatType stat,
            double value) {

        String color =
                switch (stat) {

                    case MAX_HEALTH,
                         HEALTH_REGEN ->
                            "§a";

                    case MANA,
                         MANA_REGEN ->
                            "§b";

                    case CRIT_CHANCE,
                         CRIT_DAMAGE ->
                            "§e";

                    case MELEE_DAMAGE,
                         RANGE_DAMAGE,
                         MAGIC_DAMAGE ->
                            "§c";

                    default ->
                            "§f";
                };

        return color
                + stat.getDisplayName()
                + " §f+"
                + String.format(
                "%.0f",
                value
        );
    }
}