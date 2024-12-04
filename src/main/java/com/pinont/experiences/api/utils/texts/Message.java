package com.pinont.experiences.api.utils.texts;

import com.pinont.experiences.plugin.ExpPlugin;
import com.pinont.experiences.api.utils.enums.DebugType;
import com.pinont.experiences.api.utils.enums.LoggerType;
import com.pinont.experiences.api.utils.enums.MessageType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class Message {

    public void sendConsole(String Message) {
        Logger log = ExpPlugin.getPlugin().getLogger();
        log.info(Message);
    }

    public void broadcast(String Message) {
        getServer().broadcastMessage(Message);
    }

    public void reply(CommandSender sender, String Message) {
        if (sender instanceof Player player) {
            player.sendMessage(Message);
        } else {
            sendConsole(Message);
        }
    }

    public void debug(String Message, DebugType debugType) {
        new Debug(Message).setDebugType(debugType).send();
    }

    public void sendLogger(String Message, LoggerType loggerType) {
        Logger log = ExpPlugin.getPlugin().getLogger();
        switch (loggerType) {
            case INFO -> log.info(Message);
            case WARNING -> log.warning(Message);
            case SEVERE -> log.severe(Message);
        }
    }
}
