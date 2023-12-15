package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRepository {

    public Person getPersonByID(int id){
        //todo: fetch person with id
        Person person = new Person();
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

                    person.setName(personName);
                    person.setId(personId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    // this is also an example
    // you can write your own code according to this
    public void getAllPersons(){ // assignment
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

    public Person save(Person person){
        String query = "INSERT INTO person (name) VALUES (?)"; // Insert
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setString(1,person.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public Person update(Person person,int id){
        String query = "UPDATE person SET name=? WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setString(1,person.getName());
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getPersonByID(id);
    }

}
