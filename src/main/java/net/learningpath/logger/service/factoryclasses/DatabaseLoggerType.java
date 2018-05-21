package net.learningpath.logger.service.factoryclasses;

import net.learningpath.logger.service.DatabaseLogHandler;
import net.learningpath.logger.service.LogHandler;

/**
 * See Kotlin version of this class
 * @see net.learningpath.logger.service.LoggerTypeKT
 */
public class DatabaseLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new DatabaseLogHandler();
    }
}
