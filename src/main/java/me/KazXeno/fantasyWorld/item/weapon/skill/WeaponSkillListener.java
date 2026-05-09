package me.KazXeno.fantasyWorld.item.weapon.skill;

import me.KazXeno.fantasyWorld.FantasyWorld;
import me.KazXeno.fantasyWorld.entity.CombatEntity;
import me.KazXeno.fantasyWorld.entity.EntityManager;
import me.KazXeno.fantasyWorld.item.custom.CustomItem;
import me.KazXeno.fantasyWorld.item.custom.CustomItemManager;
import me.KazXeno.fantasyWorld.item.custom.data.CustomWeaponItem;
import me.KazXeno.fantasyWorld.skill.Skill;
import me.KazXeno.fantasyWorld.skill.SkillContext;
import me.KazXeno.fantasyWorld.skill.SkillManager;
import me.KazXeno.fantasyWorld.skill.cooldown.CooldownManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class WeaponSkillListener
        implements Listener {

    // Shared skill manager
    private final SkillManager
            skillManager =
            SkillManager.getInstance();

    // Shared cooldown manager
    private final CooldownManager
            cooldownManager =
            CooldownManager.getInstance();

    // Shared entity manager
    private final EntityManager
            entityManager =
            EntityManager.getInstance();

    // Custom item manager
    private final CustomItemManager
            itemManager =
            new CustomItemManager(
                    FantasyWorld.getInstance()
            );

    @EventHandler
    public void onInteract(
            PlayerInteractEvent event) {

        // Main hand only
        if (event.getHand()
                != EquipmentSlot.HAND) {
            return;
        }

        // Right click only
        switch (event.getAction()) {

            case RIGHT_CLICK_AIR,
                 RIGHT_CLICK_BLOCK -> {
            }

            default -> {
                return;
            }
        }

        Player player =
                event.getPlayer();

        // Get held item
        ItemStack item =
                player.getInventory()
                        .getItemInMainHand();

        // Validate item
        if (item == null
                || item.getType()
                == Material.AIR) {
            return;
        }

        // Validate custom item
        if (!itemManager.isCustomItem(item)) {
            return;
        }

        // Get custom item
        CustomItem customItem =
                itemManager.getCustomItem(item);

        // Validate weapon item
        if (!(customItem
                instanceof CustomWeaponItem
                weaponItem)) {
            return;
        }

        // Get skill id
        String skillId =
                weaponItem.getSkillId();

        // Validate skill id
        if (skillId == null
                || skillId.isEmpty()) {
            return;
        }

        // Validate skill
        if (!skillManager.hasSkill(
                skillId)) {
            return;
        }

        Skill skill =
                skillManager.getSkill(
                        skillId
                );

        CombatEntity entity =
                entityManager.getEntity(
                        player.getUniqueId()
                );

        // Validate combat entity
        if (entity == null) {
            return;
        }

        // Check cooldown
        if (cooldownManager.isOnCooldown(
                player,
                skillId
        )) {

            long remaining =
                    cooldownManager
                            .getRemaining(
                                    player,
                                    skillId
                            );

            player.sendMessage(
                    "§cSkill on cooldown: "
                            + String.format(
                            "%.1f",
                            remaining / 1000.0
                    )
                            + "s"
            );

            return;
        }

        // Check mana
        if (entity.getMana()
                < skill.getManaCost()) {

            player.sendMessage(
                    "§cNot enough mana."
            );

            return;
        }

        // Consume mana
        entity.setMana(
                entity.getMana()
                        - skill.getManaCost()
        );

        // Apply cooldown
        cooldownManager.setCooldown(
                player,
                skillId,
                skill.getCooldown()
        );

        // Cast skill
        skill.cast(
                new SkillContext(
                        player,
                        entity
                )
        );
    }
}