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
            for(User testUsers : userRepository.getAll()){
                if(user.getUsername().equals(testUsers.getUsername())){
                    throw new RuntimeException("This username is already in use!");
                }
                if(user.getEmail().equals(testUsers.getEmail())){
                    throw new RuntimeException("This email is already in use!");
                }
            }
            userRepository.save(user);
            return "User successfully saved.";
        }else{
            throw new RuntimeException("User not found with ID: ");
        }
    }
    public User getUser(int userID){
        User user = userRepository.getById(userID);
        if(user!=null){
            return user;
        }else{
            throw new RuntimeException("User not found with ID: " + userID);
        }
    }

    public List<User> getAllUser(){
        List<User> allUser = userRepository.getAll();
        if(allUser!=null){
            return allUser;
        }else{
            throw new RuntimeException("User not found.");
        }
    }

    public String updateUser(int userID, User updatedUser){
        User user = userRepository.getById(userID);
        if(user!=null){
            if(!user.getUsername().equals(updatedUser.getUsername())){
                for(User testUsers : userRepository.getAll()){
                    if(user.getUsername().equals(testUsers.getUsername())){
                        throw new RuntimeException("This username is already in use!");
                    }
                }
            }
            if(!user.getEmail().equals(updatedUser.getEmail())){
                for(User testUsers : userRepository.getAll()){
                    if(user.getEmail().equals(testUsers.getEmail())){
                        throw new RuntimeException("This email is already in use!");
                    }
                }
            }
            userRepository.update(updatedUser, userID);
            return "User successfully updated!";
        }else{
            throw new RuntimeException("User not found with ID: " + userID);
        }
    }

}
