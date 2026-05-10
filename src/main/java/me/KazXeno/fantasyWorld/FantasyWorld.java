package me.KazXeno.fantasyWorld;

import me.KazXeno.fantasyWorld.command.*;
import me.KazXeno.fantasyWorld.item.custom.gui.ItemBrowserListener;
import me.KazXeno.fantasyWorld.item.equipment.EquipmentListener;
import me.KazXeno.fantasyWorld.item.equipment.EquipmentManager;
import me.KazXeno.fantasyWorld.item.weapon.skill.WeaponSkillListener;
import me.KazXeno.fantasyWorld.listener.CombatListener;
import me.KazXeno.fantasyWorld.listener.PlayerJoinListener;
import me.KazXeno.fantasyWorld.profile.ProfileListener;
import me.KazXeno.fantasyWorld.race.listener.RaceListener;
import me.KazXeno.fantasyWorld.race.task.RaceTask;
import me.KazXeno.fantasyWorld.skill.SkillManager;
import me.KazXeno.fantasyWorld.skill.impl.WarriorHealSkill;
import me.KazXeno.fantasyWorld.task.HealthRegenTask;
import me.KazXeno.fantasyWorld.task.ManaRegenTask;
import me.KazXeno.fantasyWorld.task.MovementSyncTask;
import me.KazXeno.fantasyWorld.task.PlayerSyncTask;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FantasyWorld extends JavaPlugin {

    // Plugin instance
    private static FantasyWorld instance;

    @Override
    public void onEnable() {
        // Set plugin instance
        instance = this;


        // Register test combat command
        PluginCommand testCombatCommand = getCommand("testcombat");
        if (testCombatCommand != null) {
            TestCombatCommand command = new TestCombatCommand();
            testCombatCommand.setExecutor(command);
            testCombatCommand.setTabCompleter(command);
        }
        // Register stats command
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand());
        // Register skill cast command
        Objects.requireNonNull(getCommand("cast")).setExecutor(new SkillCastCommand());
        // Register get item command
        Objects.requireNonNull(getCommand("getitem")).setExecutor(new GetItemCommand());
        // Register set race command
        Objects.requireNonNull(getCommand("setrace")).setExecutor(new SetRaceCommand());
        // Register item browser listener
        getServer().getPluginManager().registerEvents(new ItemBrowserListener(), this);
        // Register profile listener
        getServer().getPluginManager().registerEvents(new ProfileListener(), this);
        // Register race listener
        getServer().getPluginManager().registerEvents(new RaceListener(), this);
        // Register skills
        SkillManager.getInstance().registerSkill(new WarriorHealSkill());
        // Start player sync task
        new PlayerSyncTask().start(this);
        // Start mana regen task
        new ManaRegenTask().start(this);
        // Start health regen task
        new HealthRegenTask().start(this);
        // Start race runtime task
        new RaceTask().start(this);
        // Start movement sync task
        new MovementSyncTask().start(this);
        // Register combat listener
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        // Register weapon skill listener
        getServer().getPluginManager().registerEvents(new WeaponSkillListener(), this);
        // Register equipment listener
        getServer().getPluginManager().registerEvents(new EquipmentListener(), this);
        // Register player join listener
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        // Initialize online player equipment
        EquipmentManager equipmentManager = new EquipmentManager();
        for (Player player : getServer().getOnlinePlayers()) {
            equipmentManager.updateEquipment(player);
        }

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