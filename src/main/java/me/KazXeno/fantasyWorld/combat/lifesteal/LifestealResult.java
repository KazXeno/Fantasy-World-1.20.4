package me.KazXeno.fantasyWorld.combat.lifesteal;

public class LifestealResult {

    // Heal amount from lifesteal
    private final double healAmount;

    public LifestealResult(double healAmount) {
        this.healAmount = healAmount;
    }

    // Get heal amount
    public double getHealAmount() {
        return healAmount;
    }
}