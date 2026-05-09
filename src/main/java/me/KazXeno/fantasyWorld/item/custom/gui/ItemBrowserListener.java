package me.KazXeno.fantasyWorld.item.custom.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemBrowserListener
        implements Listener {

    @EventHandler
    public void onClick(
            InventoryClickEvent event) {

        // Validate GUI
        if (!event.getView()
                .getTitle()
                .equals(
                        ItemBrowserGUI.GUI_TITLE
                )) {
            return;
        }

        // Cancel inventory movement
        event.setCancelled(true);

        // Validate player
        if (!(event.getWhoClicked()
                instanceof Player player)) {
            return;
        }

        // Validate clicked item
        ItemStack item =
                event.getCurrentItem();

        if (item == null) {
            return;
        }

        // Give cloned item
        player.getInventory()
                .addItem(item.clone());

        player.sendMessage(
                "§aReceived custom item."
        );
    }
}