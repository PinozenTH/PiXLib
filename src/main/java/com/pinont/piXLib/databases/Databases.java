package com.pinont.piXLib.databases;

import com.pinont.piXLib.PiXLib;
import com.pinont.piXLib.api.utils.enums.LoggerType;
import com.pinont.piXLib.api.utils.enums.MessageType;
import com.pinont.piXLib.api.utils.enums.SQLTYPES;
import com.pinont.piXLib.api.utils.texts.Message;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Databases {


    private final ConfigurationOptions configOption = PiXLib.getPlugin().getConfig().options();
    private int attempts = configOption.configuration().getInt("database.attempts");
    private long delay = configOption.configuration().getInt("database.delay");
    private final long maxDelay = configOption.configuration().getInt("database.max-delay");
    private final double multiplier = configOption.configuration().getInt("database.multiplier");
    private final long jitter = configOption.configuration().getInt("database.jitter");

    private final Plugin main = PiXLib.getPlugin();

    public List<BukkitTask> tasks;

    public void setupConnection(SQLTYPES sqlType) {
        switch (sqlType) {
            case SQLITE:
                // Create SQLite connection
                BukkitTask task1 = Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin) this, new Runnable() {

                    @Override
                    public void run() {
                        if (attempts < 3) {
                            try {
                                // Attempt to create SQLite connection
                                for (int i = 0; i < attempts; i++) {
                                    wait(jitter);
                                }
                            } catch (Exception e) {
                                attempts++;
                                delay = Math.min((long) (delay * multiplier + (Math.random() * jitter)), maxDelay);
                                Bukkit.getScheduler().runTaskLaterAsynchronously(main, this, delay / 50L);
                            }
                        } else {
                            new Message("Failed to create SQLite connection after 3 attempts.").setLoggerType(LoggerType.SEVERE).send();
                        }
                    }
                }, 0L, delay);
                tasks.add(task1);
                break;
            case MYSQL:
                // Create MySQL connection
                BukkitTask task2 = Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin) this, new Runnable() {

                    @Override
                    public void run() {
                        if (attempts < 3) {
                            try {
                                // Attempt to create MySQL connection
                                for (int i = 0; i < attempts; i++) {
                                    wait(jitter);
                                }
                            } catch (Exception e) {
                                attempts++;
                                delay = Math.min((long) (delay * multiplier + (Math.random() * jitter)), maxDelay);
                                Bukkit.getScheduler().runTaskLaterAsynchronously(main, this, delay / 50L);
                            }
                        } else {
                            new Message("Failed to create MySQL connection after 3 attempts.").setMessageType(MessageType.CONSOLE).send();
                        }
                    }
                }, 0L, delay);
                tasks.add(task2);
                break;
        }
    }

}
