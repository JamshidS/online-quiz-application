package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.QuizMetaData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizMetaDataRepository {
    public QuizMetaData getQuizMetaDataByID(int id){
        QuizMetaData quizMetaData = new QuizMetaData();
        String query = "SELECT * FROM quiz_meta_data WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    quizMetaData.setId(resultSet.getInt("id"));
                    quizMetaData.setOption(resultSet.getString("option"));
                    quizMetaData.setCorrect(resultSet.getBoolean("correct"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizMetaData;
    }

    public List<QuizMetaData> getAllQuizMetaData() {
        QuizMetaData quizMetaData = new QuizMetaData();
        List<QuizMetaData> allQuizMetaData = new ArrayList<>();
        String query = "SELECT * FROM quiz_meta_data";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    quizMetaData.setId(resultSet.getInt("id"));
                    quizMetaData.setOption(resultSet.getString("option"));
                    quizMetaData.setCorrect(resultSet.getBoolean("correct"));
                    allQuizMetaData.add(quizMetaData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allQuizMetaData;
    }

    public QuizMetaData save(QuizMetaData quizMetaData){
        String query = "INSERT INTO quiz_meta_data (option,correct,quizquestion_id) VALUES (?,?,?)";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1,quizMetaData.getOption());
            statement.setBoolean(2,quizMetaData.isCorrect());
            statement.setLong(3,quizMetaData.getQuizQuestion().getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return quizMetaData;
    }

    public QuizMetaData update(QuizMetaData quizMetaData,int id){
        String query = "UPDATE quiz_meta_data SET option=?, correct=? WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1,quizMetaData.getOption());
            statement.setBoolean(2,quizMetaData.isCorrect());
            statement.setInt(3,id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return getQuizMetaDataByID(id);
    }

    public void delete(int id){
        String query = "DELETE FROM quiz_meta_data WHERE id = ?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setInt(1,id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                System.out.println("QuizMetaData with ID " + id + " deleted successfully!");
            }else {
                System.out.println("QuizMetaData with ID " + id + " not found.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
