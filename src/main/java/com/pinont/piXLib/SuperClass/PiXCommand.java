package com.pinont.piXLib.SuperClass;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public abstract class PiXCommand implements CommandExecutor, TabCompleter {

    public final String name;


    public PiXCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public abstract boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args);

    public abstract List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String s, String[] args);

}
