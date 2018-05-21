package net.learningpath.logger.api

import net.learningpath.logger.model.dto.MessageTypesKT
import net.learningpath.logger.model.dto.ResponseKT
import net.learningpath.logger.model.entity.LogMessageKT
import net.learningpath.logger.service.LoggerTypeKT

/**
 * See Java version of this class
 * @see net.learningpath.logger.JobLoggerV2
 */
interface JobLoggerV2KT {

    fun logMessage(logMessage: LogMessageKT, messageType: MessageTypesKT, loggerTypes: List<LoggerTypeKT>): List<ResponseKT>

}