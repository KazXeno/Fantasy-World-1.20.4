package me.KazXeno.fantasyWorld.item.custom;

import com.google.gson.Gson;
import me.KazXeno.fantasyWorld.FantasyWorld;
import me.KazXeno.fantasyWorld.item.custom.data.CustomWeaponItem;
import me.KazXeno.fantasyWorld.item.custom.data.ItemJsonData;
import me.KazXeno.fantasyWorld.stats.StatType;
import org.bukkit.Material;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ItemLoader {

    private final Gson gson =
            new Gson();

    // Load all items
    public void loadItems(
            CustomItemRegistry registry) {

        File folder =
                new File(
                        FantasyWorld.getInstance()
                                .getDataFolder(),
                        "items"
                );

        // Create folder
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File[] files =
                folder.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {

            // Ignore non-json
            if (!file.getName()
                    .endsWith(".json")) {

                continue;
            }

            try {

                FileReader reader =
                        new FileReader(file);

                ItemJsonData data =
                        gson.fromJson(
                                reader,
                                ItemJsonData.class
                        );

                // Convert stats
                Map<StatType, Double>
                        stats =
                        new HashMap<>();

                for (Map.Entry<String, Double>
                        entry
                        : data.getStats().entrySet()) {

                    stats.put(
                            StatType.valueOf(
                                    entry.getKey()
                            ),
                            entry.getValue()
                    );
                }

                // Create weapon item
                CustomWeaponItem item =
                        new CustomWeaponItem(
                                data.getId(),
                                data.getName(),
                                Material.valueOf(
                                        data.getMaterial()
                                ),
                                CustomItemRarity.valueOf(
                                        data.getRarity()
                                ),
                                data.getItemClass(),

                                1,

                                data.getAbility().getId(),

                                stats,

                                "",

                                data.getAbility()
                                        .getTrigger(),

                                data.getAbility()
                                        .getCooldown(),

                                data.getAbility()
                                        .getDuration(),

                                data.getLore()
                        );

                // Register item
                registry.registerItem(
                        item
                );

                FantasyWorld.getInstance()
                        .getLogger()
                        .info(
                                "Loaded item: "
                                        + data.getId()
                        );

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}