package com.quiz.repository;


import com.quiz.config.DBConnectorConfig;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    // this is an example and don't use this method.....
    public static void createPersonTable(){
        try(Statement statement = DBConnectorConfig.getConnection().createStatement()) { // try with recourse
            String query = "DROP SEQUENCE IF EXISTS person_id_seq;\n" +
                    "CREATE SEQUENCE person_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+// dizi
                    "CREATE TABLE IF NOT EXISTS person(" +
                    "id INTEGER DEFAULT nextval('person_id_seq') PRIMARY KEY," +
                    "name VARCHAR(255))"; // SQL
            statement.executeUpdate(query);
            System.out.println("Person table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String  createUserQuiz(){
        try (Statement statement = DBConnectorConfig.getConnection().createStatement()){
            String query = "DROP SEQUENCE IF EXISTS person_id_seq;\n" +
                    "CREATE SEQUENCE person_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "CREATE TABLE IF NOT EXISTS person("+
                    "id INTEGER  DEFAULT nextval('person_id_seq') PRIMARY KEY," +
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
            return "User Quiz table created successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error creating User Quiz table:" + e.getMessage();
        }
    }
}
