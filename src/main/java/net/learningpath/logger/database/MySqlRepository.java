package net.learningpath.logger.database;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import net.learningpath.logger.exceptions.DatabaseException;


public class MySqlRepository implements DatabaseRepository {

    private static final String DATABASE_NAME = "dbms";
    private static final String SERVER_NAME = "serverName";
    private static final String PORT_NUMBER = "3306";
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";

    @Override
    public Connection getConnection() {
        try {
            DataSource dataSource = getMySQLDataSource();
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    private DataSource getMySQLDataSource() throws IOException {
        Properties props = getDatabaseProperties();
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(getDatabaseUrl(props));
        mysqlDS.setUser(props.getProperty(USER_NAME));
        mysqlDS.setPassword(props.getProperty(PASSWORD));
        return mysqlDS;
    }

    private Properties getDatabaseProperties() throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String fileLoggerConfigPath = rootPath + "database.properties";
        Properties fileLoggerProps = new Properties();
        fileLoggerProps.load(new FileInputStream(fileLoggerConfigPath));
        return fileLoggerProps;
    }

    private String getDatabaseUrl(Properties props) {
        String dbms = props.getProperty(DATABASE_NAME);
        String server = props.getProperty(SERVER_NAME);
        String port = props.getProperty(PORT_NUMBER);
        return "jdbc:" + dbms + "://" + server + ":" + port + "/";
    }
}
