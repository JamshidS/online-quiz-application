package com.quiz.repository;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public User addUser(User user) {
        String query = "INSERT INTO user (fullName, userName, email, password, uuid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getUuid());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User updateUser(Long id, String newEmail, String newFullName) {
        User updatedUser = null;
        String query = "UPDATE user SET email = ?, fullName = ? WHERE id = ? RETURNING *";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setString(1, newEmail);
            statement.setString(2, newFullName);
            statement.setLong(3, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    updatedUser = new User();
                    updatedUser.setId(resultSet.getLong("id"));
                    updatedUser.setFullName(resultSet.getString("fullName"));
                    updatedUser.setUserName(resultSet.getString("userName"));
                    updatedUser.setEmail(resultSet.getString("email"));
                    updatedUser.setPassword(resultSet.getString("password"));
                    updatedUser.setUuid(resultSet.getString("uuid"));
                    // UserQuiz listesi doldurulmalı, burada örnek bir doldurma işlemi yapılmamıştır.
                } else {
                    System.out.println("No user matching the specified ID found. ID = " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedUser;
    }

    public void deleteUser(Long id) {
        String query = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully deleted user with ID: " + id);
            } else {
                System.out.println("No user matching the specified ID found. ID = " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(Long id) {
        User user = null;
        String query = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setFullName(resultSet.getString("fullName"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setUuid(resultSet.getString("uuid"));
                } else {
                    System.out.println("No user matching the specified ID found. ID = " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (PreparedStatement statement = DBConnectorConfig.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setFullName(resultSet.getString("fullName"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setUuid(resultSet.getString("uuid"));
                    // UserQuiz listesi doldurulmalı....
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
