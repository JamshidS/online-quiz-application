package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultRepository {

    private final QuizRepository quizRepository;

    public ResultRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    public Result save(Result result) {
        String query = "INSERT INTO result (user_quiz_uuid, quiz_uuid, point, quiz_id) VALUES (?,?,?,?)";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setString(1, result.getUserQuizUuid());
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
                    result.setUserQuizUuid(resultSet.getString("user_quiz_uuid"));
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
        String query = "UPDATE result SET user_quiz_uuid=?, quiz_uuid=?, point=?, quiz_id=? WHERE id=?";

        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setString(1, result.getUserQuizUuid());
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

    public List<Result> getAllResults() {
        String query = "SELECT * FROM result";
        List<Result> resultList = new ArrayList<>();

        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Result result = new Result();
                    result.setId(resultSet.getLong("id"));
                    result.setUserQuizUuid(resultSet.getString("user_quiz_uuid"));
                    result.setQuizUuid(resultSet.getString("quiz_uuid"));
                    result.setPoint(resultSet.getDouble("point"));
                    result.setQuiz(quizRepository.getQuizById(resultSet.getLong("quiz_id")));

                    resultList.add(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultList;
    }


    public void delete(long id) {
        String query = "DELETE FROM result WHERE id=?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
