package me.KazXeno.fantasyWorld.skill;

public interface Skill {

    // Get skill id
    String getId();

    // Get skill display name
    String getName();

    // Get skill trigger type
    SkillTriggerType getTriggerType();

    // Get mana cost
    double getManaCost();

    // Get cooldown in milliseconds
    long getCooldown();

    // Execute skill
    void cast(SkillContext context);
}