package me.KazXeno.fantasyWorld.item.custom.gui;

import me.KazXeno.fantasyWorld.FantasyWorld;
import me.KazXeno.fantasyWorld.item.custom.CustomItem;
import me.KazXeno.fantasyWorld.item.custom.CustomItemRegistry;
import me.KazXeno.fantasyWorld.item.custom.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemBrowserGUI {

    // GUI title
    public static final String
            GUI_TITLE =
            "Custom Items";

    // Item registry
    private final CustomItemRegistry
            registry =
            new CustomItemRegistry();

    // Item builder
    private final ItemBuilder
            itemBuilder =
            new ItemBuilder(
                    FantasyWorld.getInstance()
            );

    // Open item browser
    public void open(Player player) {

        // Create inventory
        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        54,
                        GUI_TITLE
                );

        int slot = 0;

        // Add registered items
        for (CustomItem item
                : registry.getItems()) {

            ItemStack stack =
                    itemBuilder.build(item);

            inventory.setItem(
                    slot,
                    stack
            );

            slot++;
        }

        // Open GUI
        player.openInventory(inventory);
    }
}