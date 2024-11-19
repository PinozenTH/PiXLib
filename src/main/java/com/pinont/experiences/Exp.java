package com.pinont.experiences;

import com.pinont.experiences.api.menus.MenuListener;
import com.pinont.experiences.api.utils.enums.MessageType;
import com.pinont.experiences.api.utils.texts.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Exp extends JavaPlugin {

    public static JavaPlugin plugin;
    public static List<Listener> listeners = new ArrayList<>();
    private static final List<Listener> listenerHiddenList = new ArrayList<>();
    private static final List<String> simpleCommand = new ArrayList<>();
    public static Map<String, CommandExecutor> commands = new HashMap<>();
    public static Map<String, TabCompleter> tabComplete = new HashMap<>();
    @Setter
    @Getter
    public static double pluginConfigVersion = 1.0;
    public static String apiVersion = "1.04-snapshot";

    @NotNull
    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setup(@NotNull JavaPlugin plugin) {
        Exp.plugin = plugin;
        new Message(ChatColor.GREEN + "Launching " + plugin.getName() + " With PiXLib Version " + apiVersion).sendConsole();
        listenerHiddenList.add(new MenuListener());

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
        listeners.addAll(listenerHiddenList);
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


