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

    public static void createUserQuiz() {
        try (Statement statement = DBConnectorConfig.getConnection().createStatement()) {
            String query = "DROP SEQUENCE IF EXISTS userquiz_id_seq;\n" +
                    "CREATE SEQUENCE userquiz_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;" +
                    "CREATE TABLE IF NOT EXISTS \"userquiz\"(" +
                    "id INTEGER  DEFAULT nextval('userquiz_id_seq') PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "uuid VARCHAR(255)," +
                    "description VARCHAR(255)," +
                    "instructions VARCHAR(255)," +
                    "duration BIGINT," +
                    "attempts BIGINT," +
                    "difficulty VARCHAR(255)," +
                    "status BOOLEAN," +
                    "created_at Date," +
                    "user_id BIGINT NOT NULL," +
                    "quiz_id BIGINT NOT NULL," +
                    "CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES \"user\"(id) NOT DEFERRABLE," +
                    "CONSTRAINT fk_quiz FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id) NOT DEFERRABLE)";
            statement.executeUpdate(query);
            System.out.println("User Quiz table created successfully");
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
                    "correct BOOLEAN DEFAULT false,"+
                    "quizQuestion_id integer NOT NULL,"+
                    "CONSTRAINT fk_quizquestion FOREIGN KEY(quizQuestion_id) REFERENCES quizQuestion(id) NOT DEFERRABLE)";
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
                            "question TEXT NOT NULL," +
                            "CONSTRAINT fk_quiz FOREIGN KEY (quizId) REFERENCES quiz(quiz_id) NOT DEFERRABLE) ";
            statement.executeUpdate(query);
            System.out.println("createQuizQuestion table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createQuizTable(){
        try(Statement statement = DBConnectorConfig.getConnection().createStatement()){
            String query = "DROP SEQUENCE IF EXISTS quiz_id_seq;\n" +
                    "CREATE SEQUENCE quiz_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "CREATE TABLE IF NOT EXISTS quiz(" +
                    "id INTEGER DEFAULT nextval('quiz_id_seq') PRIMARY KEY," +
                    "uuid VARCHAR(255),"+
                    "name VARCHAR(255),"+
                    "description VARCHAR(255) ," +
                    "instructions VARCHAR(255),"+
                    "duration INTEGER,"+
                    "attempt INTEGER,"+
                    "difficulty VARCHAR(255),"+
                    "status BOOLEAN," +
                    "created_at DATE," +
                    "result_id integer NOT NULL,"+
                    "CONSTRAINT fk_result FOREIGN KEY(result_id) REFERENCES result(id) NOT DEFERRABLE)";
            statement.executeUpdate(query);
            System.out.println("Quiz table created successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void createUserTable() {
        try (Statement statement = DBConnectorConfig.getConnection().createStatement()) {
            String query = "DROP SEQUENCE IF EXISTS user_id_seq;\n" +
                    "CREATE SEQUENCE user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "CREATE TABLE IF NOT EXISTS \"user\"(" +
                    "id INTEGER DEFAULT nextval('user_id_seq') PRIMARY KEY," +
                    "fullname VARCHAR(255)," +
                    "username VARCHAR(255)," +
                    "email VARCHAR(255)," +
                    "password VARCHAR(255)," +
                    "uuid VARCHAR(255))";
            statement.executeUpdate(query);
            System.out.println("User table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

