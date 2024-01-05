package com.quiz.service;


import com.quiz.model.Quiz;
import com.quiz.model.User;


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

   String addQuizQuestion(QuizQuestion quizQuestion);

   String updateQuizQuestion(Long questionId,String newQuestion);

   String  deleteQuizQuestion(Long questionId);

   List<QuizQuestion> getAllQuizQuestion();

   List<QuizQuestion> getAllQuizQuestionByQuizID(Long quizId);




}

