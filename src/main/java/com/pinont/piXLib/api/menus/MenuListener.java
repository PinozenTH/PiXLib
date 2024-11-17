package com.pinont.piXLib.api.menus;

import com.pinont.piXLib.PiXLib;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class MenuListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		final int slot = event.getSlot();

		if (player.hasMetadata("PiXLibMenu")) {
			final Menu menu = (Menu) player.getMetadata("PiXLibMenu").getFirst().value();

			if (menu != null) {
				for (final Button button : menu.getButtons()) {
					if (button.getSlot() == slot) {
						button.onClick(player);
						event.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		final Player player = (Player) event.getPlayer();

		if (player.hasMetadata("PiXLibMenu")) {
			player.removeMetadata("PiXLibMenu", PiXLib.getPlugin());
		}
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		if (player.hasMetadata("PiXLibMenu")) {
			player.removeMetadata("PiXLibMenu", PiXLib.getPlugin());
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		if (player.hasMetadata("PiXLibMenu")) {
			player.removeMetadata("PiXLibMenu", PiXLib.getPlugin());
		}
	}
}
