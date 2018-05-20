package net.learningpath.logger.service.factoryclasses;

import net.learningpath.logger.service.ConsoleLogHandler;
import net.learningpath.logger.service.LogHandler;

public class ConsoleLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new ConsoleLogHandler();
    }
}
