package com.pinont.experiences.api.utils.texts;

import com.pinont.experiences.plugin.ExpPlugin;
import com.pinont.experiences.api.utils.enums.DebugType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public class Debug {

    private final Plugin plugin = ExpPlugin.getPlugin();
    private final boolean debug = plugin.getConfig().getBoolean("debug.enabled");
    private final boolean bypass_perm = plugin.getConfig().getBoolean("debug.bypass-permission");
    private static final String prefix = "Debug: ";
    private DebugType type;
    private final String message;
    public Logger log = Bukkit.getLogger();

    public Debug(String message) {
        this.message = message;
    }

    public Debug setDebugType(DebugType type) {
        this.type = type;
        return this;
    }

    public void send() {
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
            new Debug(message).setDebugType(DebugType.INFO).send();
            new Debug(message).setDebugType(DebugType.PLAYER).send();
        }
    }
}
