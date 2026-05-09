package me.KazXeno.fantasyWorld.item.custom;

public enum CustomItemRarity {

    COMMON("§f"),
    UNCOMMON("§a"),
    RARE("§9"),
    EPIC("§e"),
    LEGENDARY("§6"),
    MYTHIC("§b");

    // Rarity color
    private final String color;

    CustomItemRarity(
            String color) {

        this.color = color;
    }

    // Get rarity color
    public String getColor() {
        return color;
    }
}