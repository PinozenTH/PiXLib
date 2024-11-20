package com.pinont.experiences.api.commands;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nullable;
import java.util.*;

import static com.pinont.experiences.api.commands.SimpleCommand.NO_PERMISSION;

@Getter
public abstract class SimpleCommandManager {
    private final String name;

    public SimpleCommandManager(String name) {
        this.name = name;
    }

    private final Map<Integer, Map<String, String>> args = addArgs();

    private Map<Integer, Map<String, String>> addArgs() {
        Map<Integer, Map<String, String>> args = args();
        if (args == null) return new HashMap<>();
        return args;
    }

    @Nullable
    public abstract Map<Integer, Map<String, String>> args();

    public abstract void execute(CommandSender sender, Command command, String[] args);

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {
        if (!this.args.isEmpty() && this.args.get(args.length - 1) != null) {
            String permission = this.args.get(args.length - 1).get(args[args.length - 1]);
            if (sender.hasPermission(permission)) {
                if (permission.equalsIgnoreCase(NO_PERMISSION)) {
                    return this.args.get(args.length - 1).keySet().stream().toList();
                }
                return this.args.get(args.length - 1).keySet().stream().toList();
            }
        }
        return null;
    }
}
