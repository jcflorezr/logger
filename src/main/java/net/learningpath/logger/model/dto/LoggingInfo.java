package net.learningpath.logger.model.dto;

import java.util.logging.Logger;

/**
 * See Kotlin version of this class
 * @see LoggingInfoKT
 */
public class LoggingInfo {

    private String message;
    private MessageTypes messageType;
    private Logger logger;

    public LoggingInfo(String message, MessageTypes messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public MessageTypes getMessageType() {
        return messageType;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
