package com.pinont.experiences.api.interactable;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

@Getter
public abstract class Interaction {

    private final Set<InteractionType> interactions = new HashSet<>();

    public Interaction(Set<InteractionType> action) {
        this.interactions.addAll(action);
    }

    public Interaction(InteractionType action) {
        this.interactions.add(action);
    }

    public abstract void onInteract(Player player);

}
