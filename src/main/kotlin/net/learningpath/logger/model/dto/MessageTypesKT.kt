package net.learningpath.logger.model.dto

import java.util.logging.Level

/**
 * See Java version of this class
 * @see MessageTypes
 */
enum class MessageTypesKT(id: Int, level: Level) {

    INFO(1, Level.INFO),
    ERROR(2, Level.SEVERE),
    WARNING(3, Level.WARNING);

}