package com.quiz.model;

import java.util.List;

public class User {
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String uuid;
    private List<String> userQuizs; //should be List<UserQuiz>

    public User(String fullName, String userName, String email, String password, String uuid, List<String> userQuizs) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.uuid = uuid;
        this.userQuizs = userQuizs;
    }
    public User(){

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getUserQuizs() {
        return userQuizs;
    }

    public void setUserQuizs(List<String> userQuizs) {
        this.userQuizs = userQuizs;
    }
}