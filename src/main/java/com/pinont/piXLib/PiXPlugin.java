package com.pinont.piXLib;

import com.pinont.piXLib.api.utils.enums.LoggerType;
import com.pinont.piXLib.api.utils.enums.MessageType;
import com.pinont.piXLib.api.utils.texts.Message;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PiXPlugin {

    private static JavaPlugin plugin;
    public static List<Listener> listeners = new ArrayList<>();
    public static HashMap<Listener, Boolean> ignoreList = new HashMap<>();

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(final JavaPlugin plugin) {
        PiXPlugin.plugin = plugin;
        registerEvents(listeners, plugin);
        new Message(plugin.getName() + "is Enabled!").setLoggerType(LoggerType.INFO).send();
    }

    public static void unregister(final JavaPlugin plugin) {
        plugin.getServer().getScheduler().cancelTasks(plugin);
        new Message(plugin.getName() + "is Disabled!").setLoggerType(LoggerType.INFO).send();
    }

    public static void registerEvents(List<Listener> listener, JavaPlugin plugin) {
        for (Listener l : listener) {
            plugin.getServer().getPluginManager().registerEvents(l, plugin);
            if (ignoreList.get(l)) {
                continue;
            }
            new Message("Registered listener: " + l.getClass().getSimpleName()).setMessageType(MessageType.CONSOLE).send();
        }
        new Message("All listeners registered.").setMessageType(MessageType.CONSOLE).send();
    }
}


