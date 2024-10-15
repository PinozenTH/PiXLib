package com.pinont.piXLib.api.data;

import com.pinont.piXLib.PiXLib;
import com.pinont.piXLib.api.utils.enums.LoggerType;
import com.pinont.piXLib.api.utils.texts.Message;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;

public class Config {

    private final Map<String, Boolean> debug = Map.of("debug.enabled", false,
            "debug.bypass_perm", false);
    private final File file = new File(PiXLib.getPlugin().getDataFolder().getAbsolutePath() + "/lang.yml");
    private final YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
    private Map<String, String> stringConfig;
    private Map<String, Boolean> booleanConfig;
    private Map<String, Integer> intConfig;
    private Map<String, Double> doubleConfig;
    private Map<String, Float> floatConfig;

    static void create(File file, YamlConfiguration yaml) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                yaml.save(file);
            } catch (Exception e) {
                new Message(e.getMessage()).setLoggerType(LoggerType.SEVERE).send();
            }
        } else {
            try {
                yaml.save(file);
            } catch (Exception e) {
                new Message(e.getMessage()).setLoggerType(LoggerType.SEVERE).send();
            }
        }
    }

    public Config setStringConfig(Map<String, String> config) {
        this.stringConfig = config;
        return this;
    }

    public Config setBooleanConfig(Map<String, Boolean> config) {
        this.booleanConfig = config;
        return this;
    }

    public Config setIntegerConfig(Map<String, Integer> config) {
        this.intConfig = config;
        return this;
    }

    public Config setDoubleConfig(Map<String, Double> config) {
        this.doubleConfig = config;
        return this;
    }

    public Config setFloatConfig(Map<String, Float> config) {
        this.floatConfig = config;
        return this;
    }

    public void create() {
        yaml.set("debug", debug);
        setConfig(stringConfig);
        setConfig(booleanConfig);
        setConfig(intConfig);
        setConfig(doubleConfig);
        setConfig(floatConfig);
        yaml.set("version", PiXLib.getPluginConfigVersion());
        create(file, yaml);
    }

    private void setConfig(Map<String, ?> map) {
        if (map == null) return;
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            yaml.set(entry.getKey(), entry.getValue());
        }
    }
}
