package com.quiz.model;

public class Result {
    private Long id;
    private String userQuizUuid;
    private String quizUuid;
    private double point;
    private Quiz quiz; //quiz was kept symbolically as string


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserQuizUuid() {
        return userQuizUuid;
    }

    public void setUserQuizUuid(String userQuizUuid) {
        this.userQuizUuid = userQuizUuid;
    }

    public String getQuizUuid() {
        return quizUuid;
    }

    public void setQuizUuid(String quizUuid) {
        this.quizUuid = quizUuid;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
