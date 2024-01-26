package com.quiz;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Person;
import com.quiz.repository.*;
import com.quiz.service.impl.QuizServiceImpl;
import com.quiz.service.impl.UserQuizServiceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServiceApplication {
    public static void main(String[] args) {
        DBConnectorConfig.connect();
        QuizServiceImpl quizService = new QuizServiceImpl(new QuizQuestionRepository(),new QuizRepository(), new QuizMetaDataRepository());
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new UserQuizServiceImpl().takeQuiz());
        service.shutdown();
    }
}