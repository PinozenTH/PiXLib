package com.pinont.piXLib.events;

import com.pinont.piXLib.api.events.PluginStartEvent;
import com.pinont.piXLib.api.events.PluginStopEvent;
import com.pinont.piXLib.api.utils.enums.LoggerType;
import com.pinont.piXLib.api.utils.texts.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import static com.pinont.piXLib.PiXLib.*;

public class pluginStateEvent implements Listener {

    @EventHandler
    public void onPluginStart(PluginStartEvent event) {
        Plugin plugin = event.getPlugin();
        listeners.add(this);
        listenerHiddenList.add(this);
        new Message(plugin.getName() + "is Enabled!").setLoggerType(LoggerType.INFO).send();
    }

    @EventHandler
    public void onPluginDisable(PluginStopEvent event) {
        Plugin plugin = event.getPlugin();
        // Cancel all tasks that provided by the plugin
        plugin.getServer().getScheduler().cancelTasks(plugin);
        // Feedback message to console
        new Message(plugin.getName() + "is Disabled!").setLoggerType(LoggerType.INFO).send();
    }
}