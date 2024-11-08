package com.pinont.piXLib.api.events;

import com.pinont.piXLib.api.creator.EventCreator;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

@Getter
public class OpenEnchantingTable extends EventCreator implements Cancellable {

    private Player player;

    public OpenEnchantingTable(Player player) {
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
