package com.pinont.piXLib.api.Executer;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
public class SimpleCommand implements CommandExecutor, TabCompleter {

    private PixCommandManager command;

    public void addCommand(PixCommandManager command) {
        this.command = command;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (command.getName().equalsIgnoreCase(this.command.getName())) this.command.execute(commandSender, command, strings);
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return command.getName().equalsIgnoreCase(this.command.getName()) ? this.command.tabComplete(commandSender, command, strings) : null;
    }
}
