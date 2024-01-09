package com.quiz.service;

import com.quiz.model.User;
import com.quiz.model.UserQuiz;

import java.util.List;

public interface UserService {
    User getUserByID(int userID);
    List<User> getAllUsers();
    String updateUser(int userID, User updatedUser);

}
