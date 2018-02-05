package net.learningpath.logger.exceptions;

public class DatabaseException extends RuntimeException {

    private static final String CUSTOM_MESSAGE = "Error while connecting to the database: ";

    public DatabaseException(Exception e) {
        super(CUSTOM_MESSAGE + e);
    }

}
