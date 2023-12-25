package com.quiz.service;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;

import java.util.List;

public interface UserQuizService {
    String saveUserQuiz(UserQuiz userQuiz);
    String deleteUserQuiz(long userQuizId);
    String updateUserQuiz(UserQuiz userQuiz);
    UserQuiz getUserQuizById(long userQuizId);
    List<UserQuiz> getAllUserQuiz();
}
