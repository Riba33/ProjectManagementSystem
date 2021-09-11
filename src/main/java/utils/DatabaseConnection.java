package utils;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection implements Closeable {
    private static final String URL = PropertiesLoader.getProperty("db.url");
    private static final String USERNAME = PropertiesLoader.getProperty("db.username");
    private static final String PASSWORD = PropertiesLoader.getProperty("db.password");
    private static final String JDBC_DRIVER = PropertiesLoader.getProperty("db.driver");

    private static DatabaseConnection databaseConnection;
    private Connection connection;

    @SneakyThrows
    private DatabaseConnection() {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @SneakyThrows
    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        } else if (databaseConnection.getConnection().isClosed()) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }
}
