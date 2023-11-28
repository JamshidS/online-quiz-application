package com.quiz.query;

import com.quiz.database.DatabaseConnector;
import com.quiz.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {
    public User getUserByUsername(String username) {
        User user = null;
        Connection connection = null;

        try {
            connection = DatabaseConnector.connect();

            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.setId((long) resultSet.getInt("id"));
                        user.setUserName(resultSet.getString("username"));
                        // Diğer kullanıcı özelliklerini set edilir
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.close(connection);
        }
        return user;
    }

}
