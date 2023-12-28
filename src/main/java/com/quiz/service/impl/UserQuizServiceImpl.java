package com.quiz.service.impl;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.UserQuizRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserQuizService;

public class UserQuizServiceImpl implements UserQuizService {
    private final UserQuizRepository userQuizRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;


    public UserQuizServiceImpl(UserQuizRepository userQuizRepository, UserRepository userRepository, UserRepository userRepository1, QuizRepository quizRepository) {
        this.userQuizRepository = userQuizRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }


    @Override
    public String takeQuiz(long userId, long quizId) {
        User user = userRepository.getUserById(userId);
        Quiz quiz = quizRepository.getQuizById(quizId);

        UserQuiz userQuiz = new UserQuiz();

        userQuiz.setUser(user);
        userQuiz.setQuiz(quiz);
        userQuiz.setStatus(true);

        userQuizRepository.save(userQuiz);


        return user.getUserName() + " has taken " + quiz.getName() + " quiz.";
    }

}
