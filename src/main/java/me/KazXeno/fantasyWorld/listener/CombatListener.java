package me.KazXeno.fantasyWorld.listener;

import me.KazXeno.fantasyWorld.FantasyWorld;
import me.KazXeno.fantasyWorld.combat.DamageContext;
import me.KazXeno.fantasyWorld.combat.DamageEngine;
import me.KazXeno.fantasyWorld.combat.DamageResult;
import me.KazXeno.fantasyWorld.combat.DamageType;
import me.KazXeno.fantasyWorld.combat.attack.AttackCooldownManager;
import me.KazXeno.fantasyWorld.combat.death.DeathManager;
import me.KazXeno.fantasyWorld.combat.display.MobHealthDisplay;
import me.KazXeno.fantasyWorld.combat.indicator.DamageIndicatorManager;
import me.KazXeno.fantasyWorld.combat.state.CombatTagManager;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.item.custom.data.CustomWeaponItem;
import me.KazXeno.fantasyWorld.item.weapon.WeaponManager;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener
        implements Listener {

    // Shared entity manager
    private final EntityManager
            entityManager =
            EntityManager.getInstance();

    // Damage engine
    private final DamageEngine
            damageEngine =
            new DamageEngine();

    // Weapon manager
    private final WeaponManager
            weaponManager =
            new WeaponManager();

    // Attack cooldown manager
    private final AttackCooldownManager
            cooldownManager =
            new AttackCooldownManager();

    // Damage indicator manager
    private final DamageIndicatorManager
            damageIndicatorManager =
            new DamageIndicatorManager(
                    FantasyWorld.getInstance()
            );

    // Mob health display
    private final MobHealthDisplay
            mobHealthDisplay =
            new MobHealthDisplay();

    // Combat state manager
    private final CombatTagManager
            combatTagManager =
            CombatTagManager.getInstance();

    // Death manager
    private final DeathManager
            deathManager =
            new DeathManager();

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onDamage(
            EntityDamageByEntityEvent event) {

        // Always cancel vanilla damage
        event.setCancelled(true);

        // Get victim
        Entity victimEntity =
                event.getEntity();

        // Validate living entity
        if (!(victimEntity
                instanceof LivingEntity victim)) {

            return;
        }

        // Remove vanilla invulnerability frame
        victim.setNoDamageTicks(0);

        // Get attacker
        Entity attackerEntity =
                event.getDamager();

        // Handle arrow shooter
        if (attackerEntity
                instanceof Arrow arrow) {

            if (arrow.getShooter()
                    instanceof Entity shooter) {

                attackerEntity = shooter;
            }
        }

        // Validate living attacker
        if (!(attackerEntity
                instanceof LivingEntity attacker)) {

            return;
        }

        // Convert attacker to combat entity
        CombatEntity attackerCombat =
                getCombatEntity(attacker);

        // Convert victim to combat entity
        CombatEntity victimCombat =
                getCombatEntity(victim);

        // Validate combat entities
        if (attackerCombat == null
                || victimCombat == null) {

            return;
        }

        // Initialize default mob stats
        initializeMob(victimCombat);

        // Default values
        double baseDamage = 5;

        double scaling = 1.0;

        DamageType damageType =
                DamageType.MELEE;

        // Handle player combat
        if (attacker instanceof Player player) {

            // Get equipped weapon
            CustomWeaponItem weapon =
                    weaponManager.getWeapon(
                            player
                    );

            // Validate weapon
            if (weapon == null) {

                return;
            }

            // Check attack cooldown
            if (!cooldownManager.canAttack(
                    player,

                    attackerCombat.getStats()
                            .getFinalStat(
                                    StatType
                                            .ATTACK_SPEED
                            )
            )) {

                return;
            }

            // Use player final stats
            baseDamage =
                    attackerCombat.getStats()
                            .getFinalStat(
                                    StatType
                                            .MELEE_DAMAGE
                            );

            // Default scaling
            scaling = 1.0;

            // Default damage type
            damageType =
                    DamageType.MELEE;
        }

        // Create damage context
        DamageContext context =
                new DamageContext(
                        attackerCombat,
                        victimCombat,
                        baseDamage,
                        scaling,
                        damageType
                );

        // Apply custom damage
        DamageResult result =
                damageEngine.damage(
                        context
                );

        // Handle custom death
        if (victimCombat.getHealth()
                <= 0) {

            // Handle player death
            if (victim
                    instanceof Player playerVictim) {

                deathManager.handleDeath(
                        playerVictim,
                        victimCombat
                );
            }

            return;
        }

        // Enter combat state
        combatTagManager.tag(
                attacker
        );

        combatTagManager.tag(
                victim
        );

        // Spawn damage indicator
        damageIndicatorManager.spawnDamage(
                attacker,
                victim,
                result
        );

        // Update mob health display
        if (!(victim
                instanceof Player)) {

            mobHealthDisplay.updateHealth(
                    victim,
                    victimCombat
            );
        }

        // Trigger player hurt feedback
        if (victim
                instanceof Player playerVictim) {

            // Play hurt animation
            playerVictim.playHurtAnimation(0);

            // Get hurt sound
            var hurtSound =
                    playerVictim.getHurtSound();

            // Play hurt sound
            if (hurtSound != null) {

                playerVictim.playSound(
                        playerVictim.getLocation(),
                        hurtSound,
                        1f,
                        1f
                );
            }
        }

        // Trigger mob hurt feedback
        else {
            // Play vanilla hurt feedback
            victim.damage(0);
            // Play hurt animation
            victim.playHurtAnimation(0);
        }

        // Debug message
        if (attacker
                instanceof Player player) {

            player.sendMessage(
                    "Damage: "
                            + String.format(
                            "%.1f",
                            result.getFinalDamage()
                    )
            );

            player.sendMessage(
                    "Victim Health: "
                            + String.format(
                            "%.1f",
                            victimCombat.getHealth()
                    )
            );
        }
    }

    // Convert Bukkit entity to combat entity
    private CombatEntity getCombatEntity(
            LivingEntity entity) {

        // Handle player
        if (entity
                instanceof Player player) {

            return entityManager
                    .registerPlayer(
                            player
                    );
        }

        // Handle mob
        return entityManager
                .registerMob(
                        entity
                );
    }

    // Initialize default mob stats
    private void initializeMob(
            CombatEntity entity) {

        // Prevent reinitialize
        if (entity.getStats()
                .getFinalStat(
                        StatType.MAX_HEALTH
                ) > 0) {

            return;
        }

        // Default mob health
        entity.getStats()
                .setBaseStat(
                        StatType.MAX_HEALTH,
                        100
                );

        // Default defenses
        entity.getStats()
                .setBaseStat(
                        StatType.MELEE_DEFENSE,
                        10
                );

        entity.getStats()
                .setBaseStat(
                        StatType.MAGIC_DEFENSE,
                        10
                );

        // Restore health
        entity.setHealth(100);
    }
}