package com.pinont.experiences.api.commands;

import lombok.Getter;
import org.bukkit.command.CommandSender;

@Getter
public abstract class Arguments {

    private final String arguments;

    public abstract boolean execute(CommandSender sender, String[] args);

    public Arguments(String arguments) {
        this.arguments = arguments;
    }
}
