package com.pinont.experiences.api.utils.enums;

import org.bukkit.attribute.Attribute;

public enum AttributeType {
    GENERIC_MAX_HEALTH("generic.max_health"),
    GENERIC_MAX_ABSORPTION("generic.max_absorption"),
    GENERIC_FOLLOW_RANGE("generic.follow_range"),
    GENERIC_KNOCKBACK_RESISTANCE("generic.knockback_resistance"),
    GENERIC_MOVEMENT_SPEED("generic.movement_speed"),
    GENERIC_ATTACK_DAMAGE("generic.attack_damage"),
    GENERIC_ARMOR("generic.armor"),
    GENERIC_ARMOR_TOUGHNESS("generic.armor_toughness"),
    GENERIC_ATTACK_KNOCKBACK("generic.attack_knockback"),
    GENERIC_ATTACK_SPEED("generic.attack_speed"),
    GENERIC_LUCK("generic.luck"),
    GENERIC_FLYING_SPEED("generic.flying_speed"),
    HORSE_JUMP_STRENGTH("horse.jump_strength"),
    ZOMBIE_SPAWN_REINFORCEMENTS("zombie.spawn_reinforcements"),
    ;

    public final String name;

    AttributeType(String s) {
        this.name = s;
    }

    public Attribute getAttribute() {
        return switch (this) {
            case GENERIC_MAX_HEALTH -> Attribute.GENERIC_MAX_HEALTH;
            case GENERIC_MAX_ABSORPTION -> Attribute.GENERIC_MAX_ABSORPTION;
            case GENERIC_FOLLOW_RANGE -> Attribute.GENERIC_FOLLOW_RANGE;
            case GENERIC_KNOCKBACK_RESISTANCE -> Attribute.GENERIC_KNOCKBACK_RESISTANCE;
            case GENERIC_MOVEMENT_SPEED -> Attribute.GENERIC_MOVEMENT_SPEED;
            case GENERIC_ATTACK_DAMAGE -> Attribute.GENERIC_ATTACK_DAMAGE;
            case GENERIC_ARMOR -> Attribute.GENERIC_ARMOR;
            case GENERIC_ARMOR_TOUGHNESS -> Attribute.GENERIC_ARMOR_TOUGHNESS;
            case GENERIC_ATTACK_KNOCKBACK -> Attribute.GENERIC_ATTACK_KNOCKBACK;
            case GENERIC_ATTACK_SPEED -> Attribute.GENERIC_ATTACK_SPEED;
            case GENERIC_LUCK -> Attribute.GENERIC_LUCK;
            case GENERIC_FLYING_SPEED -> Attribute.GENERIC_FLYING_SPEED;
            case HORSE_JUMP_STRENGTH -> Attribute.GENERIC_JUMP_STRENGTH;
            case ZOMBIE_SPAWN_REINFORCEMENTS -> Attribute.ZOMBIE_SPAWN_REINFORCEMENTS;
        };
    }
}
