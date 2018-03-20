package net.learningpath.logger.loggertypes;

import io.vavr.Tuple;
import io.vavr.control.Option;
import io.vavr.control.Try;
import net.learningpath.logger.exceptions.FileLogHandlerException;
import net.learningpath.logger.messagetypes.MessageTypes;
import net.learningpath.logger.model.dto.LoggingInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class FileLogHandler implements LogHandler {

    private static final String FOLDER_PATH_PROPERTY = "logFileFolder";
    private static final String LOG_FILE_NAME_PROPERTY = "logFileName";
    private static final String EMPTY = "";

    @Override
    public boolean putMessageInLog(LoggingInfo loggingInfo) {
        Logger logger = Try.of(this::getPropertiesForLogging).onFailure(FileLogHandlerException::couldNotFindThePropertiesFile)
                .map(properties -> Tuple.of(properties.getProperty(FOLDER_PATH_PROPERTY), properties.getProperty(LOG_FILE_NAME_PROPERTY)))
                .mapTry(fileTupleString -> Tuple.of(new File(fileTupleString._1), new File(fileTupleString._1 + fileTupleString._2)))
                .andThen(fileTuple -> fileTuple._1.mkdirs())
                .andThenTry(fileTuple -> fileTuple._2.createNewFile()).onFailure(FileLogHandlerException::couldNotCreateTheLogFile)
                .mapTry(tuple -> new FileHandler(tuple._2.getPath())).onFailure(FileLogHandlerException::couldNotCreateTheLogHandler)
                .map(fileHandler -> Tuple.of(loggingInfo.getLogger(), fileHandler))
                .andThen(loggerTuple -> loggerTuple._1.addHandler(loggerTuple._2))
                .map(loggerTuple -> loggerTuple._1)
                .get();
        String messageWithTimestamp =
                Option.of(DateFormat.getDateInstance(DateFormat.LONG))
                .map(dateFormat -> dateFormat.format(new Date()))
                .map(currentTimestamp -> currentTimestamp + " -> " + loggingInfo.getMessage())
                .getOrElse(EMPTY);
        return Option.of(loggingInfo.getMessageType())
                .map(MessageTypes::getLevel)
                .peek(level -> logger.log(level, messageWithTimestamp))
                .map(level -> true)
                .getOrElse(false);
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
