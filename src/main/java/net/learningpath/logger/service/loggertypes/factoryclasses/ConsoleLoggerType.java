package net.learningpath.logger.service.loggertypes.factoryclasses;

import net.learningpath.logger.service.loggertypes.ConsoleLogHandler;
import net.learningpath.logger.service.loggertypes.LogHandler;

public class ConsoleLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new ConsoleLogHandler();
    }
}
