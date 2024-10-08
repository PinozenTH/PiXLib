package com.pinont.piXLib.configurations;

import com.pinont.piXLib.PiXPlugin;
import com.pinont.piXLib.utils.enums.LoggerType;
import com.pinont.piXLib.utils.texts.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public enum Lang {
    NO_PERMISSION("no_permission", "§cYou do not have permission to do this."),
    ONLY_PLAYERS("only_players", "§cOnly players can use this command."),
    NO_CONSOLE("no_console", "§cYou cannot use this command in console."),
    NO_ARGS("no_args", "§cInvalid usage. Please use: "),
    NO_PLAYER("no_player", "§cPlayer not found."),
    NO_WORLD("no_world", "§cWorld not found."),
    NO_ITEM("no_item", "§cItem not found."),
    NO_BLOCK("no_block", "§cBlock not found."),
    NO_MATERIAL("no_material", "§cMaterial not found."),
    NO_LOCATION("no_location", "§cLocation not found."),
    NO_ENTITY("no_entity", "§cEntity not found.");

    private static final Plugin main = PiXPlugin.getPlugin();
    private final String path;
    private final String def;
    private static YamlConfiguration LANG = YamlConfiguration.loadConfiguration(new File(main.getDataFolder(), "lang.yml"));

    Lang(String path, String def) {
        this.path = path;
        this.def = def;
    }

    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, this.def));
    }

    public String getDefault() {
        return this.def;
    }

    public String getPath() {
        return this.path;
    }

    public static void loadLang() {
        File lang = new File(main.getDataFolder(), "lang.yml");
        if (!lang.exists()) {
            try {
                main.getDataFolder().mkdir();
                lang.createNewFile();
                File defConfigStream = lang;
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                    defConfig.save(lang);
                    Lang.setFile(defConfig);
                    return;
                }
            } catch(IOException e) {
                new Message(LoggerType.SEVERE, "&8[&6PiXLib&8] Couldn't create language file.");
                new Message(LoggerType.SEVERE, "&8[&6PiXLib&8] This is a fatal error. Now disabling");
                new Message(LoggerType.SEVERE, e.getMessage());
                Bukkit.getPluginManager().disablePlugin(main); // Without it loaded, we can't send them messages
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        for(Lang item:Lang.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }
        Lang.setFile(conf);
        try {
            conf.save(lang);
        } catch(IOException e) {
            new Message(LoggerType.WARNING, " Failed to save lang.yml.");
            new Message(LoggerType.WARNING, " Report this stack trace to Pinont.");
            new Message(LoggerType.WARNING, e.getMessage());
        }
    }
}
