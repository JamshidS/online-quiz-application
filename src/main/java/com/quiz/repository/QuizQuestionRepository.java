package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.QuizQuestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestionRepository {

    public QuizQuestion addQuizQuestion(QuizQuestion quizQuestion) {

        String query = "INSERT INTO quizquestion (uuid, quizId, question, createdDateTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {

            statement.setString(1, quizQuestion.getUuid());
            statement.setLong(2, quizQuestion.getQuizId());
            statement.setString(3, quizQuestion.getQuestion());
            statement.setTimestamp(4, Timestamp.valueOf(quizQuestion.getCreatedDateTime()));
            statement.executeUpdate();
            System.out.println("Question added successfully.");
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return quizQuestion;
    }

    public QuizQuestion updateQuizQuestion(Long id, String newQuestion) {

        QuizQuestion updatedQuestion = null;

        String query = "UPDATE quizquestion SET question = ? WHERE id = ? RETURNING *";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {

            statement.setString(1, newQuestion);
            statement.setLong(2, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long id1 = resultSet.getLong("id");
                String uuid = resultSet.getString("uuid");
                Long quizId = resultSet.getLong("quizId");
                String questionText = resultSet.getString("question");
                LocalDateTime createdAt = resultSet.getTimestamp("createdDateTime").toLocalDateTime();

                updatedQuestion = new QuizQuestion();
                updatedQuestion.setId(id1);
                updatedQuestion.setUuid(uuid);
                updatedQuestion.setQuizId(quizId);
                updatedQuestion.setQuestion(questionText);
                updatedQuestion.setCreatedDateTime(createdAt);

                System.out.println("Updated successfully id:" + id);


            } else {
                System.out.println("No question matching the specified ID found. ID =" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedQuestion;
    }

    public void deleteQuizQuestion(Long id) {

        String query = "DELETE FROM QuizQuestion WHERE id = ?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {

            statement.setLong(1, id);

            int a = statement.executeUpdate();

            if (a > 0) {
                System.out.println("successfully deleted id:" + id);
            } else {
                System.out.println("No question matching the specified ID found. ID =" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public QuizQuestion getQuizQuestionByID(Long id) {

        QuizQuestion quizQuestion = null;

        String query = "SELECT * FROM quizquestion WHERE id=?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection()
                .prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id1 = resultSet.getLong("id");
                    String uuid = resultSet.getString("uuid");
                    Long quizId = resultSet.getLong("quizId");
                    String questionText = resultSet.getString("question");
                    LocalDateTime createdDateTime = resultSet.getTimestamp("createdDateTime").toLocalDateTime();

                    quizQuestion = new QuizQuestion();
                    quizQuestion.setId(id1);
                    quizQuestion.setUuid(uuid);
                    quizQuestion.setQuizId(quizId);
                    quizQuestion.setQuestion(questionText);
                    quizQuestion.setCreatedDateTime(createdDateTime);
                } else {
                    System.out.println("No question matching the specified ID found. ID =" + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizQuestion;
    }

    public List<QuizQuestion> getAllQuizQuestion() {
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        String query = "SELECT * FROM quizquestion";
        try (PreparedStatement statement = DBConnectorConfig.getConnection()
                .prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long questionId = resultSet.getLong("id");
                    Long quizId = resultSet.getLong("quizId");
                    String uuid = resultSet.getString("uuid");
                    String question = resultSet.getString("question");
                    LocalDateTime createdDateTime = resultSet.getTimestamp("createdDateTime").toLocalDateTime();

                    QuizQuestion quizQuestion = new QuizQuestion();
                    quizQuestion.setId(questionId);
                    quizQuestion.setUuid(uuid);
                    quizQuestion.setQuizId(quizId);
                    quizQuestion.setQuestion(question);
                    quizQuestion.setCreatedDateTime(createdDateTime);

                    quizQuestions.add(quizQuestion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizQuestions;
    }

    public List<QuizQuestion> getAllQuestionByQuizID(Long quizId) {
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        String query = "SELECT * FROM public.QuizQuestion WHERE quizid = ?";

        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1, quizId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long questionId = resultSet.getLong("id");
                    String uuid = resultSet.getString("uuid");
                    String question = resultSet.getString("question");
                    LocalDateTime createdDateTime = resultSet.getTimestamp("createdDateTime").toLocalDateTime();

                    QuizQuestion quizQuestion = new QuizQuestion();
                    quizQuestion.setId(questionId);
                    quizQuestion.setUuid(uuid);
                    quizQuestion.setQuizId(quizId);
                    quizQuestion.setQuestion(question);
                    quizQuestion.setCreatedDateTime(createdDateTime);

                    quizQuestions.add(quizQuestion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizQuestions;
    }


}

