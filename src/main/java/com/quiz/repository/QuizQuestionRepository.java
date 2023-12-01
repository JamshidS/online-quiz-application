package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.QuizQuestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class QuizQuestionRepository {

    public void addQuizQuestion(QuizQuestion quizQuestion){

        String query = "INSERT INTO quizquestion (uuid, quizId, question, createdDateTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){

            statement.setString(1,quizQuestion.getUuid());
            statement.setLong(2,quizQuestion.getQuizId());
            statement.setString(3,quizQuestion.getQuestion());
            statement.setTimestamp(4, Timestamp.valueOf(quizQuestion.getCreatedDateTime()));
            statement.executeUpdate();
            System.out.println("Question added successfully.");
        } catch (SQLException e){

            e.printStackTrace();
        }

    }

    public void  updateQuizQuestion(Long id, String newQuestion) {

        String query = "UPDATE quizquestion SET question = ? WHERE id = ?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {

            statement.setString(1,newQuestion);
            statement.setLong(2,id);

            int a = statement.executeUpdate();

            if (a>0){
                System.out.println("Updated successfully id:" + id);
            }else {
                System.out.println("No question matching the specified ID found. ID ="+ id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void  deleteQuizQuestion(Long id) {

        String query = "DELETE FROM QuizQuestion WHERE id = ?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {

            statement.setLong(1,id);

            int a = statement.executeUpdate();

            if (a>0){
                System.out.println("successfully deleted id:" + id);
            }else {
                System.out.println("No question matching the specified ID found. ID ="+ id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void getQuizQuestionByID(int id){

        String query = "SELECT * FROM quizquestion WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection()
                .prepareStatement(query)) {
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Long questionId = resultSet.getLong("id");
                    Long quizid=resultSet.getLong("quizid");
                    String uuid=resultSet.getString("uuid");
                    String question=resultSet.getString("question");
                    LocalDateTime createdDateTime=resultSet.getTimestamp("createdDateTime").toLocalDateTime();

                    System.out.println("ID: " + questionId + ", UUID: " + uuid + ", Quiz ID: " + quizid +
                            ", Question: " + question + ", Created At: " + createdDateTime);
                }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllQuizQuestion(){

        String query = "SELECT * FROM quizquestion";
        try(PreparedStatement statement = DBConnectorConfig.getConnection()
                .prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Long questionId = resultSet.getLong("id");
                    Long quizid=resultSet.getLong("quizid");
                    String uuid=resultSet.getString("uuid");
                    String question=resultSet.getString("question");
                    LocalDateTime createdDateTime=resultSet.getTimestamp("createdDateTime").toLocalDateTime();

                    System.out.println("ID: " + questionId + ", UUID: " + uuid + ", Quiz ID: " + quizid +
                            ", Question: " + question + ", Created At: " + createdDateTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}

