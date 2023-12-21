package com.quiz.service.impl;

import com.quiz.model.QuizMetaData;
import com.quiz.repository.QuizMetaDataRepository;
import com.quiz.service.QuizService;

public class QuizServiceImpl implements QuizService {
    private final QuizMetaDataRepository quizMetaDataRepository;
    public QuizServiceImpl(QuizMetaDataRepository quizMetaDataRepository){
        this.quizMetaDataRepository = quizMetaDataRepository;
    }
    @Override
    public String addQuizMetaDataToQuizQuestion(QuizMetaData quizMetaData) {
        // idk how to write it right now. but i'll add...
        return null;
    }
}

