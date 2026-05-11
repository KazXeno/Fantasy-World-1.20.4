package me.KazXeno.fantasyWorld.item.custom.data;

import java.util.List;
import java.util.Map;

public class ItemJsonData {

    private String id;

    private String name;

    private String material;

    private String rarity;

    private String itemClass;

    private Map<String, Double> stats;

    private AbilityData ability;

    private List<String> lore;

    // Getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public String getRarity() {
        return rarity;
    }

    public String getItemClass() {
        return itemClass;
    }

    public Map<String, Double> getStats() {
        return stats;
    }

    public AbilityData getAbility() {
        return ability;
    }

    public List<String> getLore() {return lore;}

    // Ability data
    public static class AbilityData {
        private String id;
        private String trigger;
        private int cooldown;
        private int duration;
        public String getId() {
            return id;
        }
        public String getTrigger() {
            return trigger;
        }
        public int getCooldown() {
            return cooldown;
        }
        public int getDuration() {
            return duration;
        }
    }
}