package me.KazXeno.fantasyWorld.combat;

public class DamageResult {

    // Final damage dealt
    private final double finalDamage;

    // Critical hit state
    private final boolean critical;

    // Blocked damage
    private final double blockedDamage;

    // Lifesteal heal
    private final double lifestealHeal;

    public DamageResult(
            double finalDamage,
            boolean critical,
            double blockedDamage,
            double lifestealHeal) {

        this.finalDamage =
                finalDamage;

        this.critical =
                critical;

        this.blockedDamage =
                blockedDamage;

        this.lifestealHeal =
                lifestealHeal;
    }

    // Get final damage
    public double getFinalDamage() {
        return finalDamage;
    }

    // Is critical hit
    public boolean isCritical() {
        return critical;
    }

    // Get blocked damage
    public double getBlockedDamage() {
        return blockedDamage;
    }

    // Get lifesteal heal
    public double getLifestealHeal() {
        return lifestealHeal;
    }
}