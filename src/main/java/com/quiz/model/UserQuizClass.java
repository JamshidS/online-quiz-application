package com.quiz.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class UserQuizClass {
    private String uuid;
    private String name;
    private String description;
    private String instructions;
    private int duration;
    private int attempts;
    private String difficulty;
    private Boolean status;
    private LocalDate createdAt;
    private Object user; // Object was temporarily kept instead of User
    private Object quiz; // Object was temporarily kept instead of Quiz

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getQuiz() {
        return quiz;
    }

    public void setQuiz(Object quiz) {
        this.quiz = quiz;
    }
}
