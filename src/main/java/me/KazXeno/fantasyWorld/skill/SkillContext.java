package me.KazXeno.fantasyWorld.skill;

import me.KazXeno.fantasyWorld.entity.CombatEntity;
import org.bukkit.entity.Player;

public class SkillContext {

    // Skill caster
    private final Player player;

    // Combat entity
    private final CombatEntity entity;

    public SkillContext(Player player,
                        CombatEntity entity) {

        this.player = player;
        this.entity = entity;
    }

    // Get player
    public Player getPlayer() {
        return player;
    }

    // Get combat entity
    public CombatEntity getEntity() {
        return entity;
    }
}