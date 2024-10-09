package com.pinont.piXLib.api.utils.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandUtils {

    public static boolean handleCommand(CommandSender sender, Command cmd, String s, String[] args) {
        // Add your command handling logic here
        return false;
    }

    public static List<String> handleTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        // Add your tab completion logic here
        return List.of();
    }
}