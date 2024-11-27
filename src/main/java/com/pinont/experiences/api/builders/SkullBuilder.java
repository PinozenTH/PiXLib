package com.pinont.experiences.api.builders;

import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class SkullBuilder {

    private PlayerProfile playerProfile;
    private final ItemStack item;
    private final List<String> lore = new ArrayList<>();
    private String name;

    public SkullBuilder(ItemStack item) {
        this.item = item;
    }

    public SkullBuilder setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
        return this;
    }

    public SkullBuilder setOwner(OfflinePlayer player) {
        this.playerProfile = player.getPlayerProfile();
        return this;
    }

    public SkullBuilder setLore(String... lore) {
        this.lore.addAll(Arrays.asList(lore));
        return this;
    }

    public SkullBuilder setLore(List<String> lore) {
        this.lore.addAll(lore);
        return this;
    }

    public SkullBuilder addLore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public SkullBuilder setDisplayName(String name) {
        this.name = name;
        return this;
    }

    public ItemStack build() {
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        assert meta != null;
        if (playerProfile != null) meta.setOwnerProfile(playerProfile);
        if (!lore.isEmpty()) meta.setLore(lore);
        if (name != null) meta.setDisplayName(name);
        return new ItemBuilder(item).setItemMeta(meta).create();
    }

}
