package com.pinont.piXLib;

import com.pinont.piXLib.api.commands.XCommand;
import com.pinont.piXLib.api.utils.enums.MessageType;
import com.pinont.piXLib.api.utils.texts.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class PiXLib {

    public static JavaPlugin plugin;
    public static List<Listener> listeners = new ArrayList<>();
    public static List<Listener> listenerHiddenList = new ArrayList<>();
    public static Map<String, XCommand> xCommandList = new HashMap<>();
    public static Map<String, CommandExecutor> commands = new HashMap<>();
    public static Map<String, TabCompleter> tabComplete = new HashMap<>();
    @Setter
    @Getter
    public static double pluginConfigVersion = 1.0;
    public static String apiVersion = "1.02-snapshot";

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(final JavaPlugin plugin) {
        // set plugin
        PiXLib.plugin = plugin;
        new Message(ChatColor.GREEN + "Launching " + plugin.getName() + " With PiXLib Version " + apiVersion).sendConsole();

        // load annotated classes
//        load();

        // register events and commands
        registerEvents();
        registerCommands();

        // clean up after unused
        listeners.clear();
        commands.clear();
        listenerHiddenList.clear();
        registerEvents();
    }

    public static void unregister() {
        listeners.clear();
        commands.clear();
        listenerHiddenList.clear();
        plugin = null;
    }

    protected static void registerEvents() {
        if (listeners.isEmpty()) return;
        for (Listener l : listeners) {
            plugin.getServer().getPluginManager().registerEvents(l, plugin);
            if (listenerHiddenList.contains(l)) {
                continue;
            }
            new Message(ChatColor.AQUA + "Registered listener: " + l.getClass().getSimpleName()).setMessageType(MessageType.CONSOLE).send();
        }
        new Message(ChatColor.GREEN + "All listeners are registered.").setMessageType(MessageType.CONSOLE).send();
    }

    protected static void registerCommands() {
        if (!xCommandList.isEmpty()) {
            for (String command : xCommandList.keySet()) {
                Objects.requireNonNull(plugin.getCommand(command)).setExecutor(commands.get(command));
                Objects.requireNonNull(plugin.getCommand(command)).setTabCompleter(tabComplete.get(command));
                new Message(ChatColor.AQUA + "Registered XCommand: " + command).setMessageType(MessageType.CONSOLE).send();
            }
            new Message(ChatColor.GREEN + "All XCommands are registered.").setMessageType(MessageType.CONSOLE).send();
            return;
        }
        if (!commands.isEmpty()) {
            for (String command : commands.keySet()) {
                Objects.requireNonNull(plugin.getCommand(command)).setExecutor(commands.get(command));
                new Message(ChatColor.AQUA + "Registered command: " + command).setMessageType(MessageType.CONSOLE).send();
            }
            new Message(ChatColor.GREEN + "All commands are registered.").setMessageType(MessageType.CONSOLE).send();
        }
        if (!tabComplete.isEmpty()) {
            for (String command : tabComplete.keySet()) {
                Objects.requireNonNull(plugin.getCommand(command)).setTabCompleter(tabComplete.get(command));
                new Message(ChatColor.AQUA + "Registered tab completer: " + command).setMessageType(MessageType.CONSOLE).send();
            }
            new Message(ChatColor.GREEN + "All tab completes are registered.").setMessageType(MessageType.CONSOLE).send();
        }
    }
}


