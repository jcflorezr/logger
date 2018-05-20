package net.learningpath.logger;

import net.learningpath.logger.exceptions.LoggerException;
import net.learningpath.logger.service.factoryclasses.ConsoleLoggerType;
import net.learningpath.logger.service.factoryclasses.DatabaseLoggerType;
import net.learningpath.logger.service.factoryclasses.FileLoggerType;
import net.learningpath.logger.service.factoryclasses.LoggerType;
import net.learningpath.logger.model.dto.MessageTypes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class is no longer supported
 *
 * @deprecated use {@link JobLoggerV2} instead
 */
@Deprecated
public class JobLogger {

    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private static boolean logToDatabase;

    private static Supplier<Boolean> noLogHandlersSelected = () -> !logToConsole && !logToFile && !logToDatabase;
    private static Supplier<Boolean> noMessageTypeSelected = () -> !logError && !logMessage && !logWarning;

    public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam, boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap) {
        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
    }

    /**
     * This method is no longer supported. It might log the same
     * message with various types of message if "message", "warning"
     * and "error" params are set as true.
     *
     * @deprecated use {@link JobLoggerV2} instead
     * @see JobLoggerV2#logMessage(String, MessageTypes, List)
     */
    @Deprecated
    public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {
        if (noLogHandlersSelected.get()) {
            throw LoggerException.noLogHandlersSelected();
        }
        logMessage = message;
        logWarning = warning;
        logError = error;
        if (noMessageTypeSelected.get()) {
            throw LoggerException.noMessageTypeSelected();
        }
        JobLoggerV2 jobLoggerV2 = JobLoggerV2Impl.getInstance();

        List<MessageTypesChecker> messageTypesChecked = Arrays.asList(
                new MessageTypesChecker(message, MessageTypes.INFO),
                new MessageTypesChecker(error, MessageTypes.ERROR),
                new MessageTypesChecker(warning, MessageTypes.WARNING));

        List<MessageTypes> messageTypes = messageTypesChecked.stream()
                .filter(messageChecker -> messageChecker.isSelected())
                .map(messageChecker -> messageChecker.getMessageType())
                .collect(Collectors.toList());

        List<LoggerTypesChecker> loggerTypesChecked = Arrays.asList(
                new LoggerTypesChecker(logToFile, new FileLoggerType()),
                new LoggerTypesChecker(logToConsole, new ConsoleLoggerType()),
                new LoggerTypesChecker(logToDatabase, new DatabaseLoggerType()));

        List<LoggerType> loggerTypes = loggerTypesChecked.stream()
                .filter(logChecker -> logChecker.isSelected())
                .map(logChecker -> logChecker.getLoggerType())
                .collect(Collectors.toList());

        messageTypes
                .forEach(messageType -> jobLoggerV2.logMessage(messageText, messageType, loggerTypes));
    }

    private static class MessageTypesChecker {
        private boolean selected;
        private MessageTypes messageType;

        public MessageTypesChecker(boolean selected, MessageTypes messageType) {
            this.selected = selected;
            this.messageType = messageType;
        }

        public boolean isSelected() {
            return selected;
        }

        public MessageTypes getMessageType() {
            return messageType;
        }
    }

    private static class LoggerTypesChecker {
        private boolean selected;
        private LoggerType loggerType;

        public LoggerTypesChecker(boolean selected, LoggerType loggerType) {
            this.selected = selected;
            this.loggerType = loggerType;
        }

        public boolean isSelected() {
            return selected;
        }

        public LoggerType getLoggerType() {
            return loggerType;
        }
    }

}
