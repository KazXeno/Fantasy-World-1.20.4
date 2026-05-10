package me.KazXeno.fantasyWorld.item.custom;

import me.KazXeno.fantasyWorld.item.custom.data.CustomWeaponItem;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Material;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomItemRegistry {

    // Registered items
    private final Map<String,
            CustomItem> items =
            new HashMap<>();

    public CustomItemRegistry() {

        // Register warrior blade
        registerItem(
                new CustomWeaponItem(
                        "warrior_blade",
                        "Warrior Blade",
                        Material.IRON_SWORD,
                        CustomItemRarity.RARE,

                        "战士",
                        50,

                        "warrior_heal",

                        Map.of(
                                StatType.MELEE_DAMAGE,
                                5.0,

                                StatType.CRIT_CHANCE,
                                5.0,

                                StatType.CRIT_DAMAGE,
                                10.0
                        ),

                        "伤害提升",

                        "右键",

                        50,

                        30,

                        List.of(
                                "提升 5 点暴击率",
                                "提升 10 点暴击伤害",
                                "持续 30 秒"
                        )
                )
        );
    }

    // Register custom item
    public void registerItem(
            CustomItem item) {

        items.put(
                item.getId(),
                item
        );
    }

    // Get custom item
    public CustomItem getItem(
            String id) {

        return items.get(id);
    }

    // Get all registered items
    public Collection<CustomItem>
    getItems() {

        return items.values();
    }
}