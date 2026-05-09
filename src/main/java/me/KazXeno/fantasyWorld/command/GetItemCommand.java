package me.KazXeno.fantasyWorld.command;

import me.KazXeno.fantasyWorld.item.custom.gui.ItemBrowserGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetItemCommand
        implements CommandExecutor {

    // Item browser GUI
    private final ItemBrowserGUI
            gui =
            new ItemBrowserGUI();

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        // Validate player
        if (!(sender instanceof Player player)) {
            return true;
        }

        // Open GUI
        gui.open(player);

        return true;
    }
}