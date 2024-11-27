package com.pinont.experiences.plugin;

import com.pinont.experiences.api.annotations.AutoRegister;
import com.pinont.experiences.api.commands.SimpleCommand;
import com.pinont.experiences.api.utils.Common;
import com.pinont.experiences.api.utils.enums.LoggerType;
import com.pinont.experiences.api.utils.texts.Message;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
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
        Set<SimpleCommand> registeredCommandGroups = new java.util.HashSet<>();
        Set<Listener> registeredListeners = new java.util.HashSet<>();

        for (Class<?> clazz : classes) {
            if (SimpleCommand.class.isAssignableFrom(clazz)) {
                try {
                    SimpleCommand command = (SimpleCommand) clazz.getDeclaredConstructor().newInstance();
                    registeredCommandGroups.add(command);
                } catch (Exception e) {
                    Common.sneaky(e);
                }
            }

            // Check for event handlers
            if (Arrays.stream(clazz.getDeclaredMethods()).anyMatch(method -> method.isAnnotationPresent(EventHandler.class))) {
                try {
                    Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                    registeredListeners.add(listener);
                } catch (Exception e) {
                    Common.sneaky(e);
                }
            }
        }
        if (!registeredCommandGroups.isEmpty()) {
            registeredCommandGroups.forEach(SimpleCommand::register);
        }
        if (!registeredListeners.isEmpty()) {
            registeredListeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, Common.javaPlugin));
        }
    }

    private static Set<Class<?>> findClassesWithAnnotation() {
        Reflections reflections = new Reflections(Common.javaPlugin.getDescription().getMain().split("\\.")[0]); // Get the main class package
        Set<Class<?>> classes = new HashSet<>();
        classes.addAll(reflections.getTypesAnnotatedWith(AutoRegister.class));
        classes.addAll(reflections.getSubTypesOf(SimpleCommand.class));

        // find class that has the annotation of event handler
        for (Class<?> clazz : reflections.getSubTypesOf(Listener.class)) { // Get all classes that implement Listener
            if (Arrays.stream(clazz.getDeclaredMethods()).anyMatch(method -> method.isAnnotationPresent(EventHandler.class))) {
                classes.add(clazz);
                continue;
            }
            new Message("Class " + clazz.getName() + " does not have any event handlers.").setLoggerType(LoggerType.WARNING).send();
        }

        return classes;
    }
}