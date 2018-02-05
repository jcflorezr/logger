package net.learningpath.logger.database;

import java.sql.Connection;

public interface DatabaseConnector {

    Connection getConnection();

}
