package com.quiz.service.impl;


import com.quiz.model.QuizMetaData;
import com.quiz.model.QuizQuestion;
import com.quiz.repository.QuizMetaDataRepository;
import com.quiz.repository.QuizQuestionRepository;

import com.quiz.model.Quiz;
import com.quiz.model.QuizQuestion;
import com.quiz.repository.QuizQuestionRepository;
import com.quiz.repository.QuizRepository;

import com.quiz.service.QuizService;
import java.util.*;

import java.util.List;

public class QuizServiceImpl implements QuizService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizRepository quizRepository;
    private final QuizMetaDataRepository quizMetaDataRepository;

    public QuizServiceImpl(QuizQuestionRepository quizQuestionRepository, QuizRepository quizRepository, QuizMetaDataRepository quizMetaDataRepository) {
        this.quizQuestionRepository = quizQuestionRepository;

        this.quizRepository = quizRepository;
        this.quizMetaDataRepository = quizMetaDataRepository;
    }

    @Override
    public String addQuizQuestionToQuizMetaData(List<QuizMetaData> quizMetaDataList, int quizQuestionID){
        try {
            QuizQuestion quizQuestion = quizQuestionRepository.getQuizQuestionByID(quizQuestionID);
            if (quizQuestion!=null){
                boolean trueFound = false;
                for (QuizMetaData quizMetaData : quizMetaDataList){
                    if (quizMetaData.isCorrect()){
                        if (trueFound){
                            throw new RuntimeException("Only one correct answer is allowed in question.");
                        }
                        trueFound = true;
                    }
                    quizMetaData.setQuizQuestion(quizQuestion);
                    quizMetaDataRepository.save(quizMetaData);
                }
                return "QuizQuestion added to QuizMetaData successfully.";
            }else{
                throw new RuntimeException("QuizQuestion not found with ID: " + quizQuestionID);
            }
        }catch (Exception e){
            e.printStackTrace();
            return "An error occurred while adding QuizQuestion to QuizMetaData.";
        }
    }

    @Override
    public QuizMetaData getQuizMetaData(int quizMetaDataID) {
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        if (quizMetaData != null) {
            return quizMetaData;
        } else {
            throw new RuntimeException("QuizMetaData not found with ID: " + quizMetaDataID);

        }
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
    public List<QuizMetaData> getAllQuizMetaData(){
        List<QuizMetaData> allQuizMetaData = quizMetaDataRepository.getAllQuizMetaData();
        if(allQuizMetaData!=null){
            return allQuizMetaData;
        }else{
            throw new RuntimeException("QuizMetaData not found.");
        }
    }

    @Override
    public List<QuizMetaData> getAllQuizMetaDataWithQuizQuestionID(int quizQuestionID) {
        QuizQuestion quizQuestion = quizQuestionRepository.getQuizQuestionByID(quizQuestionID);
        if(quizQuestion!=null){
            return quizQuestion.getQuizMetaData();
        }else{
            throw new RuntimeException("QuizQuestion not found with ID: " + quizQuestionID);
        }
    }

    @Override
    public String updateQuizMetaData(int quizQuestionID, int quizMetaDataID, QuizMetaData updatedMetaData){
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        QuizQuestion quizQuestion = quizQuestionRepository.getQuizQuestionByID(quizQuestionID);
        if(quizMetaData!=null){
            if(!quizMetaData.isCorrect() && updatedMetaData.isCorrect()){
                for(QuizMetaData metaData : quizQuestion.getQuizMetaData()){
                    if(metaData.isCorrect()){
                        metaData.setCorrect(false);
                        quizMetaDataRepository.update(metaData,metaData.getId());
                    }
                }
                quizMetaDataRepository.update(updatedMetaData,quizMetaDataID);
                return "QuizMetaData successfully updated!";
            }else if(quizMetaData.isCorrect() && !updatedMetaData.isCorrect()){
                throw new RuntimeException("Firstly you must assign the correct answer!");
            }else{
                quizMetaDataRepository.update(updatedMetaData,quizMetaDataID);
                return "QuizMetaData successfully updated!";
            }
        }else{
            throw new RuntimeException("QuizMetaData not found with ID: " + quizMetaDataID);
        }
    }

    @Override
    public void deleteQuizMetaData(int quizMetaDataID){
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        if(quizMetaData!=null){
            quizMetaDataRepository.delete(quizMetaDataID);
        }else{
            throw new RuntimeException("QuizMetaData not found with ID: " + quizMetaDataID);
        }
    }



    public String updateQuizQuestion(Long questionId, String newQuestion) {
        QuizQuestion updateQuizQuestion = quizQuestionRepository.getQuizQuestionByID(Math.toIntExact(questionId));
        if(updateQuizQuestion != null){
            quizQuestionRepository.updateQuizQuestion(questionId,newQuestion);
            return "Question updated successfully.";

        }else {
            throw new IllegalArgumentException("Question with ID " + questionId + " not found.");
        }

    }

    @Override
    public String  deleteQuizQuestion(Long questionId) {
       if (quizQuestionRepository.getQuizQuestionByID(Math.toIntExact(questionId)) == null){

           throw new IllegalArgumentException("Deleting quiz failed, no rows affected.");
       } else {
           quizQuestionRepository.deleteQuizQuestion(questionId);
           return "Quiz deleted successfully.";
       }

    }


    @Override
    public List<QuizQuestion> getAllQuizQuestion() {
        return quizQuestionRepository.getAllQuizQuestion();
    }

    @Override
    public List<QuizQuestion> getAllQuizQuestionByQuizID(Long quizId) {

        Quiz quiz = quizRepository.getQuizById(quizId);
        if (quiz != null){
            return quizQuestionRepository.getAllQuizQuestion(Math.toIntExact(quizId));
        }
        throw new IllegalArgumentException("Qui< with ID " + quizId + "not found.");
    }
}




