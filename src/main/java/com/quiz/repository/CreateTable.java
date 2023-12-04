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
                    "name VARCHAR(255),"+
                    "uuid VARCHAR(255),"+
                    "description VARCHAR(255),"+
                    "instructions VARCHAR(255),"+
                    "duration BIGINT,"+
                    "attempts BIGINT,"+
                    "difficulty VARCHAR(255),"+
                    "status BOOLEAN,"+
                    "created_at Date,"+
                    "user_id BIGINT,"+
                    "quiz_id BIGINT)";
            statement.executeUpdate(query);
            System.out.println("User Quiz table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
