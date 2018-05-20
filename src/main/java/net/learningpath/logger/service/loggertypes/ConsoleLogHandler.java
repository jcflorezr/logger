package net.learningpath.logger.service.loggertypes;

import net.learningpath.logger.model.dto.LoggingInfo;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Log Handler is no longer supported
 *
 * @deprecated use {@link DatabaseLogHandler} or {@link FileLogHandler} instead
 */
@Deprecated
public class ConsoleLogHandler implements LogHandler {

    /**
     * This method is no longer supported. It is meaningless to log messages
     * in the console of the server which is containing this logger microservice,
     * because all the incoming messages are from external services that have
     * nothing to do with the logic of this logger microservice.
     *
     * @deprecated use use {@link DatabaseLogHandler} or {@link FileLogHandler} instead
     * @see DatabaseLogHandler#putMessageInLog(LoggingInfo)
     * @see FileLogHandler#putMessageInLog(LoggingInfo)
     */
    @Deprecated
    @Override
    public boolean putMessageInLog(LoggingInfo loggingInfo) {
        ConsoleHandler ch = new ConsoleHandler();
        String currentTimestampString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        String messageWithTimestamp = currentTimestampString + " " + loggingInfo.getMessage();
        Logger logger = loggingInfo.getLogger();
        logger.addHandler(ch);
        Level level = loggingInfo.getMessageType().getLevel();
        logger.log(level, messageWithTimestamp);
        return true;
    }

}
