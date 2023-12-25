package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Quiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository {
    public Quiz addQuiz(Quiz quiz) {
        String query = "INSERT INTO quiz (quiz_uuid, quiz_name, quiz_description, quiz_instructions, quiz_duration, quiz_attempts, quiz_difficulty, quiz_status, quiz_created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, quiz.getUuid());
            statement.setString(2, quiz.getName());
            statement.setString(3, quiz.getDescription());
            statement.setString(4, quiz.getInstructions());
            statement.setInt(5, quiz.getDuration());
            statement.setInt(6, quiz.getAttempts());
            statement.setString(7, quiz.getDifficulty());
            statement.setBoolean(8, quiz.getStatus());
            statement.setDate(9, java.sql.Date.valueOf(quiz.getCreatedAt()));  // quiz.getCreatedAt() bir LocalDate olmalı.

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating quiz failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quiz.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating quiz failed, no ID obtained.");
                }
            }

            System.out.println("Quiz added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quiz;
    }
    public void updateQuiz(Quiz quiz) {
        String query = "UPDATE quiz SET quiz_uuid=?, quiz_name=?, quiz_description=?, quiz_instructions=?, quiz_duration=?, quiz_attempts=?, quiz_difficulty=?, quiz_status=?, quiz_created_at=? WHERE quiz_id=?";

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
            statement.setLong(10, quiz.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating quiz failed, no rows affected.");
            }

            System.out.println("Quiz updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuiz(long quizId) {
        String query = "DELETE FROM quiz WHERE quiz_id=?";

        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1, quizId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting quiz failed, no rows affected.");
            }

            System.out.println("Quiz deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT * FROM quiz";

        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(resultSet.getLong("quiz_id"));
                quiz.setUuid(resultSet.getString("quiz_uuid"));
                quiz.setName(resultSet.getString("quiz_name"));
                quiz.setDescription(resultSet.getString("quiz_description"));
                quiz.setInstructions(resultSet.getString("quiz_instructions"));
                quiz.setDuration(resultSet.getInt("quiz_duration"));
                quiz.setAttempts(resultSet.getInt("quiz_attempts"));
                quiz.setDifficulty(resultSet.getString("quiz_difficulty"));
                quiz.setStatus(resultSet.getBoolean("quiz_status"));
                quiz.setCreatedAt(resultSet.getDate("quiz_created_at").toLocalDate());

                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    public Quiz getQuizById(long id) {
        Quiz quiz = new Quiz();
        String query = "SELECT * FROM quiz WHERE quiz_id=?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    quiz.setId(resultSet.getLong("quiz_id"));
                    quiz.setUuid(resultSet.getString("quiz_uuid"));
                    quiz.setName(resultSet.getString("quiz_name"));
                    quiz.setDescription(resultSet.getString("quiz_description"));
                    quiz.setInstructions(resultSet.getString("quiz_instructions"));
                    quiz.setDuration(resultSet.getInt("quiz_duration"));
                    quiz.setAttempts(resultSet.getInt("quiz_attempts"));
                    quiz.setDifficulty(resultSet.getString("quiz_difficulty"));
                    quiz.setStatus(resultSet.getBoolean("quiz_status"));
                    quiz.setCreatedAt(resultSet.getDate("quiz_created_at").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return quiz;
    }


}
