package com.pinont.piXLib.utils.texts;

import com.pinont.piXLib.PiXPlugin;
import com.pinont.piXLib.utils.enums.LoggerType;
import com.pinont.piXLib.utils.enums.MessageType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class Message extends PiXPlugin {

    private static final boolean bypass_perm = getPlugin().getConfig().getBoolean("debug.bypass-permission");
    public Logger log = getPlugin().getLogger();
    public Player p;

    public Message(MessageType type, String message) {
        if (MessageType.CONSOLE.equals(type)) {
            log.info(message);
            new Debug("Message sent to console: " + message, Debug.DebugType.PLAYER);
        } else if (MessageType.PLAYER.equals(type)) {
            p.sendMessage(message);
        } else if (MessageType.ALLPLAYER.equals(type)) {
            if (!getServer().getOnlinePlayers().isEmpty()) {
                for (Player player : getServer().getOnlinePlayers()) {
                    player.sendMessage(message);
                    new Debug("Message sent to player: " + player.getName() + " with message: " + message, Debug.DebugType.BOTH);
                    player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    new Debug("Sound played to player: " + player.getName(), Debug.DebugType.BOTH);
                }
            }
        } else if (MessageType.BOTH.equals(type)) {
            log.info(message);
            new Debug("Message sent to console: " + message, Debug.DebugType.PLAYER);
            if (!getServer().getOnlinePlayers().isEmpty()) {
                for (Player player : getServer().getOnlinePlayers()) {
                    if (player.hasPermission("pipermission.notify") || bypass_perm) {
                        player.sendMessage(message);
                        new Debug("Message sent to player: " + player.getName() + " with message: " + message, Debug.DebugType.BOTH);
                        player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        new Debug("Sound played to player: " + player.getName(), Debug.DebugType.BOTH);
                    }
                }
            }
        }
    }

    public void send(Player player) {
        if (player != null) {
            p = player;
        }
    }

    public Message(String message) {
        log.info(message);
    }

    public Message(LoggerType type, String message) {
        if (LoggerType.INFO.equals(type)) {
            log.info(message);
        } else if (LoggerType.WARNING.equals(type)) {
            log.warning(message);
        } else if (LoggerType.SEVERE.equals(type)) {
            log.severe(message);
        }
    }

    public Message(String permission, String message) {
        if (!getServer().getOnlinePlayers().isEmpty()) {
            for (Player player : getServer().getOnlinePlayers()) {
                if (player.hasPermission(permission) || bypass_perm) {
                    player.sendMessage(message);
                    new Debug("Message sent to player: " + player.getName() + " with message: " + message, Debug.DebugType.BOTH);
                    player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    new Debug("Sound played to player: " + player.getName(), Debug.DebugType.BOTH);
                }
            }
        }
    }

    public Message(Player player, String message) {
        if (player != null) {
            player.sendMessage(message);
            new Debug("Message sent to player: " + player.getName() + " with message: " + message, Debug.DebugType.BOTH);
            player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            new Debug("Sound played to player: " + player.getName(), Debug.DebugType.BOTH);
        }
    }

    public Message(CommandSender sender, String message) {
        if (sender instanceof Player)
            new Message((Player) sender, "You don't have permission to use this command!");
        else
            new Message(MessageType.CONSOLE, "You don't have permission to use this command!");
    }
}
