package net.learningpath.logger.model.entity

import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table

/**
 * See Java version of this class
 * @see LogMessages
 */
@Table("log_messages")
data class LogMessagesKT (
    @PrimaryKeyColumn(name = "year", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    val year: Int,

    @PrimaryKeyColumn(name = "month", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    val month: Int,

    @PrimaryKeyColumn(name = "day", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    val day: Int,

    @PrimaryKeyColumn(name = "hour", ordinal = 3, type = PrimaryKeyType.PARTITIONED)
    val hour: Int,

    @PrimaryKeyColumn(name = "minutes", ordinal = 4, type = PrimaryKeyType.PARTITIONED)
    val minutes: Int,

    @PrimaryKeyColumn(name = "seconds", ordinal = 5, type = PrimaryKeyType.PARTITIONED)
    val seconds: Int,

    @PrimaryKeyColumn(name = "milliseconds", ordinal = 6, type = PrimaryKeyType.PARTITIONED)
    val milliseconds: Int,

    @PrimaryKeyColumn(name = "sender", ordinal = 7, type = PrimaryKeyType.PARTITIONED)
    val sender: String,

    @PrimaryKeyColumn(name = "messagetype", ordinal = 8, type = PrimaryKeyType.PARTITIONED)
    val messageType: String,

    @Column("message")
    val message: String
)