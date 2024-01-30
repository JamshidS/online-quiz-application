package com.quiz.service.impl;

import com.quiz.exceptions.CustomException;
import com.quiz.model.Result;
import com.quiz.service.ResultService;

import java.util.List;

public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    public ResultServiceImpl(ResultRepository resultRepository){
        this.resultRepository = resultRepository;
    }
    @Override
    public String saveResult(Result result) {
        if(result == null){
            CustomException.throwNotFoundException("Result Not Found With This ID" + result.getId());
            return null;
        }
        else {
            Result resultDB = new Result();
            resultDB.setId(result.getId());
            resultDB.setUserUuid(result.getUserUuid());
            resultDB.setQuizUuid(result.getQuizUuid());
            resultDB.setPoint(result.getPoint());
            resultDB.setQuiz(result.getQuiz());

            resultRepository.save(resultDB);

            return "Result has been created succesfully";
        }
    }

    @Override
    public String deleteResult(long resultId) {
        if (resultRepository.getResultUserByID(resultId) == null) {
            CustomException.throwNotFoundException("Result Not Found with this id" + resultId + "to delete");
            return null;
        }else {
            resultRepository.delete(resultId);
            return "Result has been Deleted successfully";
        }
    }

    @Override
    public String updateResult(Result result) {
        Result resultDB = new Result();
        if (result == null){
            CustomException.throwNotFoundException("Result Not Found with this id" + result.getId() + "to update");
            return "Result Not Found";
        }else{
            resultDB.setUserUuid(result.getUserUuid());
            resultDB.setQuizUuid(result.getQuizUuid());
            resultDB.setPoint(result.getPoint());
            resultDB.setQuiz(result.getQuiz());

            resultRepository.update(resultDB, result.getId());
            return "Result has been updated succesfully";
        }
    }

    @Override
    public String getResultUserByID(long resultID) {
        if(resultRepository.getResultUserByID(resultID) == null){
            CustomException.throwNotFoundException("Result Not Found with this id" + resultId + "to get");
            return null;
        }else{
            return resultRepository.getResultUserByID(resultID);
        }
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.getAllResults();
    }
}
