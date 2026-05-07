package me.KazXeno.fantasyWorld.combat.penetration;

public class PenetrationResult {

    // Final defense after penetration
    private final double defense;

    public PenetrationResult(double defense) {
        this.defense = defense;
    }

    // Get reduced defense
    public double getDefense() {
        return defense;
    }
}