package com.pinont.piXLib.hook;

import com.pinont.piXLib.PiXPlugin;
import com.pinont.piXLib.utils.enums.LoggerType;
import com.pinont.piXLib.utils.texts.Message;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class MythicMobsAPI {

    private static final Plugin main = PiXPlugin.getPlugin();

    static class Lang {
        static final String HOOKED_TO_MYTHIC_MOBS = "Hooked to MythicMobs!";
        static final String NO_MYTHIC_MOBS = "MythicMobs not found!";
        static final String REGISTERED_MYTHIC_MOB = "Registered MythicMob: ";
        static final String NOT_VALID_MYTHIC_MOB = " is not a valid MythicMob!";
        static final String NULL_MYTHIC_MOB = " is a null MythicMob!";
    }

    public static void hook() {
        if (main.getServer().getPluginManager().getPlugin("MythicMobs") != null) {
            main.getLogger().info(Lang.HOOKED_TO_MYTHIC_MOBS);
        } else {
            main.getLogger().warning(Lang.NO_MYTHIC_MOBS);
        }
    }

    private static void checkProvidedEntity() {
        main.getConfig().getStringList("mythicMob-bosses-name").forEach(MythicMobsAPI::isPresent);
        main.getConfig().getStringList("mythicMob-mini-boss-name").forEach(MythicMobsAPI::isPresent);
    }

    private static void isPresent(String entityName) {
        if (MythicBukkit.inst().getMobManager().getMythicMob(entityName).isPresent()) {
            new Message(Lang.REGISTERED_MYTHIC_MOB + entityName).setLoggerType(LoggerType.INFO).send();
        } else {
            new Message(entityName + Lang.NOT_VALID_MYTHIC_MOB).setLoggerType(LoggerType.WARNING).send();
        }
    }

    public static void summon(Location location, String mobName) {
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(mobName).orElse(null);
        if (mob != null) {
            // spawns mob
            mob.spawn(BukkitAdapter.adapt(location), 1);
        } else {
            Bukkit.getLogger().warning(mobName + Lang.NULL_MYTHIC_MOB);
        }
    }

}
