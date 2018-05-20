package net.learningpath.logger;

import io.vavr.control.Option;
import io.vavr.control.Try;
import net.learningpath.logger.model.dto.LogReport;
import net.learningpath.logger.model.dto.LoggingInfo;
import net.learningpath.logger.exceptions.LoggerException;
import net.learningpath.logger.service.LogHandler;
import net.learningpath.logger.service.factoryclasses.DatabaseLoggerType;
import net.learningpath.logger.service.factoryclasses.LoggerType;
import net.learningpath.logger.model.dto.MessageTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class JobLoggerV2Impl implements JobLoggerV2 {

    private static final String LOGGER_NAME = "MyLog";
    private final Logger logger;

    private JobLoggerV2Impl() {
        logger = Logger.getLogger(LOGGER_NAME);
    }

    private static class SingletonForJobLogger {
        private SingletonForJobLogger() {}
        private static final JobLoggerV2Impl INSTANCE = new JobLoggerV2Impl();
    }

    public static JobLoggerV2Impl getInstance() {
        return SingletonForJobLogger.INSTANCE;
    }

    @Override
    public List<LogReport> logMessage(String messageText, MessageTypes messageType, List<LoggerType> loggerTypes) {
        return Option.of(messageText)
                .filter(message -> !message.isEmpty()).onEmpty(LoggerException::messageToLogIsEmpty)
                .map(message -> loggerTypes).getOrElse(new ArrayList<>())
                .parallelStream()
                .map(loggerType -> logMessageAccordingToLoggerType(messageText.trim(), messageType, loggerType))
                .collect(Collectors.toList());
    }

    private LogReport logMessageAccordingToLoggerType(String messageText, MessageTypes messageType, LoggerType loggerType) {
        LoggingInfo loggingInfo = new LoggingInfo(messageText, messageType);
        LogHandler logHandler = loggerType.getLoggerType();
        Option.of(loggerType)
            .filter(type -> (type instanceof DatabaseLoggerType))
            .onEmpty(() -> loggingInfo.setLogger(this.logger));
         return Try.of(() -> logHandler.putMessageInLog(loggingInfo))
            .toEither()
            .map(result -> LogReport.newLogReport(logHandler, result))
            .getOrElseGet(exception -> LogReport.newFailedLogReport(logHandler, exception));
    }

}
