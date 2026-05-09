package me.KazXeno.fantasyWorld.item.equipment;

import me.KazXeno.fantasyWorld.FantasyWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class EquipmentListener
        implements Listener {

    // Shared equipment manager
    private final EquipmentManager
            equipmentManager =
            new EquipmentManager();

    // Update on item held change
    @EventHandler
    public void onItemHeld(
            PlayerItemHeldEvent event) {

        Player player =
                event.getPlayer();

        // Update next tick
        player.getServer()
                .getScheduler()
                .runTaskLater(
                        FantasyWorld.getInstance(),

                        () -> equipmentManager
                                .updateEquipment(
                                        player
                                ),

                        1L
                );
    }

    // Update on inventory click
    @EventHandler
    public void onInventoryClick(
            InventoryClickEvent event) {

        // Validate player
        if (!(event.getWhoClicked()
                instanceof Player player)) {

            return;
        }

        // Update next tick
        player.getServer()
                .getScheduler()
                .runTaskLater(
                        FantasyWorld.getInstance(),

                        () -> equipmentManager
                                .updateEquipment(
                                        player
                                ),

                        1L
                );
    }

    // Update on hand swap
    @EventHandler
    public void onSwap(
            PlayerSwapHandItemsEvent event) {

        Player player =
                event.getPlayer();

        // Update next tick
        player.getServer()
                .getScheduler()
                .runTaskLater(
                        FantasyWorld.getInstance(),

                        () -> equipmentManager
                                .updateEquipment(
                                        player
                                ),

                        1L
                );
    }

    // Update on join
    @EventHandler
    public void onJoin(
            PlayerJoinEvent event) {

        Player player =
                event.getPlayer();

        // Update next tick
        player.getServer()
                .getScheduler()
                .runTaskLater(
                        FantasyWorld.getInstance(),

                        () -> equipmentManager
                                .updateEquipment(
                                        player
                                ),

                        1L
                );
    }
}