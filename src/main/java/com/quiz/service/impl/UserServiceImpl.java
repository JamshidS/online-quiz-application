package com.quiz.service.impl;

import com.quiz.model.User;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        try {
            userRepository.addUser(user);
            return "User created successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating user.";
        }    }

    @Override
    public List<User> getUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public String updateUser(Long id, User userDto) throws Exception {
        User existingUser = userRepository.getUserById(id);

        if (existingUser != null) {
            existingUser.setEmail(userDto.getEmail());
            existingUser.setFullName(userDto.getFullName());

            User updatedUser = userRepository.updateUser(id, userDto.getEmail(), userDto.getFullName());

            if (updatedUser != null) {
                return "User updated successfully.";
            } else {
                return "Error updating user.";
            }
        } else {
            return "User not found with ID: " + id;
        }    }

    @Override
    public String deleteUser(Long id) throws Exception {
        User existingUser = userRepository.getUserById(id);

        if (existingUser != null) {
            userRepository.deleteUser(id);
            return "User deleted successfully.";
        } else {
            return "User not found with ID: " + id;
        }    }
}
