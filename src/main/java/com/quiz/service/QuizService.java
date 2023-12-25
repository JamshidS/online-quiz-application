package com.quiz.service;

import com.quiz.model.QuizMetaData;
import com.quiz.model.QuizQuestion;

import java.util.List;

public interface QuizService {
    String addQuizMetaDataToQuizQuestion(List<QuizMetaData> quizMetaDataList, int quizQuestionID);
}
