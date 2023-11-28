package com.quiz.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.quiz.config.DBConfigData.*;

public class DBConnectorConfig {


    private static Connection connection; // static variable to store the connection

    public static void connect(){
        try{
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("Connected Successfully...");
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void closeConnection(){
        if (connection !=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
