package me.KazXeno.fantasyWorld.command;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.profile.ProfileManager;
import me.KazXeno.fantasyWorld.race.RaceManager;
import me.KazXeno.fantasyWorld.race.RaceType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRaceCommand
        implements CommandExecutor {

    // Shared profile manager
    private final ProfileManager
            profileManager =
            ProfileManager.getInstance();

    // Shared race manager
    private final RaceManager
            raceManager =
            RaceManager.getInstance();

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
                    "只有玩家可以使用此命令"
            );

            return true;
        }

        // Validate args
        if (args.length < 1) {

            player.sendMessage(
                    "§c用法: /setrace <race>"
            );

            return true;
        }

        // Parse race
        RaceType raceType;

        try {

            raceType =
                    RaceType.valueOf(
                            args[0]
                                    .toUpperCase()
                    );

        } catch (
                IllegalArgumentException e
        ) {

            player.sendMessage(
                    "§c未知种族"
            );

            return true;
        }

        // Get profile
        PlayerProfile profile =
                profileManager.getProfile(
                        player
                );

        // Remove old race
        raceManager.removeRace(
                profile
        );

        // Set race
        profile.setPlayerRace(
                raceType.name()
                        .toLowerCase()
        );

        // Apply new race
        raceManager.applyRace(
                profile
        );

        // Message
        player.sendMessage(
                "§a种族已切换为: §f"
                        + raceType.name()
        );

        return true;
    }
}