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

    public static void createResultTable(){

        try (Statement statement = DBConnectorConfig.getConnection().createStatement()){
            String query= "DROP SEQUENCE IF EXISTS result_id_seq;\n" +
                    "CREATE SEQUENCE result_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;" +
                    "CREATE TABLE IF NOT EXISTS result(" +
                    "id INTEGER DEFAULT nextval('result_id_seq') PRIMARY KEY," +
                    "point REAL NOT NULL," +
                    "user_uuid VARCHAR(255) NOT NULL," +
                    "quiz_uuid VARCHAR(255) NOT NULL)" ;
            statement.executeUpdate(query);
            System.out.println("Result table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

    }

