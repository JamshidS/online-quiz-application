package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Quiz;
import com.quiz.model.QuizMetaData;
import com.quiz.model.QuizQuestion;
import com.quiz.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository {
    public Quiz save(Quiz quiz) {
        String query = "INSERT INTO quiz (uuid,name,description,instructions,duration,attempt,difficulty,status,created_at,result_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1, quiz.getUuid());
            statement.setString(2, quiz.getName());
            statement.setString(3, quiz.getDescription());
            statement.setString(4, quiz.getInstructions());
            statement.setInt(5, quiz.getDuration());
            statement.setInt(6, quiz.getAttempts());
            statement.setString(7, quiz.getDifficulty());
            statement.setBoolean(8, quiz.getStatus());
            statement.setDate(9, java.sql.Date.valueOf(quiz.getCreatedAt()));
            statement.setLong(10, quiz.getResult().getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return quiz;
    }

    public Quiz update(Quiz quiz, int id) {
        String query = "UPDATE quiz SET uuid=?, name=?, description=?, instructions=?, duration=?, attempts=?, difficulty=?, status=?, created_at=? result_id=? WHERE quiz_id=?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setString(1, quiz.getUuid());
            statement.setString(2, quiz.getName());
            statement.setString(3, quiz.getDescription());
            statement.setString(4, quiz.getInstructions());
            statement.setInt(5, quiz.getDuration());
            statement.setInt(6, quiz.getAttempts());
            statement.setString(7, quiz.getDifficulty());
            statement.setBoolean(8, quiz.getStatus());
            statement.setDate(9, java.sql.Date.valueOf(quiz.getCreatedAt()));
            statement.setLong(10,quiz.getResult().getId());
            statement.setInt(11, quiz.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(id);
    }

    public void delete(int id) {
        String query = "DELETE FROM quiz WHERE id = ?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setInt(1,id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                System.out.println("Quiz with ID " + id + " deleted successfully!");
            }else {
                System.out.println("Quiz with ID " + id + " not found.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Quiz> getAll() {
        Quiz quiz = new Quiz();
        List<Quiz> allQuiz = new ArrayList<>();
        String query = "SELECT * FROM quiz";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    quiz.setId(resultSet.getInt("id"));
                    quiz.setUuid(resultSet.getString("uuid"));
                    quiz.setName(resultSet.getString("name"));
                    quiz.setDescription(resultSet.getString("description"));
                    quiz.setInstructions(resultSet.getString("instructions"));
                    quiz.setDuration(resultSet.getInt("duration"));
                    quiz.setAttempts(resultSet.getInt("attempt"));
                    quiz.setDifficulty(resultSet.getString("difficulty"));
                    quiz.setStatus(resultSet.getBoolean("status"));
                    allQuiz.add(quiz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allQuiz;
    }

    public Quiz getById(int id) {
        Quiz quiz = new Quiz();
        String query = "SELECT * FROM quiz WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    quiz.setId(resultSet.getInt("id"));
                    quiz.setUuid(resultSet.getString("uuid"));
                    quiz.setName(resultSet.getString("name"));
                    quiz.setDescription(resultSet.getString("description"));
                    quiz.setInstructions(resultSet.getString("instructions"));
                    quiz.setDuration(resultSet.getInt("duration"));
                    quiz.setAttempts(resultSet.getInt("attempt"));
                    quiz.setDifficulty(resultSet.getString("difficulty"));
                    quiz.setStatus(resultSet.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }

}
