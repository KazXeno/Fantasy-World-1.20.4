# 幻灵世界 Fantasy World

Minecraft 1.20.4 PaperMC MMORPG PvE Plugin

---

# 项目简介

幻灵世界 是一个以：

- PvE
- MMORPG
- Build Diversity
- 职业差异
- 长期成长
- Action Combat

为核心设计方向的 Minecraft RPG 插件。

本项目目标并不是：
```text
“在原版上加一点 RPG 内容”
```

核心设计理念
PvE First

本插件：

不考虑 PvP 平衡
所有系统围绕 PvE 设计
强调职业体验与 Build 差异
Framework First

优先保证：

系统扩展性
Runtime 稳定性
Combat Pipeline 正确性

避免：

Listener Spaghetti
写死逻辑
系统耦合
Data Driven

长期目标：

技能配置化
怪物配置化
装备配置化
职业配置化
种族配置化

尽量减少：

Hardcode
当前已完成系统
Combat Core Framework
 Stats System
 Modifier System
 Combat Entity System
 Damage Pipeline
 Critical Strike
 Penetration
 Final Damage Reduction
 Lifesteal
 Resource System
 Health Bridge
 Runtime Sync System
 Combat Sandbox Command
Combat Pipeline

当前伤害流程：

Base Damage
→ Damage Scaling
→ Critical Strike
→ Penetration
→ Defense Reduction
→ Final Damage Reduction
→ Final Damage
→ Lifesteal
RPG Runtime System

当前系统：

RPG Health
↔ Minecraft Health

Minecraft 原版血量：

仅作为 UI 显示
不作为真实血量

真实数据：

CombatEntity
Universal Resource System

所有职业都拥有：

Mana
Cooldown
Weapon Ability

Mana 不仅仅代表：

法力

而是：

技能资源

用于：

武器技能
主动技能
职业技能
爆发技能
位移技能
治疗技能
Skill Combat Philosophy

本项目战斗系统目标：

不是只做伤害计算
而是建立真正的 Gameplay Loop

所有职业都围绕：

Mana 管理
Cooldown 管理
Skill Rotation
Burst Timing
Resource Decision

进行战斗。

当前 Stats
生存属性
MAX_HEALTH
HEALTH_REGEN
MANA
MANA_REGEN
防御属性
MELEE_DEFENSE
MAGIC_DEFENSE
DAMAGE_REDUCTION
KNOCKBACK_RESISTANCE
攻击属性
MELEE_DAMAGE
RANGE_DAMAGE
MAGIC_DAMAGE
TRUE_DAMAGE
穿透属性
ARMOR_PEN
MAGIC_PEN
暴击属性
CRIT_CHANCE
CRIT_DAMAGE
战斗属性
ATTACK_SPEED
SPEED
LIFESTEAL
COOLDOWN_REDUCTION
种族
人类

没有特别明显的特色。

特性
常驻受到伤害减 20%
龙人

抗火能力较强，但惧怕水。

特性
常驻抗火
碰水时伤害降低 20%
妖精

速度极快，但较脆弱。

特性
常驻速度提升 20%
常驻跳跃提升至 2.5 格
最大血量 -15%
兽人

拥有强大力量，但行动缓慢。

特性
常驻伤害提升 15%
速度降低 15%
抗击退 20%
最大血量 +15%
亡灵

夜晚强大，白天虚弱。

特性
常驻吸血 30%
夜晚伤害提升 20%
白天吸血降低至 15%
白天伤害降低 15%
天使

白天拥有神圣力量。

特性
免疫中毒与凋零
白天获得 Regeneration I
白天速度提升 10%
夜晚伤害降低 15%
职业
战士

均衡近战职业。

特点
中等攻速
中等伤害
使用剑
专属机制
应急自疗药水
固定 CD 主动回血
消耗 Mana
高等级附加额外效果
狂战士

高伤害近战职业。

特点
攻速缓慢
高爆发
使用斧头
特殊机制

血量越低伤害越高：

