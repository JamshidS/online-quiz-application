package com.quiz.service.impl;

import com.quiz.model.Quiz;
import com.quiz.model.QuizQuestion;
import com.quiz.repository.QuizQuestionRepository;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuizService;

import java.util.List;

public class QuizServiceImpl implements QuizService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizQuestionRepository quizQuestionRepository, QuizRepository quizRepository) {
        this.quizQuestionRepository = quizQuestionRepository;

        this.quizRepository = quizRepository;
    }




    @Override
    public String addQuizQuestion(QuizQuestion quizQuestion) {
        if (quizQuestion != null) {
            quizQuestionRepository.addQuizQuestion(quizQuestion);
            return "Question added successfully.";
        } else {
            throw new IllegalArgumentException("quizQuestion parameter cannot be null");

        }
    }

    @Override
    public String updateQuizQuestion(Long questionId, String newQuestion) {
        QuizQuestion updateQuizQuestion = quizQuestionRepository.getQuizQuestionByID(questionId);
        if(updateQuizQuestion != null){
            quizQuestionRepository.updateQuizQuestion(questionId,newQuestion);
            return "Question updated successfully.";

        }else {
            throw new IllegalArgumentException("Question with ID " + questionId + " not found.");
        }

    }

    @Override
    public String  deleteQuizQuestion(Long questionId) {
       if (quizQuestionRepository.getQuizQuestionByID(questionId) == null){

           throw new IllegalArgumentException("Deleting quiz failed, no rows affected.");
       } else {
           quizQuestionRepository.deleteQuizQuestion(questionId);
           return "Quiz deleted successfully.";
       }

    }


    @Override
    public List<QuizQuestion> getAllQuizQuestion() {
        return quizQuestionRepository.getAllQuizQuestions();
    }

    @Override
    public List<QuizQuestion> getAllQuizQuestionByQuizID(Long quizId) {

        Quiz quiz = quizRepository.getQuizById(quizId);
        if (quiz != null){
            return quizQuestionRepository.getAllQuestionByQuizID(quizId);
        }
        throw new IllegalArgumentException("Qui< with ID " + quizId + "not found.");
    }
}


