package me.KazXeno.fantasyWorld.combat.modifier;

import me.KazXeno.fantasyWorld.combat.DamageContext;
import me.KazXeno.fantasyWorld.combat.modifier.impl.BerserkerDamageModifier;
import me.KazXeno.fantasyWorld.combat.modifier.impl.AssassinDamageModifier;

import java.util.ArrayList;
import java.util.List;

public class DamageModifierManager {

    // Registered modifiers
    private final List<DamageModifier>
            modifiers =
            new ArrayList<>();

    // Constructor
    public DamageModifierManager() {

        // Register modifiers
        registerModifier(
                new BerserkerDamageModifier()
        );
        registerModifier(
                new AssassinDamageModifier()
        );
    }

    // Register modifier
    public void registerModifier(
            DamageModifier modifier) {

        modifiers.add(
                modifier
        );
    }

    // Apply modifiers
    public double applyModifiers(
            DamageContext context,
            double damage) {

        double modifiedDamage =
                damage;

        // Apply all modifiers
        for (DamageModifier modifier
                : modifiers) {

            modifiedDamage =
                    modifier.modifyDamage(
                            context,
                            modifiedDamage
                    );
        }

        return modifiedDamage;
    }
}