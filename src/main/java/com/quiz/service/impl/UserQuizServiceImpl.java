package com.quiz.service.impl;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.UserQuizRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserQuizService;

import java.time.LocalDate;

public class UserQuizServiceImpl implements UserQuizService {
    private final UserQuizRepository userQuizRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;


    public UserQuizServiceImpl(UserQuizRepository userQuizRepository, UserRepository userRepository, QuizRepository quizRepository) {
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
        userQuiz.setAttempts(userQuiz.getAttempts() + 1);
        userQuiz.setCreatedAt(LocalDate.now());
        userQuiz.setName(quiz.getName());
        userQuiz.setDescription(quiz.getDescription());
        userQuiz.setInstructions(quiz.getInstructions());
        userQuiz.setDuration(quiz.getDuration());
        userQuiz.setDifficulty(quiz.getDifficulty());


        userQuizRepository.save(userQuiz);


        String result = evaluate(userQuiz);


        return user.getUserName() + " has taken " + quiz.getName() + " quiz. Quiz Score: " + quiz.getResult().getPoint() + ". Result: " + result;
    }


    private String evaluate(UserQuiz userQuiz) {

        double quizpoint = userQuiz.getQuiz().getResult().getPoint();
        double total = 0;

        userQuiz.getQuiz().getResults().add(quizpoint);


        for ( double result: userQuiz.getQuiz().getResults()){
            total += result;
        }

        double average = total / userQuiz.getQuiz().getResults().size();
        userQuizRepository.update(userQuiz , userQuiz.getId());

        if (quizpoint > average){
            return "Your score is below the average. average is " + average + ".";
        }else if (quizpoint == average){
            return "Your score is equal to the average. average is " + average + ".";
        }else {
            return "Your score is above the average. average is " + average + ".";
        }



    }

}
