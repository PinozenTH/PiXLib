package com.pinont.piXLib.configurations;

import com.pinont.piXLib.PiXPlugin;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

public enum Config {
    DEBUG("debug", "false"),
    PREFIX("debug.bypass_permission", "false"),
    VERSION("version", "1");

    public final String path;
    public final String def;
    public Plugin plugin = PiXPlugin.getPlugin();

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

    public Double getDouble() {
        return Double.parseDouble(Config.valueOf(this.path).toString());
    }

    public void loadDefaultConfig() {
        Configuration config = plugin.getConfig();
        if (!config.contains(this.path)) {
            config.set(this.path, this.def);
            plugin.saveConfig();
        }
    }
}
