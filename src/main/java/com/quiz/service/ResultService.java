package com.quiz.service;

import com.quiz.model.Result;

import java.util.List;

public interface ResultService {
    String saveResult(Result result);
    String deleteResult(long resultId);
    String updateResult(Result result);
    Result getResultById(long resultId);
    List<Result> getAllResult();

}
