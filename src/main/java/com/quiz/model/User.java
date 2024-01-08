package com.quiz.model;

import java.util.List;

public class User {
    private int id;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String uuid;
    private List<UserQuiz> userQuizList;

    public User(){

    }

    public User(String fullname, String username, String email, String password, String uuid) {
        this.fullname = fullname;
        this.username = username;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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