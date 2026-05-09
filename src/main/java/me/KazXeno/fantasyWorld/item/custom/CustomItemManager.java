package me.KazXeno.fantasyWorld.item.custom;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomItemManager {

    // Plugin instance
    private final JavaPlugin plugin;

    // Item registry
    private final CustomItemRegistry
            registry =
            new CustomItemRegistry();

    public CustomItemManager(
            JavaPlugin plugin) {

        this.plugin = plugin;
    }

    // Get custom item id
    public String getItemId(
            ItemStack item) {

        if (item == null) {
            return null;
        }

        ItemMeta meta =
                item.getItemMeta();

        if (meta == null) {
            return null;
        }

        return meta.getPersistentDataContainer()
                .get(
                        new NamespacedKey(
                                plugin,
                                "custom_item_id"
                        ),
                        PersistentDataType.STRING
                );
    }

    // Check custom item
    public boolean isCustomItem(
            ItemStack item) {

        return getItemId(item)
                != null;
    }

    // Get custom item data
    public CustomItem getCustomItem(
            ItemStack item) {

        String id =
                getItemId(item);

        if (id == null) {
            return null;
        }

        return registry.getItem(id);
    }
}