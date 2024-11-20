package com.pinont.experiences.api.gui;

import com.pinont.experiences.plugin.ExpPlugin;
import com.pinont.experiences.api.utils.Common;
import com.pinont.experiences.api.utils.enums.LoggerType;
import com.pinont.experiences.api.utils.enums.MenuSize;
import com.pinont.experiences.api.utils.texts.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Gui {

	private final List<Button> buttons = new ArrayList<>();
	private final List<Props> props = new ArrayList<>();

	private int size = 9 * 3;
	private String title = "Custom Menu";

	private final Gui parent;
	private boolean extraButtonsRegistered = false;

	public Gui() {
		this(null);
	}

	public Gui(@Nullable Gui parent) {
		this.parent = parent;
	}

	public final List<Button> getButtons() {
		return buttons;
	}
	public final List<Props> getProps() {
		return props;
	}

	protected final void addButton(Button button) {
		this.buttons.add(button);
	}
	protected final void addProp(Props prop) {
		this.props.add(prop);
	}

	protected final void setSize(int size) {
		this.size = size;
	}

	protected final void setSize(MenuSize size) { this.size = size.getSize(); }

	protected final void setTitle(String title) {
		this.title = title;
	}

	public final void displayTo(Player player) {
		final Inventory inventory = Bukkit.createInventory(player, this.size,
				Common.colorize(this.title));

		for (final Button button : this.buttons)
			inventory.setItem(button.getSlot(), button.getItem());
		for (final Props prop : this.props)
			for (int slot : prop.getSlot())
				inventory.setItem(slot, prop.getItem());

		if (this.parent != null && !this.extraButtonsRegistered) {
			this.extraButtonsRegistered = true;

			final Button returnBackButton = new Button(this.size - 1) {
				@Override
				public ItemStack getItem() {
					final ItemStack item = new ItemStack(Material.OAK_DOOR);
					final ItemMeta meta = item.getItemMeta();
                    assert meta != null;
                    meta.setDisplayName(ChatColor.WHITE + "Return Back");
					item.setItemMeta(meta);

					return item;
				}

				@Override
				public void onClick(Player player) {
					try {
						final Gui newMenuCreatorInstance = parent.getClass().getConstructor().newInstance();

						newMenuCreatorInstance.displayTo(player);

					} catch (final ReflectiveOperationException ex) {
						new Message(ex.getMessage()).setLoggerType(LoggerType.SEVERE).send();
					}
				}
			};

			this.buttons.add(returnBackButton);
			inventory.setItem(returnBackButton.getSlot(), returnBackButton.getItem());
		}

		if (player.hasMetadata("PiXLibMenu"))
			player.closeInventory();

		player.setMetadata("PiXLibMenu", new FixedMetadataValue(ExpPlugin.getPlugin(), this));

		player.openInventory(inventory);
	}
}
