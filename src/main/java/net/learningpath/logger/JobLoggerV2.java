package net.learningpath.logger;

import net.learningpath.logger.model.dto.LogReport;
import net.learningpath.logger.service.factoryclasses.LoggerType;
import net.learningpath.logger.model.dto.MessageTypes;

import java.util.List;

public interface JobLoggerV2 {

    List<LogReport> logMessage(String messageText, MessageTypes messageType, List<LoggerType> loggerTypes);

}
