package com.quiz.repository;
import com.quiz.config.*;
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
    public Result getResultByID(int id)
    {
        Result result = new Result();
        String query = "SELECT * FROM result WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query))
        {
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    result.setId(resultSet.getLong("id"));
                    result.setPoint(resultSet.getDouble("point"));
                    result.setQuizUuid(resultSet.getString("quiz_uuid"));
                    result.setUserUuid(resultSet.getString("user_uuid"));
                }else{
                    System.out.println("No result found with this ID: "+id);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Result> getAllResults(){
        List<Result> results = new ArrayList<>();
        String query = "SELECT * FROM results";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Result result = new Result();
                    result.setId(resultSet.getLong("id"));
                    result.setPoint(resultSet.getDouble("point"));
                    result.setQuizUuid(resultSet.getString("quiz_uuid"));
                    result.setUserUuid(resultSet.getString("user_uuid"));

                    // add result to the arraylist
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // return the fetched data from the Database
        return results;
    }

    public Result save(Result result) {
        String query = "INSERT INTO result (user_quiz_uuid, quiz_uuid, point, quiz_id) VALUES (?,?,?,?)";
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

    public void delete(long id) {
        String query = "DELETE FROM result WHERE id=?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public Result update(Result result, long id) {
        String query = "UPDATE result SET user_quiz_uuid=?, quiz_uuid=?, point=?, quiz_id=? WHERE id=?";

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
        return getResultByID((int) id);
    }

    public Result getResultUserByID(long userUuid)
    {
        Result result = new Result();
        String query = "SELECT * FROM result WHERE userUuid=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query))
        {
            statement.setInt(1, (int) userUuid);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    result.setId(resultSet.getLong("id"));
                    result.setPoint(resultSet.getDouble("point"));
                    result.setQuizUuid(resultSet.getString("quiz_uuid"));
                    result.setUserUuid(resultSet.getString("user_uuid"));
                }else{
                    System.out.println("No result found with this ID: "+userUuid);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

}
