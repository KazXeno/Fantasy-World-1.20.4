package me.KazXeno.fantasyWorld;

import me.KazXeno.fantasyWorld.command.TestCombatCommand;
import me.KazXeno.fantasyWorld.task.ManaRegenTask;
import me.KazXeno.fantasyWorld.task.HealthRegenTask;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import me.KazXeno.fantasyWorld.task.PlayerSyncTask;
import me.KazXeno.fantasyWorld.listener.CombatListener;

public final class FantasyWorld extends JavaPlugin {

    // Plugin instance
    private static FantasyWorld instance;

    @Override
    public void onEnable() {
        // Set plugin instance
        instance = this;


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
        // Start mana regen task
        new ManaRegenTask().start(this);
        // Start health regen task
        new HealthRegenTask().start(this);
        // Register combat listener
        getServer().getPluginManager().registerEvents(new CombatListener(), this);

        getLogger().info("幻灵世界已启动");
    }

    @Override
    public void onDisable() {
        getLogger().info("幻灵世界已关闭");
    }

    public static FantasyWorld getInstance(){
        return instance;
    }
}