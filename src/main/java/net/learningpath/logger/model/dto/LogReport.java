package net.learningpath.logger.model.dto;

import net.learningpath.logger.loggertypes.DatabaseLogHandler;
import net.learningpath.logger.loggertypes.FileLogHandler;
import net.learningpath.logger.loggertypes.LogHandler;

import java.util.Objects;

public class LogReport {

    private LogHandler logHandler;
    private boolean success;
    private String action;
    private String errorType;
    private String errorMessage;
    private DetailedError errorDetails;

    private LogReport(LogHandler logHandler, boolean success) {
        this.logHandler = logHandler;
        this.success = success;
        setAction(this.logHandler);
    }

    private LogReport(LogHandler logHandler, Throwable cause) {
        this(logHandler, false);
        this.errorType = cause.getClass().getName();
        this.errorMessage = cause.getMessage();
        this.errorDetails = new DetailedError(cause);
    }

    public static LogReport newLogReport(LogHandler logHandler, boolean success) {
        return new LogReport(logHandler, success);
    }

    public static LogReport newFailedLogReport(LogHandler logHandler, Throwable cause) {
        return new LogReport(logHandler, cause);
    }

    public LogHandler getLogHandler() {
        return logHandler;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getAction() {
        return action;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public DetailedError getErrorDetails() {
        return errorDetails;
    }

    private void setAction(LogHandler logHandler) {
        if (logHandler instanceof DatabaseLogHandler) action = "logging into database";
        else if (logHandler instanceof FileLogHandler) action = "logging into file";
        else action = "logging into console";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogReport logReport = (LogReport) o;
        return success == logReport.success &&
                Objects.equals(logHandler, logReport.logHandler) &&
                Objects.equals(action, logReport.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logHandler, success, action);
    }
}
