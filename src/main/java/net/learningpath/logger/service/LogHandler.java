package net.learningpath.logger.service;

import net.learningpath.logger.model.dto.LoggingInfo;

public interface LogHandler {

    boolean putMessageInLog(LoggingInfo loggingInfo);
}
