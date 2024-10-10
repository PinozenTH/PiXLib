package com.pinont.piXLib.api.utils;

import org.bukkit.ChatColor;

public class Common {
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
