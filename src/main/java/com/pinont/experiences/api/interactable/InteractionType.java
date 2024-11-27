package com.pinont.experiences.api.interactable;

import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;

public enum InteractionType {
    LEFT_CLICK,
    RIGHT_CLICK,
    MIDDLE_CLICK,
    DROP,
    DOUBLE_CLICK,
    SWAP_OFFHAND,
    NUMBER_KEY,
    CREATIVE,
    WINDOW_BORDER_LEFT,
    WINDOW_BORDER_RIGHT;

    public static InteractionType getInteractionType(ClickType interactionType) {
        return switch (interactionType) {
            case LEFT, SHIFT_LEFT -> LEFT_CLICK;
            case RIGHT, SHIFT_RIGHT -> RIGHT_CLICK;
            case MIDDLE -> MIDDLE_CLICK;
            case DROP, CONTROL_DROP -> DROP;
            case DOUBLE_CLICK -> DOUBLE_CLICK;
            case SWAP_OFFHAND -> SWAP_OFFHAND;
            case NUMBER_KEY -> NUMBER_KEY;
            case CREATIVE -> CREATIVE;
            case WINDOW_BORDER_LEFT -> WINDOW_BORDER_LEFT;
            case WINDOW_BORDER_RIGHT -> WINDOW_BORDER_RIGHT;
            default -> null;
        };
    }

    public static InteractionType getInteractionType(Action interactionType) {
        return switch (interactionType) {
            case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> LEFT_CLICK;
            case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> RIGHT_CLICK;
            case PHYSICAL -> DROP;
        };
    }
}
