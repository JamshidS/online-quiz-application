package com.quiz.service.impl;

import com.quiz.exceptions.CustomException;
import com.quiz.model.Quiz;
import com.quiz.model.Result;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.ResultRepository;
import com.quiz.repository.UserQuizRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserQuizService;

import java.time.LocalDate;
import java.util.List;

public class UserQuizServiceImpl implements UserQuizService, Runnable{

    private final UserQuizRepository userQuizRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final ResultRepository resultRepository;

    public UserQuizServiceImpl(UserQuizRepository userQuizRepository, UserRepository userRepository, QuizRepository quizRepository, ResultRepository resultRepository) {
        this.userQuizRepository = userQuizRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public String saveUserQuiz(UserQuiz userQuiz) {
        if (userQuiz == null) {
            throw new RuntimeException("User Quiz Not Found");
        }else {
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


            return "User Quiz has been created successfully";
        }


    }

    @Override
    public String deleteUserQuiz(long userQuizId) {
        if (userQuizRepository.getUserQuizById(userQuizId) == null) {
            throw new RuntimeException("UserQuiz Not Found");
        }else {
            userQuizRepository.delete(userQuizId);
            return "User Quiz has been Deleted successfully";
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
            return "User Quiz has been Updated Successfully";
        }
        throw new RuntimeException("User Quiz Not Found");
    }

    @Override
    public UserQuiz getUserQuizById(long userQuizId) {
        UserQuiz userQuiz = userQuizRepository.getUserQuizById(userQuizId);
        if (userQuiz != null) {
            return userQuiz;
        }
        throw new RuntimeException("User Quiz Not Found");
    }

    @Override
    public List<UserQuiz> getAllUserQuiz() {
        return userQuizRepository.getAllUserQuizs();
    }


    @Override
    public String takeQuiz(int userId, int quizId) {
        User user = userRepository.getById(userId);
        Quiz quiz = quizRepository.getById(quizId);

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
        if (userQuiz == null || userQuiz.getQuiz() == null || userQuiz.getQuiz().getResult() == null) {
            CustomException.throwInvalidInputException( "Error: Invalid UserQuiz or Quiz Result.");
        }

        double quizPoint = userQuiz.getQuiz().getResult().getPoint();
        double total = 0;
        int numberOfResults = 0;

        List<Result> quizResults = resultRepository.getAllResults();

        for (Result result : quizResults) {
            if (result.getQuiz().getId() == userQuiz.getQuiz().getId()) {
                total += result.getPoint();
                numberOfResults++;
            }
        }

        double average =  total / numberOfResults;


        if (quizPoint > average) {
            return "Congratulations! You scored above the average.";
        } else if (quizPoint == average) {
            return "You scored exactly the average.";
        } else {
            return "You scored below the average. Keep practicing!";
        }
    }


    @Override
    public void run() {
        int userId= 1;
        int quizId = 1;
        takeQuiz(userId,quizId);
    }
}
