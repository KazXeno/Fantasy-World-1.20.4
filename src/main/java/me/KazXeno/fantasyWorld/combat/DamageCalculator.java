package me.KazXeno.fantasyWorld.combat;

public class DamageCalculator {

    // Calculate scaled damage
    public double calculate(
            DamageContext context) {

        // Base damage
        double baseDamage =
                context.getBaseDamage();

        // Damage scaling
        double scaling =
                context.getScaling();

        // Final scaled damage
        return baseDamage * scaling;
    }
}