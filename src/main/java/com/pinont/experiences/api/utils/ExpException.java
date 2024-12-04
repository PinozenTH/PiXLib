package com.pinont.experiences.api.utils;

import com.pinont.experiences.api.utils.enums.LoggerType;
import com.pinont.experiences.api.utils.texts.Message;

public class ExpException extends Exception {
    public ExpException(String message) {
        new Message().sendLogger(message, LoggerType.SEVERE);
    }

    public ExpException(Throwable t) {
        new Message().sendLogger(t.getMessage(), LoggerType.SEVERE);
    }
}
