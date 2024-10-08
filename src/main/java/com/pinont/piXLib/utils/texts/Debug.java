package com.pinont.piXLib.utils.texts;

import com.pinont.piXLib.PiXPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static com.pinont.piXLib.PiXPlugin.getPlugin;

public class Debug {

    private final Plugin plugin = getPlugin();
    private final boolean debug = plugin.getConfig().getBoolean("debug.enabled");
    private final boolean bypass_perm = plugin.getConfig().getBoolean("debug.bypass-permission");
    private static final String prefix = "Debug: ";
    public Logger log = Bukkit.getLogger();

    public Debug(String message) {
        if (debug) {
            log.info(message);
        }
    }

    public Debug(String message, DebugType type) {
        if (debug && DebugType.INFO.equals(type)) {
            log.info(prefix + message);
        } else if (debug && DebugType.WARNING.equals(type)) {
            log.warning(prefix + message);
        } else if (debug && DebugType.SEVERE.equals(type)) {
            log.severe(prefix + message);
        } else if (debug && DebugType.PLAYER.equals(type)) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.hasPermission("pipermission.debug") || bypass_perm) {
                    player.sendMessage(prefix + message);
                    player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                }
            }
        } else if (debug && DebugType.BOTH.equals(type)) {
            new Debug(message, DebugType.INFO);
            new Debug(message, DebugType.PLAYER);
        }
    }

    public enum DebugType {
        INFO,
        WARNING,
        SEVERE,
        PLAYER,
        BOTH
    }
}
