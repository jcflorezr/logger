package net.learningpath.logger.service.loggertypes;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DatabaseLogHandler.class})
public class DatabaseLogHandlerTest {

//    @Mock
//    private MySqlRepository mySqlConnector;
//    @Mock
//    private Connection connection;
//    @Mock
//    private PreparedStatement stmt;
//
//    @InjectMocks
//    private DatabaseLogHandler databaseLogHandler;
//
//    @Before
//    public void setUp() {
//        databaseLogHandler = new DatabaseLogHandler();
//    }
//
//    // Happy path
//    @Test
//    public void putMessageInLog() throws Exception {
//        PowerMockito.whenNew(MySqlRepository.class).withNoArguments().thenReturn(mySqlConnector);
//        when(mySqlConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(anyString())).thenReturn(stmt);
//        when(stmt.executeUpdate()).thenReturn(1);
//        LoggingInfo loggingInfo = createDummyLoggingInfo();
//        boolean actual = databaseLogHandler.putMessageInLog(loggingInfo);
//        assertTrue(actual);
//    }
//
//    @Test
//    public void messageNotInsertedintoDatabase() throws Exception {
//        PowerMockito.whenNew(MySqlRepository.class).withNoArguments().thenReturn(mySqlConnector);
//        when(mySqlConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(anyString())).thenReturn(stmt);
//        when(stmt.executeUpdate()).thenReturn(0);
//        DatabaseLogHandler databaseLogHandler = new DatabaseLogHandler();
//        LoggingInfo loggingInfo = createDummyLoggingInfo();
//        boolean actual = databaseLogHandler.putMessageInLog(loggingInfo);
//        assertFalse(actual);
//    }
//
//    @Test(expected = DatabaseLogHandlerException.class)
//    public void shouldGetDatabaseLogHandlerException() throws Exception {
//        PowerMockito.whenNew(MySqlRepository.class).withNoArguments().thenReturn(mySqlConnector);
//        Exception exception = new Exception("dummy exception...");
//        when(mySqlConnector.getConnection()).thenThrow(new DatabaseException(exception));
//        DatabaseLogHandler databaseLogHandler = new DatabaseLogHandler();
//        LoggingInfo loggingInfo = createDummyLoggingInfo();
//        databaseLogHandler.putMessageInLog(loggingInfo);
//    }
//
//    private LoggingInfo createDummyLoggingInfo() {
//        return new LoggingInfo("anyMsg", MessageTypes.ERROR);
//    }
}