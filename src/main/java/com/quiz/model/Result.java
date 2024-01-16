package com.quiz.model;

public class Result {
    private Long id;
    private String userUuid;
    private String quizUuid;
    private double point;
    private String quiz; //quiz was kept symbolically as string

    public Result(String userUuid, String quizUuid, double point, String quiz) {
        this.userUuid = userUuid;
        this.quizUuid = quizUuid;
        this.point = point;
        this.quiz = quiz;
    }
    public Result(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
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

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
}
