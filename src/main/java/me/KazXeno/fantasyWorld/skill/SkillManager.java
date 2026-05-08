package me.KazXeno.fantasyWorld.skill;

import java.util.HashMap;
import java.util.Map;

public class SkillManager {

    // Registered skills
    private final Map<String, Skill>
            skills =
            new HashMap<>();

    // Register skill
    public void registerSkill(
            Skill skill) {

        skills.put(
                skill.getId(),
                skill
        );
    }

    // Get skill
    public Skill getSkill(
            String id) {

        return skills.get(id);
    }

    // Check skill existence
    public boolean hasSkill(
            String id) {

        return skills.containsKey(id);
    }
}