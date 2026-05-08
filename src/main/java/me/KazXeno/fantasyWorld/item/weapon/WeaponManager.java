package me.KazXeno.fantasyWorld.item.weapon;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WeaponManager {

    // Weapon registry
    private final WeaponRegistry registry =
            new WeaponRegistry();

    // Get player weapon
    public WeaponData getWeapon(Player player) {

        ItemStack item =
                player.getInventory()
                        .getItemInMainHand();

        // Validate empty hand
        if (item.getType() == Material.AIR) {
            return null;
        }

        return registry.getWeapon(
                item.getType()
        );
    }
}