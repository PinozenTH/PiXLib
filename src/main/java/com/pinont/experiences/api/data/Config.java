package com.pinont.experiences.api.data;

import com.pinont.experiences.Exp;
import com.pinont.experiences.api.utils.enums.LoggerType;
import com.pinont.experiences.api.utils.texts.Message;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;

public class Config {

    private final Map<String, Boolean> debug = Map.of("debug.enabled", false,
            "debug.bypass_perm", false);
    private static final File file = new File(Exp.getPlugin().getDataFolder().getAbsolutePath() + "/config.yml");
    private static final YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
    private Map<String, String> stringConfig;
    private Map<String, Boolean> booleanConfig;
    private Map<String, Integer> intConfig;
    private Map<String, Double> doubleConfig;
    private Map<String, Float> floatConfig;

    static void saveFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                yaml.save(Config.file);
            } catch (Exception e) {
                new Message(e.getMessage()).setLoggerType(LoggerType.SEVERE).send();
            }
        } else {
            try {
                yaml.save(Config.file);
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
        yaml.set("version", Exp.getPluginConfigVersion());
        saveFile();
    }

    private void setConfig(Map<String, ?> map) {
        if (map == null) return;
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            yaml.set(entry.getKey(), entry.getValue());
        }
    }
}
