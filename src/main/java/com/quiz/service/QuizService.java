package com.quiz.service;

import com.quiz.model.QuizQuestion;

public interface QuizService {
   void addQuizQuestion(Long quizId,String question);

   QuizQuestion updateQuizQuestion(Long quizId,Long questionId,String newQuestion);

   void deleteQuizQuestion(Long quizID,Long questionId);

   QuizQuestion getQuizQuestionByQuizId(Long listQuizId);




}

