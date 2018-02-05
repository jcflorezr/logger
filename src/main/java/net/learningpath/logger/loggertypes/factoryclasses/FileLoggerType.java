package net.learningpath.logger.loggertypes.factoryclasses;

import net.learningpath.logger.loggertypes.FileLogHandler;
import net.learningpath.logger.loggertypes.LogHandler;

public class FileLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new FileLogHandler();
    }
}
