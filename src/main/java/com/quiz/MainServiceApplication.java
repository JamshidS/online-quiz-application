package com.quiz;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Person;
import com.quiz.repository.*;
import com.quiz.service.impl.QuizServiceImpl;

public class MainServiceApplication {
    public static void main(String[] args) {
        DBConnectorConfig.connect();
        QuizServiceImpl quizService = new QuizServiceImpl(new QuizQuestionRepository(),new QuizRepository(), new QuizMetaDataRepository());
    }
}