package me.KazXeno.fantasyWorld.item.weapon;

import me.KazXeno.fantasyWorld.combat.DamageType;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class WeaponRegistry {

    // Registered weapons
    private final Map<Material, WeaponData> weapons =
            new HashMap<>();

    public WeaponRegistry() {

        registerDefaults();
    }

    // Register default weapons
    private void registerDefaults() {

        // Wooden sword
        registerWeapon(
                Material.WOODEN_SWORD,
                new WeaponData(
                        WeaponType.SWORD,
                        DamageType.MELEE,
                        20,
                        1.6,
                        1.0
                )
        );

        // Stone sword
        registerWeapon(
                Material.STONE_SWORD,
                new WeaponData(
                        WeaponType.SWORD,
                        DamageType.MELEE,
                        30,
                        1.6,
                        1.0
                )
        );

        // Iron sword
        registerWeapon(
                Material.IRON_SWORD,
                new WeaponData(
                        WeaponType.SWORD,
                        DamageType.MELEE,
                        40,
                        1.6,
                        1.2
                )
        );

        // Diamond sword
        registerWeapon(
                Material.DIAMOND_SWORD,
                new WeaponData(
                        WeaponType.SWORD,
                        DamageType.MELEE,
                        55,
                        1.6,
                        1.4
                )
        );

        // Netherite sword
        registerWeapon(
                Material.NETHERITE_SWORD,
                new WeaponData(
                        WeaponType.SWORD,
                        DamageType.MELEE,
                        70,
                        1.6,
                        1.6
                )
        );

        // Bow
        registerWeapon(
                Material.BOW,
                new WeaponData(
                        WeaponType.BOW,
                        DamageType.RANGE,
                        45,
                        1.0,
                        1.5
                )
        );

        // Crossbow
        registerWeapon(
                Material.CROSSBOW,
                new WeaponData(
                        WeaponType.CROSSBOW,
                        DamageType.RANGE,
                        55,
                        0.8,
                        1.8
                )
        );

        // Staff
        registerWeapon(
                Material.BLAZE_ROD,
                new WeaponData(
                        WeaponType.STAFF,
                        DamageType.MAGIC,
                        50,
                        1.2,
                        1.8
                )
        );
    }

    // Register weapon
    public void registerWeapon(Material material,
                               WeaponData data) {

        weapons.put(material, data);
    }

    // Get weapon data
    public WeaponData getWeapon(Material material) {

        return weapons.get(material);
    }

    // Check weapon existence
    public boolean hasWeapon(Material material) {

        return weapons.containsKey(material);
    }
}