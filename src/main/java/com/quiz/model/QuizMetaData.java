package com.quiz.model;

import java.util.ArrayList;
import java.util.List;

public class QuizMetaData {
    private Long id;
    private String option;
    private boolean correct;
    private String quiz;
    // String will change to Quiz (after Quiz class implemention done)

    public QuizMetaData() {
    }

    public QuizMetaData(Long id, String option, boolean correct, String quiz) {
        this.id = id;
        this.option = option;
        this.correct = correct;
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
}
