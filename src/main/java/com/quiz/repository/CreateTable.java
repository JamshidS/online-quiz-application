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

    public static void createQuizMetaDataTable(){
        try(Statement statement = DBConnectorConfig.getConnection().createStatement()) {
            String query = "DROP SEQUENCE IF EXISTS quiz_meta_data_id_seq;\n" +
                    "CREATE SEQUENCE quiz_meta_data_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "CREATE TABLE IF NOT EXISTS quiz_meta_data(" +
                    "id INTEGER DEFAULT nextval('quiz_meta_data_id_seq') PRIMARY KEY," +
                    "option VARCHAR(255),"+
                    "correct BOOLEAN DEFAULT false)";
//                  "CONSTRAINT fk_quiz_question_id FOREIGN KEY(quiz_question_id) NOT DEFERRABLE)";
            statement.executeUpdate(query);
            System.out.println("QuizMetaData table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

