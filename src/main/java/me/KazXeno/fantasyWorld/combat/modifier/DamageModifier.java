package me.KazXeno.fantasyWorld.combat.modifier;

import me.KazXeno.fantasyWorld.combat.DamageContext;

public interface DamageModifier {
    // Modify damage
    double modifyDamage(DamageContext context, double damage);
}