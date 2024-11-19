package com.pinont.experiences.api.commands;

import com.pinont.experiences.api.utils.Common;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@Getter
public class SimpleCommand implements CommandExecutor, TabCompleter {

    private List<SimpleCommandManager> simpleCommandManager;
    private Map<SimpleCommandManager, CommandSenderType> simpleCommandManagerCommandSenderTypeHashMap;

    public enum CommandSenderType {
        CONSOLE,
        PLAYER,
        BOTH
    }

    public void addCommand(SimpleCommandManager simpleCommandManager, CommandSenderType commandSenderType) {
        this.simpleCommandManager.add(simpleCommandManager);
        this.simpleCommandManagerCommandSenderTypeHashMap.put(simpleCommandManager, commandSenderType);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        for (SimpleCommandManager simpleCommandManager : this.simpleCommandManager) {
            if (simpleCommandManager.getName().equalsIgnoreCase(command.getName())) {
                switch (simpleCommandManagerCommandSenderTypeHashMap.get(simpleCommandManager)) {
                    case CONSOLE:
                        if (!(commandSender instanceof Player)) {
                            simpleCommandManager.execute(commandSender, command, strings);
                        } else {
                            commandSender.sendMessage("This command can only be executed by console.");
                        }
                        break;
                    case PLAYER:
                        if (commandSender instanceof Player) {
                            simpleCommandManager.execute(commandSender, command, strings);
                        } else {
                            commandSender.sendMessage("This command can only be executed by players.");
                        }
                        break;
                    case BOTH:
                        simpleCommandManager.execute(commandSender, command, strings);
                        break;
                }
                return true;
            }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        for (SimpleCommandManager simpleCommandManager : this.simpleCommandManager) {
            if (simpleCommandManager.getName().equalsIgnoreCase(command.getName())) {
                return simpleCommandManager.tabComplete(commandSender, command, strings);
            }
        }
        return null;
    }

    public void register() {
        for (SimpleCommandManager simpleCommandManager : this.simpleCommandManager) {
            Objects.requireNonNull(Common.javaPlugin.getCommand(simpleCommandManager.getName())).setExecutor(this);
            Objects.requireNonNull(Common.javaPlugin.getCommand(simpleCommandManager.getName())).setTabCompleter(this);
        }
    }
}
