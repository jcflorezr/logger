package net.learningpath.logger.service.loggertypes.factoryclasses;

import net.learningpath.logger.service.loggertypes.FileLogHandler;
import net.learningpath.logger.service.loggertypes.LogHandler;

public class FileLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new FileLogHandler();
    }
}
