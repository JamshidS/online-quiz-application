package com.quiz.service.impl;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.repository.UserQuizRepository;
import com.quiz.service.QuizService;

public class QuizServiceImpl implements QuizService {

    private final UserQuizRepository userQuizRepository;

    public QuizServiceImpl(UserQuizRepository userQuizRepository) {
        this.userQuizRepository = userQuizRepository;
    }

    @Override
    public String takeQuiz(User user, Quiz quiz) {
        UserQuiz userQuiz = new UserQuiz();
        userQuiz.setUser(user);
        userQuiz.setQuiz(quiz);
        userQuiz.setStatus(true);

        userQuizRepository.save(userQuiz);


        return user.getUserName() + " has taken " + quiz.getName() + " quiz.";
    }
}
