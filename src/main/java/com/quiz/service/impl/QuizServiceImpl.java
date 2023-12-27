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
                            return "Only one correct answer is allowed in question.";
                        }
                        trueFound = true;
                    }
                    quizMetaData.setQuizQuestion(quizQuestion);
                    quizMetaDataRepository.save(quizMetaData);
                }
                return "QuizQuestion added to QuizMetaData successfully.";
            }else{
                return "QuizQuestion with ID " + quizQuestionID + " not found.";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "An error occurred while adding QuizQuestion to QuizMetaData.";
        }
    }
    @Override
    public QuizMetaData getQuizMetaData(int quizMetaDataID){
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        if (quizMetaData != null){
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

    /*
    public String saveQuizMetaData(String option, boolean correct){
        // Is this necessary? I'm not sure. We save QuizMetaData while we adding QuizQuestion to QuizMetaData in other function.
    }*/

    /*
    public String updateQuizMetaData(int quizQuestionID, int quizMetaDataID){
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        if(quizMetaData!=null){

        // I didn't know how to write the part of selecting which parameter to update. I will find it and add it.

            quizMetaData.setOption();
            quizMetaData.setCorrect();

            quizMetaDataRepository.update(quizMetaData,quizMetaDataID);

            return "QuizMetaData successfully updated!";
        } else{
            return "QuizMetaData not found with ID: " + quizMetaDataID;
        }
    }*/
    @Override
    public void deleteQuizMetaData(int quizMetaDataID){
        quizMetaDataRepository.delete(quizMetaDataID);
    }
}

