package com.pinont.piXLib.api.creator;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class EventCreator extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Object eventObjective;

    public EventCreator(Object eventObjective) {
        this.eventObjective = eventObjective;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public void callEvent() {
        Bukkit.getPluginManager().callEvent(this);
    }
}
