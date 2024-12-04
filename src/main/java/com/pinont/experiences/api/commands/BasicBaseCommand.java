package com.pinont.experiences.api.commands;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicBaseCommand implements CommandExecutor, TabCompleter {

    @Getter
    private final String name;
    private final List<BasicSubCommand> subCommands = new ArrayList<>();

    protected BasicBaseCommand(@NotNull String name) {
        this.name = name;
    }

    public void addSubCommand(BasicSubCommand subCommand) {
        subCommands.add(subCommand);
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase(name)) {
            if (args.length == 0) {
                return subCommands.stream().map(BasicSubCommand::getName).toList();
            } else if (args.length == 1) {
                for (BasicSubCommand subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        return subCommand.tabComplete(sender, args);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        for (BasicSubCommand subCommand : subCommands) {
            if (strings[0].equalsIgnoreCase(subCommand.getName())) {
                if (strings.length == 2) {
                    if (subCommand.getArguments().contains(strings[1])) {
                        for (Arguments arguments : subCommand.getArguments()) {
                            if (arguments.getArguments().equalsIgnoreCase(strings[1])) {
                                return arguments.execute(commandSender, strings);
                            }
                        }
                    }
                }
                return subCommand.execute(commandSender, strings);
            }
            commandSender.sendMessage("Invalid arguments.");
        }
        return false;
    }

    public void register() {
        Objects.requireNonNull(Bukkit.getPluginCommand(name)).setExecutor(this);
        Objects.requireNonNull(Bukkit.getPluginCommand(name)).setTabCompleter(this);
    }
}
