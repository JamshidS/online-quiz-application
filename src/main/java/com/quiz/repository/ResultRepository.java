package com.quiz.repository;

import com.quiz.config.DBConfigData;
import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultRepository {

    public Result getResultByID(int id) {

        Result result = new Result();

        String query = "SELECT * FROM result WHERE id=?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection()
                .prepareStatement(query)) {
            statement.setInt(1, id);
            // this is for logging
            System.out.println("Query: " + statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int resultId = resultSet.getInt("id");
                    Double point = resultSet.getDouble("point");
                    String userUuid = resultSet.getString("user_uuid");
                    String quizUuid = resultSet.getString("quiz_uuid");

                    result.setPoint(point);
                    result.setQuizUuid(quizUuid);
                    result.setUserUuid(userUuid);
                    result.setId((long) id);


                    /*System.out.println("ID: " + resultId);
                    System.out.println("Name: " + point);
                    System.out.println("User uuid: " + userUuid);
                    System.out.println("Quiz uuid: " + quizUuid);*/ //LOGGING
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Result save (Result result){
        String query = "INSERT INTO result (point,user_uuid,quiz_uuid) VALUES (?,?,?)";
        try(PreparedStatement statement= DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setDouble(1,result.getPoint());
            statement.setString(2,result.getUserUuid());
            statement.setString(3,result.getQuizUuid());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

        public Result update(Result result,int id){
        String query="UPDATE result SET point=?,user_uuid=?,quiz_uuid=? WHERE id=?";
        try(PreparedStatement statement=DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setDouble(1,result.getPoint());
            statement.setString(2,result.getUserUuid());
            statement.setString(3,result.getUserUuid());
            statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getResultByID(id);
    }

    public void delete(int id){
        String query = "DELETE FROM result WHERE id = ?";
        try(PreparedStatement statement=DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setInt(1,id);
            statement.executeUpdate();
            System.out.println("Successfully deleted");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    

}


