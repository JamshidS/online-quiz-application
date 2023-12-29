package com.quiz.service.impl;

import com.quiz.model.QuizMetaData;
import com.quiz.model.QuizQuestion;
import com.quiz.repository.QuizMetaDataRepository;
import com.quiz.repository.QuizQuestionRepository;
import com.quiz.service.QuizService;
import java.util.*;

public class QuizServiceImpl implements QuizService {
    private final QuizMetaDataRepository quizMetaDataRepository;
    private final QuizQuestionRepository quizQuestionRepository;

    public QuizServiceImpl(QuizMetaDataRepository quizMetaDataRepository, QuizQuestionRepository quizQuestionRepository){
        this.quizMetaDataRepository = quizMetaDataRepository;
        this.quizQuestionRepository = quizQuestionRepository;
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
    public QuizMetaData getQuizMetaData(int quizMetaDataID){
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        if (quizMetaData!=null){
            return quizMetaData;
        }else{
            throw new RuntimeException("QuizMetaData not found with ID: " + quizMetaDataID);
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
}

