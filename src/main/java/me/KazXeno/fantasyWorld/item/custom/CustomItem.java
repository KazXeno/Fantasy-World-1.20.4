package me.KazXeno.fantasyWorld.item.custom;

import org.bukkit.Material;

public abstract class CustomItem {

    // Internal item id
    private final String id;

    // Display name
    private final String name;

    // Base material
    private final Material material;

    // Item rarity
    private final CustomItemRarity rarity;

    // Required class
    private final String requiredClass;

    // Required level
    private final int requiredLevel;

    public CustomItem(
            String id,
            String name,
            Material material,
            CustomItemRarity rarity,
            String requiredClass,
            int requiredLevel) {

        this.id = id;
        this.name = name;
        this.material = material;
        this.rarity = rarity;
        this.requiredClass = requiredClass;
        this.requiredLevel = requiredLevel;
    }

    // Get item id
    public String getId() {
        return id;
    }

    // Get display name
    public String getName() {
        return name;
    }

    // Get material
    public Material getMaterial() {
        return material;
    }

    // Get rarity
    public CustomItemRarity getRarity() {
        return rarity;
    }

    // Get required class
    public String getRequiredClass() {
        return requiredClass;
    }

    // Get required level
    public int getRequiredLevel() {
        return requiredLevel;
    }
}