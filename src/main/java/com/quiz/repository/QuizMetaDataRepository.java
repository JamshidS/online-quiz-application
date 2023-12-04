package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.QuizMetaData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuizMetaDataRepository {
    public QuizMetaData getQuizMetaDataByID(int id){
        String query = "SELECT * FROM quiz_meta_data WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    QuizMetaData quizMetaData = new QuizMetaData();
                    quizMetaData.setId(resultSet.getLong("id"));
                    quizMetaData.setOption(resultSet.getString("option"));
                    quizMetaData.setCorrect(resultSet.getBoolean("correct"));
                    //quizMetaData.setQuiz();

                    // is it true?
                    return quizMetaData;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // is this true?
        return null;
    }

    // return type?
    public List<QuizMetaData> getAllQuizMetaData() {
        List<QuizMetaData> allQuizMetaData = new ArrayList<>();
        String query = "SELECT * FROM quiz_meta_Data";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    QuizMetaData quizMetaData = new QuizMetaData();
                    quizMetaData.setId(resultSet.getLong("id"));
                    quizMetaData.setOption(resultSet.getString("option"));
                    quizMetaData.setCorrect(resultSet.getBoolean("correct"));
                    //quizMetaData.setQuiz();
                    allQuizMetaData.add(quizMetaData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allQuizMetaData;
    }

}
