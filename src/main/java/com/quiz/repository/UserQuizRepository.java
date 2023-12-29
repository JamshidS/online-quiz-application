package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.UserQuiz;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserQuizRepository {
    UserRepository userRepository = new UserRepository();
    QuizRepository quizRepository = new QuizRepository();

    public UserQuiz getUserQuizById(long id){
        UserQuiz userQuiz = new UserQuiz();
        String query = " SELECT * FROM user_quiz WHERE id=? ";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1,id);
            System.out.println("Query: " + statement.toString());
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){

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
                    userQuiz.setUser(userRepository.getUserById(resultSet.getLong("user_id")));
                    userQuiz.setQuiz(quizRepository.getQuizById(resultSet.getLong("quiz_id")));



                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userQuiz;
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
                    statement.setLong(10, userQuiz.getUser().getId());
                    statement.setLong(11, userQuiz.getQuiz().getId());

                    userQuizList.add(userQuiz);
                }
                return userQuizList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userQuizList;
    }


    public UserQuiz save(UserQuiz userQuiz){
        String query = "INSERT INTO person (name,uuid, description, instruction, duration, attempts, difficulty, status, created_at, user_id, quiz_id ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1, userQuiz.getName());
            statement.setString(2, userQuiz.getUuid());
            statement.setString(3, userQuiz.getDescription());
            statement.setString(4, userQuiz.getInstructions());
            statement.setInt(5, userQuiz.getDuration());
            statement.setInt(6, userQuiz.getAttempts());
            statement.setString(7, userQuiz.getDifficulty());
            statement.setBoolean(8, userQuiz.getStatus());
            statement.setDate(9, Date.valueOf(userQuiz.getCreatedAt()));
            statement.setLong(10, userQuiz.getUser().getId());
            statement.setLong(11, userQuiz.getQuiz().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userQuiz;
    }


    public UserQuiz update(UserQuiz userQuiz, long id) throws RuntimeException {
        String query = "UPDATE user_quiz SET name=?, uuid=?, description=?, instructions=?, duration=?, attempts=?, difficulty=?, status=?, created_at=?, user_id=?, quiz_id=? WHERE id=?";

        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1, userQuiz.getName());
            statement.setString(2, userQuiz.getUuid());
            statement.setString(3, userQuiz.getDescription());
            statement.setString(4, userQuiz.getInstructions());
            statement.setInt(5, userQuiz.getDuration());
            statement.setInt(6, userQuiz.getAttempts());
            statement.setString(7, userQuiz.getDifficulty());
            statement.setBoolean(8, userQuiz.getStatus());
            statement.setDate(9, Date.valueOf(userQuiz.getCreatedAt()));
            statement.setLong(10, userQuiz.getUser().getId());
            statement.setLong(11, userQuiz.getQuiz().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getUserQuizById(id);
    }

    public void delete(long id){
        String query = "DELETE FROM user_quiz WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}



