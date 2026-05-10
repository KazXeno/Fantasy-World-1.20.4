package me.KazXeno.fantasyWorld.command;

import me.KazXeno.fantasyWorld.combat.DamageContext;
import me.KazXeno.fantasyWorld.combat.DamageEngine;
import me.KazXeno.fantasyWorld.combat.DamageResult;
import me.KazXeno.fantasyWorld.combat.DamageType;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.entity.PlayerCombatEntity;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestCombatCommand implements CommandExecutor, TabCompleter {

    // Entity manager
    private final EntityManager entityManager = EntityManager.getInstance();

    // Damage engine
    private final DamageEngine damageEngine =
            new DamageEngine();

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!(sender instanceof Player player)) {
            return true;
        }

        // Validate arguments
        if (args.length < 9) {

            player.sendMessage(" ");

            player.sendMessage("/testcombat <baseDamage> <scaling> <damageType> <critChance> <critDamage> <defense> <penetration> <damageReduction> <lifesteal>");

            player.sendMessage(" ");

            return true;
        }

        try {

            // Parse command values
            double baseDamage =
                    Double.parseDouble(args[0]);

            double scaling =
                    Double.parseDouble(args[1]);

            DamageType damageType =
                    DamageType.valueOf(
                            args[2].toUpperCase()
                    );

            double critChance =
                    Double.parseDouble(args[3]);

            double critDamage =
                    Double.parseDouble(args[4]);

            double defense =
                    Double.parseDouble(args[5]);

            double penetration =
                    Double.parseDouble(args[6]);

            double damageReduction =
                    Double.parseDouble(args[7]);

            double lifesteal =
                    Double.parseDouble(args[8]);

            // Register attacker
            PlayerCombatEntity attacker =
                    entityManager.registerPlayer(player);

            // Create victim
            PlayerCombatEntity victim =
                    new PlayerCombatEntity(
                            player.getUniqueId()
                    );

            // Setup damage stats
            attacker.getStats().setBaseStat(
                    StatType.MELEE_DAMAGE,
                    100
            );

            attacker.getStats().setBaseStat(
                    StatType.RANGE_DAMAGE,
                    100
            );

            attacker.getStats().setBaseStat(
                    StatType.MAGIC_DAMAGE,
                    100
            );

            attacker.getStats().setBaseStat(
                    StatType.TRUE_DAMAGE,
                    100
            );

            // Setup crit stats
            attacker.getStats().setBaseStat(
                    StatType.CRIT_CHANCE,
                    critChance
            );

            attacker.getStats().setBaseStat(
                    StatType.CRIT_DAMAGE,
                    critDamage
            );

            // Setup penetration stats
            attacker.getStats().setBaseStat(
                    StatType.ARMOR_PEN,
                    penetration
            );

            attacker.getStats().setBaseStat(
                    StatType.MAGIC_PEN,
                    penetration
            );

            // Setup lifesteal
            attacker.getStats().setBaseStat(
                    StatType.LIFESTEAL,
                    lifesteal
            );

            // Setup attacker health
            attacker.getStats().setBaseStat(
                    StatType.MAX_HEALTH,
                    1000
            );

            attacker.setHealth(1000);

            // Setup victim health
            victim.getStats().setBaseStat(
                    StatType.MAX_HEALTH,
                    1000
            );

            // Setup defense
            victim.getStats().setBaseStat(
                    StatType.MELEE_DEFENSE,
                    defense
            );

            victim.getStats().setBaseStat(
                    StatType.MAGIC_DEFENSE,
                    defense
            );

            // Setup final reduction
            victim.getStats().setBaseStat(
                    StatType.FINAL_DAMAGE_REDUCTION,
                    damageReduction
            );

            // Restore victim health
            victim.setHealth(1000);

            // Create damage context
            DamageContext context =
                    new DamageContext(
                            attacker,
                            victim,
                            baseDamage,
                            scaling,
                            damageType
                    );

            // Apply damage
            DamageResult result =
                    damageEngine.damage(context);

            player.sendMessage(" ");

            player.sendMessage("=== Combat Result ===");

            player.sendMessage("Damage Type: "
                    + damageType);

            player.sendMessage("Final Damage: "
                    + result.getFinalDamage());

            player.sendMessage("Critical Hit: "
                    + result.isCritical());

            player.sendMessage("Lifesteal Heal: "
                    + result.getLifestealHeal());

            player.sendMessage("Victim Health: "
                    + victim.getHealth());

            player.sendMessage("Attacker Health: "
                    + attacker.getHealth());

            player.sendMessage(" ");

        } catch (Exception exception) {

            player.sendMessage("Invalid command values.");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,
                                      Command command,
                                      String alias,
                                      String[] args) {

        List<String> suggestions =
                new ArrayList<>();

        // Damage type suggestions
        if (args.length == 3) {

            for (DamageType damageType : DamageType.values()) {
                suggestions.add(damageType.name());
            }
        }

        // Crit chance suggestions
        else if (args.length == 4) {

            suggestions.add("0.25");
            suggestions.add("0.5");
            suggestions.add("1.0");
        }

        // Crit damage suggestions
        else if (args.length == 5) {

            suggestions.add("0.5");
            suggestions.add("1.0");
            suggestions.add("2.0");
        }

        // Defense suggestions
        else if (args.length == 6) {

            suggestions.add("50");
            suggestions.add("100");
            suggestions.add("500");
        }

        // Penetration suggestions
        else if (args.length == 7) {

            suggestions.add("0");
            suggestions.add("25");
            suggestions.add("100");
        }

        // Damage reduction suggestions
        else if (args.length == 8) {

            suggestions.add("0.1");
            suggestions.add("0.2");
            suggestions.add("0.5");
        }

        // Lifesteal suggestions
        else if (args.length == 9) {

            suggestions.add("0.05");
            suggestions.add("0.1");
            suggestions.add("0.25");
        }

        return suggestions;
    }
}