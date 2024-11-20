package com.pinont.experiences.api.gui;

import com.pinont.experiences.plugin.ExpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MenuListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		final int slot = event.getSlot();
		final Gui menuCreator = (Gui) player.getMetadata("PiXLibMenu").getFirst().value();
		if (menuCreator != null) {
			for (final Button button : menuCreator.getButtons()) {
				if (button.getSlot() == slot) {
					button.onClick(player);
					event.setCancelled(true);
				}
			}

			for (final Props prop : menuCreator.getProps()) {
				if (prop.getSlot().contains(slot)) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		final Player player = (Player) event.getPlayer();

		if (player.hasMetadata("PiXLibMenu")) {
			player.removeMetadata("PiXLibMenu", ExpPlugin.getPlugin());
		}
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		if (player.hasMetadata("PiXLibMenu")) {
			player.removeMetadata("PiXLibMenu", ExpPlugin.getPlugin());
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		if (player.hasMetadata("PiXLibMenu")) {
			player.removeMetadata("PiXLibMenu", ExpPlugin.getPlugin());
		}
	}
}
