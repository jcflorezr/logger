package net.learningpath.logger.exceptions;

public class LoggerException extends RuntimeException {

    private static final String MESSAGE_IS_EMPTY = "Message to log cannot be null or empty.";
    private static final String NO_LOG_HANDLERS_SELECTED = "None of the available handlers were selected (console, file, database).";
    private static final String NO_MESSAGE_TYPE_SELECTED = "None of the available message types was selected (info, error, warning).";

    public LoggerException(String message) {
        super(message);
    }

    public static void messageToLogIsEmpty() {
        throw new LoggerException(MESSAGE_IS_EMPTY);
    }

    public static LoggerException noLogHandlersSelected() {
        return new LoggerException(NO_LOG_HANDLERS_SELECTED);
    }

    public static LoggerException noMessageTypeSelected() {
        return new LoggerException(NO_MESSAGE_TYPE_SELECTED);
    }
}
