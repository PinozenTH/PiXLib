package com.pinont.piXLib.api.menus;

import io.lumine.mythic.bukkit.utils.menu.ClickAction;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public abstract class Button {

    private final int slot;

    public Button(int slot) {
        this.slot = slot;
    }

    public final int getSlot() {
        return slot;
    }

    public abstract ItemStack getItem();

    public abstract void onClick(Player player);
}