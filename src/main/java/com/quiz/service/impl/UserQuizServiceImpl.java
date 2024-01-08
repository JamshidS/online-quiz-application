package com.quiz.service.impl;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.UserQuizRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserQuizService;

import java.time.LocalDate;
import java.util.List;

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
