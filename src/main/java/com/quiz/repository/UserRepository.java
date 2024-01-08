package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.QuizMetaData;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public User save(User user) {
        String query = "INSERT INTO \"user\" (fullname,username,email,password,uuid) VALUES (?,?,?,?,?)";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getUuid());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public User update(User user, int id) {
        String query = "UPDATE \"user\" SET fullname=?, username=?, email=?, password=?, uuid=? WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getUuid());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getById(id);
    }

    public void delete(int id) {
        String query = "DELETE FROM \"user\" WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setInt(1,id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                System.out.println("User with ID " + id + " deleted successfully!");
            }else {
                System.out.println("User with ID " + id + " not found.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User getById(int id) {
        User user = new User();
        String query = "SELECT * FROM \"user\" WHERE id=?";
        try(PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    user.setId(resultSet.getInt("id"));
                    user.setFullname(resultSet.getString("fullname"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setUuid(resultSet.getString("uuid"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAll() {
        User user = new User();
        List<User> allUser = new ArrayList<>();
        String query = "SELECT * FROM \"user\"";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setFullname(resultSet.getString("fullname"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setUuid(resultSet.getString("uuid"));
                    allUser.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUser;
    }

}
