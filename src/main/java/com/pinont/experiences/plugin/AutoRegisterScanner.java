package com.pinont.experiences.plugin;

import com.pinont.experiences.api.commands.BasicBaseCommand;
import com.pinont.experiences.api.commands.SimpleCommand;
import com.pinont.experiences.api.commands.BasicSubCommand;
import com.pinont.experiences.api.utils.Common;
import com.pinont.experiences.api.utils.enums.LoggerType;
import com.pinont.experiences.api.utils.texts.Message;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utilizes \@AutoRegister annotation to add auto registration support for commands, events.
 */
final class AutoRegisterScanner {

    public static void scanAndRegister() {

        // Find any class that is annotated with @AutoRegister
        Set<Class<?>> classes = findClassesWithAnnotation();
        Set<BasicBaseCommand> registeredBaseCommands = new java.util.HashSet<>();
        Set<BasicSubCommand> registeredSubCommands = new java.util.HashSet<>();

        for (Class<?> clazz : classes) {
            if (SimpleCommand.class.isAssignableFrom(clazz)) {
                try {
                    SimpleCommand command = (SimpleCommand) clazz.getDeclaredConstructor().newInstance();
                    command.register();
                } catch (Exception e) {
                    Common.sneaky(e);
                }
            }

            if (BasicSubCommand.class.isAssignableFrom(clazz)) {
                try {
                    BasicSubCommand subCommand = (BasicSubCommand) clazz.getDeclaredConstructor().newInstance();
                    registeredSubCommands.add(subCommand);
                } catch (Exception e) {
                    Common.sneaky(e);
                }
            }

            if (BasicBaseCommand.class.isAssignableFrom(clazz)) {
                try {
                    BasicBaseCommand baseCommand = (BasicBaseCommand) clazz.getDeclaredConstructor().newInstance();
                    registeredBaseCommands.add(baseCommand);
                } catch (Exception e) {
                    Common.sneaky(e);
                }
            }
        }

        if (!registeredBaseCommands.isEmpty()) {
            for (BasicBaseCommand baseCommand : registeredBaseCommands) {
//                registeredSubCommands.forEach(subCommand -> {
//                    if (subCommand.getBaseCommand().equals(baseCommand)) {
//                        baseCommand.addSubCommand(subCommand);
//                        new Message().sendConsole("Registered subcommand " + subCommand.getName() + " to base command " + baseCommand.getName());
//                        registeredSubCommands.remove(subCommand); // cut off the subcommand from the list to improve performance and duplicate checking
//                    }
//                });
                baseCommand.register();
                new Message().sendConsole("Registered base command " + baseCommand.getName());
            }
        }
    }

    private static Set<Class<?>> findClassesWithAnnotation() {
        String path_to_main = Common.javaPlugin.getDescription().getMain();
        String path_to_package = path_to_main.substring(0, path_to_main.lastIndexOf(".")); // retrieve the package name
        Reflections reflections = new Reflections(path_to_package);
        Set<Class<?>> classes = new HashSet<>();
        classes.addAll(reflections.getSubTypesOf(SimpleCommand.class));
        classes.addAll(reflections.getSubTypesOf(BasicBaseCommand.class));
        classes.addAll(reflections.getSubTypesOf(BasicSubCommand.class));

        // find class that has the annotation of event handler and register it
        for (Class<?> clazz : reflections.getSubTypesOf(Listener.class)) { // Get all classes that implement Listener
            if (Arrays.stream(clazz.getDeclaredMethods()).anyMatch(method -> method.isAnnotationPresent(EventHandler.class))) {
                if (classes.contains(clazz)) continue;
                try {
                    Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                    Bukkit.getPluginManager().registerEvents(listener, Common.javaPlugin);
                } catch (Exception e) {
                    Common.sneaky(e);
                }
                continue;
            }
            new Message().sendLogger("Class " + clazz.getName() + " does not have any event handlers.", LoggerType.WARNING);
        }

        return classes;
    }
}