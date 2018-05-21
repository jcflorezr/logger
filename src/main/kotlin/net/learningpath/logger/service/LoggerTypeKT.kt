package net.learningpath.logger.service

/**
 * See Java version of this class
 * @see net.learningpath.logger.service.factoryclasses.LoggerType
 * @see net.learningpath.logger.service.factoryclasses.DatabaseLoggerType
 */
interface LoggerTypeKT {

    fun getLoggerType() : LogHandlerKT

}

class DatabaseLoggerTypeKT : LoggerTypeKT {

    override fun getLoggerType(): LogHandlerKT {
        return DatabaseLogHandlerKT()
    }

}