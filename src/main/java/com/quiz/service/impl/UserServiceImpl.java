package com.quiz.service.impl;

import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(User user){
        if(user!=null){
            isUserUnique(user);
            userRepository.save(user);
            return "User successfully saved.";
        }else{
            throw new RuntimeException("User not found with ID: ");
        }
    }

    public User getUserByID(int userID){
        User user = userRepository.getById(userID);
        if(user!=null){
            return user;
        }else{
            throw new RuntimeException("User not found with ID: " + userID);
        }
    }

    public List<User> getAllUsers(){
        List<User> allUser = userRepository.getAll();
        if(allUser!=null){
            return allUser;
        }else{
            throw new RuntimeException("User not found.");
        }
    }

    public String updateUser(int userID, User updatedUser){
        User user = userRepository.getById(userID);
        List<User> users = userRepository.getAll();
        if(user!=null){
            if(!user.getUserName().equals(updatedUser.getUserName()) || !user.getEmail().equals(updatedUser.getEmail())){
                isUserUnique(user);
            }
            userRepository.update(updatedUser, userID);
            return "User successfully updated!";
        }else{
            throw new RuntimeException("User not found with ID: " + userID);
        }
    }

    public void isUserUnique(User user){
        List<User> users = userRepository.getAll();
        for(User testUsers : users){
            if(user.getUserName().equals(testUsers.getUserName())){
                throw new RuntimeException("This username is already in use!");
            }
            if(user.getEmail().equals(testUsers.getEmail())){
                throw new RuntimeException("This email is already in use!");
            }
        }
    }
}
