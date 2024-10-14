package com.pinont.piXLib.api.creator;

import com.pinont.piXLib.PiXLib;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ItemCreator {

    private final ItemStack item;
    private final ItemMeta meta = getMeta();
    @Getter
    private final PersistentDataContainer data = meta.getPersistentDataContainer();

    public ItemCreator(@NotNull ItemStack item) {
        this.item = item;
    }

    public ItemStack create() {
        item.setItemMeta(meta);
        return item;
    }

    public ItemCreator setDisplayName(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemCreator setLore(String... lore) {
        meta.setLore(java.util.Arrays.asList(lore));
        return this;
    }

    public ItemCreator setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemCreator setType(Material type) {
        item.setType(type);
        return this;
    }

    public ItemCreator setDurability(short durability) {
        item.setDurability(durability);
        return this;
    }

    public ItemCreator setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemCreator setCustomModelData(int data) {
        meta.setCustomModelData(data);
        return this;
    }

    public ItemCreator setCustomTag(String key, String value) {
        data.set(new NamespacedKey(PiXLib.getPlugin(), key), PersistentDataType.STRING, value);
        return this;
    }

    public ItemMeta getMeta() {
        return Objects.requireNonNull(item).getItemMeta();
    }

    public String getCustomTag(String key) {
        return data.get(new NamespacedKey(PiXLib.getPlugin(), key), PersistentDataType.STRING);
    }
}
