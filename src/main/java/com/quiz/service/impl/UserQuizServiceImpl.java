package com.quiz.service.impl;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.repository.UserQuizRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserQuizService;

import java.util.List;

public class UserQuizServiceImpl implements UserQuizService {

    private final UserQuizRepository userQuizRepository;

    public UserQuizServiceImpl(UserQuizRepository userQuizRepository) {
        this.userQuizRepository = userQuizRepository;
    }

    @Override
    public String saveUserQuiz(UserQuiz userQuiz) {
        UserQuiz userQuizforDatabase = new UserQuiz();
        userQuizforDatabase.setName(userQuiz.getName());
        userQuizforDatabase.setId(userQuiz.getId());
        userQuizforDatabase.setUuid(userQuiz.getUuid());
        userQuizforDatabase.setDescription(userQuiz.getDescription());
        userQuizforDatabase.setInstructions(userQuiz.getInstructions());
        userQuizforDatabase.setDuration(userQuiz.getDuration());
        userQuizforDatabase.setAttempts(userQuiz.getAttempts());
        userQuizforDatabase.setDifficulty(userQuiz.getDifficulty());
        userQuizforDatabase.setCreatedAt(userQuiz.getCreatedAt());
        userQuizforDatabase.setQuiz(userQuiz.getQuiz());
        userQuizforDatabase.setUser(userQuiz.getUser());


        userQuizRepository.save(userQuizforDatabase);


        return "UserQuiz Saved";
    }

    @Override
    public String deleteUserQuiz(long userQuizId) {
        if (userQuizRepository.getUserQuizById(userQuizId) == null) {
            throw new RuntimeException("UserQuiz Not Found");
        }else {
            userQuizRepository.delete(userQuizId);
            return "UserQuiz Deleted";
        }

    }

    @Override
    public String updateUserQuiz(UserQuiz userQuiz) {
        UserQuiz userQuizforUpdate = getUserQuizById(userQuiz.getId());

        if (userQuizforUpdate != null) {

            userQuizforUpdate.setName(userQuiz.getName());
            userQuizforUpdate.setId(userQuiz.getId());
            userQuizforUpdate.setUuid(userQuiz.getUuid());
            userQuizforUpdate.setDescription(userQuiz.getDescription());
            userQuizforUpdate.setInstructions(userQuiz.getInstructions());
            userQuizforUpdate.setDuration(userQuiz.getDuration());
            userQuizforUpdate.setAttempts(userQuiz.getAttempts());
            userQuizforUpdate.setDifficulty(userQuiz.getDifficulty());
            userQuizforUpdate.setCreatedAt(userQuiz.getCreatedAt());
            userQuizforUpdate.setQuiz(userQuiz.getQuiz());
            userQuizforUpdate.setUser(userQuiz.getUser());

            userQuizRepository.save(userQuizforUpdate);
            return "UserQuiz Updated";
        }
        throw new RuntimeException("UserQuiz Not Found");
    }

    @Override
    public UserQuiz getUserQuizById(long userQuizId) {
        UserQuiz userQuiz = userQuizRepository.getUserQuizById(userQuizId);
        if (userQuiz != null) {
            return userQuiz;
        }
        throw new RuntimeException("UserQuiz Not Found");
    }

    @Override
    public List<UserQuiz> getAllUserQuiz() {
        return userQuizRepository.getAllUserQuizs();
    }
}
