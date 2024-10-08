package com.pinont.piXLib.apis;

import com.pinont.piXLib.utils.enums.LoggerType;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class PiXPlaceHolderExpansion extends PlaceholderExpansion {

    @Override
    @NotNull
    public abstract String getAuthor();

    @Override
    @NotNull
    public abstract String getIdentifier();

    @Override
    @NotNull
    public abstract String getVersion();

    @Override
    public abstract String getRequiredPlugin();

    @Override
    public abstract boolean canRegister();

    @Override
    public abstract String onRequest(OfflinePlayer player, @NotNull String params);
}