package com.pinont.piXLib.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;

public abstract class PiXCommandHandler implements CommandExecutor, TabCompleter {


    public void register(String name, Plugin plugin) {
        Objects.requireNonNull(Bukkit.getPluginCommand(name)).setExecutor(plugin);
        Objects.requireNonNull(Bukkit.getPluginCommand(name)).setTabCompleter(plugin);
    }

    @Override
    public abstract boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args);

    public abstract List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String s, String[] args);

}
