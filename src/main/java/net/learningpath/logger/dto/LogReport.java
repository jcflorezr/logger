package net.learningpath.logger.dto;

import net.learningpath.logger.loggertypes.DatabaseLogHandler;
import net.learningpath.logger.loggertypes.FileLogHandler;
import net.learningpath.logger.loggertypes.LogHandler;

import java.util.Objects;

public class LogReport {

    private LogHandler logHandler;
    private boolean success;
    private String action;
    private String cause;

    public LogReport(LogHandler logHandler, boolean success) {
        this.logHandler = logHandler;
        this.success = success;
        setAction(this.logHandler);
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

    private void setAction(LogHandler logHandler) {
        if (logHandler instanceof DatabaseLogHandler) action = "logging into database";
        else if (logHandler instanceof FileLogHandler) action = "logging into file";
        else action = "logging into console";
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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
