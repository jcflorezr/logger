package net.learningpath.logger.loggertypes;

import net.learningpath.logger.dto.LoggingInfo;
import net.learningpath.logger.exceptions.FileLogHandlerException;
import net.learningpath.logger.messagetypes.MessageTypes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileLogHandler.class})
public class FileLogHandlerTest {

    @Mock
    private Properties fileLoggerProps;
    @Mock
    private FileInputStream fileInputStream;
    @Mock
    private File file;
    @Mock
    private FileHandler fileHandler;
    @Mock
    private Logger logger;

    @InjectMocks
    private FileLogHandler fileLogHandler;

    @Before
    public void setUp() {
        fileLogHandler = new FileLogHandler();
    }

    // Happy path
    @Test
    public void putMessageInLog() throws Exception {
        PowerMockito.whenNew(FileInputStream.class).withAnyArguments().thenReturn(fileInputStream);
        PowerMockito.whenNew(Properties.class).withNoArguments().thenReturn(fileLoggerProps);
        PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
        PowerMockito.whenNew(FileHandler.class).withAnyArguments().thenReturn(fileHandler);
        doNothing().when(fileLoggerProps).load(fileInputStream);
        when(file.exists()).thenReturn(true);
        doNothing().when(logger).addHandler(fileHandler);
        doNothing().when(logger).log(any(), anyString());
        LoggingInfo loggingInfo = createDummyLoggingInfo();
        boolean actual = fileLogHandler.putMessageInLog(loggingInfo);
        assertTrue(actual);
    }

    @Test(expected = FileLogHandlerException.class)
    public void shouldThrowFileLogHandlerException() throws Exception {
        PowerMockito.whenNew(FileInputStream.class).withAnyArguments().thenReturn(fileInputStream);
        PowerMockito.whenNew(Properties.class).withNoArguments().thenReturn(fileLoggerProps);
        PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
        doNothing().when(fileLoggerProps).load(fileInputStream);
        when(file.exists()).thenReturn(false);
        when(file.createNewFile()).thenThrow(IOException.class);
        LoggingInfo loggingInfo = createDummyLoggingInfo();
        fileLogHandler.putMessageInLog(loggingInfo);
    }

    private LoggingInfo createDummyLoggingInfo() {
        LoggingInfo loggingInfo = new LoggingInfo("anyMsg", MessageTypes.ERROR);
        loggingInfo.setLogger(mock(Logger.class));
        return loggingInfo;
    }

}