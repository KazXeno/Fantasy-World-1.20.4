package me.KazXeno.fantasyWorld.combat.pipeline;

public class DamagePipelineResult {

    // Base damage
    private double baseDamage;

    // Scaled damage
    private double scaledDamage;

    // Critical damage
    private double criticalDamage;

    // Final damage
    private double finalDamage;

    // Critical hit state
    private boolean critical;

    public double getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(
            double baseDamage) {

        this.baseDamage = baseDamage;
    }

    public double getScaledDamage() {
        return scaledDamage;
    }

    public void setScaledDamage(
            double scaledDamage) {

        this.scaledDamage = scaledDamage;
    }

    public double getCriticalDamage() {
        return criticalDamage;
    }

    public void setCriticalDamage(
            double criticalDamage) {

        this.criticalDamage = criticalDamage;
    }

    public double getFinalDamage() {
        return finalDamage;
    }

    public void setFinalDamage(
            double finalDamage) {

        this.finalDamage = finalDamage;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(
            boolean critical) {

        this.critical = critical;
    }
}