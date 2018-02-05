package net.learningpath.logger.loggertypes;

import net.learningpath.logger.dto.LoggingInfo;
import net.learningpath.logger.exceptions.FileLogHandlerException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLogHandler implements LogHandler {

    private static final String FOLDER_PATH = "logFileFolder";
    private static final String LOG_FILE_NAME = "/logFile.txt";

    @Override
    public boolean putMessageInLog(LoggingInfo loggingInfo) {
        try {
            Properties props = getPropertiesForLogging();
            String fileNameWithPath = props.getProperty(FOLDER_PATH) + LOG_FILE_NAME;
            File file = new File(fileNameWithPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileHandler fh = new FileHandler(fileNameWithPath);
            Logger logger = loggingInfo.getLogger();
            logger.addHandler(fh);
            Level level = loggingInfo.getMessageType().getLevel();
            String currentTimestampString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
            String messageWithTimestamp = currentTimestampString + " " + loggingInfo.getMessage();
            logger.log(level, messageWithTimestamp);
            return true;
        } catch (IOException e) {
            throw new FileLogHandlerException(e);
        }
    }

    private Properties getPropertiesForLogging() throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String fileLoggerConfigPath = rootPath + "file-logger.properties";
        Properties fileLoggerProps = new Properties();
        FileInputStream fileInputStream = new FileInputStream(fileLoggerConfigPath);
        fileLoggerProps.load(fileInputStream);
        return fileLoggerProps;
    }
}
