package me.KazXeno.fantasyWorld.item.custom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomItemRegistry {

    // Shared instance
    private static final CustomItemRegistry
            instance =
            new CustomItemRegistry();

    // Registered items
    private final Map<String,
            CustomItem> items =
            new HashMap<>();

    // Private constructor
    private CustomItemRegistry() {

        // Load json items
        new ItemLoader()
                .loadItems(this);
    }

    // Get shared instance
    public static CustomItemRegistry
    getInstance() {

        return instance;
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