package net.learningpath.logger.loggertypes.factoryclasses;

import net.learningpath.logger.loggertypes.ConsoleLogHandler;
import net.learningpath.logger.loggertypes.LogHandler;

public class ConsoleLoggerType implements LoggerType {
    @Override
    public LogHandler getLoggerType() {
        return new ConsoleLogHandler();
    }
}
