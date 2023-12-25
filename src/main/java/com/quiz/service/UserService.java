package com.quiz.service;

import com.quiz.model.User;

import java.util.List;

public interface UserService {
    String createUser(User user);
    List<User> getUsers();
    User getUser(Long id) throws Exception;
    String updateUser(Long id, User userDto) throws Exception;
    String deleteUser(Long id) throws Exception;
    
}
