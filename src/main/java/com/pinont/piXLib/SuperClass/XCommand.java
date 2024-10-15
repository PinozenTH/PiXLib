package com.pinont.piXLib.SuperClass;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class XCommand implements CommandExecutor, TabCompleter {

    @Getter
    public final String name;
    public final boolean allowConsole;

    public XCommand(String name, boolean allowConsole) {
        this.name = name;
        this.allowConsole = allowConsole;
    }

    public abstract boolean command(CommandSender sender, Command command, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, Command command, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!allowConsole && !(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command.");
            return false;
        }
        return command(sender, command, args);
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return tabComplete(sender, command, args);
    }

}
