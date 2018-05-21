package net.learningpath.logger.service;

import net.learningpath.logger.model.dto.LoggingInfo;

/**
 * See Kotlin version of this class
 * @see LogHandlerKT
 */
public interface LogHandler {

    boolean putMessageInLog(LoggingInfo loggingInfo);

}
