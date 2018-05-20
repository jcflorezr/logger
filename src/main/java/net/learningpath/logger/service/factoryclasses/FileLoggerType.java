package net.learningpath.logger.service.factoryclasses;

import net.learningpath.logger.service.FileLogHandler;
import net.learningpath.logger.service.LogHandler;

public class FileLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new FileLogHandler();
    }
}
