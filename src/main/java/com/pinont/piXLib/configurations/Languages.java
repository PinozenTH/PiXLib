package com.pinont.piXLib.configurations;

public enum Languages {
    PREFIX("§7[§bpiXLib§7] "),
    NO_PERMISSION(PREFIX.value + "§cYou do not have permission to do this."),
    ONLY_PLAYERS(PREFIX.value + "§cOnly players can use this command."),
    NO_CONSOLE(PREFIX.value + "§cYou cannot use this command in console."),
    NO_ARGS(PREFIX.value + "§cInvalid usage. Please use: "),
    NO_PLAYER(PREFIX.value + "§cPlayer not found."),
    NO_WORLD(PREFIX.value + "§cWorld not found."),
    NO_ITEM(PREFIX.value + "§cItem not found."),
    NO_BLOCK(PREFIX.value + "§cBlock not found."),
    NO_MATERIAL(PREFIX.value + "§cMaterial not found."),
    NO_LOCATION(PREFIX.value + "§cLocation not found."),
    NO_ENTITY(PREFIX.value + "§cEntity not found."),
    NO_ENCHANTMENT(PREFIX.value + "§cEnchantment not found."),
    NO_POTION(PREFIX.value + "§cPotion not found."),
    NO_EFFECT(PREFIX.value + "§cEffect not found."),
    NO_SOUND(PREFIX.value + "§cSound not found."),
    NO_PARTICLE(PREFIX.value + "§cParticle not found."),
    NO_COLOR(PREFIX.value + "§cColor not found."),
    NO_DYE_COLOR(PREFIX.value + "§cDyeColor not found."),
    NO_FIREWORK_EFFECT(PREFIX.value + "§cFireworkEffect not found."),
    NO_FIREWORK_TYPE(PREFIX.value + "§cFireworkType not found."),
    NO_BANNER_PATTERN(PREFIX.value + "§cBannerPattern not found."),
    NO_BANNER_COLOR(PREFIX.value + "§cBannerColor not found."),
    NO_BANNER(PREFIX.value + "§cBanner not found."),
    NO_POTION_EFFECT(PREFIX.value + "§cPotionEffect not found."),
    NO_POTION_TYPE(PREFIX.value + "§cPotionType not found.");

    public final String value;

    Languages(String value) {
        this.value = value;
    }
}
