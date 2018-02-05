package net.learningpath.logger.exceptions;

public class DatabaseLogHandlerException extends RuntimeException {

    private static final String CUSTOM_MESSAGE = "Error while logging into a database: ";

    public DatabaseLogHandlerException(Exception e) {
        super(CUSTOM_MESSAGE + e);
    }

}
