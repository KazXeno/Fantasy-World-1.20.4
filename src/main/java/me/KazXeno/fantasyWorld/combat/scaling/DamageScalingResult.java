package me.KazXeno.fantasyWorld.combat.scaling;

public class DamageScalingResult {

    // Final scaled damage
    private final double damage;

    public DamageScalingResult(double damage) {
        this.damage = damage;
    }

    // Get scaled damage
    public double getDamage() {
        return damage;
    }
}