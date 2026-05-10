package me.KazXeno.fantasyWorld.stats;

public enum StatType {
    // 生存属性
    MAX_HEALTH("生命值"),
    HEALTH_REGEN("生命恢复"),
    MANA("魔力"),
    MANA_REGEN("魔力恢复"),

    // 防御属性
    MELEE_DEFENSE("物抗"),
    MAGIC_DEFENSE("法抗"),
    KNOCKBACK_RESISTANCE("抗击退"),

    // 攻击属性
    MELEE_DAMAGE("近战伤害"),
    RANGE_DAMAGE("远程伤害"),
    MAGIC_DAMAGE("魔法伤害"),
    TRUE_DAMAGE("真实伤害"),

    // 穿透属性
    ARMOR_PEN("物理穿透"),
    MAGIC_PEN("魔法穿透"),

    // 暴击属性
    CRIT_CHANCE("暴击率"),
    CRIT_DAMAGE("暴击伤害"),

    // 战斗属性
    ATTACK_SPEED("攻击速度"),
    SPEED("移速"),
    JUMP_HEIGHT("跳跃高度"),
    LIFESTEAL("吸血"),
    COOLDOWN_REDUCTION("冷却缩减"),

    // 特殊属性
    FINAL_DAMAGE_MULTIPLIER("最终增伤倍率"),
    FINAL_DAMAGE_REDUCTION("最终减伤倍率"),
    FINAL_HEALING_MULTIPLIER("最终治疗倍率");


    private final String displayName;

    StatType(String displayName){
        this.displayName = displayName;
    }
    public String getDisplayName(){
        return displayName;
    }
}
