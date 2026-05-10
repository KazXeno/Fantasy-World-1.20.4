package me.KazXeno.fantasyWorld.command;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.skill.Skill;
import me.KazXeno.fantasyWorld.skill.SkillContext;
import me.KazXeno.fantasyWorld.skill.SkillManager;
import me.KazXeno.fantasyWorld.skill.cooldown.CooldownManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillCastCommand
        implements CommandExecutor {

    // Skill manager
    private final SkillManager
            skillManager =
            SkillManager.getInstance();

    // Cooldown manager
    private final CooldownManager
            cooldownManager =
            CooldownManager.getInstance();

    // Shared entity manager
    private final EntityManager
            entityManager =
            EntityManager.getInstance();

    public SkillCastCommand() {
    }

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

        // Validate args
        if (args.length == 0) {

            player.sendMessage(
                    "§cUsage: /cast <skill>"
            );

            return true;
        }

        String skillId =
                args[0].toLowerCase();

        // Validate skill
        if (!skillManager.hasSkill(skillId)) {

            player.sendMessage(
                    "§cUnknown skill."
            );

            return true;
        }

        Skill skill =
                skillManager.getSkill(skillId);

        CombatEntity entity =
                entityManager.getEntity(
                        player.getUniqueId()
                );

        // Validate combat entity
        if (entity == null) {
            return true;
        }

        // Check cooldown
        if (cooldownManager.isOnCooldown(
                player,
                skillId
        )) {

            long remaining =
                    cooldownManager.getRemaining(
                            player,
                            skillId
                    );

            player.sendMessage(
                    "§cSkill on cooldown: "
                            + String.format(
                            "%.1f",
                            remaining / 1000.0
                    )
                            + "s"
            );

            return true;
        }

        // Check mana
        if (entity.getMana()
                < skill.getManaCost()) {

            player.sendMessage(
                    "§cNot enough mana."
            );

            return true;
        }

        // Consume mana
        entity.setMana(
                entity.getMana()
                        - skill.getManaCost()
        );

        // Apply cooldown
        cooldownManager.setCooldown(
                player,
                skillId,
                skill.getCooldown()
        );

        // Cast skill
        skill.cast(
                new SkillContext(
                        player,
                        entity
                )
        );

        return true;
    }
}