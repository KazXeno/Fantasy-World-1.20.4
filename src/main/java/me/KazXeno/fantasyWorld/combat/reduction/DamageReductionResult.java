package me.KazXeno.fantasyWorld.combat.reduction;

public class DamageReductionResult {

    // Final reduced damage
    private final double damage;

    public DamageReductionResult(double damage) {
        this.damage = damage;
    }

    // Get reduced damage
    public double getDamage() {
        return damage;
    }
}