package me.KazXeno.fantasyWorld.item.custom;

import me.KazXeno.fantasyWorld.item.custom.data.CustomWeaponItem;
import me.KazXeno.fantasyWorld.item.custom.lore.LoreBuilder;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemBuilder {
    // Lore builder
    private final LoreBuilder
            loreBuilder =
            new LoreBuilder();
    // Plugin instance
    private final JavaPlugin plugin;

    public ItemBuilder(
            JavaPlugin plugin) {

        this.plugin = plugin;
    }

    // Build custom item
    public ItemStack build(
            CustomItem item) {

        ItemStack stack =
                new ItemStack(
                        item.getMaterial()
                );

        ItemMeta meta =
                stack.getItemMeta();

        if (meta == null) {
            return stack;
        }

        // Set display name
        meta.setDisplayName(
                item.getRarity()
                        .getColor()
                        + item.getName()
        );

        // Generate lore
        if (item instanceof CustomWeaponItem weaponItem) {
            meta.setLore(loreBuilder.buildWeaponLore(weaponItem));
        }

        // Hide attributes
        meta.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES
        );

        // Store custom item id
        meta.getPersistentDataContainer()
                .set(
                        new NamespacedKey(
                                plugin,
                                "custom_item_id"
                        ),
                        PersistentDataType.STRING,
                        item.getId()
                );

        stack.setItemMeta(meta);

        return stack;
    }
}