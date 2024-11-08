package com.pinont.piXLib.api.events;

import com.pinont.piXLib.api.creator.EventCreator;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

@Getter
public class EnchantingTableClickEvent extends EventCreator implements Cancellable {

    private Player player;
    private Inventory inventory;
    private int slot;
    private ClickType clickType;

    public EnchantingTableClickEvent(Player player, Inventory inventory, int slot, ClickType clickType) {
        super(player);
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {
        if (b) player.getOpenInventory().close();
    }
}
