package net.learningpath.logger.loggertypes;

import net.learningpath.logger.model.dto.LoggingInfo;

public interface LogHandler {

    boolean putMessageInLog(LoggingInfo loggingInfo);
}
