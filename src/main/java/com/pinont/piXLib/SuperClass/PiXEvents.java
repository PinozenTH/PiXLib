package com.pinont.piXLib.SuperClass;

import org.bukkit.event.Listener;

public abstract class PiXEvents implements Listener {
    public final Boolean hidden;
    public PiXEvents(Boolean hide) {
        this.hidden = hide;
    }

    public Boolean isHidden() {
        return hidden;
    }
}
