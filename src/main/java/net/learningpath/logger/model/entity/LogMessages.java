package net.learningpath.logger.model.entity;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * See Kotlin version of this class
 * @see LogMessageKT
 */
@Table("log_messages")
public class LogMessages {

    @PrimaryKeyColumn(name = "year", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private int year;

    @PrimaryKeyColumn(name = "month", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private int month;

    @PrimaryKeyColumn(name = "day", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private int day;

    @PrimaryKeyColumn(name = "hour", ordinal = 3, type = PrimaryKeyType.PARTITIONED)
    private int hour;

    @PrimaryKeyColumn(name = "minutes", ordinal = 4, type = PrimaryKeyType.PARTITIONED)
    private int minutes;

    @PrimaryKeyColumn(name = "seconds", ordinal = 5, type = PrimaryKeyType.PARTITIONED)
    private int seconds;

    @PrimaryKeyColumn(name = "milliseconds", ordinal = 6, type = PrimaryKeyType.PARTITIONED)
    private int milliseconds;

    @PrimaryKeyColumn(name = "sender", ordinal = 7, type = PrimaryKeyType.PARTITIONED)
    private String sender;

    @PrimaryKeyColumn(name = "messagetype", ordinal = 8, type = PrimaryKeyType.PARTITIONED)
    private String messageType;

    @Column("message")
    private String message;

    public LogMessages(int year, int month, int day, int hour, int minutes, int seconds, int milliseconds, String sender, String messageType, String message) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.sender = sender;
        this.messageType = messageType;
        this.message = message;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageType() {
        return messageType;
    }
}
