package me.KazXeno.fantasyWorld.combat;

public class DamageResult {

    // Final damage value
    private final double finalDamage;

    // Critical hit result
    private final boolean critical;

    // Lifesteal heal amount
    private final double lifestealHeal;

    public DamageResult(double finalDamage,
                        boolean critical,
                        double lifestealHeal) {

        this.finalDamage = finalDamage;
        this.critical = critical;
        this.lifestealHeal = lifestealHeal;
    }

    // Get final damage
    public double getFinalDamage() {
        return finalDamage;
    }

    // Check critical hit
    public boolean isCritical() {
        return critical;
    }

    // Get lifesteal heal amount
    public double getLifestealHeal() {
        return lifestealHeal;
    }
}