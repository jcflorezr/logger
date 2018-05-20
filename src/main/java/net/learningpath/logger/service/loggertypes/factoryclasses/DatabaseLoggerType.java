package net.learningpath.logger.service.loggertypes.factoryclasses;

import net.learningpath.logger.service.loggertypes.DatabaseLogHandler;
import net.learningpath.logger.service.loggertypes.LogHandler;

public class DatabaseLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new DatabaseLogHandler();
    }
}
