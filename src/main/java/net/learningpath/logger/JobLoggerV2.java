package net.learningpath.logger;

import net.learningpath.logger.model.dto.LogReport;
import net.learningpath.logger.service.factoryclasses.LoggerType;
import net.learningpath.logger.model.dto.MessageTypes;

import java.util.List;

/**
 * See Kotlin version of this class
 * @see net.learningpath.logger.api.JobLoggerV2KT
 */
public interface JobLoggerV2 {

    List<LogReport> logMessage(String messageText, MessageTypes messageType, List<LoggerType> loggerTypes);

}
