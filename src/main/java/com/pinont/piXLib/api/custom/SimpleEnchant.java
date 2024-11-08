package com.pinont.piXLib.api.custom;

import org.bukkit.event.Listener;

public abstract class SimpleEnchant implements Listener {

    private final String name;
    private final int maxLevel;

    public SimpleEnchant(String name, int maxLevel) {
        super();
        this.name = name;
        this.maxLevel = maxLevel;
    }

    
}
