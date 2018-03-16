package net.learningpath.logger.database;

import java.sql.Connection;

public interface DatabaseRepository {

    Connection getConnection();

}
