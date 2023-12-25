package com.quiz.service.impl;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.QuizQuestion;
import com.quiz.service.QuizService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class QuizServiceImpl implements QuizService {
    @Override
    public void addQuizQuestion(Long quizId, String question) {


        String query = "INSERT INTO quizquestion (uuid, quizId, question, createdDateTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){

        statement.setString(1,UUID.randomUUID().toString());
        statement.setLong(2,quizId);
        statement.setString(3,question);
        statement.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();
            System.out.println("Question added successfully.");
        } catch (SQLException e){

            e.printStackTrace();
            System.out.println("Question could not be added");
        }
    }

    @Override
    public QuizQuestion updateQuizQuestion(Long quizId, Long questionId, String newQuestion) {

        QuizQuestion updatedQuestion = null;

        String query = "UPDATE quizquestion SET question = ? WHERE id = ? and  quizid = ? RETURNING *";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {

            statement.setString(1, newQuestion);
            statement.setLong(2, questionId);
            statement.setLong(3, quizId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String uuid = resultSet.getString("uuid");
                Long quizId1 = resultSet.getLong("quizId");
                String questionText = resultSet.getString("question");
                LocalDateTime createdAt = resultSet.getTimestamp("createdDateTime").toLocalDateTime();

                updatedQuestion = new QuizQuestion();
                updatedQuestion.setId(id);
                updatedQuestion.setUuid(uuid);
                updatedQuestion.setQuizId(quizId1);
                updatedQuestion.setQuestion(questionText);
                updatedQuestion.setCreatedDateTime(createdAt);

                System.out.println("Updated successfully id:" + id);


            } else {
                System.out.printf("No question matching the specified ID found. QuizID= %1$d , QuestionID=%2$d", quizId, questionId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedQuestion;
    }

    @Override
    public void deleteQuizQuestion(Long quizID, Long questionId) {

        String query = "DELETE FROM QuizQuestion WHERE id = ? and quizid = ?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {

            statement.setLong(1,questionId);
            statement.setLong(2,quizID);

            int a = statement.executeUpdate();
            if (a>0){
                System.out.printf("successfully deleted QuestionID=%1$d, QuizID=%2$d ", questionId,quizID);
            }else {
                System.out.printf("No question matching the specified ID found. QuestionID=%1$d, QuizID=%2$d ", questionId,quizID );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public QuizQuestion getQuizQuestionByQuizId(Long listQuizId) {
        QuizQuestion quizQuestion = null;

        String query = "SELECT * FROM quizquestion WHERE quizid=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection()
                .prepareStatement(query)) {
            statement.setLong(1,listQuizId);
            try(ResultSet resultSet = statement.executeQuery()){
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
                }else{
                    System.out.println("No question matching the specified ID found. ID ="+ listQuizId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizQuestion;
    }

}


