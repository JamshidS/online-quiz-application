package com.quiz.service.impl;

import com.quiz.exceptions.CustomException;
import com.quiz.model.Result;
import com.quiz.repository.ResultRepository;
import com.quiz.service.ResultService;

import java.util.List;

public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public String saveResult(Result result) {
        if (result == null) {
            CustomException.throwNotFoundException("Result Not Found");
            return "Result Not Found";
    }else {
            Result resultforDatabase = new Result();
            resultforDatabase.setId(result.getId());
            resultforDatabase.setUserQuizUuid(result.getUserQuizUuid());
            resultforDatabase.setQuizUuid(result.getQuizUuid());
            resultforDatabase.setPoint(result.getPoint());
            resultforDatabase.setQuiz(result.getQuiz());

            resultRepository.save(resultforDatabase);

            return "Result has been created successfully";
        }
    }

    @Override
    public String deleteResult(long resultId) {
        if (resultRepository.getResultById(resultId) == null) {
            CustomException.throwNotFoundException("Result Not Found");
            return "Result Not Found";
    }else {
            resultRepository.delete(resultId);
            return "Result has been Deleted successfully";
        }
    }

    @Override
    public String updateResult(Result result) {
        Result resultforDatabase = new Result();
        if (result == null) {
            CustomException.throwNotFoundException("Result Not Found");
            return "Result Not Found";
    }else {
        resultforDatabase.setUserQuizUuid(result.getUserQuizUuid());
        resultforDatabase.setQuizUuid(result.getQuizUuid());
        resultforDatabase.setPoint(result.getPoint());
        resultforDatabase.setQuiz(result.getQuiz());

        resultRepository.update(resultforDatabase, result.getId());
        return "Result has been updated successfully";
        }
    }

    @Override
    public Result getResultById(long resultId) {
    if (resultRepository.getResultById(resultId) == null) {
        CustomException.throwNotFoundException("Result Not Found");
        return null;
    }else {
        return resultRepository.getResultById(resultId);
    }
    }

    @Override
    public List<Result> getAllResult() {
        return resultRepository.getAllResults();
    }
}
