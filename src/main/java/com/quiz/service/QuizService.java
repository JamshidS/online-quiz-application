package com.quiz.service;

import com.quiz.model.Quiz;
import com.quiz.model.User;

public interface QuizService {

    String takeQuiz(User user, Quiz quiz);
}
