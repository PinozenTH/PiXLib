package com.pinont.piXLib.configurations;

import org.bukkit.ChatColor;

public enum Config {
    DEBUG("debug", "false"),
    PREFIX("bypass_permission", "false"),
    VERSION("version", "1");

    public final String path;
    public final String def;

    Config(String path, String def) {
        this.path = path;
        this.def = def;
    }

    public Boolean getBoolean() {
        return Boolean.valueOf(Config.valueOf(this.path).toString());
    }

    public int getInt() {
        return Integer.parseInt(Config.valueOf(this.path).toString());
    }
}
