package com.pinont.piXLib;

import com.pinont.piXLib.utils.enums.MessageType;
import com.pinont.piXLib.utils.texts.Message;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class PiXPlugin {

    private static JavaPlugin plugin;

    public static List<BukkitTask> tasks;

    public static List<Listener> listeners = new ArrayList<>();

    public static void addListener(Listener listener) {
        listeners.add(listener);
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(final JavaPlugin plugin) {
        PiXPlugin.plugin = plugin;
        registerEvents(listeners, plugin);
        new Message(MessageType.CONSOLE, "Plugin enabled.");
    }

    public static void registerEvents(List<Listener> listener, JavaPlugin plugin) {
        for (Listener l : listener) {
            plugin.getServer().getPluginManager().registerEvents(l, plugin);
            new Message(MessageType.CONSOLE, "Registered listener: " + l.getClass().getSimpleName());
        }
        new Message(MessageType.CONSOLE, "All listeners registered.");
    }
}


