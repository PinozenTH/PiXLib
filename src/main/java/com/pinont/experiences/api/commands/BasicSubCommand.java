package com.pinont.experiences.api.commands;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class BasicSubCommand {
    private final BasicBaseCommand baseCommand;
    private final String name;
    private final List<Arguments> arguments = new ArrayList<>();

    public BasicSubCommand(BasicBaseCommand baseCommand, String commandName) {
        this.baseCommand = baseCommand;
        this.name = commandName;
    }

    public void addArgs(Arguments arguments) {
        this.arguments.add(arguments);
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (arguments.isEmpty()) return null;
            List<String> list = new ArrayList<>();
            for (Arguments arguments : this.arguments) {
                list.add(arguments.getArguments());
            }
            return list;
        }
        return null;
    }

}
