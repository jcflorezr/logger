package net.learningpath.logger.loggertypes;

import net.learningpath.logger.dto.LoggingInfo;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogHandler implements LogHandler {

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
