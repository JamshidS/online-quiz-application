package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultRepository {

    private final QuizRepository quizRepository;

    public ResultRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    public Result save(Result result) {
        String query = "INSERT INTO result (user_uuid, quiz_uuid, point, quiz_id) VALUES (?,?,?,?)";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setString(1, result.getUserUuid());
            statement.setString(2, result.getQuizUuid());
            statement.setDouble(3, result.getPoint());
            statement.setLong(4, result.getQuiz().getId());

            statement.executeUpdate();

            System.out.println("Result added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;
    }

    public Result getResultById(long id) {
        String query = "SELECT * FROM result WHERE id=?";
        Result result = new Result();
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    result.setId(resultSet.getLong("id"));
                    result.setUserUuid(resultSet.getString("user_uuid"));
                    result.setQuizUuid(resultSet.getString("quiz_uuid"));
                    result.setPoint(resultSet.getDouble("point"));
                    result.setQuiz(quizRepository.getQuizById(resultSet.getLong("quiz_id")));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }

    public Result update(Result result, long id) {
        String query = "UPDATE result SET user_uuid=?, quiz_uuid=?, point=?, quiz_id=? WHERE id=?";

        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setString(1, result.getUserUuid());
            statement.setString(2, result.getQuizUuid());
            statement.setDouble(3, result.getPoint());
            statement.setLong(4, result.getQuiz().getId());
            statement.setLong(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return getResultById(id);
    }
}
