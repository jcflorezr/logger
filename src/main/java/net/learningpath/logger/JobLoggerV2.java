package net.learningpath.logger;

import net.learningpath.logger.dto.LogReport;
import net.learningpath.logger.loggertypes.factoryclasses.LoggerType;
import net.learningpath.logger.messagetypes.MessageTypes;

import java.util.List;

public interface JobLoggerV2 {

    List<LogReport> logMessage(String messageText, MessageTypes messageType, List<LoggerType> loggerTypes);

}
