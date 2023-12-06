package com.quiz.model;

import java.time.LocalDate;
import java.util.List;

public class QuizQuestion {

    private Long id; //PRIMARY KEY
    private String uuid;
    private String  quizId; // FOREIGN KEY
    private String question;
    private LocalDate createdAt;
    private List<QuizMetaData> quizMetaData;

    public QuizQuestion() {

    }

    public QuizQuestion(Long id, String uuid, String quizId, String question, LocalDate createdAt, List<QuizMetaData> quizMetaData) {
        this.id = id;
        this.uuid = uuid;
        this.quizId = quizId;
        this.question = question;
        this.createdAt = createdAt;
        this.quizMetaData = quizMetaData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<QuizMetaData> getQuizMetaData() {
        return quizMetaData;
    }

    public void setQuizMetaData(List<QuizMetaData> quizMetaData) {
        this.quizMetaData = quizMetaData;
    }
}
