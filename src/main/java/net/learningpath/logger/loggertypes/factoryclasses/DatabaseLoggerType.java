package net.learningpath.logger.loggertypes.factoryclasses;

import net.learningpath.logger.loggertypes.DatabaseLogHandler;
import net.learningpath.logger.loggertypes.LogHandler;

public class DatabaseLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new DatabaseLogHandler();
    }
}
