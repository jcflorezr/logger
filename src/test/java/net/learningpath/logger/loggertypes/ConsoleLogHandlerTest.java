package net.learningpath.logger.loggertypes;

import net.learningpath.logger.dto.LoggingInfo;
import net.learningpath.logger.messagetypes.MessageTypes;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class ConsoleLogHandlerTest {

    // Happy path
    @Test
    public void putMessageInLog() {
        ConsoleLogHandler consoleLogHandler = new ConsoleLogHandler();
        LoggingInfo loggingInfo = createDummyLoggingInfo();
        assertTrue(consoleLogHandler.putMessageInLog(loggingInfo));
    }

    private LoggingInfo createDummyLoggingInfo() {
        LoggingInfo loggingInfo = new LoggingInfo("anyMsg", MessageTypes.ERROR);
        loggingInfo.setLogger(Logger.getLogger("anyNameForLogger"));
        return loggingInfo;
    }

}