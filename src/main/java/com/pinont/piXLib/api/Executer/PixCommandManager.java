package com.pinont.piXLib.api.Executer;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

@Getter
public abstract class PixCommandManager {
    private final Command command;

    public PixCommandManager(Command command) {
        this.command = command;
    }

    public final Command getCommands() {
        return command;
    }

    public abstract String getName();

    public abstract void execute(CommandSender sender, Command command, String[] args);

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {
        return command.getAliases();
    }

}
