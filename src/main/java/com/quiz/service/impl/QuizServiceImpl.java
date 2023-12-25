package com.quiz.service.impl;

import com.quiz.model.QuizMetaData;
import com.quiz.model.QuizQuestion;
import com.quiz.repository.QuizMetaDataRepository;
import com.quiz.repository.QuizQuestionRepository;
import com.quiz.service.QuizService;
import java.util.*;

public class QuizServiceImpl implements QuizService {
    private QuizMetaDataRepository quizMetaDataRepository;
    private QuizQuestionRepository quizQuestionRepository;

    public QuizServiceImpl(QuizMetaDataRepository quizMetaDataRepository, QuizQuestionRepository quizQuestionRepository){
        this.quizMetaDataRepository = quizMetaDataRepository;
        this.quizQuestionRepository = quizQuestionRepository;
    }

    @Override
    public String addQuizMetaDataToQuizQuestion(List<QuizMetaData> quizMetaDataList, int quizQuestionID) {
        try {
            QuizQuestion quizQuestion = quizQuestionRepository.getQuizQuestionByID(quizQuestionID);

            if (quizQuestion!=null) {
                boolean trueFound = false;
                for (QuizMetaData quizMetaData : quizMetaDataList) {
                    if (quizMetaData.isCorrect()) {
                        if (trueFound) {
                            return "Only one 'True' value is allowed in QuizMetaData.";
                        }
                        trueFound = true;
                    }
                    quizMetaData.setQuizQuestion(quizQuestion);
                    quizMetaDataRepository.save(quizMetaData);
                }
                return "QuizMetaData added successfully.";
            } else {
                return "QuizQuestion with ID " + quizQuestionID + " not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while adding QuizMetaData.";
        }

    }
}

