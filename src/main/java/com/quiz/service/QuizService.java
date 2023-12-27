package com.quiz.service;

import com.quiz.model.QuizMetaData;
import com.quiz.model.QuizQuestion;

import java.util.List;

public interface QuizService {
    String addQuizQuestionToQuizMetaData(List<QuizMetaData> quizMetaDataList, int quizQuestionID);
    QuizMetaData getQuizMetaData(int quizMetaDataID);
    List<QuizMetaData> getAllQuizMetaData();
    void deleteQuizMetaData(int quizMetaDataID);
}
