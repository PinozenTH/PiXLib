package com.pinont.piXLib.gui;

import com.pinont.piXLib.PiXPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class MenuListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		final Player dude = (Player) event.getWhoClicked();
		final int slot = event.getSlot();

		System.out.println("Clicking in menu! Has metadata ? " + dude.hasMetadata("PiXLibMenu"));

		if (dude.hasMetadata("PiXLibMenu")) {
			final Menu menu = (Menu) dude.getMetadata("PiXLibMenu").get(0).value();


			System.out.println("Clicked " + slot + " in menu " + menu);

			if (menu != null) {
				for (final Button button : menu.getButtons()) {
					if (button.getSlot() == slot) {
						System.out.println("Found clickable slot " + button.getSlot() + " with item " + button.getItem());

						button.onClick(dude);
						event.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event, Plugin plugin) {
		final Player dude = (Player) event.getPlayer();

		if (dude.hasMetadata("PiXLibMenu")) {
			//final Menu menu = (Menu) dude.getMetadata("PiXLibMenu").get(0).value();
			//menu.onClose();

			System.out.println("Removing menu metadata.");
			dude.removeMetadata("PiXLibMenu", plugin);
		}
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event, Plugin plugin) {
		final Player player = event.getPlayer();
		if (player.hasMetadata("PiXLibMenu")) {
			player.removeMetadata("PiXLibMenu", plugin);
		}
	}
}
