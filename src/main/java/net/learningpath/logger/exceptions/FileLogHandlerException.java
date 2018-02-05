package net.learningpath.logger.exceptions;

public class FileLogHandlerException extends RuntimeException {

    private static final String CUSTOM_MESSAGE = "Error while logging into a file: ";

    public FileLogHandlerException(Exception e) {
        super(CUSTOM_MESSAGE + e);
    }

}
