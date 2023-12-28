package com.quiz.service;

import com.quiz.model.Quiz;
import com.quiz.model.User;

public interface UserQuizService {
    String takeQuiz(long userId, long quizId);
}
