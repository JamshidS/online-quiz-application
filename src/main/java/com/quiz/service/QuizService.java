package com.quiz.service;


import com.quiz.model.Quiz;
import com.quiz.model.QuizMetaData;


import com.quiz.model.QuizQuestion;

import java.util.List;

public interface QuizService {


    String addQuizQuestionToQuizMetaData(List<QuizMetaData> quizMetaDataList, int quizQuestionID);

    QuizMetaData getQuizMetaData(int quizMetaDataID);

    List<QuizMetaData> getAllQuizMetaData();

    List<QuizMetaData> getAllQuizMetaDataWithQuizQuestionID(int quizQuestionID);

    String updateQuizMetaData(int quizQuestionID, int quizMetaDataID, QuizMetaData updatedMetaData);

    void deleteQuizMetaData(int quizMetaDataID);

    String saveQuizMetaData(int quizMetaDataID);

    QuizQuestion getQuizQuestion(int id);

    List<QuizQuestion> getAllQuizQuestion();

    String updateQuizQuestion(Long questionId, String newQuestion);

    String deleteQuizQuestion(Long questionId);

    String addQuizQuestion(QuizQuestion quizQuestion);

    Quiz getQuiz(int id);

    List<Quiz> getAllQuiz();

    Quiz updateQuiz(int id, Quiz updatedQuiz);

    void deleteQuiz(int id);

    String saveQuiz(Quiz quiz);
}

