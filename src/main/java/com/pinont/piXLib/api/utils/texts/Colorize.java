package com.pinont.piXLib.utils.texts;

import org.bukkit.ChatColor;

public class Colorize {

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
