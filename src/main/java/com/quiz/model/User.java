package com.quiz.model;

import java.util.List;

public class User {
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String uuid;
    private List<String> userQuizs; //should be List<UserQuiz>
}