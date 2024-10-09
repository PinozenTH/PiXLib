package com.pinont.piXLib.events;

import com.pinont.piXLib.api.events.PlayerDamageEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            PlayerDamageEvents playerDamageEvents = new PlayerDamageEvents((Player) event.getEntity());
            Bukkit.getPluginManager().callEvent(playerDamageEvents);

            if (playerDamageEvents.isCancelled()) {
                event.setCancelled(true);
            }
        }
    }

}
