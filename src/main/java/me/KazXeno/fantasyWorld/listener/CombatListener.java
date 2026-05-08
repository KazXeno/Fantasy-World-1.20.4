package me.KazXeno.fantasyWorld.listener;

import me.KazXeno.fantasyWorld.combat.DamageContext;
import me.KazXeno.fantasyWorld.combat.DamageEngine;
import me.KazXeno.fantasyWorld.combat.DamageType;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.entity.MobCombatEntity;
import me.KazXeno.fantasyWorld.entity.PlayerCombatEntity;
import me.KazXeno.fantasyWorld.combat.DamageResult;
import me.KazXeno.fantasyWorld.combat.attack.AttackCooldownManager;
import me.KazXeno.fantasyWorld.item.weapon.WeaponData;
import me.KazXeno.fantasyWorld.item.weapon.WeaponManager;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import me.KazXeno.fantasyWorld.combat.DamageResult;
import me.KazXeno.fantasyWorld.combat.death.DeathManager;
import me.KazXeno.fantasyWorld.combat.display.MobHealthDisplay;
import me.KazXeno.fantasyWorld.combat.indicator.DamageIndicatorManager;
import me.KazXeno.fantasyWorld.FantasyWorld;

public class CombatListener implements Listener {

    // Shared entity manager
    private final EntityManager entityManager = EntityManager.getInstance();
    // Damage engine
    private final DamageEngine damageEngine = new DamageEngine();
    // Weapon manager
    private final WeaponManager weaponManager = new WeaponManager();
    // Attack cooldown manager
    private final AttackCooldownManager cooldownManager = new AttackCooldownManager();
    // Death manager
    private final DeathManager deathManager = new DeathManager();
    // Damage indicator manager
    private final DamageIndicatorManager damageIndicatorManager = new DamageIndicatorManager(FantasyWorld.getInstance());
    // Mob health display
    private final MobHealthDisplay mobHealthDisplay = new MobHealthDisplay();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        // Get victim
        Entity victimEntity =
                event.getEntity();

        // Validate living entity
        if (!(victimEntity instanceof LivingEntity victim)) {
            return;
        }

        // Get attacker
        Entity attackerEntity =
                event.getDamager();

        // Handle arrow shooter
        if (attackerEntity instanceof Arrow arrow) {

            if (arrow.getShooter() instanceof Entity shooter) {
                attackerEntity = shooter;
            }
        }

        // Validate living attacker
        if (!(attackerEntity instanceof LivingEntity attacker)) {
            return;
        }

        // Cancel vanilla damage
        event.setCancelled(true);

        // Remove vanilla invulnerability frame
        victim.setNoDamageTicks(0);

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

// Handle player weapon
        if (attacker instanceof Player player) {
            WeaponData weapon = weaponManager.getWeapon(player);
            // Validate weapon
            if (weapon == null) {
                return;
            }
            // Check attack cooldown
            if (!cooldownManager.canAttack(player, weapon.getAttackSpeed())) {
                return;
            }
            baseDamage = weapon.getDamage();
            scaling = weapon.getScaling();
            damageType = weapon.getDamageType();
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

        // Apply damage
        DamageResult result = damageEngine.damage(context);
        //Spawn damage indicator
        damageIndicatorManager.spawnDamage(attacker, victim, result);
        // Update mob health display
        if(!(victim instanceof Player)){
            mobHealthDisplay.updateHealth(victim,victimCombat);
        }
        // Trigger vanilla hurt feedback
        victim.damage(0);
        // Debug message
        if (attacker instanceof Player player) {

            player.sendMessage("Damage: " + String.format("%.1f", result.getFinalDamage()));
            player.sendMessage("Victim Health: " + String.format("%.1f", victimCombat.getHealth()));
        }

        // Handle death
        if(victimCombat.getHealth() <= 0){
            deathManager.handleDeath(victim, victimCombat);
        }
    }

    // Convert Bukkit entity to combat entity
    private CombatEntity getCombatEntity(LivingEntity entity) {

        // Handle player
        if (entity instanceof Player player) {

            return entityManager.registerPlayer(player);
        }

        // Handle mob
        return entityManager.registerMob(entity);
    }

    // Detect damage type from weapon
    private DamageType detectDamageType(
            LivingEntity attacker) {

        // Handle non-player mobs
        if (!(attacker instanceof Player player)) {
            return DamageType.MELEE;
        }

        ItemStack item =
                player.getInventory()
                        .getItemInMainHand();

        Material material =
                item.getType();

        String name =
                material.name();

        // Detect melee weapon
        if (name.contains("SWORD")
                || name.contains("AXE")) {

            return DamageType.MELEE;
        }

        // Detect range weapon
        if (material == Material.BOW
                || material == Material.CROSSBOW) {

            return DamageType.RANGE;
        }

        // Detect magic weapon
        if (material == Material.BLAZE_ROD
                || material == Material.STICK) {

            return DamageType.MAGIC;
        }

        // Default damage type
        return DamageType.MELEE;
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
        entity.getStats().setBaseStat(
                StatType.MAX_HEALTH,
                100
        );

        // Default defenses
        entity.getStats().setBaseStat(
                StatType.MELEE_DEFENSE,
                10
        );

        entity.getStats().setBaseStat(
                StatType.MAGIC_DEFENSE,
                10
        );

        // Restore health
        entity.setHealth(100);
    }
}