80% 以上：正常
80% ~ 50%：+20% 伤害
50% 以下：+40% 伤害
缺点
受到额外 20% 伤害
技能方向
狂暴
吸血
爆发
弓箭手

高机动远程输出。

特点
使用弓
远距离输出
可使用短匕首防身
特殊机制

距离越远伤害越高：

1~2 格：-15%
2~5 格：无加成
5~10 格：+10%
10 格以上：+20%
缺点
持弓时移动速度降低
技能方向
穿透箭
多重箭
后跳
游侠

灵活型远程职业。

特点
使用弩
机动性较高
擅长群体输出
特殊机制
持弩时移速提升
弩箭带穿透
可使用烟花造成爆炸伤害
缺点
基础伤害 -10%
技能方向
爆炸箭
烟花射击
机动位移
法师

高自由度技能职业。

特点
使用魔法
最多同时装备 6 个法术
高 Mana 依赖
法术类型
单体
AOE
治疗
净化
增伤
控制
特殊机制
技能独立 CD
可在主城切换法术
刺客

高攻速爆发职业。

特点
高攻速
低基础伤害
使用改攻速剑
特殊机制

蓄力打击：

3 秒未攻击
→ 下一击 2.2x 伤害

脱战首击：

脱战状态
→ 首击 2.8x 伤害

优先级：

脱战 > 蓄力 > 普攻
技能方向
突进
隐身
爆发连击
RoadMap
第一阶段 - Runtime Combat 重构

目标：

完全接管 Minecraft 战斗系统
Combat Listener
 拦截原版伤害
 接管 EntityDamageByEntityEvent
 RPG Damage Routing
 原版回血禁用
 RPG Death Handling
 无敌帧重构
 Damage Indicator
Resource System
 Health Regeneration
 Mana Regeneration
 Combat State
 Out-of-combat Recovery
 Shield / Barrier System
Runtime UI
 ActionBar 美化
 自定义 BossBar
 Damage Hologram
 自定义死亡信息
 Skill Cooldown UI
第二阶段 - Equipment System

目标：

建立完整装备体系
Weapon System
 武器基础数据
 武器攻击类型
 武器攻速
 武器 Scaling
 Weapon Registry
 Weapon Skill
Armor System
 护甲属性
 套装效果
 Damage Reduction
 Resistance
Item Framework
 自定义 Item Data
 Lore Builder
 Rarity System
 Upgrade System
 Reforge System
第三阶段 - Buff System

目标：

建立真正的状态效果系统
Buff Framework
 Buff Registry
 Buff Duration
 Buff Stack
 Periodic Effects
 Attribute Buff
 Debuff System
特殊状态
 Burn
 Freeze
 Poison
 Bleed
 Shock
 Curse
第四阶段 - Skill System

目标：

建立职业技能框架
Skill Core
 Skill Registry
 Cooldown System
 Mana Cost
 Cast Context
 Skill Scaling
 Targeting System
Spell System
 Projectile Spell
 AoE Spell
 Chain Spell
 Beam Spell
 Summon Spell
第五阶段 - Race System

目标：

将种族特性完全数据化
Race Framework
 Race Registry
 Passive Effects
 Environment Modifier
 Race Scaling
第六阶段 - Class System

目标：

建立职业成长与玩法差异
Class Framework
 Class Registry
 Skill Tree
 Passive Tree
 Talent System
 Progression
第七阶段 - Mob System

目标：

建立真正 PvE 内容
Mob Framework
 Mob Stats
 Threat System
 Boss AI
 Skill Casting
 Phase System
Dungeon System
 Dungeon Instance
 Boss Room
 Mob Scaling
 Reward Distribution
第八阶段 - World Content

目标：

建立 MMORPG 世界内容
Content
 主城
 NPC
 Quest System
 Reputation
 Fast Travel
 Region Scaling
长期目标
Endgame
 Raid Boss
 Mythic Dungeon
 Seasonal Content
 Equipment Progression
 Build Diversity