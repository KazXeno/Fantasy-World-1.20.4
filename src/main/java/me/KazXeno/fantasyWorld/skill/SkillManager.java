package me.KazXeno.fantasyWorld.skill;

import java.util.HashMap;
import java.util.Map;

public class SkillManager {

    // Shared instance
    private static final SkillManager
            instance =
            new SkillManager();

    // Registered skills
    private final Map<String, Skill>
            skills =
            new HashMap<>();

    // Private constructor
    private SkillManager() {
    }

    // Get shared instance
    public static SkillManager
    getInstance() {

        return instance;
    }

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