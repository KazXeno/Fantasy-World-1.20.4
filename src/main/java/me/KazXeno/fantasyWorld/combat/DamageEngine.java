package me.KazXeno.fantasyWorld.combat;

import me.KazXeno.fantasyWorld.combat.pipeline.DamagePipeline;

public class DamageEngine {

    // Shared damage pipeline
    private final DamagePipeline
            pipeline =
            new DamagePipeline();

    // Apply damage
    public DamageResult damage(
            DamageContext context) {

        return pipeline.process(
                context
        );
    }
}