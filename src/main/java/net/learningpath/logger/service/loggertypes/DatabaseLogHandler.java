package net.learningpath.logger.service.loggertypes;

import net.learningpath.logger.model.dto.LoggingInfo;

public class DatabaseLogHandler implements LogHandler {

    private static final String STMT_STRING = "INSERT INTO Log_Values(?, ?)";

    @Override
    public boolean putMessageInLog(LoggingInfo loggingInfo) {
//        try {
//            DatabaseRepository connector = new MySqlRepository();
//            Connection connection = connector.getConnection();
//            PreparedStatement stmt = connection.prepareStatement(STMT_STRING);
//            String currentTimestampString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
//            String messageWithTimestamp = currentTimestampString + " " + loggingInfo.getMessage();
//            int id = loggingInfo.getMessageType().getId();
//            stmt.setString(1, messageWithTimestamp);
//            stmt.setInt(2, id);
//            int rowsAffected = stmt.executeUpdate();
//            return rowsAffected > 0 ? true : false;
//        } catch (Exception e) {
//            throw new DatabaseLogHandlerException(e);
//        }
        return false;
    }

}
