package com.pinont.piXLib.utils;

import com.pinont.piXLib.PiXLib;
import com.pinont.piXLib.SuperClass.PiXCommand;
import com.pinont.piXLib.SuperClass.PiXEvents;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class ClassScanner {

    public static void registerAllSubclasses(JavaPlugin plugin, Class<?> superClass, TYPE type) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forClassLoader(plugin.getClass().getClassLoader()))
                .setScanners(new SubTypesScanner(false)));

        Set<Class<?>> subTypes = reflections.getSubTypesOf((Class<Object>) superClass);

        for (Class<?> subType : subTypes) {
            try {
                Object instance = subType.getDeclaredConstructor().newInstance();
                // Perform any registration logic here
                if (type == TYPE.COMMAND && PiXCommand.class.isAssignableFrom(subType)) {
                    // Add class to commandList
                    PiXLib.commands.put(((PiXCommand) instance).getName(), (org.bukkit.command.CommandExecutor) instance);
                } else if (type == TYPE.EVENT && PiXEvents.class.isAssignableFrom(subType)) {
                    // Register events
                    PiXLib.listeners.add((org.bukkit.event.Listener) instance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public enum TYPE {
        COMMAND,
        EVENT
    }
}