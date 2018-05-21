package net.learningpath.logger.service

import net.learningpath.logger.model.entity.LogMessageKT
import net.learningpath.logger.repository.cassandra.LoggerRepositoryKT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * See Java version of this class
 * @see DatabaseLogHandler
 */
@Service
class DatabaseLogHandlerKT : LogHandlerKT {

    @Autowired
    private val loggerRepository: LoggerRepositoryKT? = null

    override fun putMessageInLog(logMessage: LogMessageKT) : Boolean {
        val savedLog = loggerRepository!!.save(logMessage)
        return savedLog != null
    }

}