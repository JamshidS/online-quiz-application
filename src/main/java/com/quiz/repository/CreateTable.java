package com.quiz.repository;


import com.quiz.config.DBConnectorConfig;

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

    public static void createUserQuiz(){
        try (Statement statement = DBConnectorConfig.getConnection().createStatement()){
            String query = "CREATE TABLE IF NOT EXISTS user_quiz(" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(255))";
            statement.executeUpdate(query);
            System.out.println("User Quiz table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
