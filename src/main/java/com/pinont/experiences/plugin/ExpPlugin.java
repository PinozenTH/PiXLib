package com.pinont.experiences.plugin;

import com.pinont.experiences.api.commands.SimpleCommand;
import com.pinont.experiences.api.gui.MenuListener;
import com.pinont.experiences.api.utils.enums.MessageType;
import com.pinont.experiences.api.utils.texts.Message;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class ExpPlugin extends JavaPlugin {

    public static JavaPlugin plugin;
    public static ExpPlugin instance;
    private List<Listener> listeners;
    private List<Listener> listenerHiddenList;
    private Map<String, CommandExecutor> commands;
    private Map<String, TabCompleter> tabComplete;
    private List<SimpleCommand> simpleCommands;

    private boolean hasListener = false;
    private boolean hasCommand = false;
    private boolean hasSimpleCommand = false;

    @Getter
    public static final double pluginConfigVersion = 1.0;
    @Getter
    public static final String apiVersion = "1.04-snapshot";

    @NotNull
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public abstract void onPluginStart();

    public abstract void onPluginStop();

    public static ExpPlugin getInstance() {
        if (instance == null) {
            try {
                instance = JavaPlugin.getPlugin(ExpPlugin.class);

            } catch (final IllegalStateException ex) {
                if (Bukkit.getPluginManager().getPlugin("PlugManX") != null)
                    Bukkit.getLogger().severe("Failed to get instance of the plugin, if you reloaded using PlugManX you need to do a clean restart instead.");

                throw ex;
            }

            Objects.requireNonNull(instance, "Cannot get a new instance! Have you reloaded?");
        }

        return instance;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
        hasListener = true;
    }

    public void addCommand(String command, CommandExecutor commandExecutor) {
        commands.put(command, commandExecutor);
        hasCommand = true;
    }

    public void addCommand(SimpleCommand simpleCommand) {
        simpleCommands.add(simpleCommand);
        hasSimpleCommand = true;
    }

    public void addTabComplete(String command, TabCompleter tabCompleter) {
        tabComplete.put(command, tabCompleter);
    }

    @Override
    public final void onEnable() {
        plugin = this;
        setup(this);
        this.onPluginStart();

        if (hasListener) {
            registerEvents();
        }
        if (hasCommand) {
            registerCommands();
        }
        if (hasSimpleCommand) {
            for (SimpleCommand simpleCommand : simpleCommands) {
                simpleCommand.register();
            }
            new Message(ChatColor.GREEN + "All simple commands are registered.").setMessageType(MessageType.CONSOLE).send();
            if (!simpleCommands.isEmpty()) simpleCommands.clear();
        }
    }

    @Override
    public final void onDisable() {
        this.onPluginStop();
        unregister();
    }

    private void setup(@NotNull JavaPlugin plugin) {
        ExpPlugin.plugin = plugin;
        new Message(ChatColor.GREEN + "Launching " + plugin.getName() + " With PiXLib Version " + apiVersion).sendConsole();
        listenerHiddenList.add(new MenuListener());
    }

    private void unregister() {
        if (!listeners.isEmpty()) listeners.clear();
        if (!listenerHiddenList.isEmpty()) listenerHiddenList.clear();
        if (!commands.isEmpty()) commands.clear();
        if (!tabComplete.isEmpty()) tabComplete.clear();
        if (!simpleCommands.isEmpty()) simpleCommands.clear();
        plugin = null;
    }

    private void registerEvents() {
        if (listeners.isEmpty()) return;
        listeners.addAll(listenerHiddenList);
        for (Listener l : listeners) {
            plugin.getServer().getPluginManager().registerEvents(l, plugin);
            if (!listenerHiddenList.isEmpty() && listenerHiddenList.contains(l)) {
                continue;
            }
            new Message(ChatColor.AQUA + "Registered listener: " + l.getClass().getSimpleName()).setMessageType(MessageType.CONSOLE).send();
        }
        new Message(ChatColor.GREEN + "All listeners are registered.").setMessageType(MessageType.CONSOLE).send();
        if (!listenerHiddenList.isEmpty()) listenerHiddenList.clear();
        listeners.clear();
    }

    private void registerCommands() {
        if (!commands.isEmpty()) {
            for (String command : commands.keySet()) {
                Objects.requireNonNull(plugin.getCommand(command)).setExecutor(commands.get(command));
                new Message(ChatColor.AQUA + "Registered command: " + command).setMessageType(MessageType.CONSOLE).send();
            }
            new Message(ChatColor.GREEN + "All commands are registered.").setMessageType(MessageType.CONSOLE).send();
            commands.clear();
        }
        if (!tabComplete.isEmpty()) {
            for (String command : tabComplete.keySet()) {
                Objects.requireNonNull(plugin.getCommand(command)).setTabCompleter(tabComplete.get(command));
                new Message(ChatColor.AQUA + "Registered tab completer: " + command).setMessageType(MessageType.CONSOLE).send();
            }
            new Message(ChatColor.GREEN + "All tab completes are registered.").setMessageType(MessageType.CONSOLE).send();
            tabComplete.clear();
        }
    }
}


