package com.pinont.experiences.api.utils.texts;

import com.pinont.experiences.api.utils.Common;
import com.pinont.experiences.api.utils.enums.TitleType;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

@Getter
public class Title {

    private final String message;
    private String subtitle = "";
    private int fadeIn = 20;
    private int stay = 60;
    private int fadeOut = 20;

    public Title(String message) {
        this.message = message;
    }

    public Title setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public Title setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public Title setStay(int stay) {
        this.stay = stay;
        return this;
    }

    public Title setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    public void sendTitle(Player player, TitleType titleType) {
        switch (titleType) {
            case TITLE:
                player.sendTitle(message, subtitle, fadeIn, stay, fadeOut);
                break;
            case SUBTITLE:
                player.sendTitle("", message, fadeIn, stay, fadeOut);
                break;
            case ACTIONBAR:
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(Common.colorize(message)));
                break;
        }
    }

}
