package net.learningpath.logger.exceptions;

public class FileLogHandlerException extends RuntimeException {

    private FileLogHandlerException(String message, Throwable e) {
        super(message, e);
    }

    public static void couldNotFindThePropertiesFile(Throwable e) {
        throw new FileLogHandlerException("Error when trying to access the properties file to get the log file info.", e);
    }

    public static void couldNotCreateTheLogFile(Throwable e) {
        throw new FileLogHandlerException("Error when trying to create or access the log file.", e);
    }

    public static void couldNotCreateTheLogHandler(Throwable e) {
        throw new FileLogHandlerException("Error when trying to create the log file handler.", e);
    }
}
