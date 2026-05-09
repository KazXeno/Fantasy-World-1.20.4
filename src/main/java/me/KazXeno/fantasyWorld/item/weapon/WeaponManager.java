package me.KazXeno.fantasyWorld.item.weapon;

import me.KazXeno.fantasyWorld.FantasyWorld;
import me.KazXeno.fantasyWorld.item.custom.CustomItem;
import me.KazXeno.fantasyWorld.item.custom.CustomItemManager;
import me.KazXeno.fantasyWorld.item.custom.data.CustomWeaponItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WeaponManager {

    // Custom item manager
    private final CustomItemManager
            itemManager =
            new CustomItemManager(
                    FantasyWorld.getInstance()
            );

    // Get player weapon
    public CustomWeaponItem getWeapon(
            Player player) {

        ItemStack item =
                player.getInventory()
                        .getItemInMainHand();

        // Validate empty hand
        if (item.getType()
                == Material.AIR) {
            return null;
        }

        // Validate custom item
        if (!itemManager.isCustomItem(
                item)) {
            return null;
        }

        CustomItem customItem =
                itemManager.getCustomItem(
                        item
                );

        // Validate weapon item
        if (!(customItem
                instanceof CustomWeaponItem
                weaponItem)) {

            return null;
        }

        return weaponItem;
    }
}