package me.KazXeno.fantasyWorld.item.weapon;

import me.KazXeno.fantasyWorld.combat.DamageType;

public class WeaponData {

    // Weapon type
    private final WeaponType weaponType;

    // Damage type
    private final DamageType damageType;

    // Base weapon damage
    private final double damage;

    // Attack speed
    private final double attackSpeed;

    // Damage scaling
    private final double scaling;

    public WeaponData(WeaponType weaponType,
                      DamageType damageType,
                      double damage,
                      double attackSpeed,
                      double scaling) {

        this.weaponType = weaponType;
        this.damageType = damageType;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.scaling = scaling;
    }

    // Get weapon type
    public WeaponType getWeaponType() {
        return weaponType;
    }

    // Get damage type
    public DamageType getDamageType() {
        return damageType;
    }

    // Get weapon damage
    public double getDamage() {
        return damage;
    }

    // Get attack speed
    public double getAttackSpeed() {
        return attackSpeed;
    }

    // Get scaling
    public double getScaling() {
        return scaling;
    }
}