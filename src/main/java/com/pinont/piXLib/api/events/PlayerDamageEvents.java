package com.pinont.piXLib.api.events;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDamageEvents extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private boolean cancelled;

    public PlayerDamageEvents(Player player) {
        this.player = player;
    }

    public void setDamage(double damage) {
        player.setLastDamage(damage);
    }

    public Double getDamage() {
        return player.getLastDamage();
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public Player getPlayer() {
        return player;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
