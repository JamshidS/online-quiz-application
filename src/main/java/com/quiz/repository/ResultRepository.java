package com.quiz.repository;
import com.quiz.config.*;
import com.quiz.model.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultRepository {
    public Result getResultByID(int id)
    {
        Result result = new Result();
        String query = "SELECT * FROM result WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query))
        {
            statement.setInt(1,id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
