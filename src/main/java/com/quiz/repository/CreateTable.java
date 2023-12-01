package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    // this is an example and don't use this method.....
   public static void createPersonTable(){
        try(Statement statement = DBConnectorConfig.getConnection().createStatement()) { // try with recourse
            String query = "CREATE TABLE IF NOT EXISTS person(" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(255))"; // SQL
            statement.executeUpdate(query);
            System.out.println("Person table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUserTable() {
        try (
                Connection connection = DBConnectorConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            String query = "CREATE TABLE IF NOT EXISTS users(" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "full_name VARCHAR(255) NOT NULL," +
                    "user_name VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "uuid VARCHAR(36) NOT NULL)";
            statement.executeUpdate(query);
            System.out.println("User table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
