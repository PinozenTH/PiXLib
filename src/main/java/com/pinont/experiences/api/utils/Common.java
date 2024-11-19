package com.pinont.experiences.api.utils;

import com.pinont.experiences.Exp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Common {
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static Material[] itemOnlyMaterial() {
        List<Material> filterList = Arrays.asList(Material.AIR, Material.WATER, Material.LAVA, Material.VOID_AIR, Material.MOVING_PISTON, Material.PISTON_HEAD, Material.END_GATEWAY, Material.TALL_SEAGRASS, Material.TALL_GRASS, Material.FIRE, Material.SOUL_FIRE, Material.REDSTONE_WIRE, Material.NETHER_PORTAL, Material.END_PORTAL, Material.PUMPKIN_STEM, Material.MELON_STEM, Material.COCOA, Material.TRIPWIRE, Material.CARROTS, Material.POTATOES, Material.BEETROOTS, Material.FROSTED_ICE, Material.BAMBOO_SAPLING, Material.BUBBLE_COLUMN, Material.POWDER_SNOW, Material.BIG_DRIPLEAF_STEM);

        return Arrays.stream(Material.values()).filter(material -> !filterList.contains(material) && !material.name().startsWith("LEGACY_") && !material.name().contains("WALL_") && !material.name().startsWith("ATTACHED_") && !material.name().endsWith("_CAULDRON") && !material.name().startsWith("POTTED_") && !material.name().endsWith("_CROP") && !material.name().endsWith("_BUSH") && !material.name().endsWith("_PLANT") && !material.name().endsWith("_CAKE") && !material.name().startsWith("CAVE_")).toArray(Material[]::new);
    }

    public static Boolean isItemMaterial(Material material) {
        return Arrays.asList(itemOnlyMaterial()).contains(material);
    }

    public static Plugin plugin = Exp.getPlugin();
    public static JavaPlugin javaPlugin = Exp.plugin;

    public static Set<String> getCommands() {
        return plugin.getDescription().getCommands().keySet();
    }
}
