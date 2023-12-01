package com.quiz;

import com.quiz.config.DBConnectorConfig;
import com.quiz.repository.CreateTable;
import com.quiz.repository.UserRepository;

public class MainServiceApplication {
    public static void main(String[] args) {
        DBConnectorConfig.connect();

        // you can use this to try how to fetch data from database
        /*UserRepository userRepository = new UserRepository();
        userRepository.getAllPersons();*/
    }
}