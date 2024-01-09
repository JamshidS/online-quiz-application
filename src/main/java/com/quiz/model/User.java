package com.quiz.model;

import java.util.List;

public class User {
    private int id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String uuid;
    private List<UserQuiz> userQuizList;

    public User(){

    }

    public User(String fullName, String userName, String email, String password, String uuid) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<UserQuiz> getUserQuizList() {
        return userQuizList;
    }

    public void setUserQuizList(List<UserQuiz> userQuizList) {
        this.userQuizList = userQuizList;
    }
}