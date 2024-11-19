package com.pinont.experiences.api.utils.enums;

import lombok.Getter;

@Getter
public enum MenuSize {
    ONE_LINE(9),
    TWO_LINES(18),
    THREE_LINES(27),
    FOUR_LINES(36),
    FIVE_LINES(45),
    SIX_LINES(54);

    private final int size;

    MenuSize(int size) {
        this.size = size;
    }

}
