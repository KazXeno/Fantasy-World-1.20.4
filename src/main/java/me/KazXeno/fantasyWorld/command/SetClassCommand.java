package me.KazXeno.fantasyWorld.command;

import me.KazXeno.fantasyWorld.classsystem.ClassType;
import me.KazXeno.fantasyWorld.classsystem.PlayerClassManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetClassCommand
        implements CommandExecutor {

    // Shared class manager
    private final PlayerClassManager
            classManager =
            PlayerClassManager.getInstance();

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        // Validate player
        if (!(sender
                instanceof Player player)) {

            sender.sendMessage(
                    "Only players can use this command"
            );

            return true;
        }

        // Validate args
        if (args.length < 1) {

            player.sendMessage(
                    "§cUsage: /setclass <class>"
            );

            return true;
        }

        // Parse class
        ClassType classType;

        try {

            classType =
                    ClassType.valueOf(
                            args[0]
                                    .toUpperCase()
                    );

        } catch (
                IllegalArgumentException e
        ) {

            player.sendMessage(
                    "§cUnknown class"
            );

            return true;
        }

        // Set class
        classManager.setClass(
                player.getUniqueId(),
                classType
        );

        // Message
        player.sendMessage(
                "§aClass changed to: §f"
                        + classType.name()
        );

        return true;
    }
}