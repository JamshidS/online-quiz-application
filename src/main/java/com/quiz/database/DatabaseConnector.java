package com.quiz.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/yourdatabase";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void close(Connection connection) {
        if (connection != null) {
            
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

