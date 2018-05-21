package net.learningpath.logger.service

import net.learningpath.logger.model.entity.LogMessageKT

/**
 * See Java version of this class
 * @see LogHandler
 */
interface LogHandlerKT {

    fun putMessageInLog(logMessage: LogMessageKT): Boolean

}