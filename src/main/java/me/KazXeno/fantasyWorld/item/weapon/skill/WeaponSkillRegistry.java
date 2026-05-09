package me.KazXeno.fantasyWorld.item.weapon.skill;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class WeaponSkillRegistry {

    // Weapon skill map
    private final Map<Material, WeaponSkillData> skills = new HashMap<>();

    public WeaponSkillRegistry() {

        // Warrior heal sword
        skills.put(Material.IRON_SWORD, new WeaponSkillData("warrior_heal"));
    }

    // Get weapon skill
    public WeaponSkillData getSkill(Material material) {
        return skills.get(material);
    }
}