package com.pinont.experiences.api.commands;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class SimpleCommandManager {
    private final String name;

    public SimpleCommandManager(String name) {
        this.name = name;
    }

    private final Map<Integer, List<String>> args = addArgs();

    private Map<Integer, List<String>> addArgs() {
        Map<Integer, List<String>> args = args();
        if (args == null) return new HashMap<>();
        return args;
    }

    @Nullable
    public abstract Map<Integer, List<String>> args();

    public abstract void execute(CommandSender sender, Command command, String[] args);

    public List<String> tabComplete(CommandSender sender, Command command, String[] args) {
        if (!this.args.isEmpty() && this.args.get(args.length - 1) != null) {
            return this.args.get(args.length - 1);
        }
        return null;
    }
}
