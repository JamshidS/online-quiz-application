package com.quiz.service;

import com.quiz.model.User;
import com.quiz.model.UserQuiz;

import java.util.List;

public interface UserService {
    User getUser(int userID);
    List<User> getAllUser();
    String updateUser(int userID, User updatedUser);

}
