package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.UserQuiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserQuizRepository {

    public UserQuiz getUserQuizById(long id){
        String query = " SELECT * FROM user_quiz WHERE id=? ";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1,id);
            System.out.println("Query: " + statement.toString());
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    UserQuiz userQuiz = new UserQuiz();
                    userQuiz.setId(resultSet.getLong("id"));
                    userQuiz.setName(resultSet.getString("name"));
                    userQuiz.setUuid(resultSet.getString("uuid"));
                    userQuiz.setDescription(resultSet.getString("description"));
                    userQuiz.setInstructions(resultSet.getString("instructions"));
                    userQuiz.setDuration(resultSet.getInt("duration"));
                    userQuiz.setAttempts(resultSet.getInt("attempts"));
                    userQuiz.setDifficulty(resultSet.getString("difficulty"));
                    userQuiz.setStatus(resultSet.getBoolean("status"));
                    userQuiz.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
                    //userQuiz.setUser();  I want to use getUserById()
                    //userQuiz.setQuiz();  I want to use getQuizById()

                    return userQuiz;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<UserQuiz> getAllUserQuizs(){
        String query = "SELECT * FROM user_quiz";
        List<UserQuiz> userQuizList = new ArrayList<>();


        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    UserQuiz userQuiz = new UserQuiz();
                    userQuiz.setId(resultSet.getLong("id"));
                    userQuiz.setName(resultSet.getString("name"));
                    userQuiz.setUuid(resultSet.getString("uuid"));
                    userQuiz.setDescription(resultSet.getString("description"));
                    userQuiz.setInstructions(resultSet.getString("instructions"));
                    userQuiz.setDuration(resultSet.getInt("duration"));
                    userQuiz.setAttempts(resultSet.getInt("attempts"));
                    userQuiz.setDifficulty(resultSet.getString("difficulty"));
                    userQuiz.setStatus(resultSet.getBoolean("status"));
                    userQuiz.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
                    //userQuiz.setUser();  I want to use getUserById()
                    //userQuiz.setQuiz();  I want to use getQuizById()

                    userQuizList.add(userQuiz);
                }
                return userQuizList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}



