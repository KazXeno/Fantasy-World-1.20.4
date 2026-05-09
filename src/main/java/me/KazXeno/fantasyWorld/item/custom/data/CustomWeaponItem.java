package me.KazXeno.fantasyWorld.item.custom.data;

import me.KazXeno.fantasyWorld.item.custom.CustomItem;
import me.KazXeno.fantasyWorld.item.custom.CustomItemRarity;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public class CustomWeaponItem
        extends CustomItem {

    // Weapon skill id
    private final String skillId;

    // Weapon stats
    private final Map<StatType, Double>
            stats;

    // Ability name
    private final String abilityName;

    // Ability trigger
    private final String abilityTrigger;

    // Ability mana cost
    private final double manaCost;

    // Ability cooldown
    private final double cooldown;

    // Ability description
    private final List<String>
            description;

    public CustomWeaponItem(
            String id,
            String name,
            Material material,
            CustomItemRarity rarity,
            String requiredClass,
            int requiredLevel,
            String skillId,
            Map<StatType, Double> stats,
            String abilityName,
            String abilityTrigger,
            double manaCost,
            double cooldown,
            List<String> description) {

        super(
                id,
                name,
                material,
                rarity,
                requiredClass,
                requiredLevel
        );

        this.skillId = skillId;
        this.stats = stats;
        this.abilityName = abilityName;
        this.abilityTrigger = abilityTrigger;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.description = description;
    }

    // Get skill id
    public String getSkillId() {
        return skillId;
    }

    // Get stats
    public Map<StatType, Double>
    getStats() {

        return stats;
    }

    // Get ability name
    public String getAbilityName() {
        return abilityName;
    }

    // Get ability trigger
    public String getAbilityTrigger() {
        return abilityTrigger;
    }

    // Get mana cost
    public double getManaCost() {
        return manaCost;
    }

    // Get cooldown
    public double getCooldown() {
        return cooldown;
    }

    // Get description
    public List<String>
    getDescription() {

        return description;
    }
}