package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    // this is an example code do not run this...
    public void getPersonByID(int id){
        //todo: fetch person with id

        // query to fetch person with id
        String query = "SELECT * FROM person WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection()
                .prepareStatement(query)) {
            statement.setInt(1,id);
            // this is for logging
            System.out.println("Query: "+statement.toString());
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    int personId = resultSet.getInt("id");
                    String personName = resultSet.getString("name");

                    System.out.println("ID: "+personId);
                    System.out.println("Name: "+personName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // this is also an example
    // you can write your own code according to this
    public void getAllPersons(){
        String query = "SELECT * FROM person";
        try(PreparedStatement statement = DBConnectorConfig.getConnection()
                .prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    int personId = resultSet.getInt("id");
                    String personName = resultSet.getString("name");

                    System.out.println("ID: "+personId);
                    System.out.println("Name: "+personName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByID(int id){
        //todo: write JDBC codes here
        return null;
    }
}
