package com.pinont.piXLib;

import com.pinont.piXLib.api.events.PluginStartEvent;
import com.pinont.piXLib.api.events.PluginStopEvent;
import com.pinont.piXLib.api.utils.enums.MessageType;
import com.pinont.piXLib.api.utils.texts.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PiXLib {

    public static JavaPlugin plugin;
    public static List<Listener> listeners = new ArrayList<>();
    public static List<Listener> listenerHiddenList = new ArrayList<>();
    public static List<CommandExecutor> commandHiddenList = new ArrayList<>();
    public static HashMap<String, CommandExecutor> commands = new HashMap<>();

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(final JavaPlugin plugin) {
        // set plugin
        PiXLib.plugin = plugin;

        // register events and commands
        registerEvents();
        registerCommands();

        // clean up after unused
        listeners.clear();
        commands.clear();
        listenerHiddenList.clear();

        // Fire the plugin started event
        PluginStartEvent event = new PluginStartEvent(plugin);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public static void unregister() {
        // Fire the plugin stop event
        PluginStopEvent event = new PluginStopEvent(plugin);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public static void registerEvents() {
        if (listeners.isEmpty()) return;
        for (Listener l : listeners) {
            plugin.getServer().getPluginManager().registerEvents(l, plugin);
            if (listenerHiddenList.contains(l)) {
                continue;
            }
            new Message("Registered listener: " + l.getClass().getSimpleName()).setMessageType(MessageType.CONSOLE).send();
        }
        new Message("All listeners registered.").setMessageType(MessageType.CONSOLE).send();
    }

    public static void registerCommands() {
        if (commands.isEmpty()) return;
        for (String command : commands.keySet()) {
            Objects.requireNonNull(plugin.getCommand(command)).setExecutor(commands.get(command));
            if (commandHiddenList.contains(commands.get(command))) {
                continue;
            }
            new Message("Registered command: " + command).setMessageType(MessageType.CONSOLE).send();
        }
        new Message("All commands registered.").setMessageType(MessageType.CONSOLE).send();
    }
}


