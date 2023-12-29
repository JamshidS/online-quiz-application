package com.quiz.service;

import com.quiz.model.QuizQuestion;

import java.util.List;

public interface QuizService {
   String addQuizQuestion(QuizQuestion quizQuestion);

   String updateQuizQuestion(Long questionId,String newQuestion);

   String  deleteQuizQuestion(Long questionId);

   List<QuizQuestion> getAllQuizQuestion();

   List<QuizQuestion> getAllQuizQuestionByQuizID(Long quizId);



}

