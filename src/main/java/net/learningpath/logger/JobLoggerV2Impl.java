package net.learningpath.logger;

import net.learningpath.logger.dto.LogReport;
import net.learningpath.logger.dto.LoggingInfo;
import net.learningpath.logger.exceptions.LoggerException;
import net.learningpath.logger.loggertypes.LogHandler;
import net.learningpath.logger.loggertypes.factoryclasses.DatabaseLoggerType;
import net.learningpath.logger.loggertypes.factoryclasses.LoggerType;
import net.learningpath.logger.messagetypes.MessageTypes;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class JobLoggerV2Impl implements JobLoggerV2 {

    private static final String LOGGER_NAME = "MyLog";
    private final Logger logger;

    private JobLoggerV2Impl() {
        logger = Logger.getLogger(LOGGER_NAME);
    }

    private static class SingletonForJobLogger {
        private static final JobLoggerV2Impl INSTANCE = new JobLoggerV2Impl();
    }

    public static JobLoggerV2Impl getInstance() {
        return SingletonForJobLogger.INSTANCE;
    }

    @Override
    public List<LogReport> logMessage(String messageText, MessageTypes messageType, List<LoggerType> loggerTypes) {
        Optional.ofNullable(messageText)
                .filter(message -> !message.isEmpty())
                .orElseThrow(() -> LoggerException.messageToLogIsEmpty());
        return loggerTypes.parallelStream()
                .map(loggerType -> logMessageAccordingToLoggerType(messageText.trim(), messageType, loggerType))
                .collect(Collectors.toList());
    }

    private LogReport logMessageAccordingToLoggerType(String messageText, MessageTypes messageType, LoggerType loggerType) {
        LoggingInfo loggingInfo = new LoggingInfo(messageText, messageType);
        Optional.of(loggerType)
                .filter(type -> !(type instanceof DatabaseLoggerType))
                .map(type -> logger)
                .ifPresent(loggerParam -> loggingInfo.setLogger(loggerParam));
        LogHandler logHandler = loggerType.getLoggerType();
        try {
            boolean success = logHandler.putMessageInLog(loggingInfo);
            return new LogReport(logHandler, success);
        } catch (Exception e) {
            LogReport logReport = new LogReport(logHandler, false);
            logReport.setCause(e.toString());
            return logReport;
        }
    }

}
