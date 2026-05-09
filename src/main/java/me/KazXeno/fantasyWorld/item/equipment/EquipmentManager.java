package me.KazXeno.fantasyWorld.item.equipment;

import me.KazXeno.fantasyWorld.FantasyWorld;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.item.custom.CustomItem;
import me.KazXeno.fantasyWorld.item.custom.CustomItemManager;
import me.KazXeno.fantasyWorld.item.custom.data.CustomWeaponItem;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifierType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class EquipmentManager {

    // Shared entity manager
    private final EntityManager
            entityManager =
            EntityManager.getInstance();

    // Custom item manager
    private final CustomItemManager
            itemManager =
            new CustomItemManager(
                    FantasyWorld.getInstance()
            );

    // Update player equipment stats
    public void updateEquipment(
            Player player) {

        // Register/get combat entity
        CombatEntity entity =
                entityManager.registerPlayer(
                        player
                );

        // Remove old weapon modifiers
        entity.getStats()
                .removeModifiersBySource(
                        "mainhand_weapon"
                );

        // Get main hand item
        ItemStack weapon =
                player.getInventory()
                        .getItemInMainHand();

        // Validate empty hand
        if (weapon.getType()
                == Material.AIR) {
            return;
        }

        // Apply main hand stats only
        applyItemStats(
                entity,
                weapon
        );
    }

    // Apply custom item stats
    private void applyItemStats(
            CombatEntity entity,
            ItemStack item) {

        // Validate custom item
        if (!itemManager.isCustomItem(
                item
        )) {
            return;
        }

        // Get custom item
        CustomItem customItem =
                itemManager.getCustomItem(
                        item
                );

        // Validate weapon item
        if (!(customItem
                instanceof CustomWeaponItem
                weaponItem)) {

            return;
        }

        // Apply weapon stats
        for (Map.Entry<StatType, Double>
                entry
                : weaponItem.getStats()
                .entrySet()) {

            entity.getStats()
                    .addModifier(
                            new StatModifier(
                                    "mainhand_weapon",
                                    entry.getKey(),
                                    entry.getValue(),
                                    StatModifierType.FLAT
                            )
                    );
        }
    }
}