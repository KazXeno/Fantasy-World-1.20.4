package me.KazXeno.fantasyWorld.race.passive;

import me.KazXeno.fantasyWorld.profile.PlayerProfile;
import me.KazXeno.fantasyWorld.stats.StatType;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifier;
import me.KazXeno.fantasyWorld.stats.modifier.StatModifierType;

public class HumanPassive
        implements RacePassive {

    @Override
    public void apply(
            PlayerProfile profile) {
        System.out.println("Human Passive apply");
        profile.getCombatEntity().getStats().addModifier(new StatModifier(
                "race_human", StatType.FINAL_DAMAGE_REDUCTION, 20, StatModifierType.FLAT));
    }

    @Override
    public void remove(
            PlayerProfile profile) {

        profile.getCombatEntity()
                .getStats()
                .removeModifiersBySource(
                        "race_human"
                );
    }
}