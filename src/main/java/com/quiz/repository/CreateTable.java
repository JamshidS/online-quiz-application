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
                    "user_id BIGINT NOT NULL,"+
                    "quiz_id BIGINT NOT NULL,"+
                    "CONSTRAINT user_id FOREIGN KEY (id) REFERENCES person(id) NOT DEFERRABLE,"+
                    "CONSTRAİNT quiz_id FOREIGN KEY (id) REFERENCES quiz(id) NOT DEFERRABLE)";
            statement.executeUpdate(query);
            return "User Quiz table created successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error creating User Quiz table:" + e.getMessage();
        }
    }

    public static void createQuizMetaDataTable(){
        try(Statement statement = DBConnectorConfig.getConnection().createStatement()) {
            String query = "DROP SEQUENCE IF EXISTS quiz_meta_data_id_seq;\n" +
                    "CREATE SEQUENCE quiz_meta_data_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "CREATE TABLE IF NOT EXISTS quiz_meta_data(" +
                    "id INTEGER DEFAULT nextval('quiz_meta_data_id_seq') PRIMARY KEY," +
                    "option VARCHAR(255),"+
                    "correct BOOLEAN DEFAULT false,"+
                    "quizQuestion_id integer NOT NULL,"+
                    "CONSTRAINT fk_quizQuestion_id FOREIGN KEY(id) REFERENCES quizQuestion(id) NOT DEFERRABLE)";
            statement.executeUpdate(query);
            System.out.println("QuizMetaData table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createQuizQuestion(){
        try(Statement statement = DBConnectorConfig.getConnection().createStatement()) { // try with recourse
            String query =
                    "CREATE TABLE IF NOT EXISTS quizQuestion(" +
                    "createdDateTime Timestamp NOT NULL, " +
                    "id SERIAL PRIMARY KEY ,  " +
                    "quizId INT, " + // FOREIGN KEY IT WILL BE EDITED AFTER CREATING THE QUIZ CLASS
                    "uuid VARCHAR(255) NOT NULL, " +
                    "question TEXT NOT NULL ) ";
            statement.executeUpdate(query);
            System.out.println("createQuizQuestion table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void createQuizTable(){
        try(Statement statement = DBConnectorConfig.getConnection().createStatement()){
            String query = "DROP SEQUENCE IF EXISTS quiz_id CASCADE;\n" +
                    "CREATE SEQUENCE quiz_id INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "DROP TABLE IF EXISTS quiz;"+
                    "CREATE TABLE IF NOT EXISTS quiz(" +
                    "quiz_id INTEGER DEFAULT nextval('quiz_id') PRIMARY KEY," +
                    "quiz_uuid  UUID DEFAULT gen_random_uuid(), "+
                    "quiz_name VARCHAR(255),"+
                    "quiz_description VARCHAR(255) ," +
                    "quiz_instructions VARCHAR(255),"+
                    "quiz_duration INTEGER,"+
                    "quiz_attempt INTEGER,"+
                    "quiz_difficulty VARCHAR(255),"+
                    "quiz_status BOOLEAN," +
                    "quiz_created_at DATE)";


            statement.executeUpdate(query);
            System.out.println("Quiz table created successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void createResultTable(){
        // create result table here
        // you can take reference of other methods
    }
}

