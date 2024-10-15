package com.pinont.piXLib.api.data;

import com.pinont.piXLib.PiXLib;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;

public class Lang {

    private final Map<String, String> lang;

    public Lang(Map<String, String> lang) {
        this.lang = lang;
    }

    public static String get(String key) {
        return "a";
    }

    public void create() {
        File file = new File(PiXLib.getPlugin().getDataFolder().getAbsolutePath() + "/lang.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        for (var entry : lang.entrySet()) {
            yaml.set(entry.getKey(), entry.getValue());
        }
        Config.create(file, yaml);
    }


}
