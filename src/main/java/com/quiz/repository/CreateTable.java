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

    public static void createQuizMetaData(){
        try(Statement statement = DBConnectorConfig.getConnection().createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS quiz_meta_data(" +
                    "id SERIAL PRIMARY KEY," +
                    "option VARCHAR(255)," +
                    "correct BOOLEAN DEFAULT false)";
                    // Quiz class is not present yet.
            statement.executeUpdate(query);
            System.out.println("Quiz Meta Data table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
