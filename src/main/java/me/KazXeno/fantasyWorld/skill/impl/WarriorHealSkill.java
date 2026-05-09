package me.KazXeno.fantasyWorld.skill.impl;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.skill.Skill;
import me.KazXeno.fantasyWorld.skill.SkillContext;
import me.KazXeno.fantasyWorld.skill.SkillTriggerType;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class WarriorHealSkill
        implements Skill {

    @Override
    public String getId() {
        return "warrior_heal";
    }

    @Override
    public String getName() {
        return "Warrior Heal";
    }

    @Override
    public SkillTriggerType
    getTriggerType() {

        return SkillTriggerType.ACTIVE;
    }

    @Override
    public double getManaCost() {
        return 25;
    }

    @Override
    public long getCooldown() {
        return 15000;
    }

    @Override
    public void cast(
            SkillContext context) {

        Player player =
                context.getPlayer();

        CombatEntity entity =
                context.getEntity();

        // Calculate heal amount
        double heal =
                entity.getStats()
                        .getFinalStat(
                                StatType.MAX_HEALTH
                        ) * 0.25;

        double health =
                entity.getHealth();

        double maxHealth =
                entity.getStats()
                        .getFinalStat(
                                StatType.MAX_HEALTH
                        );

        // Apply healing
        health += heal;

        health = Math.min(
                health,
                maxHealth
        );

        entity.setHealth(health);

        // Play sound
        player.playSound(
                player.getLocation(),
                Sound.ENTITY_PLAYER_LEVELUP,
                1f,
                1f
        );

        // Send message
        player.sendMessage(
                "§aYou healed for "
                        + String.format(
                        "%.1f",
                        heal
                )
                        + " HP."
        );
    }
}