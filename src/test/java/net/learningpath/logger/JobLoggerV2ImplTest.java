package net.learningpath.logger;

import net.learningpath.logger.dto.LogReport;
import net.learningpath.logger.exceptions.DatabaseLogHandlerException;
import net.learningpath.logger.exceptions.FileLogHandlerException;
import net.learningpath.logger.exceptions.LoggerException;
import net.learningpath.logger.loggertypes.ConsoleLogHandler;
import net.learningpath.logger.loggertypes.DatabaseLogHandler;
import net.learningpath.logger.loggertypes.FileLogHandler;
import net.learningpath.logger.loggertypes.factoryclasses.ConsoleLoggerType;
import net.learningpath.logger.loggertypes.factoryclasses.DatabaseLoggerType;
import net.learningpath.logger.loggertypes.factoryclasses.FileLoggerType;
import net.learningpath.logger.loggertypes.factoryclasses.LoggerType;
import net.learningpath.logger.messagetypes.MessageTypes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobLoggerV2ImplTest {

    @Mock
    private ConsoleLogHandler consoleLogHandlerMock;
    @Mock
    private FileLogHandler fileLogHandlerMock;
    @Mock
    private DatabaseLogHandler databaseLogHandlerMock;
    @Mock
    private ConsoleLoggerType consoleLoggerTypeMock;
    @Mock
    private FileLoggerType fileLoggerTypeMock;
    @Mock
    private DatabaseLoggerType databaseLoggerTypeMock;

    @InjectMocks
    private JobLoggerV2Impl jobLoggerV2;

    @Before
    public void setUp() {
        jobLoggerV2 = JobLoggerV2Impl.getInstance();
    }

    // Happy path
    @Test
    public void logMessageInAllTheAvailableWays() {
        when(fileLoggerTypeMock.getLoggerType()).thenReturn(fileLogHandlerMock);
        when(consoleLoggerTypeMock.getLoggerType()).thenReturn(consoleLogHandlerMock);
        when(databaseLoggerTypeMock.getLoggerType()).thenReturn(databaseLogHandlerMock);

        when(fileLogHandlerMock.putMessageInLog(any())).thenReturn(true);
        when(consoleLogHandlerMock.putMessageInLog(any())).thenReturn(true);
        when(databaseLogHandlerMock.putMessageInLog(any())).thenReturn(true);

        String message = "any message";
        MessageTypes messageType = MessageTypes.INFO;
        List<LoggerType> loggerTypes = asList(fileLoggerTypeMock, consoleLoggerTypeMock, databaseLoggerTypeMock);
        List<LogReport> actualLogReports = jobLoggerV2.logMessage(message, messageType, loggerTypes);

        assertThat(actualLogReports.size(), is(3));
        assertTrue(actualLogReports.contains(new LogReport(consoleLogHandlerMock, true)));
        assertTrue(actualLogReports.contains(new LogReport(fileLogHandlerMock, true)));
        assertTrue(actualLogReports.contains(new LogReport(databaseLogHandlerMock, true)));
        assertNull(actualLogReports.get(0).getCause());
        assertNotNull(actualLogReports.get(0).getAction());
        assertNotNull(actualLogReports.get(0).getLogHandler());
        assertNull(actualLogReports.get(1).getCause());
        assertNotNull(actualLogReports.get(1).getAction());
        assertNotNull(actualLogReports.get(1).getLogHandler());
        assertNull(actualLogReports.get(2).getCause());
        assertNotNull(actualLogReports.get(2).getAction());
        assertNotNull(actualLogReports.get(2).getLogHandler());
    }

    @Test
    public void unsuccessfulLoggingProcesses() {
        when(fileLoggerTypeMock.getLoggerType()).thenReturn(fileLogHandlerMock);
        when(databaseLoggerTypeMock.getLoggerType()).thenReturn(databaseLogHandlerMock);

        when(fileLogHandlerMock.putMessageInLog(any())).thenThrow(FileLogHandlerException.class);
        when(databaseLogHandlerMock.putMessageInLog(any())).thenThrow(DatabaseLogHandlerException.class);

        String message = "any message";
        MessageTypes messageType = MessageTypes.INFO;
        List<LoggerType> loggerTypes = asList(fileLoggerTypeMock, databaseLoggerTypeMock);
        List<LogReport> actualLogReports = jobLoggerV2.logMessage(message, messageType, loggerTypes);

        assertThat(actualLogReports.size(), is(2));
        assertTrue(actualLogReports.contains(new LogReport(fileLogHandlerMock, false)));
        assertTrue(actualLogReports.contains(new LogReport(databaseLogHandlerMock, false)));
        assertNotNull(actualLogReports.get(0).getCause());
        assertNotNull(actualLogReports.get(0).getAction());
        assertNotNull(actualLogReports.get(0).getLogHandler());
        assertNotNull(actualLogReports.get(1).getCause());
        assertNotNull(actualLogReports.get(1).getAction());
        assertNotNull(actualLogReports.get(1).getLogHandler());
    }

    @Test(expected = LoggerException.class)
    public void messageMustNotBeNull() {
        when(fileLoggerTypeMock.getLoggerType()).thenReturn(fileLogHandlerMock);
        when(databaseLoggerTypeMock.getLoggerType()).thenReturn(databaseLogHandlerMock);

        when(fileLogHandlerMock.putMessageInLog(any())).thenThrow(FileLogHandlerException.class);
        when(databaseLogHandlerMock.putMessageInLog(any())).thenThrow(DatabaseLogHandlerException.class);

        String message = null;
        MessageTypes messageType = MessageTypes.INFO;
        List<LoggerType> loggerTypes = asList(fileLoggerTypeMock, databaseLoggerTypeMock);
        jobLoggerV2.logMessage(message, messageType, loggerTypes);
    }

    @Test(expected = LoggerException.class)
    public void messageMustNotBeEmpty() {
        when(fileLoggerTypeMock.getLoggerType()).thenReturn(fileLogHandlerMock);
        when(databaseLoggerTypeMock.getLoggerType()).thenReturn(databaseLogHandlerMock);

        when(fileLogHandlerMock.putMessageInLog(any())).thenThrow(FileLogHandlerException.class);
        when(databaseLogHandlerMock.putMessageInLog(any())).thenThrow(DatabaseLogHandlerException.class);

        String message = "";
        MessageTypes messageType = MessageTypes.INFO;
        List<LoggerType> loggerTypes = asList(fileLoggerTypeMock, databaseLoggerTypeMock);
        jobLoggerV2.logMessage(message, messageType, loggerTypes);
    }

}