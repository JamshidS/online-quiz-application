package com.quiz.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class QuizQuestion {

    private Long id; //PRIMARY KEY
    private String uuid;
    private Long  quizId; // FOREIGN KEY
    private String question;
    private LocalDateTime createdDateTime;
    private List<QuizMetaData> quizMetaData;

    public QuizQuestion() {
    }

    public QuizQuestion(Long id, String uuid, Long quizId, String question, LocalDateTime createdDateTime, List<QuizMetaData> quizMetaData) {
        this.id = id;
        this.uuid = uuid;
        this.quizId = quizId;
        this.question = question;
        this.createdDateTime = createdDateTime;
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

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public List<QuizMetaData> getQuizMetaData() {
        return quizMetaData;
    }

    public void setQuizMetaData(List<QuizMetaData> quizMetaData) {
        this.quizMetaData = quizMetaData;
    }
}
