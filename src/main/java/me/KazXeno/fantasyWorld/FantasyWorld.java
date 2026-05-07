package me.KazXeno.fantasyWorld;

import me.KazXeno.fantasyWorld.command.TestCombatCommand;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import me.KazXeno.fantasyWorld.task.PlayerSyncTask;

public final class FantasyWorld extends JavaPlugin {

    @Override
    public void onEnable() {

        // Register test combat command
        PluginCommand testCombatCommand =
                getCommand("testcombat");
        if (testCombatCommand != null) {
            TestCombatCommand command =
                    new TestCombatCommand();
            testCombatCommand.setExecutor(command);
            testCombatCommand.setTabCompleter(command);
        }


        // Start player sync task
        new PlayerSyncTask().start(this);

        getLogger().info("幻灵世界已启动");
    }

    @Override
    public void onDisable() {

        getLogger().info("幻灵世界已关闭");
    }
}