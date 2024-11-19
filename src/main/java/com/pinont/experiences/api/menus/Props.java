package com.pinont.experiences.api.menus;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Props {

    private final List<Integer> slot = new ArrayList<>();

    public Props(int... slots) {
        for (int slot : slots) {
            this.slot.add(slot);
        }
    }

    public final List<Integer> getSlot() {
        return slot;
    }

    public abstract ItemStack getItem();
}