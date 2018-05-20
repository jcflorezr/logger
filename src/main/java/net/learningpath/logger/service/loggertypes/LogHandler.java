package net.learningpath.logger.service.loggertypes;

import net.learningpath.logger.model.dto.LoggingInfo;

public interface LogHandler {

    boolean putMessageInLog(LoggingInfo loggingInfo);
}
