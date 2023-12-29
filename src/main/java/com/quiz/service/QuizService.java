package com.quiz.service;

import com.quiz.model.QuizMetaData;
import com.quiz.model.QuizQuestion;

import java.util.List;

public interface QuizService {
    String addQuizQuestionToQuizMetaData(List<QuizMetaData> quizMetaDataList, int quizQuestionID);
    QuizMetaData getQuizMetaData(int quizMetaDataID);
    List<QuizMetaData> getAllQuizMetaData();
    List<QuizMetaData> getAllQuizMetaDataWithQuizQuestionID(int quizQuestionID);
    String updateQuizMetaData(int quizQuestionID, int quizMetaDataID, QuizMetaData metaData);
    void deleteQuizMetaData(int quizMetaDataID);
}
