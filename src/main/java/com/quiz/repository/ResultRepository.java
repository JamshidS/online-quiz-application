package com.quiz.repository;
import com.quiz.config.*;
import com.quiz.model.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultRepository {
    public Result getByID(int id)
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

    public List<Result> getAll(){
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

    // write save -> insert
    // write delete -> delete
    // write update -> set
    // write getResultByUserId -> inner join method

}
