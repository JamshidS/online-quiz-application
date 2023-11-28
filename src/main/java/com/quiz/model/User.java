package com.quiz.model;

import java.util.List;

public class User {
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String uuid;
    private List<UserQuiz> quizs;


    public User(){

    }


    public User(Long id, String fullName, String userName, String email, String password, String uuid, List<UserQuiz> quizs) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.uuid = uuid;
        this.quizs = quizs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uuid='" + uuid + '\'' +
                ", quizs=" + quizs +
                '}';
    }

    public List<UserQuiz> getQuizs() {
        return quizs;
    }

    public void setQuizs(List<UserQuiz> quizs) {
        this.quizs = quizs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}